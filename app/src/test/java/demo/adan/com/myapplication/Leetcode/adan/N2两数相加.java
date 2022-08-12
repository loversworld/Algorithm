package demo.adan.com.myapplication.Leetcode.adan;

import org.junit.Test;

/**
 * @author adan
 * on 2022/8/12
 * ClassName：
 */
public class N2两数相加 {

    @Test
    public void main() {
        ListNode result = addTwoNumbers2(
                new ListNode(2, new ListNode(4, new ListNode(9))),
                new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(9)))));

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println(" = 10407");

        result = addTwoNumbers2(
                new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))))),
                new ListNode(9, new ListNode(9)));

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println(" = 100098");

        result = addTwoNumbers2(
                new ListNode(5),
                new ListNode(5));

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println(" = 10");
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = l1;
        boolean t = false;

        while (l1 != null) {
            if (t) l1.val++;
            if (l2 == null) {
                if (l1.val >= 10) {
                    t = true;
                    l1.val %= 10;
                    if (l1.next == null) {
                        l1.next = new ListNode(1);
                        break;
                    } else {
                        l1 = l1.next;
                    }
                } else {
                    break;
                }
            } else {
                l1.val += l2.val;
                if (l1.val >= 10) {
                    t = true;
                    l1.val %= 10;
                } else {
                    t = false;
                }

                if (l1.next == null) {
                    if (l2.next != null) {
                        l1.next = l2.next;
                        l1 = l1.next;
                        l2 = null;
                    } else if (t) {
                        l1.next = new ListNode(1);
                        break;
                    } else {
                        break;
                    }
                } else {
                    l1 = l1.next;
                    l2 = l2.next;
                }
            }
        }

        return result;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        ListNode result = l1;
        ListNode one = new ListNode(1);

        while (l1 != null) {
            if (l2 == null) {
                if (l1.val > 9) {
                    l1.val %= 10;
                    if (l1.next == null) {
                        l1.next = one;
                    } else {
                        l1.next.val++;
                    }
                    l1 = l1.next;
                    continue;
                }
                break;
            }
            l1.val += l2.val;

            if (l1.next != null) {
                l1.next.val += (l1.val / 10);
                l1.val %= 10;
            } else if (l2.next != null) {
                l2.next.val += (l1.val / 10);
                l1.val %= 10;
                l1.next = l2.next;
                l2.next = null;
            } else if (l1.val > 9) {
                l1.val %= 10;
                l1.next = one;
            }

            l1 = l1.next;
            l2 = l2.next;
        }
        return result;
    }
}
