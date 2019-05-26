https://leetcode.com/problems/first-bad-version/

// 思路：尽量减少isBadVersion()的使用次数，明显要用Binary Search。套用模版即可。

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low = 1, high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid)) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }
}