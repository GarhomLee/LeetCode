https://leetcode.com/problems/top-k-frequent-words/

// 解法一：HashMap + minHeap
//         step0: 构建class Node，记录字符串word以及出现的频数frequency
//         step1: 遍历words数组，将出现的字符串s存放在HashMap中，key为字符串s，value为s对应的Node，记录着s
//             出现的频率。
//         step2: 重写PriorityQueue的Comparator，构造minHeap，且对于频率相同的Node，把字符串顺序较大的放靠近顶部。
//             遍历HashMap，将Node依次放进PriorityQueue，同时维护PriorityQueue的最大size为k。因此，遍历结束后，
//             PriorityQueue中存在的Node为出现次数最多的k个Node。
//         step3: 将PriorityQueue中的元素【倒序放入List】，即频数大的在前、频数相同字符串顺序小的在前。
// 时间复杂度：O(n log k)
// 空间复杂度：O(n)
// 犯错点：1.细节错误：排序时，根据题意用minHeap，频数小的在顶部，那么同样频数时应该字符串顺序大的在前面，这样最后逆序输出时
//             才是频数大的在前、频数相同字符串顺序小的在前。
//         2.细节错误：题目要求结果里频数大的在前、频数相同字符串顺序小的在前，而按minHeap顺序输出后恰好是翻转的结果。因此，
//             要把结果翻转回来。

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Node> map = new HashMap<>();
        for (String s: words) {
            map.putIfAbsent(s, new Node(s, 0));
            Node node = map.get(s);
            node.frequency += 1;
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>(1, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                //return node1.frequency == node2.frequency ? node1.word.compareTo(node2.word) : node1.frequency - node2.frequency;  // {Mistake 1}
                return node1.frequency == node2.frequency ? node2.word.compareTo(node1.word) : node1.frequency - node2.frequency;  // {Correction 1}
            }
        });
        
        for (Node node: map.values()) {
            pq.offer(node);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            //res.add(node.word);  // {Mistake 2}
            res.add(0, node.word);  // {Correction 2}
        }
        
        return res;
    }
    
    class Node {
        String word;
        int frequency;
        public Node(String w, int f) {
            word = w;
            frequency = f;
        }
    }
}


解法二：QuickSelect？