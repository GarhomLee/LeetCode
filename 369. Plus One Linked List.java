https://leetcode.com/problems/plus-one-linked-list/

// 解法一：Recursion (DFS)
//         递归函数定义：int dfs(ListNode node)表示求得以node为头部的linked list进行+1操作后得到的carry。
//         终止条件：node==null，返回1，这样能被linked list的最后一个node得到并进行+1操作。
//         递归过程：递归调用dfs()得到carry，用来更新当前的node.val，然后返回更新后的node.val可能得到的carry。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.边界条件错误：根据题意，head==null时直接返回它自己，而不是返回带有1的node。

class Solution {
    private int dfs(ListNode node) {
        if (node == null) {
            return 1;
        }
        
        int carry = dfs(node.next);
        carry = node.val + carry;
        node.val = carry % 10;
        return carry / 10;
    }
    
    
    public ListNode plusOne(ListNode head) {
        if (head == null) {
            return head;
        }
        
        int carry = dfs(head);
        if (carry != 0) {
            ListNode node = new ListNode(carry);
            node.next = head;
            head = node;
        }
        
        return head;
    }
}


// 解法二：Iteraion with Two Pointers + Math
//         巧妙地观察到规律，将最后一位+1时，会改变的数只有从后往前的连续9和从后往前的第一个非9。因此，用lastNonNine
//         记录从后往前的第一个非9的ListNode，将它的值+1。
//         如果lastNonNine后面还有ListNode，那么一定都是9，进行+1后都变成了0。
//         特殊情况下，dummy是第一个非9，即从dummy.next=head开始都是9，那么dummy的值变为1，最后返回的是dummy。
//         否则，返回dummy.next。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public ListNode plusOne(ListNode head) {
        if (head == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy, lastNonNine = dummy;
        
        while (curr != null) {
            if (curr.val != 9) {
                lastNonNine = curr;
            }
            curr = curr.next;
        }
        
        lastNonNine.val += 1;
        while (lastNonNine.next != null) {
            lastNonNine = lastNonNine.next;
            lastNonNine.val = 0;
        }
        
        return dummy.val == 0 ? dummy.next : dummy;
    }
}