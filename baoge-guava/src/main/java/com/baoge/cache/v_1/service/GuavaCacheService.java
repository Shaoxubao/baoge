package com.baoge.cache.v_1.service;

import com.google.common.cache.Cache;

/**
 * @Author shaoxubao
 * @Date 2020/2/26 14:38
 * <p>
 * 愿意消耗一些内存空间来提升速度
 */
public interface GuavaCacheService<K, V> extends Cache<K, V> {

}
