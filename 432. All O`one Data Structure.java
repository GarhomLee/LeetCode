https://leetcode.com/problems/all-oone-data-structure/

// 思路：HashMap（对key的操作是O(1)时间） + DoubleLinkedList（inc()，dec()，getMax()和getMin()的操作是O（对inc，dec，getMax和getMin的操作是O(1)时间）
//     + Node（将key和对应的count次数对应）
//         自建class Node，维护以下变量：
//         1）int value，表示调用数次inc()和dec()后的值
//         2）Set<String> set，存储相同数值的key。用HashSet而不是List是为了remove()用O(1)时间，而从Set中查看元素可以用set.iterator().next()
//         3）Node pre和Node next，为了实现DoubleLinkedList
//         自建class DoubleLinkedList，维护头尾两个sentinel nodes，以及int size，注意DoubleLinkedList的所有Node是【以value排好序的】。
//         DoubleLinkedList实现以下功能：
//         1）Node increase(Node node, String key)，根据key和所在的当前的node，将key放到value + 1的下一个Node，然后将key从原来的Node node删除。
//             分两种情况：a）value + 1的Node不存在，新建并插入；b）value + 1的Node已经存在，直接将key放入。
//             如果原来的Node中已经没有元素（即内部Set为空），则将这个Node删除。
//         2）Node decrease(Node node, String key)，根据key和所在的当前的node，将key放到value - 1的上一个Node，然后将key从原来的Node node删除。
//             分两种情况：a）value -1 1的Node不存在，新建并插入；b）value - 1的Node已经存在，直接将key放入。
//             如果原来的Node中已经没有元素（即内部Set为空），则将这个Node删除。
//             注意：即使value为0，也不作处理，而是交给class AllOne处理。
//         3）void addToNext(Node preNode, Node newNode)，将newNode插入到preNode的下一位，然后size++
//         4）addFirst(Node node)，将node加入DoubleLinkedList成为第一个Node。可以调用addToNext()来实现。
//         5）void remove(Node node)，从DoubleLinkedList中删除node，然后size--
//         6）Node getFirst()和Node getLast()，得到第一个Node和最后一个Node
//         7）boolean isEmpty()，判断DoubleLinkedList是否为空，即除了头尾外Node个数为0
//         8）boolean isLast(Node node)和boolean isFirst(Node node)，分别判断node是否为最后一个元素或第一个元素。
//         因此，利用HashMap（key为String key，value为所在的Node）和DoubleLinkedList，就可以实现class AllOne的功能：
//         1）void inc(String key) ，将新的key加入，或将已有key对应的value++。
//             分两种情况：a）key是新的，那么将value为0的Node带着key加入DoubleLinkedList，同时key和对应Node加入Map；b）key已经存在，直接做下一步。
//             不管哪一种情况，都要调用DoubleLinkedList的increase()，然后更新Map。
//         2）void dec(String key)，将key对应的value--。如果key不存在，直接返回。
//             调用DoubleLinkedList的decrease()，【然后更新Map】。
//             此时需要判断，如果操作后的value为0，那么需要从DoubleLinkedList将这个Node删除，同时将key从Map中删除。
//         3）String getMaxKey()，得到value最大的任意一个key。判断DoubleLinkedList是否为空，为空时直接返回空字符串""；否则调用getLast()，从Set中
//             取一个key。
//         4）String getMinKey()，得到value最小的任意一个key。判断DoubleLinkedList是否为空，为空时直接返回空字符串""；否则调用getFirst()，从Set中
//             取一个key。

// 犯错点：1.实现细节错误：在DoubleLinkedList中实现increase()或decrease()时，要先从原来的Node里删除掉key，才能判断Node是否还有元素，是否需要把Node
//                 删除。如果不先删掉key，直接判断是否元素个数为1，显然会出错。
//         2.实现细节错误：在实现AllOne中的dec()时，调用DoubleLinkedList的decrease()得到新的Node后，要及时更新Map中key对应的Node。否则，下一次从
//                 Map中取key对应的Node就会出错，得不到最新的Node。

class AllOne {
    Map<String, Node> map;
    DoubleLinkedList dll;
    
    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap();
        dll = new DoubleLinkedList();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (!map.containsKey(key)) {
            Node node = new Node(0, key);
            map.put(key, node);
            dll.addFirst(node);
        }
        Node newNode = dll.increase(map.get(key), key);
        map.put(key, newNode);
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!map.containsKey(key)) return;
        Node newNode = dll.decrease(map.get(key), key);
        // {Mistake 2}
        map.put(key, newNode);  // {Correction 2}
        if (newNode.value == 0) {
            map.remove(key);
            dll.remove(newNode);
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (dll.isEmpty()) return "";
        
        return dll.getLast().set.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (dll.isEmpty()) return "";
        
        return dll.getFirst().set.iterator().next();
    }
    
    class DoubleLinkedList {
        Node head;
        Node tail;
        int size;
        public DoubleLinkedList() {
            head = new Node(-100, "");
            tail = new Node(-100, "");
            head.next = tail;
            tail.pre = head;
            size = 0;
        }
        
        public Node increase(Node node, String key) {
            Node newNode = null;
            /* move key to the new node */
            if (isLast(node) || node.next.value != node.value + 1) {  // create a new node and add it to dll
                newNode = new Node(node.value + 1, key);
                addToNext(node, newNode);
            } else {  // add key directly to a existing node
                node.next.set.add(key);
                newNode = node.next;
            }
            /* remove the old node if key is the only element */
            // {Mistake 1}
            node.set.remove(key);  // {Correction 1}
            if (node.set.size() == 0) remove(node);

            return newNode;
        }
        
        public Node decrease(Node node, String key) {
            Node newNode = null;
            if (isFirst(node) || node.pre.value != node.value - 1) {  // create a new node and add it to dll
                newNode = new Node(node.value - 1, key);
                addToNext(node.pre, newNode);
            } else {  // add key directly to a existing node
                node.pre.set.add(key);
                newNode = node.pre;
            }
            /* remove the old node if key is the only element */
            // {Mistake 1}
            node.set.remove(key);
            if (node.set.size() == 0) remove(node);  // {Correction 1}
            
            return newNode;
        }
        
        /** add newNode as the next node to preNode */
        public void addToNext(Node preNode, Node newNode) {
            newNode.next = preNode.next;
            newNode.next.pre = newNode;
            preNode.next = newNode;
            newNode.pre = preNode;
            size++;
        }
        
        /** add node as the first Node */
        public void addFirst(Node node) {
            addToNext(head, node);
        }
        
        /** remove node from dll */
        public void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size--;
        }
        
        public Node getFirst() {
            return isEmpty() ? null : head.next;
        }
        
        public Node getLast() {
            return isEmpty() ? null : tail.pre;
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        public boolean isLast(Node node) {
            return node.next == tail;
        }
        
        public boolean isFirst(Node node) {
            return node.pre == head;
        }
    }
    
    class Node {
        int value;
        Set<String> set;
        Node pre;
        Node next;
        public Node(int v, String key) {
            set = new HashSet();
            value = v;
            set.add(key);
        }
    }
    
}


二刷： 2 HashMaps + DoubleLinkedList

class AllOne {
    Map<String, Integer> countMap;   // key -> count
    Map<Integer, Node> nodeMap; // count -> node
    Node head, tail;            
    
    public AllOne() {
        countMap = new HashMap<>();
        nodeMap = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    public void inc(String key) {
        int currCount = !countMap.containsKey(key) ? 0 : countMap.get(key), nextCount = currCount + 1;
        
        // update countMap
        countMap.put(key, nextCount);
        
        // update nodeMap
        Node currNode = currCount == 0 ? head : nodeMap.get(currCount);
        if (!nodeMap.containsKey(nextCount)) {
            nodeMap.put(nextCount, new Node());                
            addAfter(currNode, nodeMap.get(nextCount));
        }
        nodeMap.get(nextCount).set.add(key);
        
        // update linked list
        if (currNode != head) {
            currNode.set.remove(key);
            if (currNode.set.isEmpty()) {
                nodeMap.remove(currCount);
                remove(currNode);
            }
        }
    }
    
    public void dec(String key) {
        int currCount = countMap.get(key), nextCount = currCount - 1;
        Node currNode = nodeMap.get(currCount);
        
        if (nextCount == 0) {
            // update countMap
            countMap.remove(key);            
        } else {
            // update countMap
            countMap.put(key, nextCount);
            
            // update nodeMap
            if (!nodeMap.containsKey(nextCount)) {
                nodeMap.put(nextCount, new Node());                
                addAfter(currNode.prev, nodeMap.get(nextCount));
            }
            nodeMap.get(nextCount).set.add(key);
        }
        
        // update linked list
        currNode.set.remove(key);
        if (currNode.set.isEmpty()) {
            nodeMap.remove(currCount);
            remove(currNode);
        }
    }
    
    public String getMaxKey() {
        Node lastNode = tail.prev;
        return lastNode == head ? "" : lastNode.set.iterator().next();
    }
    
    public String getMinKey() {
        Node firstNode = head.next;
        return firstNode == tail ? "" : firstNode.set.iterator().next();
    }
    
    private void addAfter(Node node1, Node node2) {
        node2.next = node1.next;
        node2.next.prev = node2;
        node1.next = node2;
        node2.prev = node1;
    }
    
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
        
    // contains a set of keys with the same count
    class Node {
        Node prev, next;
        Set<String> set;
        
        public Node() {
            set = new HashSet<>();
        }
    }
}