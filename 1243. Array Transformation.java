https://leetcode.com/problems/array-transformation/

思路：Simulation
时间复杂度：O(d * n)
空间复杂度：O(n)

class Solution {
    public List<Integer> transformArray(int[] arr) {
        List<Integer> res = new ArrayList<>();
        
        // loop when there is at least one element changed
        boolean isChanged = true;
        while (isChanged) {
            int pre = arr[0];
            isChanged = false;  // initialize the flag for every round
            for (int i = 1; i + 1 < arr.length; i++) {
                if (arr[i] > pre && arr[i] > arr[i + 1]) {
                    // this element is greater than its neighbors
                    isChanged = true;
                    pre = arr[i];
                    arr[i]--;
                } else if (arr[i] < pre && arr[i] < arr[i + 1]) {
                    // this element is less than its neighbors
                    isChanged = true;
                    pre = arr[i];
                    arr[i]++;
                } else {
                    pre = arr[i];
                }
            }
        }
        
        for (int num : arr) {
            res.add(num);
        }
        return res;
    }
}