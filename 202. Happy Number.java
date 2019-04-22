https://leetcode.com/problems/happy-number/

// 因为不是happy number的话，会导致数的循环，所以可以维护一个Set来判断是否有重复出现的数。

class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int next = n;
        while (next != 1) {
            int temp = 0;
            while (next > 0) {
                temp += (next % 10) * (next % 10);
                next /= 10;
            }
            next = temp;
            if (set.contains(next)) return false;
            set.add(next);
        }
        return true;
    }
}