https://leetcode.com/problems/lfu-cache/

// 146. LRU Cache的变体。

// 解法一：HashMap + TreeSet
//        用HashMap存Node，HashMap中key为input的key，value为对应的Node。
//        因为TreeSet内部维护了Balanced Binary Tree，所以将Comparator构造好后，TreeSet的最小元素就是使用频率最低、
//        且时间戳最小（表示最早被访问）的元素。
//        LFU Cache实现以下功能：
//        1）get(int key)，从nodeMap中得到对应的node，同时需要调用update()更新它在TreeSet中的位置。
//        2）put(int key, int value)，分两种情况：
//           a）key原本已经存在，那么只需要更新它的value，并调用update()更新它在TreeSet中的位置
//           b）key是新加入的，那么根据题意，先判断元素个数是否超过了capacity，如果超过了，需要先从TreeSet和nodeMap中
//             删除“最小元素”。然后，再将key对应的node加入TreeSet和nodeMap，其频率为1。
//        3）update(Node node)，更新node的frequency和timeStamp，以此来更新它在TreeSet中的位置。需要先从TreeSet删除，
//           再更新，再重新加入TreeSet。
// 时间复杂度：get: O(log(capacity)); put: O(log(capacity))
// 空间复杂度：O(n)
// 犯错点：1.要从nodeMap删除Node，要提供Node里记录的key，在nodeMap的keySet里匹配，而不是提供Node本身。
// 注意：会出现capacity == 0的情况

class LFUCache {
    Map<Integer, Node> nodeMap;
    TreeSet<Node> set;
    int time;
    int capacity;
    
    public LFUCache(int capa) {
        nodeMap = new HashMap();
        set = new TreeSet(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                if (node1.frequency != node2.frequency) return node1.frequency - node2.frequency;
                return node1.timeStamp - node2.timeStamp;
            }
        });
        time = 0;
        capacity = capa;
    }
    
    public int get(int key) {
        if (!nodeMap.containsKey(key)) return -1;
        Node node = nodeMap.get(key);
        update(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        /* corner case: capacity is 0 */
        if (capacity == 0) return;
        
        if (nodeMap.containsKey(key)) {  // the key is already in the cache
            Node node = nodeMap.get(key);
            node.value = value;
            update(node);
        } else {  // the key is new to the cache
            if (nodeMap.size() == capacity) {  // if the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item
                Node least = set.pollFirst();  // remove the node with least frequency which is least recently used from TreeSet
                //nodeMap.remove(least);  // {Mistake 1: least is a Node, thus this operation does nothing}
                nodeMap.remove(least.key);  // {Correction 1} remove the node with least frequency which is least recently used from nodeMap
            }
            Node node = new Node(key, value, 1, time++);
            nodeMap.put(key, node);
            set.add(node);
        }
    }
    
    private void update(Node node) {
        set.remove(node);  // remove the node from TreeSet
        node.frequency += 1;  // update its frequency
        node.timeStamp = time++;  // update its time stamp
        set.add(node);  // add the node back to TreeSet with updated frequency and time stamp
    }
    
    /** a class of Node, including key, value and the frequency of the key */
    class Node {
        int key;
        int value;
        int frequency;
        int timeStamp;
        public Node(int k, int v, int f, int t) {
            key = k;
            value = v;
            frequency = f;
            timeStamp = t;
        }
    }
}


// 解法二：2 HashMaps + 1 Double Linked List，优化时间复杂度和空间复杂度。
//        第一个HashMap是nodeMap，key为input里的key，value为对应的node。
//        第二个HashMap是frequencyMap，key为所有当前存在的node里出现的可能频率，value为DoubleLinkedList，里面存相同频率的node。
//        还有DoubleLinkedList，和146. LRU Cache中的DoubleLinkedList功能相同，越靠近头部的元素越是最近被访问。
//        同时，维护几个全局变量：minFrequency表示当前存在的所有node中的最小的频率；capacity表示给定的LFU cache的最大容量。
//        LFU cache中的元素个数可以由nodeMap.size()得到。
//        LFU Cache实现以下功能：
//        1）get(int key)，从nodeMap中得到对应的node，同时需要调用update()更新frequencyMap。
//        2）put(int key, int value)，分两种情况：
//           a）key原本已经存在，那么只需要更新它的value，并调用update()更新frequencyMap
//           b）key是新加入的，那么根据题意，先判断元素个数是否超过了capacity，如果超过了，需要先从frequencyMap中对应频率的DoubleLinkedList
//             删除“最小元素”，同时从nodeMap中把“最小元素”删除。然后，再将key对应的node加入frequencyMap和nodeMap，其频率为1。
//             因为这个元素的频率为1，所以minFrequency要重置为1.
//        3）update(Node node)，node的frequency，然后从原来所在的DoubleLinkedList删除，并加入到新的频率对应的DoubleLinkedList
//           的头部。这时要进行一个判断：如果node原来的的frequency和minFrequency相等，且更新后原来的DoubleLinkedList元素个数变为0，
//           那么需要更新minFrequency++。
// 时间复杂度：get: O(1); put: O(1)
// 空间复杂度：O(n)
// 注意：会出现capacity == 0的情况

class LFUCache {
    Map<Integer, Node> nodeMap;  // map key->Node
    Map<Integer, DoubleLinkedList> frequencyMap;  // map frequency->DoubleLinkedList
    int minFrequency;  // the minimum frequency of all keys
    int capacity;  // the maximum capacity of LFU cache, which can be 0
    
    /** constructor */
    public LFUCache(int capa) {
        /* initialize all variables */
        nodeMap = new HashMap();
        frequencyMap = new HashMap();
        minFrequency = 0;
        capacity = capa;
    }
    
    public int get(int key) {
        /* corner case */
        if (!nodeMap.containsKey(key)) return -1;
        Node node = nodeMap.get(key);  // get the node
        update(node);  // update the node
        return node.value;  // return the value related to the desired key
    }
    
    public void put(int key, int value) {
        /* corner case: capacity is 0 */
        if (capacity == 0) return;
        
        if (nodeMap.containsKey(key)) {  // the key is already in the cache
            Node node = nodeMap.get(key);  // get the node
            node.value = value;  // update the value related to the desired key
            update(node);  // update the node
        } else {  // the key is new to the cache
            if (nodeMap.size() == capacity) {  // if the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item
                Node last = frequencyMap.get(minFrequency).getLast();
                frequencyMap.get(minFrequency).delete(last);  // delete the node with least frequency which is least recently used
                nodeMap.remove(last.key);  // remove the node from nodeMap
            }
            if (!frequencyMap.containsKey(1)) {
                frequencyMap.put(1, new DoubleLinkedList());
            }
            
            Node node = new Node(key, value, 1);
            nodeMap.put(key, node);  // put this node into nodeMap
            frequencyMap.get(1).addFirst(node);  //  put this node into frequencyMap
            minFrequency = 1;  // since this is a new node, its frequency must be 1 because of the put() operation
        }
    }
    
    private void update(Node node) {
        int frequency = node.frequency;  // get the current frequency
        node.frequency += 1;  // update its frequency
        frequencyMap.get(frequency).delete(node);  // delete this node in DoubleLinkedList of previous frequency
        
        if (!frequencyMap.containsKey(node.frequency)) {
            frequencyMap.put(node.frequency, new DoubleLinkedList());
        }
        frequencyMap.get(node.frequency).addFirst(node);  // add this node in DoubleLinkedList of new frequency

        if (frequencyMap.get(frequency).size == 0 && frequency == minFrequency) {  // this node is the only node with the least frequency
            minFrequency++;  // increase the global variable minFrequency
        }
    }
    
     /** a class of DoubleLinkedList, with the most recently used key at the head. */
    class DoubleLinkedList {
        Node head;
        Node tail;
        int size;
        public DoubleLinkedList() {
            head = new Node(0, 0, 0);
            tail = new Node(0, 0, 0);
            head.next = tail;
            tail.pre = head;
            int size = 0;
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
    
    /** a class of Node, including key, value and the frequency of the key */
    class Node {
        int key;
        int value;
        int frequency;
        Node pre;
        Node next;
        public Node(int k, int v, int f) {
            key = k;
            value = v;
            frequency = f;
        }
    }
}