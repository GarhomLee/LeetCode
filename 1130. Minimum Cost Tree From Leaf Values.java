https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/

// 解法一：Divide&Conquer (mergesort)，找规律
//         观察题目，可以发现规律，要将当前范围[low:high]中最大的数和第二大的数分开放在左右子树，且尽可能让其他数都在
//         第二大的数所在的子树中。
//         因此，可以在[low:high]中最大的数的位置将数组分为左右两边，这两个数的乘积就是当前non-leaf节点的值，加到res中。
//         由于调用递归函数后，左右两部分都已经排好序，因此这两个数就是左半边的右边界数和右半边的右边界数。
// 注意：下面的代码能通过所有样例，但和上面的描述不完全相符，需要改进。比如没有区分第二大的数在最大数的左边还是右边，相对位置
//         也许会影响分割方式。
// 时间复杂度：O(n log n)
// 空间复杂度：O(log n)

class Solution {
    int res = 0;
    public int mctFromLeafValues(int[] arr) {
        mergesort(arr, 0, arr.length - 1);
        return res;
    }
    
    private void mergesort(int[] arr, int low, int high) {
        if (low >= high) return;
        int maxIndex = low, maxNum = Integer.MIN_VALUE;
        for (int i = low; i <= high; i++) {
            if (arr[i] > maxNum) {
                maxNum = arr[i];
                maxIndex = i;
            }
        }
        if (maxIndex == high) maxIndex--;
        mergesort(arr, low, maxIndex);
        mergesort(arr, maxIndex + 1, high);
        
        int leftMax = maxIndex >= low ? arr[maxIndex] : 0;
        int rightMax = maxIndex + 1 <= high ? arr[high]: 0;
        res += leftMax * rightMax;

        Arrays.sort(arr, low, high + 1);
    }
}


解法二：DP


解法三：Stack
时间复杂度：O(n)
空间复杂度：O(n)