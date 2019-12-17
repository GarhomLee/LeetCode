https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/

// 思路：Two Pointers (curr & curr.next)
//         边界条件：head == null
//         一般情况：curr从head开始，当curr.next != head时进行循环。可能的情况：
//             1）curr.val <= insertVal <= curr.next.val，说明找到了合适的插入位置，插入并返回。
//             2）不满足1），但满足curr.val > curr.next.val，说明到了circular linked list的break point，
//                 则判断如果满足insertVal >= curr.val或者insertVal >= curr.next.val，说明应该插入在
//                 break point，插入并返回。
//             3）其他情况，继续搜索，更新curr = curr.next。
//             如果跳出了循环，那么说明只能插入在head前。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        /*edge case*/
        if (head == null) {
            node.next = node;
            return node;
        }

        Node curr = head;
        while (curr.next != head) {
            if (insertVal >= curr.val && insertVal <= curr.next.val) {
                node.next = curr.next;
                curr.next = node;
                return head;
            } else if (curr.val > curr.next.val) {
                if (insertVal >= curr.val || insertVal <= curr.next.val) {
                    node.next = curr.next;
                    curr.next = node;  
                    return head;
                }
            }
            
            curr = curr.next;
        }
        
        node.next = curr.next;
        curr.next = node;  
        return head;
    }
}