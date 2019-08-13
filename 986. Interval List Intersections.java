https://leetcode.com/problems/interval-list-intersections/

// 思路：Two pointers "Merge Sort"
//         题目给定A数组和B数组都已经排好序。假设A[i]为当前A数组的最小interval，B[j]为当前B数组的最小interval。
//         如果A[i]的end小于B[j]的end，只需要右移i，因为A[i+1]还可能和B[j]有交集，而相反B[j+1]不可能和A[i]有交集。
//         同理，如果A[i]的end大于等于B[j]的end，只需要右移j。整个遍历的过程类似按A数组和B数组的end从小到大排序，类似
//         Merge Sort中的merge的过程。
//         step1: 初始化i=0， j=0。interval交集的start为max(A[i][0], B[j][0])，end为min(A[i][1], B[j][1])。
//             如果start <= end，说明有合法的交集，加入List。
//         step2: 根据A[i]的end和B[j]的end的大小比较判断更新哪个指针，寻找下一个可能的交集。
//         step3: 将List的结果转成int[] res，返回res。
// 时间复杂度：O(a+b), a=A.length, b=B.length
// 空间复杂度：O(k), k=num of intersections

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            int start = Math.max(A[i][0], B[j][0]);
            int end = Math.min(A[i][1], B[j][1]);
            if (start <= end) {
                list.add(new int[]{start, end});
            }
            
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        
        int[][] res = new int[list.size()][2];
        for (int index = 0; index < res.length; index++) {
            res[index] = list.get(index);
        }
        
        return res;
    }
}