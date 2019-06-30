https://leetcode.com/problems/design-linked-list/

// 思路：Double Linked List的实现，用前后各一个的sentinel node。
// 犯错点：1.对于deleteAtIndex(int index)，index < 0时也是不合法操作
//        2.对于addAtIndex(int index, int val)，index < 0时等同于在头部插入元素，直接调用addAtHead()。很迷。

class MyLinkedList {
    Node head, tail;
    int size;  // convenient for get(int index)
    
    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = new Node(-1);
        tail = new Node(-1);
        /* initialize the link between head and tail */
        head.next = tail;
        tail.pre = head;
        size = 0;  // initialize the size
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        /* corner case */
        if (index >= size) return -1;

        Node curr = head;
        while (index-- >= 0) curr = curr.next;  // find the desired node
        return curr.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node curr = new Node(val);
        /* build the link between curr and curr.next */
        curr.next = head.next;
        curr.next.pre = curr;
        /* build the link between head and curr */
        head.next = curr;
        curr.pre = head;
        size++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node curr = new Node(val);
        /* build the link between tail.pre, curr and tail in the opposite order of addAtHead */ 
        /* build the link between tail.pre and curr */
        tail.pre.next = curr;
        curr.pre = tail.pre;
        /* build the link between curr and tail */
        curr.next = tail;
        tail.pre = curr;
        size++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        /* corner case: invalid operation */
        if (index > size) return;
        // {Mistake 2}
        if (index < 0) addAtHead(val);  // {Correction 2: while it is confusing, if index < 0 val should be added to 0th position}
        
        Node curr = new Node(val), pre = head;
        while (index-- > 0) pre = pre.next;  // find the position previous to where val should be added
        curr.next = pre.next;
        curr.next.pre = curr;
        pre.next = curr;
        curr.pre = pre;
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        /* corner case: invalid operation */
        //if (index >= size || index < 0) return;  // {Mistake 1: the case of index < 0 should be considered}
        if (index >= size) return;  // {Correction 1}
        
        Node pre = head;
        while (index-- > 0) pre = pre.next;  // find the position previous to where val should be deleted
        pre.next = pre.next.next;
        pre.next.pre = pre;
        size--;
    }
    
    class Node {
        int val;
        Node pre;
        Node next;
        public Node(int v) {
            val = v;
        }
    }
}