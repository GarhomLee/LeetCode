https://leetcode.com/problems/candy/

// 思路：Greedy，Two-pass scanning
//      维护两个数组，分别存放从左往右扫描（和左边一个元素比较）的结果compareLeft，和从右往左扫描（和右边一个元素比较）的结果compareRight。
//      从左往右扫描，当前元素和左边一个元素比较，如果当前元素更大，那么糖果数+1，否则，当前元素可能相等或更小，糖果数维持不变（即为1）。
//      从右往左扫描同理。
//      然后再进行一次遍历，将max(compareLeft[i], compareRight[i])求和，即为所求。因为如果ratings[i]比左边元素ratings[i - 1]小，
//      那么反过来ratings[i - 1]就比右边元素ratings[i]大，会被从右往左扫描捕捉到。

class Solution {
    public int candy(int[] ratings) {
        int[] compareRight = new int[ratings.length], compareLeft = new int[ratings.length];
        Arrays.fill(compareRight, 1);
        Arrays.fill(compareLeft, 1);

        for (int i = 1; i < ratings.length; i++) {  // from left to right, compare i with i-1
            if (ratings[i] > ratings[i - 1]) compareLeft[i] = compareLeft[i - 1] + 1;
        }
        for (int i = ratings.length - 2; i >= 0 ; i--) {  // from right to left, compare i with i+1
            if (ratings[i] > ratings[i + 1]) compareRight[i] = compareRight[i + 1] + 1;
        }
        
        int sum = 0;
        for (int i = 0; i < ratings.length; i++) {  // get the max and sum up
            sum += Math.max(compareLeft[i], compareRight[i]);
        }
        return sum;
    }
}