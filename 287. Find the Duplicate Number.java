https://leetcode.com/problems/find-the-duplicate-number/

// 解法一：Floyd's Tortoise and Hare (Cycle Detection)，即快慢指针，类似142. Linked List Cycle II。
// 由于需要满足题目的三个条件：不能改变原array，额外空间O(1)，时间复杂度小于 O(n2)，因此不能直接sort。正确解法是用一快一慢两个指针。
// 1）fast和slow都初始化为nums[0]。数学上可以证明在长度为n+1的数组里存1...n一定会有重复，从0出发一定不会再回到nums[0]
// 2）用do while循环（重点注意，否则下一步会变成死循环），fast走两步，即fast = nums[nums[fast]]；slow走一步，即nums[slow]，直至fast == slow
// 3）再用一个指针search = nums[0]，每次走一步，直至和slow相等
// 4）时间复杂度：O(n)；空间复杂度：O(1)

class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        int search = nums[0];
        while (slow != search) {
            slow = nums[slow];
            search = nums[search];
        }
        return search;
    }
}

// 解法二：数组找重复/找缺失的通用模板，特征：长度为n，index为[0 ... n - 1]，合法元素为[1 ... n]，可以将元素放到相对应的位置，
//     而问题一般问的是不在对应位置的元素
//     eg. [4,2,3,4,1]
// 1）如果nums[i]和nums[nums[i] - 1]不相等（如例子中，nums[4] = 1, nums[nums[4] - 1] = nums[1 - 1] = nums[0] = 4），调换位置，
//     直至换到合理的位置
// 2）如果nums[i]和nums[nums[i] - 1]不相等，实际上有两种情况：如nums[1] = 2 = nums[nums[1] - 1]；或者nums[0] = 4 = nums[nums[0] - 1]，
//     而只有第二种情况为所求，所以需要进行判断。

class Solution {
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
            if (nums[i] != i + 1) return nums[i];
        }
        return -1;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}