https://leetcode.com/problems/h-index-ii/

// 274. H-Index的follow-up，给定的数组已经排好了序。属于binary search的变种。
// 关键点：比较的是length - mid（表示paper数）和citations[mid]（表示引用数）。
//         length - mid == citations[mid]的情况可以并入length - mid < citations[mid]，因为要找最大的length - mid，要增加length - mid需要移动high。
//         循环结束时，返回的是length - low。
//         可用test case帮助记忆：[0,0,0,0,0,0]和[6,6,6,6,6]

class Solution {
    public int hIndex(int[] citations) {
        int length = citations.length, low = 0, high = length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (length - mid > citations[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return length - low;
    }
}