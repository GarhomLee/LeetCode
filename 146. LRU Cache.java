https://leetcode.com/problems/lru-cache/submissions/

// 思路：利用Map + Double Linked List实现。
//      自建class Node，利用Node实现自建的class DoubleLinkedList。维护以下变量：
//      1）head，tail，是前后两个sentinel node
//      2）size，当前DoubleLinkedList的大小
//      3）capacity，DoubleLinkedList的最大容量
//      DoubleLinkedList可以实现以下功能：
//      1）moveFirst(Node node),将指定node【移动到头部】，size不需要改变。内部实现是先调用delete()再调用addFirst()。
//      2）addFirst(Node node)，将指定node加入头部，更新size++
//      3）delete(Node node)，删除指定node，更新size--
//      4）getLast()，得到最末尾的node
//      同时，Map的key为input中的key，value为对应的Node，里面包含key和value。
//      利用DoubleLinkedList，可以实现LRUCache的功能：
//      1）get(int key)，从Map中查找key对应的Node，同时将这个Node调用moveFirst()移动到DoubleLinkedList头部
//      2）put(int key, int value)，先判断key是否存在。如果key已经存在，只需要update对应的Node里的val，然后调用moveFirst()
//         移动到DoubleLinkedList头部；如果key原来不存在，那么需要在Map加入key和对应的Node，然后addFirst()插入到
//         DoubleLinkedList头部，这时需要判断是否size > capacity，如果超过最大容量，那么需要删除DoubleLinkedList
//         的最后一个元素，同时删除Map中对应的key。

class LRUCache {
    Map<Integer, Node> map;
    DoubleLinkedList dll;
    
    public LRUCache(int capacity) {
        dll = new DoubleLinkedList(capacity);
        map = new HashMap();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node curr = map.get(key);
        dll.moveFirst(curr);
        return curr.val;
    }
    
    public void put(int key, int value) {
        Node curr = map.containsKey(key) ? map.get(key) : new Node(key, value);
        if (map.containsKey(key)) {
            curr.val = value;
            dll.moveFirst(curr);
        } else {
            map.put(key, curr);
            dll.addFirst(curr);
            if (dll.size > dll.capacity) {
                Node last = dll.getLast();
                dll.delete(last);
                map.remove(last.key);
            }
        }
    }
    
    class DoubleLinkedList {
        Node head;
        Node tail;
        int size;
        int capacity;
        public DoubleLinkedList(int cap) {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.pre = head;
            size = 0;
            capacity = cap;
        }
        public void moveFirst(Node node) {
            delete(node);
            addFirst(node);
        }
        public void addFirst(Node node) {
            node.next = head.next;
            node.next.pre = node;
            head.next = node;
            node.pre = head;
            size++;
        }
        public void delete(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size--;
        }
        public Node getLast() {
            return tail.pre;
        }
    }
    
    class Node {
        int key;
        int val;
        Node next;
        Node pre;
        public Node(int k, int v) {
            key = k;
            val = v;
        }
    }
}