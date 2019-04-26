https://leetcode.com/problems/reverse-linked-list/

// 经典的linked list题，有recursion和iteration两种解法。

// 解法一：iteration，可作为模板。
// 1）维护curr和pre指针，分别指向当前扫描到的node和其前一个node
// 2）在循环中，维护临时指针next，表示curr更新时要移动到的位置
// 3）利用curr和pre将node方向翻转，同时更新pre为curr，更新curr为next

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode curr = head, pre = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}

// 解法二:recursion,稍有难度,需要画图理解

// 1)corner cases: head为空或head.next为空,直接返回head
// 2)维护next指针指向head.next,然后用recursion翻转从next开始到结尾的链表
// 3)假设翻转完成,那么返回的next应该指向翻转后的链表头,而当前head指向链表尾,所以head.next.next = head表示把head放到链表尾,同时更新head.next为null
// 4)注意【返回值为next】

class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        next = reverseList(next);
        head.next.next = head;
        head.next = null;
        return next;
    }
}