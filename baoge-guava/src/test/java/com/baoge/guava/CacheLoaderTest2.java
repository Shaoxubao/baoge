package com.baoge.guava;

import com.baoge.entity.Employee;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2020/3/2
 */
public class CacheLoaderTest2 {

    private boolean isTrue = false; // 未从DB取数据

    @Test
    public void testBasic() throws Exception {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder().maximumSize(10)
                .expireAfterAccess(30, TimeUnit.MILLISECONDS)
                .build(new CacheLoader<String, Employee>() {
                    @Override
                    public Employee load(String key) throws Exception {
                        return findByName(key);
                    }
                });

        Employee employee = cache.get("baoge");
        assertThat(employee, notNullValue());
        assertLoadFromDBThenReset();

        employee = cache.get("baoge");
        assertThat(employee, notNullValue());
        assertLoadFromCacheThenReset();

        TimeUnit.MILLISECONDS.sleep(31); // key失效
        employee = cache.get("baoge");
        assertThat(employee, notNullValue());
        assertLoadFromDBThenReset();
    }

    /**
     * key 通过LRU,逐出策略
     */
    @Test
    public void testEvictionBySize() {
        CacheLoader<String, Employee> cacheLoader = createCacheLoader();
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .maximumSize(3).build(cacheLoader);

        cache.getUnchecked("baoge1");
        assertLoadFromDBThenReset();
        cache.getUnchecked("baoge2");
        assertLoadFromDBThenReset();
        cache.getUnchecked("baoge3");
        assertLoadFromDBThenReset();

        assertThat(cache.size(), equalTo(3L));

        cache.getUnchecked("baoge4");
        assertThat(cache.getIfPresent("baoge1"), nullValue()); // baoge4插入后，baoge1被逐出
        assertThat(cache.getIfPresent("baoge4"), notNullValue());
    }

    /**
     * key 逐出策略
     */
    @Test
    public void testEvictionByWeight() {
        Weigher<String, Employee> weigher = (key, employee) ->
                employee.getName().length() + employee.getDepartMentId().length() + employee.getEmployeeId().length();

        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .maximumWeight(45)
                .concurrencyLevel(1) // 不会分成多个segment
                .weigher(weigher)
                .build(createCacheLoader());

        cache.getUnchecked("Gavin");
        assertLoadFromDBThenReset();
        cache.getUnchecked("Kavin");
        assertLoadFromDBThenReset();
        cache.getUnchecked("Aavin");
        assertLoadFromDBThenReset();

        assertThat(cache.size(), equalTo(3L));
        assertThat(cache.getIfPresent("Gavin"), notNullValue());

        cache.getUnchecked("Jason");
        // cache.getIfPresent("Gavin")访问过一次，Kavin变成最老的，Kavin继而又被上面一句插入Jason后逐出
        assertThat(cache.getIfPresent("Kavin"), nullValue());
        assertThat(cache.size(), equalTo(3L));

    }

    private CacheLoader<String, Employee> createCacheLoader() {
        return new CacheLoader<String, Employee>() {
            @Override
            public Employee load(String key) throws Exception {
                return findByName(key);
            }
        };
    }

    private void assertLoadFromDBThenReset() {
        assertThat(true, equalTo(isTrue));
        this.isTrue = false;
    }

    private void assertLoadFromCacheThenReset() {
        assertThat(false, equalTo(isTrue));
    }

    private Employee findByName(String name) {
//        System.out.println("The employee " + name + "is load from DB.");
        isTrue = true;
        return new Employee(name, name, name);
    }

}
