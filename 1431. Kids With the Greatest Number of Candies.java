https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/

idea: Brute Force
time complexity: O(n)
space complexity: O(n)

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int num : candies) {
            max = Math.max(max, num);
        }
        
        List<Boolean> res = new ArrayList<>();
        for (int num : candies) {
            res.add(num + extraCandies >= max);
        }
        
        return res;
    }
}