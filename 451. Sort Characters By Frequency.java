https://leetcode.com/problems/sort-characters-by-frequency/

// Map + PriorityQueue
// 1）构建class Node，记录和character对应的frequency信息
// 2）遍历数组，在Map里key为character，value为对应的frequency
// 3）将Map中的信息以Node的形式存入PriorityQueue，注意要改写Comparator
// 4）根据frequency从大到小依次从PriorityQueue取Node，然后构建String

class Solution {
    public String frequencySort(String s) {
        char[] sArray = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(1, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return node2.fre - node1.fre;
            }
        });
        /* map each character to its frequency */
        for (char c: sArray) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        /* put characters with their frequency into priority queue for sorting */
        for (Character c: map.keySet()) {
            pq.offer(new Node(c, map.get(c)));
        }
        /* heapsort */
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            for (int i = 0; i < node.fre; i++) {
                sb.append(node.c);
            }
        }
        return sb.toString();
    }
    class Node {
        char c;
        int fre;
        Node(char character, int frequency) {
            c = character;
            fre = frequency;
        }
    }
}