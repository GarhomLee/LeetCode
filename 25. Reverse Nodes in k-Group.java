https://leetcode.com/problems/reverse-nodes-in-k-group/

// 24. Swap Nodes in Pairs的follow-up。

// 解法一：iteration
// 1）维护足够数量的pointer：
//     joint指向翻转的pair的前一个node；
//     tail指向翻转前的group nodes中的第一个node，翻转后变为group nodes中的最后一个node；
//     curr指向翻转前的group nodes中的第一个node，扫描后指向了group nodes中的最后一个node；
//     nextPair翻转的group nodes的接下来一个node，翻转后curr更新为nextPair
// 2）在每次循环中，先做一次扫描，看是否满足group size == k。如果不满足，可以不用做任何翻转，直接返回
// 3）使用翻转模板
// 4）利用joint连接pre，tail连接curr，并更新joint和tail

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) return head;
        ListNode dummy = new ListNode(-1), curr = head, joint = dummy;
        dummy.next = head;
        while (curr != null) {
            int count = 1;
            while (curr.next != null && count < k) {
                curr = curr.next;
                count++;
            }
            if (count < k) return dummy.next;
            ListNode nextPair = curr.next, pre = nextPair, tail = joint.next;
           
            curr = joint.next;
            while (curr != nextPair) {
                ListNode next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }
            joint.next = pre;
            joint = tail;
            curr = nextPair;
            
        }
        return dummy.next;
    }
}

// 解法二：recursion

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) {
            curr = curr.next;
            count++;
        }
        
        if (count == k) {
            curr = reverseKGroup(curr, k);
            while (count-- > 0) {
                ListNode next = head.next;
                head.next = curr;
                curr = head;
                head = next;
            }
            head = curr;
        }
        
        return head;
    }
}