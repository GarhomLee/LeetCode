https://leetcode.com/problems/confusing-number/

// 思路：Math
// 时间复杂度：O(log N)
// 空间复杂度：O(log N)

class Solution {
    public boolean confusingNumber(int N) {
        Set<Integer> set = new HashSet<>(Arrays.asList(0, 1, 6, 8, 9));
        int rotate = 0;
        int temp = N;
        while (temp != 0) {
            int last = temp % 10;
            if (!set.contains(last)) {
                return false;
            }
            
            if (last == 6) {
                last = 9;
            } else if (last == 9) {
                last = 6;
            }
            
            rotate = rotate * 10 + last;
            temp = temp / 10;
        }
        
        return rotate != N;
    }
}