https://leetcode.com/problems/arranging-coins/

// 解法一：常规解法
//         n在每层减少相应的row个硬币，直至n<=0。
//         如果n==0，说明[1:row]层是满的，返回row。否则，如果n<0，说明只有[1:row-1]层是满的，第row层是不满的，所以返回row-1.
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int arrangeCoins(int n) {
        int row = 0;  // row indicates the row number (1-based) as well as the number of coins at that row
        while (n > 0) {
            row++;
            n -= row;
        }
        return n == 0? row : row - 1;  // if the loop stops at n==0, it fully fills the row-th staircase with row coins; otherwise, only the (row-1)-th staircased is fully filled with (row-1) coins
    }
}

// 解法二：Binary search
//         对于某个数i，i越大，[0:i]的和越大。所以等价于找到某一个数i，使得大于这个数i的所有数j，都有[0:j]的和大于n。
//         low boundary: low=0
//         high boundary: high=n，因为[0:i]的右端点不可能超过n
//         g(m):要找到一个mid，使得[0:mid]的和（根据通项公式为mid * (mid + 1)) / 2）大于n。
//             这里不取等于是为了统一n恰好为sum[0:mid]和n大于sum[0:mid]的情况，统一为所求数再+1，最后返回的时候统一减回去。
//             eg.n=6，low=4，返回3；n=8，low=4，同样返回3；n=10，low=5，则返回4
//         返回值：low-1
// 时间复杂度：O(log n)
// 空间复杂度：O(1)

class Solution {
    public int arrangeCoins(int n) {
        int low = 0, high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (((long) mid * (mid + 1)) / 2 > n) high = mid - 1;  // Attention: cast mid * (mid + 1) into long to avoid overflow
            else low = mid + 1;
        }
        return low - 1;
    }
}

解法三：解数学公式