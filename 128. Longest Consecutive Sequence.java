https://leetcode.com/problems/longest-consecutive-sequence/

// 解法一：HashMap，one-pass
//        Map的key为数字n，n为所在的连续片段的左或右端点；value为n所在的连续片段的当前长度。
//        遍历nums数组，对于每个数字n，有五种情况：
//        1）n已经被遍历过，存在于Map中，跳过
//        2）Map中没有和n相邻的数（左或右），即n所在的片段长度为1，这是默认情况
//        3）Map中只有n+1，即存在n的右边片段，所以n为这个片段的新的左端点，更新对应的value，同时更新右端点对应的value为新的长度。
//        4）Map中只有n-1，即存在n的右边片段，所以n为这个片段的新的右端点，更新对应的value，同时更新左端点对应的value为新的长度。
//        5）Map中既有n-1又有n+1，即n为连接左右片段的中间点，因此更新新的长片段的左右端点的value为最新长度。
//        以上所有情况都要更新maxLen。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.maxLen不能初始化为1，因为nums数组可能为空，这时长度应该为0

class Solution {
    public int longestConsecutive(int[] nums) {
        //int maxLen = 1;  // {Mistake 1: nums array might be empty}
        int maxLen = 0;  // {Correction 1}
        Map<Integer, Integer> map = new HashMap();
        for (int n: nums) {
            if (map.containsKey(n)) continue;  // n has been visited
            int len = 1;  // initialize the length associate to current element n
            map.put(n, 1);
            
            if (!map.containsKey(n - 1) && map.containsKey(n + 1)) {  // there is a segment adjacent to the right of n
                len = map.get(n + 1);
                map.put(n, len + 1);  // n has become the new left boundary
                map.put(n + len, len + 1);  // update the length stored in the right boundary
                len++;  // update len to include n
            } else if (map.containsKey(n - 1) && !map.containsKey(n + 1)) {  // there is a segment adjacent to the left of n
                len = map.get(n - 1);
                map.put(n, len + 1);  // n has become the new left boundary
                map.put(n - len, len + 1);  // update the length stored in the right boundary
                len++;  // update len to include n
            } else if (map.containsKey(n - 1) && map.containsKey(n + 1)){  // n links two segments to a whole piece
                int leftLen = map.get(n - 1), rightLen = map.get(n + 1);
                len = leftLen + rightLen + 1;
                map.put(n - leftLen, len);
                map.put(n + rightLen, len);
            }
            maxLen = Math.max(maxLen, len);  // update maxLen
        }
        
        return maxLen;
    }
}


// 解法二：HashSet, two-pass，很巧妙的做法。
//        先遍历一次nums数组，将所有元素放进Set中。
//        然后再遍历nums数组，根据Set判断当前数字num是不是片段的最左端点。
//        如果是左端点，不断+1直到在Set中找到右端点，得到当前片段的长度。
//        利用所有的片段长度更新maxLen。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        
        int maxLen = 0;
        for (int num : nums) {
            if (set.contains(num - 1)) continue;
            int currLen = 0;
            while (set.contains(num)) {
                currLen++;
                num++;
            }
            maxLen = Math.max(maxLen, currLen);
        }
        return maxLen;
    }
}