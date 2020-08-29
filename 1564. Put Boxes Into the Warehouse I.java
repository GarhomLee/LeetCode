https://leetcode.com/problems/put-boxes-into-the-warehouse-i/

idea: Sort + Greedy
time complexity: O(m + n log n), m=warehouse.length, n=boxes.length
space complexity: O(m + n)

class Solution {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Stack<Integer> stack = new Stack<>();
        for (int height : warehouse) {
            int curr = stack.isEmpty() ? height : Math.min(height, stack.peek());
            stack.push(curr);
        }
        
        Arrays.sort(boxes);
        int res = 0, n = boxes.length;
        for (int i = 0; i < n && !stack.isEmpty(); stack.pop()) {
            if (boxes[i] <= stack.peek()) {
                res++;
                i++;
            }
        }
        
        return res;
    }
}