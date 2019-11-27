https://leetcode.com/problems/last-stone-weight/

思路：Heap，类似huffman coding tree
时间复杂度：O(n log n)
空间复杂度：O(n)

class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        
        for (int stone : stones) {
            pq.offer(stone);
        }
        
        while (pq.size() > 1) {
            int heavy = pq.poll(), light = pq.poll();
            if (heavy - light > 0) {
                pq.offer(heavy - light);
            }
        }
        
        return pq.isEmpty() ? 0 : pq.poll();
    }
}