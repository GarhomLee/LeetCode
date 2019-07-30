https://leetcode.com/problems/beautiful-array/

// 思路：Divide & Conquer。视频讲解：https://www.youtube.com/watch?v=9L6bPGDfyqo
//         根据题意，对于任意i<k<j，不能有A[k] * 2 = A[i] + A[j]，也就是不能有中间数A[k]为两边数A[i]和A[j]的平均数。
//         那么，如果利用某种方法，使得左边数A[i]为奇数，右边数A[j]为偶数，那么它们平均数不可能是整数，一定不会有整数A[k]。
//         因此，可以将数组分为左右两边，左边全部为奇数，右边全部为偶数。由于左半边数组本身也符合题目条件，右半边数组本身也
//         符合题目条件，因此整个数组就符合题目条件。由此，可以看出是Divide & Conquer的思想。
//         再观察题目可以发现，对于等式A[k] * 2 = A[i] + A[j]左右两边进行线性运算（加、减、乘、除），等式依然成立。
//         那么，将长度为N的符合题意的数组A所有元素*2-1，得到的是长度为2N的数组中的所有奇数项；同理将数组A所有元素*2，
//         得到的是长度为2N的数组中的所有偶数项。由于A本身符合题意，所以整体变换成奇数数组或者偶数数组，都是符合题意的。
//         将奇数数组和偶数数组拼接，就得到了符合题意的新数组，长度为2N。

// 解法一：Top-down Recursion with Memoization
//         由于中间结果可能会被重复计算，如N=10时计算左半边和右半边都为N=5，所以可以用HashMap来存储中间结果。
//         HashMap的key为可能的取值N，value为该取值对应的符合题意的int[]。
//         递归函数定义：int[] dnq(int N)表示得到长度为N的符合题意的int[]。
//         终止条件：1）N == 1，直接返回int[]{1};
//                 2）N已经求取过，map中存有其信息，直接返回map.get(N)
//         递归过程：当N为奇数时，需要给左边多一个元素，因此递归函数参数是(N + 1) / 2。其他情况下，参数都是N / 2。
//                 利用递归函数得到长度为N/2的结果后，将左半边所有元素*2-1变为奇数数组，右半边所有元素*2变为偶数数组，
//                 然后将左右半边拼接成为长度为N的新数组，可知这个新数组也符合题意。

class Solution {
    Map<Integer, int[]> map = new HashMap<>();
    
    public int[] beautifulArray(int N) {
        
        return dnq(N);
    }
    
    private int[] dnq(int N) {
        /* base cases */
        if (N == 1) {
            return new int[]{1};
        }
        if (map.containsKey(N)) {
            return map.get(N);  // use memoization
        }
        
        int[] leftPart = dnq((N + 1) / 2);
        int[] rightPart = dnq(N / 2);
        int[] res = new int[N];
        /* construct res array based on left part and right part */
        for (int i = 0; i < res.length; i++) {
            if (i < leftPart.length) {
                res[i] = leftPart[i] * 2 - 1;
            } else {
                res[i] = rightPart[i - leftPart.length] * 2;
            }
        }
        
        map.put(N, res);  // associate res array with N
        return res;
    }
}


// 解法二：Bottom-up
//         从长度为1的只含有元素1的数组开始，利用上述规则，不断得到长度是其两倍的合法数组，直到数组长度大于N。
//         从这个数组中取于等于N的数，得到最终结果数组res。
// 犯错点：1.细节错误：得到长度大于N的temp数组，其中大于N和小于等于N的数可能会是交替出现的，因此不能简单的
//             截取temp数组前N个数，而是应该从左往右依次选出小于等于N的数组成新的res数组。

class Solution {
    public int[] beautifulArray(int N) {
        /* corner case */
        int[] temp = new int[]{1};
        if (N == 1) return temp;
        
        while (temp.length < N) {
            int len = temp.length;
            int[] leftPart = temp;
            int[] rightPart = new int[len];
            /* update left part and right part */
            for (int i = 0; i < len; i++) {
                rightPart[i] = leftPart[i] * 2;
                leftPart[i] = leftPart[i] * 2 - 1;
            }
            /* construct temp array based on left part and right part */
            temp = new int[len * 2];
            System.arraycopy(leftPart, 0, temp, 0, len);
            System.arraycopy(rightPart, 0, temp, len, len);
        }
        
        /* get res array from valid subsequences in temp array */
        int[] res = new int[N];
        //System.arraycopy(temp, 0, res, 0, N); // {Mistake 1}
        for (int i = 0, j = 0; i < temp.length; i++) {  // {Correction 1}
            if (temp[i] <= N) {
                res[j++] = temp[i];
            }
        }
        return res;
    }
}