https://leetcode.com/problems/shuffle-an-array/

// 对比：类似380. Insert Delete GetRandom O(1)的delete操作

// 思路：random
//         从[1:len)中取随即元素，然后和末尾元素交换，就完成了一次随机过程。然后缩小len--。
//         重复这个过程直到[1:len)没有元素，这样整个数组就都完成了随机选择。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    int[] copy;
    Random rand;
    
    public Solution(int[] nums) {
        copy = nums;
        rand = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return copy;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int len = copy.length;
        int[] res = copy.clone();
        while (len > 0) {
            int index = rand.nextInt(len);
            swap(res, index, len - 1);
            len--;
        }
        return res;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}