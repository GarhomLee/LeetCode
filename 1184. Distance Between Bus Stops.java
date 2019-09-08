https://leetcode.com/problems/distance-between-bus-stops/

// 思路：扫描求prefix sum。
//         保证start <= destination，那么转化为求forward = sum[destination] - sum[start]和sum[n] - forward
//         的较小值。
// 时间复杂度：O(n)
// 空间复杂度：O(n)，可以优化到O(1)

class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start == destination) {
            return 0;
        }
        
        int n = distance.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= distance.length; i++) {
            sum[i] = sum[i - 1] + distance[i - 1];
        }
        
        if (start > destination) {
            int temp = start;
            start = destination;
            destination = temp;
        }
        int forward = sum[destination] - sum[start];
        
        return Math.min(forward, sum[n] - forward);
    }
}