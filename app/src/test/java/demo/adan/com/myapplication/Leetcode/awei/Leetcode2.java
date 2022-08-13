package demo.adan.com.myapplication.Leetcode.awei;

import org.junit.Test;

public class Leetcode2 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    @Test
    public void main(){
//        ListNode n1 = new ListNode(2);
//        ListNode n2 = new ListNode(4);
//        ListNode n3 = new ListNode(3);
//        n1.next = n2;
//        n2.next = n3;
//        n3.previous = n2;
//        n2.previous = n1;

//        ListNode n25 = new ListNode(9);
//        n25.next = n1.next.next;
//        n1.next.next.previous = n25;
//        n1.next.next = n25;
//        n25.previous = n1.next;

//        n1.next = n1.next.next;
//        n1.next.previous = n1;


        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(1)));
        ListNode l2 = new ListNode(1);
        ListNode result = addTwoNumbers(l1,l2);

        while (result != null ){
            System.out.println(result.val);
            result = result.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r = new ListNode(0,null);
        ListNode res = r;
        int y = 0;
        boolean over = false;
        while (!over) {
            if (l1 != null && l2 != null) {
                int t = l1.val + l2.val + y;
                if (t >= 10) {
                    y = 1;
                    r.val = t % 10;
                    r.next = new ListNode(y,null);
                }else {
                    y = 0;
                    r.val = t;
                    r.next = new ListNode(y,null);
                }
            }else  {
                if (l1 == null && l2 == null) {
                    if (y == 1) {
                        r.val = y;
                        r.next = null;
                        y = 0;
                    }
                }else  {
                    if (l1 != null) {
                        int t = l1.val + y;
                        if (t >= 10) {
                            y = 1;
                            r.val = t % 10;
                            r.next = new ListNode(y,null);
                        }else {
                            r.val = l1.val + y;
                            r.next = l1.next;
                            y = 0;
                        }
                    }
                    if (l2 != null) {
                        int t = l2.val + y;
                        if (t >= 10) {
                            y = 1;
                            r.val = t % 10;
                            r.next = new ListNode(y,null);
                        }else {
                            r.val = l2.val + y;
                            r.next = l2.next;
                            y = 0;
                        }
                    }
                }
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            if (l1 == null && l2 == null && y == 0) {
                over = true;
                r.next = null;
            }else {
                r = r.next;
            }
        }

        return res;
    }
}
