package jdk8.arraylist;

public class MyArrayList {

    /**
     * 默认初始化容量大小
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 默认容量的空数组
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    /**
     * 空数组
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    transient Object[] elementData;

    private int size;

    /**
     * 默认无参构造函数
     */
    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public MyArrayList(int initialCapacity) {

    }


}
