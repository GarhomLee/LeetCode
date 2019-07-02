https://leetcode.com/problems/circular-array-loop/

// 思路：slow&fast pointer，比较复杂。判断是否有cycle/loop的问题，一般可以用slow&fast pointer。
//      先判断边界条件：nums.length < 2一定不能形成符合题意的环。
//      遍历数组的每一个元素，如果为0，不管是不是被标记的，一定不能移动，所以不能成环，跳过。
//      维护两个指针：slow，初始化为当前元素位置i；fast，初始化为位置i的下一位。
//      进入while循环，不断寻找处于同一个方向的所有元素，这些元素的特点是和起始元素nums[i]同号，所以相乘大于0.
//      对于slow和fast，有两种可能：
//      1）slow == fast，两个速度不同的指针相遇，有两种可能导致这个结果：
//         a）slow == nextIndex(nums, slow)，cycle里只有一个元素，即slow走一步又回到原地，那么fast走两步也一定回到原地。
//            如果是这种情况，那么这个元素不符合题意，所以直接跳出while循环。
//         b）slow != nextIndex(nums, slow)，找到了符合题意的环，返回true
//      2）slow != fast，更新两个指针，slow走一步，fast走两步。
//      当跳出while循环，可能是由于遇到方向不同的元素，或者找到不符合题意的只有一个元素环。不管哪种情况，都要将从
//      当前元素nums[i]开始的相同方向的元素标记为0，表示它们不能成环，如果遍历时遇到可以直接跳过。可以这样标记和跳过
//      是因为元素是否成环只和本身的值nums[i]有关，而和从那里开始走到nums[i]无关。
//      遍历完所有元素，没有找到环，返回false。
// 时间复杂度：worst case O(n^2)
// 空间复杂度：O(1)
// 犯错点：本题坑很多。
//        1.判断是否处在同一个方向，需要考虑nums[slow]，nums[fast]，nums[nextIndex(nums, fast)]三个数。
//          如果不考虑nums[nextIndex(nums, fast)]，那么可能fast指针会跳过一些数，即使走了回头路也判断不出来。
//        2.将不能成环的元素设为0时，需要先用临时变量得到下一个slow，再赋值，然后slow用临时变量更新。
//          如果先赋值0再调用nextIndex()得到下一个slow，那么根据nextIndex()的实现，nums[i]已经发生了变化，因此
//          下一个slow不是我们预期的那个位置。
//        3.在nextIndex()的实现中，要根据i + nums[i]的正负值选择返回值，而不仅仅通过nums[i]。即使nums[i] < 0
//          表示向左走，只要i + nums[i] >= 0，那么下一个位置依然是(i + nums[i]) % n。只有当i + nums[i] < 0，
//          表示超过数组左边界，下一个位置才是n + (i + nums[i]) % n

class Solution {
    public boolean circularArrayLoop(int[] nums) {
        /* corner case */
        if (nums == null || nums.length < 2) return false;
        
        for (int i = 0; i < nums.length; i++) {
            /* skip those marked as non-cyclic */
            if (nums[i] == 0) continue;
            
            /* use slow&fast pointers */
            int slow = i, fast = nextIndex(nums, i);
            //while (nums[i] * nums[slow] > 0 && nums[i] * nums[fast] > 0)  // {Mistake 1: some num that leads to go backward might be skipped, thus error}
            while (nums[i] * nums[slow] > 0 && nums[i] * nums[fast] > 0 && nums[i] * nums[nextIndex(nums, fast)] > 0) { // {Correction 1}
                                                                                                                        // keep searching if they're in the same direction
                if (slow == fast) {  // two pointers meet somewhere
                    if (slow == nextIndex(nums, slow)) {  // cycle's length is 1, stop searching
                        break;
                    } else {  // there is a cycle with length > 1
                        return true;
                    }
                } else {  // two pointers do not encounter
                    slow = nextIndex(nums, slow);
                    fast = nextIndex(nums, nextIndex(nums, fast));
                }
            }
            
            /* jump out of the loop, which means all elements in current path is non-cyclic, thus set to 0 to skip if later encountered */
            slow = i;
            int startVal = nums[i];
            while (startVal * nums[slow] > 0) {
                int nextIndex = nextIndex(nums, slow);  // {Correction 2}
                nums[slow] = 0;
                slow = nextIndex;
                //slow = nextIndex(nums, slow);  // {Mistake 2: if nums[slow] is asssigned to 0 before getting next slow, according to the implementation of nextIndex() it might not return the expected next slow pointer
            }
        }
        
        return false;
    }
    
    private int nextIndex(int[] nums, int i) {
        int n = nums.length;
        //return nums[i] > 0 ? (i + nums[i]) % n : (n + i + nums[i]) % n;  // {Mistake 3}
        return i + nums[i] >= 0 ? (i + nums[i]) % n : n + (i + nums[i]) % n;  // {Correction 3}
    }
}