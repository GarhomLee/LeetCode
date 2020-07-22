https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/

idea: Sort intervals + Greedy. Referring to: https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/discuss/744420/C%2B%2BJavaPython-Interval-Scheduling-Maximization-(ISMP)
time complexity: O(n)
space complexity: O(26)

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[][] intervals = new int[26][2];
        for (int i = 0; i < 26; i++) {
            intervals[i][0] = n;
        }
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            intervals[c - 'a'][0] = Math.min(intervals[c - 'a'][0], i);
            intervals[c - 'a'][1] = Math.max(intervals[c - 'a'][1], i);
        }
        
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (intervals[i][0] < n) {
                int left = intervals[i][0], right = intervals[i][1];
                int minLeft = left, maxRight = right;
                for (int j = minLeft; j <= maxRight; j++) { // dynamically change the stop condition
                    minLeft = Math.min(minLeft, intervals[s.charAt(j) - 'a'][0]);
                    maxRight = Math.max(maxRight, intervals[s.charAt(j) - 'a'][1]);
                }
                
                if (minLeft == left) {
                    list.add(new int[]{minLeft, maxRight});
                }
            }
        }
        
        Collections.sort(list, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        List<String> ret = new ArrayList<>();
        int prevEnd = -1;
        if (list.isEmpty()) {
            ret.add(s);
        } else {
            for (int[] interval : list) {
                if (interval[0] > prevEnd) {
                    ret.add(s.substring(interval[0], interval[1] + 1));
                    prevEnd = interval[1];
                }
            }
        }
        
        
        return  ret;
    }
}
