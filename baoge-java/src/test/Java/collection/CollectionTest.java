package collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2019/7/31 11:37
 */
public class CollectionTest {

    /**
     * 集合测试（RandomAccess是一个标记接口，官方解释是只要List实现这个接口，就能支持快速随机访问。）
     * 实现RandomAccess接口的List可以通过for循环来遍历数据比使用iterator遍历数据更高效，未实现RandomAccess接口的List可以通过iterator遍历数据比使用for循环来遍历数据更高效。
     */
    @Test
    public void collectionTest() {

        long arrayListIndexedTime = arrayListIndexed();
        long arrayListIteratorTime = arrayListIterator();
        long linkedListIndexedTime = linkedListIndexed();
        long linkedListIteratorTime = linkedListIterator();
        System.out.println("测试ArrayList通过for遍历所消耗时间：" + arrayListIndexedTime);
        System.out.println("测试ArrayList通过iterator遍历所消耗时间：" + arrayListIteratorTime);
        System.out.println("测试LinkedList通过for遍历所消耗时间：" + linkedListIndexedTime);
        System.out.println("测试LinkedList通过iterator遍历所消耗时间：" + linkedListIteratorTime);
    }

    // 测试ArrayList通过for遍历所消耗时间
    public static long arrayListIndexed() {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            arrayList.add(i);
        }
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i);
        }
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        // 遍历消耗时间
        long resultTime = endTime - startTime;
        return resultTime;
    }

    // 测试ArrayList通过iterator遍历所消耗时间
    public static long arrayListIterator() {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            arrayList.add(i);
        }
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        // 遍历消耗时间
        long resultTime = endTime - startTime;
        return resultTime;
    }

    // 测试LinkedList通过for遍历所消耗时间
    public static long linkedListIndexed() {
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            linkedList.add(i);
        }
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < linkedList.size(); i++) {
            linkedList.get(i);
        }
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        //遍历消耗时间
        long resultTime = endTime - startTime;
        return resultTime;
    }

    // 测试LinkedList通过iterator遍历所消耗时间
    public static long linkedListIterator() {
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            linkedList.add(i);
        }
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        // 遍历消耗时间
        long resultTime = endTime - startTime;
        return resultTime;
    }

    @Test
    public void testListClear() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);

        List<Integer> listSub = list.subList(0, 2);

        list.subList(0, 2).clear();

        System.out.println(list.size());

        String text = "如有疑问，请致电0519-83010120-1303：1、“公司登记注册文件”一栏：请提供香港公司的商业登记证，经营地址、营业期限请按最新的商业登记证填写；2.“海外银行开户证明&quot;一栏：请重新提供公司海外银行账户开户证明或银行对账单；";

        String r = text.replaceAll("&quot;","\\”");
        System.out.println(r);
    }

}
