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


        ListNode l1 = new ListNode(2,new ListNode(4,new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode result = addTwoNumbers(l1,l2);

        while (result != null ){
            System.out.println(result.val);
            result = result.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r = null;
        int a = 876;

        return r;
    }
}
