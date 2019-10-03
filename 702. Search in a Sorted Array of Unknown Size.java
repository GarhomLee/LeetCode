https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/

// 思路：Binary Search
//         step1:要使用Binary Search，首先要知道上下边界。因此，可以利用类似sliding window的方式，将窗口长度倍增，
//             从而找到target落在的窗口。
//         step2:正常Binary Search模版。
// 时间复杂度：O(log t), t=【index】 of target
// 空间复杂度：O(1)

class Solution {
    public int search(ArrayReader reader, int target) {
        int low = 0, high = 1;
        while (reader.get(high) < target) {
            low = high;
            high *= 2;
        }
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (reader.get(mid) >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        int res = reader.get(low);
        return res == target ? low : -1;
    }
}