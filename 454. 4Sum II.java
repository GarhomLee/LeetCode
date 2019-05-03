https://leetcode.com/problems/4sum-ii/

// 跟之前做的2sum, 3sum, 4sum完全不是一回事，有另一套解题的思路。
// 1）将4个数组分为两组，每组2个数组
// 2）二重循环A, B两个数组，用HashMap，将A+B[j]的sum作为key，对应出现的次数作为value，统计每一种sum出现了多少次
// 3）维护一个count变量作为最后的返回结果
// 4）二重循环C, D两个数组，因为target = 0 = A+B[j] + C[x] + D[y]，所以在HashMap中找是否存在0 - (C[x] + D[y])的key。如果存在，count += 对应的value，表示这四个数可以有count种方式得到target
// 5）Time complexity: O(n^2);Space complexity: O(n)

class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> sumCountMapping = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                sumCountMapping.put(A[i] + B[j], sumCountMapping.getOrDefault(A[i] + B[j], 0) + 1);
            }
        }
        int result = 0;
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                result += sumCountMapping.getOrDefault(0 - C[i] - D[j], 0);
            }
        }
        return result;
    }
}