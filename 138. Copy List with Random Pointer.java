https://leetcode.com/problems/copy-list-with-random-pointer/

// 分三步。
// 1）偶联，在现有nodes之间插入copies，数值等于每个copy的前一个node
// 2）遍历nodes（原本的nodes之间现在已经有了copies），如果当前node有random，那么这个random的next就是这个node的copy（也就是node.next）的random
// 3）解偶联，可以在head前连接一个copyDummy使代码更简洁清晰

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val, null, null);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }
        
        curr = head;
        while (curr != null) {
            Node copy = curr.next;
            if (curr.random != null) copy.random = curr.random.next;
            curr = copy.next;
        }
        
        curr = head;
        Node copyDummy = new Node(-1, null, null), copy = copyDummy;
        copyDummy.next = head;
        while (curr != null) {
            copy.next = curr.next;
            curr.next = curr.next.next;
            curr = curr.next;
            copy = copy.next;
        }
        return copyDummy.next;
    }
}