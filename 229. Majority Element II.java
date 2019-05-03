https://leetcode.com/problems/majority-element-ii/

// 169的follow-up，同样是用Boyer-Moore Voting Algorithm
// 1）维护两个变量candidate1和candidate2，都初始化为Integer.MIN_VALUE；同时维护其对应的count1和count2，初始化为0
// 2）如果count1 == 0，那么candidate1为空，所以将当前nums赋值为candidate1，同时【count1 = 1】
// 3）count2 == 0，对应赋值candidate2，同时【count2 = 1】
// 4）遇到与candidate1或candidate2，count1++或count2++
// 5）遇到第三种数，count1和count2同时自减1（被抵消了）
// 6）得到两个candidate后，还需再进行一次循环，【只有满足个数大于n / 3才符合要求】
// 5）时间复杂度：O(n)；空间复杂度：O(1)

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int curr1 = Integer.MIN_VALUE, curr2 = Integer.MIN_VALUE;
        int count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == curr1) count1++;
            else if (nums[i] == curr2) count2++;
            else if (count1 == 0) {
                curr1 = nums[i];
                count1 = 1;
            }
            else if (count2 == 0) {
                curr2 = nums[i];
                count2 = 1;
            }
            else {
                count1--;
                count2--;
            }
        }
        
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == curr1) count1++;
            else if (nums[i] == curr2) count2++;
        }
        if (count1 > nums.length / 3) list.add(curr1);
        if (count2 > nums.length / 3) list.add(curr2);
        
        return list;
    }
}