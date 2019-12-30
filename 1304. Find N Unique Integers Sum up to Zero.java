https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/

// 思路：找规律，只要每两个数是关于0对称即可。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int[] sumZero(int n) {
        int[] res = new int[n];
        int left = 0, right = n - 1;
        int rnum = n / 2, lnum = -rnum;
        while (left <= right) {
            res[left++] = lnum++;
            res[right--] = rnum--;
        }
        
        return res;
    }
}