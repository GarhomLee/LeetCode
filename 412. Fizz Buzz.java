https://leetcode.com/problems/fizz-buzz/

// 思路：常规遍历，条件判断
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (Integer i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                res.add(i.toString());
            } else {
                String s = "";
                if (i % 3 == 0) {
                    s += "Fizz";
                }
                if (i % 5 == 0) {
                    s += "Buzz";
                }
                res.add(s);
            }
        }
        
        return res;
    }
}