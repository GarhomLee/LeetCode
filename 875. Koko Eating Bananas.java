https://leetcode.com/problems/koko-eating-bananas/

// 思路：Binary Search (search in range)
//         观察发现，每个分割的小块的元素个数越多，分割的块数就越少。那么，题目转化为，在[1:max]范围内
//         （max为piles数组元素的最大值），找到一个数mid，使得[mid:max]有分割的块数count<=H。
// 时间复杂度：O(n log m), n=piles.length, m=max in piles array
// 空间复杂度：O(1)

class Solution {
    private int getHours(int[] piles, int part) {
        int count = 0;
        for (int pile : piles) {
            count += pile / part;
            if (pile % part != 0) {
                count++;
            }
        }
        
        return count;
    }
    
    public int minEatingSpeed(int[] piles, int H) {
        int low = 1, high = 1;
        for (int num : piles) {
            high = Math.max(high, num);
        }
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = getHours(piles, mid);
            
            if (count <= H) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            
        }
        
        return low;
    }
}