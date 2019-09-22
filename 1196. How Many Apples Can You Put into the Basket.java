https://leetcode.com/problems/how-many-apples-can-you-put-into-the-basket/

// 思路：Sort + Greedy。注意：不是0/1背包问题。
//         每次取重量最小的苹果，直到总重量超过限制5000.
// 时间复杂度：O(n log n)
// 空间复杂度：O(1)

class Solution {
    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int count = 0, sum = 0;
        for (int w: arr) {
            sum += w;
            if (sum > 5000) {
                break;
            }
            count++;
        }
        
        return count;
    }
}