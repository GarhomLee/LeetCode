https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/

// 思路：Bit Manipulation + API调用
//         Integer.bitCount(int num)返回num的二进制表示中的1的个数。
//         Integer.toBinaryString(int num)返回num的二进制表示的String。
// 时间复杂度：O(R log R)
// 空间复杂度：O(1)

class Solution {
    public int countPrimeSetBits(int L, int R) {
        Set<Integer> primeSet = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));
        int res = 0;
        for (int i = L; i <= R; i++) {
            int count = Integer.bitCount(i);
            if (primeSet.contains(count)) {
                res++;
            }
        }
        
        return res;
    }
}