package jdk8.arraylist.remove;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2020/4/28 14:12
 *
 * List如何一边遍历，一边删除?
 */
public class ListRemoveQuestionDemo {

    public static void main(String[] args) {
        List<String> blogList = new ArrayList<>();
        blogList.add("博客园");
        blogList.add("CSDN");
        blogList.add("简书");

        // 这种遍历删除会报错ConcurrentModificationException，
//        for (String item : blogList) {
//            if (item.equals("博客园")) {
//                blogList.remove("博客园");
//            }
//        }

        System.out.println(blogList);

        // 正确遍历删除方式1(每次删除一个元素，都会将modCount的值重新赋值给expectedModCount)
//        Iterator<String> iterator = blogList.iterator();
//        while (iterator.hasNext()) {
//            String blog = iterator.next();
//            if (blog.equals("博客园")) {
//                iterator.remove();
//            }
//        }
//        System.out.println(blogList);

        // 正确遍历删除方式2
//        for (int i = 0; i < blogList.size(); i++) {
//            String blog = blogList.get(i);
//            if (blog.equals("博客园")) {
//                blogList.remove(i);
//
//                i = i - 1; // 要记得修正下标的值
//            }
//        }
//        System.out.println(blogList);

        // 正确遍历删除方式3(倒叙遍历不需要修正下标的值)
        for (int i = blogList.size() - 1; i >= 0; i--) {
            String blog = blogList.get(i);
            if (blog.equals("CSDN")) {
                blogList.remove(i);
            }
        }

        System.out.println(blogList);

    }

}
