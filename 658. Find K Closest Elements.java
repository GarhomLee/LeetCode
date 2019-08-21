https://leetcode.com/problems/find-k-closest-elements/

// 解法一：常规Binary Search
//         step0: 特殊情况k==0，直接返回空List 
//         step1: 用Arrays.binarySearch()找到x的最小位置，如果结果为负数，那么转换成正数，为x应该被insert的位置
//         step2: 维护两个指针left和right，分别指向左边离x最近的数和右边离x最近的数，初始化为index-1和index。
//                 维护两个变量leftDiff和rightDiff，分别表示x和arr[left]以及arr[right]的差值。如果超出arr范围，
//                 那么赋值为Integer.MAX_VALUE。
//                 如果leftDiff > rightDiff，说明arr[right]离x更近，那么加到res列表末尾；否则，arr[left]离x更近，
//                 【加到res列表头部】。
//                 最后返回res。
// 时间复杂度：O(log(n) + k)
// 空间复杂度：O(1)

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if (k == 0) {
            return res;
        }
        
        int index = Arrays.binarySearch(arr, x);
        if (index < 0) {
            index = -index - 1;
        }
        
        int left = index - 1, right = index;
        while (k-- > 0) {
            int leftDiff = left >= 0 ? x - arr[left] : Integer.MAX_VALUE;
            int rightDiff = right < arr.length ? arr[right] - x : Integer.MAX_VALUE;
            if (leftDiff > rightDiff) {
                res.add(arr[right++]);
            } else {
                res.add(0, arr[left--]);
            }
        }
        
        return res;
    }
}


解法二：Binary Search查找大小为k的子区间。视频讲解：https://www.youtube.com/watch?v=3ifFNvdfjyg
        找到最小的起始位置low，使得对所有的arr[low:length-1]都有x - arr[low] <= arr[low+k] - x。
时间复杂度：O(log(n-k) + k)
空间复杂度：O(1)