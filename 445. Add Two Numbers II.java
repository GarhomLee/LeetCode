https://leetcode.com/problems/add-two-numbers-ii/

// 2. Add Two Numbers的follow-up，将两个加数以正常顺序的linked list表示。

// 解法一：将linked list翻转，于是转化为2. Add Two Numbers问题。

// 假设题目限制了不能改变linked list，需要用别的解法。

// 解法二：Iteration with Stacks
//        先将l1和l2的所有节点存进Stack里，然后不断从Stack将节点pop出来，于是也能转化为2. Add Two Numbers问题，
//        相当于从低位到高位进行加法操作。

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack(), stack2 = new Stack();
        /* put ListNode into Stack */
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                stack1.push(l1);
                l1 = l1.next;
            }
            if (l2 != null) {
                stack2.push(l2);
                l2 = l2.next;
            }
        }
        
        ListNode dummy = new ListNode(-1);
        int carry = 0;
        /* process from the least significant digit to the most significant digit */
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int num1 = stack1.isEmpty() ? 0 : stack1.pop().val;
            int num2 = stack2.isEmpty() ? 0 : stack2.pop().val;
            ListNode node = new ListNode((num1 + num2 + carry) % 10);
            carry = (num1 + num2 + carry) / 10;
            node.next = dummy.next;
            dummy.next = node;
        }

        /* there is still a carry */
        if (carry != 0) {
             ListNode node = new ListNode(carry);
            node.next = dummy.next;
            dummy.next = node;
        }
        
        return dummy.next;
    }
}


解法三：Recursion