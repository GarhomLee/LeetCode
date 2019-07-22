https://leetcode.com/problems/number-of-equivalent-domino-pairs/

// 思路：降维。根据题意，每个元素取值[1:9]，每组数有两个元素，因此可以看作是二维数组。题目实际上是将{a,b}和{b,a}当成同一个数，
//         问可以组成多少个相同数的配对。因此，可以【将{i,j}保持i<=j并统一从二维降维到一维】，统计所有出现的次数。对于出现次数
//         大于1的数，假设出现了k次，能组成【C(k,2)个配对】。
// 犯错点：1.思路错误：统计相同数能组成的配对数应该在统计完所有出现的次数后再计算

class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] count = new int[100];
        int res = 0;
        for (int[] pair: dominoes) {
            int a = Math.min(pair[0], pair[1]), b = Math.max(pair[0], pair[1]);
            count[transform(a, b)]++;
            //if (count[transform(a, b)] > 1) res += count[transform(a, b)] * (count[transform(a, b)] - 1);  // {Mistake 1}
        }
        
        for (int i = 1; i < 100; i++) {
            if (count[i] > 1) res += count[i] * (count[i] - 1) / 2;  // {Correction 1}
        }
        return res;
    }
    
    private int transform(int a, int b) {
        return a * 10 + b;
    }
}