https://leetcode.com/problems/can-place-flowers/solution/

思路：Greedy
        从左往右扫描，在可以种的地方都种上花。
时间复杂度：O(n)
空间复杂度：O(1)

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) continue;
            if (i > 0 && flowerbed[i - 1] == 1) continue;
            if (i + 1 < len && flowerbed[i + 1] == 1) continue;
            
            n--;
            flowerbed[i] = 1;
            if (n <= 0) {
                return true;
            }
        }
        
        return n == 0;
    }
}