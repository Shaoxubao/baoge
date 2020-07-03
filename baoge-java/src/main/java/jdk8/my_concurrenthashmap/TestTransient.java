package jdk8.my_concurrenthashmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2020/7/3 9:34
 */
public class TestTransient implements Serializable {

    transient volatile List<Integer> list;

    private transient volatile int count;

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void put(Integer value) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(value);

//        addCount();
    }

    public void addCount() {
        count++;
    }
}
