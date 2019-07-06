https://leetcode.com/problems/complex-number-multiplication/

// 思路：考察数学。
//      因为题目规定了input和output都要带有x+yi中的x，加号，y，和i，因此只需要把input中的实数和虚数分别取出，
//      然后进行简单的相乘和相加（注意i*i=-1)，最后变成字符串即可。

class Solution {
    public String complexNumberMultiply(String a, String b) {
        String[] aSplit = a.split("\\+|i"), bSplit = b.split("\\+|i");
        int aReal = Integer.parseInt(aSplit[0]), aImag = Integer.parseInt(aSplit[1]);
        int bReal = Integer.parseInt(bSplit[0]), bImag = Integer.parseInt(bSplit[1]);
        return (aReal * bReal - aImag * bImag) + "+" + (aReal * bImag + bReal * aImag) + "i";
    }
}