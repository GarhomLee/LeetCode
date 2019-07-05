https://leetcode.com/problems/distribute-candies/

// 关键：理解题目意思，将不同种类糖果（数字表示其种类）平均分成两份，其中一份种类最多，求种类最多的那一份中的种类数。
// 思路：去重复，得到unique number的个数，并和candies.length / 2比较。因为每一份的糖果数都是candies.length / 2，
//      因此如果unique number的个数大于等于candies.length / 2，也只能取candies.length / 2；如果小于，就直接
//      取unique number的个数。

// 解法一：Sort

// 解法二：HashSet

class Solution {
    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet();
        for (int n: candies) {
            set.add(n);
        }
        return set.size() >= candies.length / 2 ? candies.length / 2 : set.size();
    }
}