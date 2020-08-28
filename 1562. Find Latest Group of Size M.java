https://leetcode.com/problems/find-latest-group-of-size-m/

solution1: merge interval with updated length. Refer to: https://leetcode.com/problems/find-latest-group-of-size-m/discuss/806786/JavaC%2B%2BPython-Count-the-Length-of-Groups-O(N)
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length, res = -1;
        int[] len = new int[n + 2];
        int[] count = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int curr = arr[i];
            
            int left = curr - len[curr - 1], right = curr + len[curr + 1];
            count[len[left]]--;
            count[len[right]]--;
            
            int newLen = len[curr - 1] + len[curr + 1] + 1;
            len[left] = newLen;
            len[right] = newLen;
            count[newLen]++;
            
            if (count[m] > 0) {
                res = i + 1;
            }
        }
        
        return res;
    }
}


solution2: TreeMap/TreeSet
time complexity: O(n log n)
space complexity: O(n)