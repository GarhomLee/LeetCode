https://leetcode.com/problems/h-index/

// 解法一：内置Arrays.sort()，时间O(NlogN)，空间O(1)

class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        Arrays.sort(citations);
        int h = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] >= citations.length - i) h = citations.length - i;
            else break;
        }
        return h;
    }
}

// 解法二：bucket sort，时间O(N)，空间O(N)
// 注意：当h >= i时，【返回的是i】，因为意思是引用数多于i的文章数至少为i篇

class Solution {
    public int hIndex(int[] citations) {
        if (citations == null  || citations.length == 0) return 0;
        int length = citations.length;
        int[] buckets = new int[length + 1];
        for (int n: citations) {
            if (n >= length) buckets[length]++;
            else buckets[n]++;
        }
        int h = 0;
        for (int i = length; i >= 0; i--) {
            h += buckets[i];
            if (h >= i) return i;
        }
        return 0;
    }
}