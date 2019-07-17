https://leetcode.com/problems/perfect-number/

// 暴力解法：从i=1遍历到i=num，这样会造成重复计算

// 优化解法：从i=1遍历到i=sqrt(num)
//     当i能被num整除，那么另一个数num / i也能被num整除。要排除掉i == num / i即i * i == num的情况。
// 注意点：当i==1时，num / i结果是num本身，会被加进sum里，根据题目要求，要把num本身从sum中排除，所以最后判断的是sum - num == num

class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num <= 0) return false;
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i != 0) continue;
            sum += i;
            if (i * i != num) {
                sum += num / i;
            }
        }
        
        return sum - num == num;
    }
}