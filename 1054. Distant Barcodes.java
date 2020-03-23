https://leetcode.com/problems/distant-barcodes/

solution: HashMap + maxHeap
    First, use hash map to record the frequency of each barcode.
    Then, construct the maxHeap, and the higher frequency has a higher priority.
    Next, fill the res array according to priority. Fill the even index first, and 
    then odd index.
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int len = barcodes.length;
        int[] res = new int[len];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int code : barcodes) {
            map.put(code, map.getOrDefault(code, 0) + 1);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int code : map.keySet()) {
            int count = map.get(code);
            pq.offer(new int[]{code, count});
        }
        
        int index = 0;
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int code = pair[0], count = pair[1];
            while (count-- > 0) {
                res[index] = code;
                index += 2;
                if (index >= len) {
                    index = 1;
                }
            }
        }
        
        return res;
    }
}