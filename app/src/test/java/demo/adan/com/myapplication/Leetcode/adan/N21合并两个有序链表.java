package demo.adan.com.myapplication.Leetcode.adan;

import org.junit.Test;

/**
 * @author adan
 * on 2022/8/12
 * ClassName：
 */
public class N21合并两个有序链表 {

    @Test
    public void main() {
        ListNode result = mergeTwoLists3(new ListNode(1, new ListNode(2, new ListNode(4))),
                new ListNode(1, new ListNode(3, new ListNode(4))));

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null) return list2;
        else if (list2 == null) return list1;

        ListNode result = new ListNode();
        ListNode c = result;
        while (true) {
            if (list1.val <= list2.val) {
                c.next = list1;
                c = c.next;
                list1 = list1.next;
                if (list1 == null) {
                    c.next = list2;
                    break;
                }
            } else {
                c.next = list2;
                c = c.next;
                list2 = list2.next;
                if (list2 == null) {
                    c.next = list1;
                    break;
                }
            }
        }
        return result.next;
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        else if (list2 == null) return list1;

        ListNode result = new ListNode();
        ListNode current = result;

        while (true) {
            if (list1.val <= list2.val) {

                current.next = list1;

                label:
                while (list1.next != null) {
                    if (list1.next.val <= list2.val) {
                        list1 = list1.next;
                    } else {
                        break label;
                    }
                }

                if (list1.next == null) {
                    list1.next = list2;
                    break;
                }

                current = list1;
                list1 = list1.next;

            } else {

                current.next = list2;

                label:
                while (list2.next != null) {
                    if (list2.next.val <= list1.val) {
                        list2 = list2.next;
                    } else {
                        break label;
                    }
                }

                if (list2.next == null) {
                    list2.next = list1;
                    break;
                }

                current = list2;
                list2 = list2.next;
            }
        }
        return result.next;
    }

    public ListNode mergeTwoLists3(ListNode list1, ListNode list2) {

        if (list1 == null) return list2;
        else if (list2 == null) return list1;

        if (list1.val <= list2.val) {
            fun(list1, list2);
            return list1;
        } else {
            fun(list2, list1);
            return list2;
        }
    }

    private void fun(ListNode list1, ListNode list2) {
        while (true) {
            if (list1.next == null) {
                list1.next = list2;
                break;
            }
            if (list2 == null) {
                break;
            }
            ListNode node = fun2(list1, list2);
            ListNode newList2 = node.next;
            node.next = list2;

            list1 = list2;
            list2 = newList2;
        }
    }

    private ListNode fun2(ListNode list1, ListNode list2) {
        while (list1.next != null && list1.next.val <= list2.val) {
            list1 = list1.next;
        }
        return list1;
    }

}
