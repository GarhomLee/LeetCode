https://leetcode.com/problems/product-of-array-except-self/

// 1）维护数组products，最后返回
// 2）先从左向右扫描。维护一个变量left，记录当前元素的左边所有元素的乘积，赋值给products。边扫描边更新left和products。扫描结束后，products即为当前元素的左边所有元素的乘积
// 3）然后从右向左扫描。维护一个变量right，记录当前元素的右边所有元素的乘积，然后products乘上right为新的products。边扫描边更新right和products。扫描结束后，products数组即为所求
// 4）时间复杂度：O(n)；空间复杂度：O(1)

class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] products = new int[nums.length];
        products[0] = 1;
        int left = 1 ;
        for (int i = 1; i < nums.length; i++) {
            products[i] =  left * nums[i - 1];
            left *= nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            products[i] *= right * nums[i + 1];
            right *= nums[i + 1];
        }
        return products;
    }
}