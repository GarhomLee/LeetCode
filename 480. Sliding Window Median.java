https://leetcode.com/problems/sliding-window-median/

// 对比：239. Sliding Window Maximum

// 解法一：Sliding Window + Binary Search (Insertion Sort)
//         维护长度为k的List，存放sliding window中排序后的元素。
//         不断向list中加入window右边缘元素nums[i]，加入的位置根据binary search的结果决定。
//         当list的长度等于k时，求中位数加入res数组，并删除左边缘元素nums[i - k + 1]，维持长度为k-1.
// 时间复杂度：O((n-k+1)*k)
// 空间复杂度：O(k)
// 犯错点：1.类型转换错误：List<Integer>.get()的返回值类型是int，而求median时需要求平均值得到double。
//             因此，在取平均时需要强制转换类型为double。

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new double[0];
        }
        
        List<Integer> list = new ArrayList<>();
        
        double[] res = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            add(list, nums[i]);  // add the rightmost element into the window
            /* execute when the window size is already k */
            if (i >= k - 1) {
                res[i - k + 1] = getMedian(list, k);
                list.remove((Integer) nums[i - k + 1]);  // remove the leftmost element in the window, thus the size becomes k - 1
            }
        }
        
        return res;
    }
    /** get the median from a sorted List with size k */
    private double getMedian(List<Integer> list, int k) {
        //return (list.get(k / 2) + list.get((k - 1) / 2)) / 2;  // {Mistake 1}
        return ((double) list.get(k / 2) + list.get((k - 1) / 2)) / 2;  // {Correction 1}
    }
    /** add element n into the proper position in the sorted List using binary search */
    private void add(List<Integer> list, int n) {
        int index = Collections.binarySearch(list, n);
        if (index >= 0) {
            list.add(index, n);
        } else {
            list.add(-index - 1, n);
        }
    }
}


解法二：Sliding Window + Two Priority Queues，类似295. Find Median from Data Stream
时间复杂度：O((n-k+1)*k)，因为Java的Priority Queue的删除前的查找是O(n)时间
空间复杂度：O(k)


解法三：Sliding Window + Balance Binary Search Tree with median pointer
时间复杂度：O((n-k+1)*log k)
空间复杂度：O(k)