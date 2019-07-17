https://leetcode.com/problems/fraction-addition-and-subtraction/

// 思路：String + 数学问题。
//     按照正常的分数加减法依次求和即可。
// 关键点：1.将原始算式expression分割成单独的分数，要用regex，写成split("(?=[-,+])")。其中"?="表示在'-'和'+'前
//             分割，符合分割成单独的分数的要求。
//         2.将两个分数的加和约分，要用到最大公约数gcd，背下求两个数的gcd的欧几里得算法。注意两个数都不为负数。

class Solution {
    public String fractionAddition(String expression) {
        String[] split = expression.split("(?=[-,+])");
        String res = "0/1";
        for (String s: split) {
            res = add(res, s);
        }
        return res;
    }
    
    private String add(String s1, String s2) {
        String[] split1 = s1.split("/");
        int numer1 = Integer.parseInt(split1[0]), denom1 = Integer.parseInt(split1[1]);
        String[] split2 = s2.split("/");
        int numer2 = Integer.parseInt(split2[0]), denom2 = Integer.parseInt(split2[1]);
        numer1 = numer1 * denom2 + numer2 * denom1;
        denom1 = denom1 * denom2;
        int gcd = gcd(Math.abs(numer1), denom1);
        return numer1 / gcd + "/" + denom1 / gcd;
    }
    
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}