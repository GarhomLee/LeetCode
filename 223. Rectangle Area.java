https://leetcode.com/problems/rectangle-area/

// 根据面积公式，两个矩形面积和减去重叠的面积即为所求，所以关键在于如何求重叠的面积。
// 1）维护4个变量，分别表示重叠部分的左下端点坐标和右上端点坐标。
// 2）关键点：确定了left和bottom的取值后，利用它们来确定right和top的取值。如果没有重叠，那么right和left取值相同，top和bottom取值相同，所以重叠面积为0。


class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int left = Math.max(A, E), right = Math.max(Math.min(C, G), left);
        int bottom = Math.max(B, F), top = Math.max(Math.min(D, H), bottom);
        return (C - A) * (D - B) + (G - E) * (H - F) - (right - left) * (top - bottom);
    }
}