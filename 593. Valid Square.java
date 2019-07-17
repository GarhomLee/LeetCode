https://leetcode.com/problems/valid-square/

// 思路：HashMap + 数学问题。
//         Map中，key为任意两点的距离，value为该距离出现的次数。
//         根据数学上的定义，正方形要满足两个条件：
//         1）四条边长度相等
//         2）四个角都为90度
//         第二点比较难判断，但可以用另一种判断条件：【对角线长度相等】。
//         因此，求得所有两点距离后，合法的正方形必然有所有dist>0，且只有2种不同距离，且最长距离有且只有出现2次。
// 犯错点：1.题目理解错误：square是正方形，rectangle才是矩形。题目要求的是四条边长度相等。
//         2.题目理解错误：四个点顺序不定，无法直接判断哪两个点处于对角线。

class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        /* base case */
        if (p1.length != 2 || p2.length != 2 || p3.length != 2 || p4.length != 2) return false;
        
        long[] distArray = new long[]{dist(p1, p2), dist(p2, p3), dist(p3, p4), dist(p1, p4), dist(p2, p4), dist(p1, p3)};
        Map<Long, Integer> map = new HashMap();
        long maxLen = 0;  // record the longest distance between two points
        for (long dist: distArray) {
            if (dist == 0) return false;  // overlapped points

            map.put(dist, map.getOrDefault(dist, 0) + 1);
            maxLen = Math.max(maxLen, dist);
        }
        //return map.size() == 2 && dist(p2, p4) == dist(p1, p3);  // {Mistake 2}
        return map.size() == 2 && map.get(maxLen) == 2;  // {Correction 2}
    }
    
    private long dist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}