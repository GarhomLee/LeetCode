https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/

// 思路：Binary Search (search in range)
//         观察到规律：选取的值越大，得到的sum越大。同时，选取的值可以不在arr内，因此可以在[0:max]进行
//         二分搜索（max为arr数组中元素最大值），使得从选取值开始得到的sum都大于等于target。
//         同时，维护diff变量和res变量，分别表示当前sum和target最小的差值和对应的选取值。如果得到差值和
//         diff相同，那么res要更新为较小的选取值。
// 时间复杂度：O(n log m), n=arr.length, m=max num in arr
// 空间复杂度：O(1)

class Solution {
    private int getSum(int[] arr, int value) {
        int sum = 0;
        for (int num : arr) {
            sum += num > value ? value : num;
        }
        
        return sum;
    }
    
    public int findBestValue(int[] arr, int target) {
        int low = 0, high = 0;
        for (int num : arr) {
            high = Math.max(high, num);
        }
        
        int res = high, diff = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int sum = getSum(arr, mid);
            if (sum >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            
            if (Math.abs(sum - target) < diff) {
                res = mid;
                diff = Math.abs(sum - target);
            } else if (Math.abs(sum - target) == diff) {
                res = Math.min(res, mid);
            }
        }
        
        return res;
    }
}