https://leetcode.com/problems/top-k-frequent-elements/

// 解法一：Map + PriorityQueue (minHeap)
// 1）新建class Node存储元素以及其出现的频数
// 2）遍历数组，用Map存储元素以及其出现的频数
// 3）遍历Map，转换成Node存入PriorityQueue中，维护其size为k
// 4）将PriorityQueue中Node的元素加入list

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(1, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return node1.fre - node2.fre;
            }
        });
        for (int n: map.keySet()) {
            pq.offer(new Node(n, map.get(n)));
            if (pq.size() > k) pq.poll();
        }
        List<Integer> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            list.add(pq.poll().elem);
        }
        return list;
    }
    class Node {
        int elem, fre;
        Node(int e, int f) {
            elem = e;
            fre = f;
        }
    }
}

// 解法二：bucket sort