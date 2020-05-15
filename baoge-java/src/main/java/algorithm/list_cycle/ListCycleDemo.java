package algorithm.list_cycle;

/**
 * @Author shaoxubao
 * @Date 2020/5/14 16:34
 *
 * 给定一个链表，判断链表中是否有环
 */
public class ListCycleDemo {

    public static void main(String[] args) {
        ListNode list = initList();

        Boolean isHasCycle = isHasCycle(list);

        System.out.println(isHasCycle ? "链表存在环" : "链表无环");
    }

    private static Boolean isHasCycle(ListNode list) {
        if (list == null) {
            return false;
        }

        ListNode slow = list;
        ListNode fast = list.next;
        while (slow != fast) {
            if (slow.next == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }

    private static ListNode initList() {
        ListNode list = new ListNode(1);
        ListNode list2 = new ListNode(2);
        ListNode list3 = new ListNode(3);
        ListNode list4 = new ListNode(4);
        ListNode list5 = new ListNode(5);
        ListNode list6 = new ListNode(6);
        ListNode list7 = new ListNode(7);

        list.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;
        list5.next = list6;
        list6.next = list7;
        list7.next = list3;

        return list;
    }

}


class ListNode {
    private int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }

}
