https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/

// 解法一：Recursion
//        维护全局变量pre，指向扁平化后的当前linked list的最后一个元素。
//        终止条件：head == null
//        递归过程：遍历当前linked list所有元素。如果遇到有child的情况，先记录next，然后将curr.next递归调用flatten()
//             更新为扁平化后的curr.child，同时将curr.child.prev也更新为curr。然后curr.child设为null。
//             同时，利用pre将扁平化后的curr.child和next连接起来，同时next.prev也更新为pre（如果next不为null）。curr
//             也指向pre。
//             无论是否遇到child，pre都更新为curr，然后curr更新为curr.next.
//             最后递归函数返回head，即扁平化后的当前linked list的头部元素。
// 时间复杂度：O(n)
// 空间复杂度：O(h), h = the depth of children
// 犯错点：1.题目给的是【double-linked list】，因此处理完next指针后，还需要处理prev指针

class Solution {
    Node pre = null;  // global variable, indicating the last node of currently flattened list

    public Node flatten(Node head) {
        if (head == null) return head;
        
        Node curr = head;
        while (curr != null) {
            if (curr.child != null) {
                Node next = curr.next;
                curr.next = flatten(curr.child);  // recursively flatten curr.child, and then append it to curr.next
                // {Mistake 1: since it is a double-linked list, the prev pointer should be updated too}
                curr.child.prev = curr;  // {Correction 1}
                curr.child = null;
                pre.next = next;
                // {Mistake 1: since it is a double-linked list, the prev pointer should be updated too}
                if (next != null) next.prev = pre;  // {Correction 1: next might be null}
                curr = pre;  // set curr as pre for later curr = curr.next
            }
            pre = curr;  // update pre
            curr = curr.next;  // update curr
        }
        return head;
    }
}


解法二：Iteration