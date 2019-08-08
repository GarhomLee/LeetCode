https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/

// 思路：Greedy？关键在于不断跳过一部分数字，不断逼近接近第k位的数字。
//         维护3个变量：curr表示当前数；k表示还需要跳过的数字个数；count表示以curr为prefix的小于等于n
//         的数字的个数，即以curr为有最多10个子节点的denary tree的根节点，包含curr在内的整个树的大小。
//         循环开始前，【curr赋值为1，k--】（可以代入特殊值尝试一下）。
//         当k > 0时，在循环中调用helper method计算以curr为prefix的小于等于n的数字的个数count。
//         1）如果count <= k，说明curr为根节点的整棵树可以全部跳过，那么更新curr为其同一层的右边元素
//             curr+1，且k-=count
//         2）如果count > k，说明要求的数在urr为根节点的树中，那么更新curr为最左边的子节点curr *= 10，
//             且k只自减1.
//         当得到k==0，表示不需要跳过任何数了，已经找到结果，返回结果curr。
//         int count(long n, long curr, long next)表示计算以curr为prefix的小于等于n的数字的个数，
//         参数next表示和curr处在同一层的【右边隔壁的树的最左边元素】，相当于指示了以curr为根节点的树的
//         范围和分隔位置。如初始curr=1，next=2，那么在循环中curr=10时next=20，表示树在当前层的范围为
//         [10:20)；curr=100时next=200，表示树在当前层的范围为[100:200)
//         当curr <= n，还有可以搜索的数字，不断循环。有两种情况：
//         1）n >= next，说明当前这一层的[curr:next)所有数都要统计进去，因此count += next - curr
//         2）n < next，说明当前这一层符合题意的范围为[curr:n]，个数为n + 1 - curr，因此
//             count += n + 1 - curr
//         综合两种情况，count更新取决于n和next的较小值，因此可以写成count += Math.min(n + 1, next) - curr。
//         同时，要更新curr *= 10，更新next *= 10.
// 时间复杂度：O(k*log n)
// 空间复杂度：O(1)

class Solution {
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            int count = count(n, curr, curr + 1);
            if (count <= k) {
                curr = curr + 1;
                k -= count;
            } else {
                curr *= 10;
                k--;
            }
        }
        
        return curr;
    }
    
    private int count(long n, long curr, long next) {
        int count = 0;
        while (curr <= n) {
            count += Math.min(n + 1, next) - curr;
            curr *= 10;
            next *= 10;
        }
        
        return count;
    }
}