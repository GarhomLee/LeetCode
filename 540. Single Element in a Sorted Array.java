https://leetcode.com/problems/single-element-in-a-sorted-array/

// 思路：Binary Search，关键是找到【元素个数为奇数个】的范围[low:high]。
//         维护两个指针low和high，表示搜索范围[low:high]，【保持搜索范围[low:high]所包含的元素为奇数个】。
//         当low==high时找到了single element停止搜索。
//         从mid将[low:high]分为[low:mid-1]和[mid+1:high]两部分，判断nums[mid - 1]或nums[mid + 1]
//         哪个和nums[mid]相同，那么这部分元素个数就为偶数个，抛弃掉。
//         1）如果((high - mid) & 1) == 1，即[low:mid-1]和[mid+1:high]各有奇数个元素，那么如果
//             nums[mid] == nums[mid + 1]，说明[mid:high]有偶数个元素，排除掉整个[mid:high]，所以更新
//             high = mid - 1。否则更新low = mid + 1。
//         2）如果((high - mid) & 1) == 0，即[low:mid-1]和[mid+1:high]各有偶数个元素，那么如果
//             nums[mid] == nums[mid - 1]，说明[low:mid]有奇数个元素，排除掉整个另一半部分[mid+1:high]，
//             所以【更新high = mid】。否则【更新low = mid】。
// 时间复杂度：O(log n)
// 空间复杂度：O(1)

class Solution {
    public int singleNonDuplicate(int[] nums) {            
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (((high - mid) & 1) == 1) {  // odd num of elements in [mid+1:high] and [low:mid-1]
                if (mid + 1 <= high && nums[mid] == nums[mid + 1]) {  // even num of elements in [mid,high]
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {  // even num of elements in [low:mid-1] and [mid+1:high]
                if (mid - 1 >= low && nums[mid] == nums[mid - 1]) {
                    high = mid;
                } else {
                    low = mid;
                }
            }
        }
        
        return nums[low];
    }
}