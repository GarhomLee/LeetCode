https://leetcode.com/problems/swap-nodes-in-pairs/

// 解法一：iteration
// 维护足够数量的pointer：pre指向翻转的pair的前一个node；curr指向翻转前的pair两个nodes中的第一个node；nextPair翻转的pair的接下来一个node，翻转后curr更新为nextPair

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1), curr = head, pre = dummy;
        dummy.next = head;
        while (curr != null && curr.next != null) {
            ListNode nextPair = curr.next.next;
            pre.next = curr.next;
            pre.next.next = curr;
            curr.next = nextPair;
            pre = curr;
            curr = nextPair;
        }
        return dummy.next;
    }
}

// 解法二：recursion

class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode currentPair = head, nextPair = currentPair.next.next;
        head = currentPair.next;
        currentPair.next.next = currentPair;
        currentPair.next = swapPairs(nextPair);
        return head;
    }
}