https://leetcode.com/problems/super-washing-machines/

// 思路：数学问题，找数学规律。看清题意，每次可以任意相邻格子传递load。参考：https://leetcode.com/problems/super-washing-machines/discuss/99185/Super-Short-and-Easy-Java-O(n)-Solution
//         先求总和sum，如果不能被len整除，直接返回-1。
//         维护变量：curr代表将load和average差值从左向右的累加值，maxLoad需要转移的load的最大值，根据
//         题意可知使得所有格子的load相同的最小步数取决于maxLoad。
//         遍历machines数组元素，得到load和average的差值diff，用diff更新累加值curr，而maxLoad根据
//         Math.max(Math.abs(curr)和diff更新。

class Solution {
    public int findMinMoves(int[] machines) {
        int sum = 0;
        for (int n: machines) {
            sum += n;
        }
        if (sum % machines.length != 0) return -1;
        
        int average = sum / machines.length;
        int curr = 0, maxLoad = 0;
        for (int load: machines) {
            int diff = load - average;
            curr += diff;
            maxLoad = Math.max(maxLoad, Math.max(Math.abs(curr), diff));
        }
        
        return maxLoad;
    }
}