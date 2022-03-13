https://leetcode.com/problems/max-stack/

// 思路：DoubleLinkedList + TreeMap
// 时间复杂度：push(): O(log n); 
//         pop(): O(log n); 
//         peek(): O(1); 
//         peekMax(): O(log n); 
//         popMax(): O(log n);
// 空间复杂度：O(n)

class MaxStack {
    DoubleLinkedList dll;   // as stack
    TreeMap<Integer, List<Node>> map;
    
    /** initialize your data structure here. */
    public MaxStack() {
        dll = new DoubleLinkedList();
        map = new TreeMap<>();
    }
    
    public void push(int x) {
        Node node = new Node(x);
        dll.push(node);
        map.putIfAbsent(x, new ArrayList<Node>());
        map.get(x).add(node);
    }
    
    public int pop() {
        Node node = dll.pop();
        map.get(node.val).remove(node);
        if (map.get(node.val).isEmpty()) {
            // remove this key-value pair if there is no more node of this value
            map.remove(node.val);
        }
        
        return node.val;
    }
    
    public int top() {
        return dll.getLast().val;
    }
    
    public int peekMax() {
        return map.lastKey();
    }
    
    public int popMax() {
        int max = map.lastKey();
        List<Node> list = map.get(max);
        Node node = list.remove(list.size() - 1);
        dll.remove(node);
        if (map.get(max).isEmpty()) {
            // remove this key-value pair if there is no more node of this value
            map.remove(max);
        }
        
        return max;
    }
    
    class DoubleLinkedList {
        Node head;
        Node tail;
        int size;
        public DoubleLinkedList() {
            head = new Node(0);
            tail = new Node(0);
            head.next = tail;
            tail.pre = head;
            size = 0;
        }
        
        public void push(Node node) {
            tail.pre.next = node;
            node.pre = tail.pre;
            node.next = tail;
            tail.pre = node;
            size++;
        }
        
        public Node pop() {
            return remove(tail.pre);
        }
        
        public Node remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size--;
            
            return node;
        }
        
        public Node getLast() {
            return tail.pre;
        }
    }
    
    class Node {
        Node pre;
        Node next;
        int val;
        public Node(int v) {
            val = v;
        }
    }
}