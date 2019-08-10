https://leetcode.com/problems/relative-ranks/

// 思路：Sort
//         用辅助数组copy原数组且升序排序，排序的index和实际排名rank顺序相反。
// 犯错点：1.Java内置函数使用错误：Arrays.sort()中的Comparator重写只能对Class有效，如Integer，int[]，
//         且sort的对象要与之对应。

class Solution {
    public String[] findRelativeRanks(int[] nums) {
        int[] clone = nums.clone();
        Arrays.sort(clone);
        
        String[] res = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int rank = clone.length - Arrays.binarySearch(clone, nums[i]);
            switch (rank) {
                case 1:
                    res[i] = "Gold Medal";
                    break;
                case 2:
                    res[i] = "Silver Medal";
                    break;
                case 3:
                    res[i] = "Bronze Medal";
                    break;
                default:
                    res[i] = String.valueOf(rank);
                    break;    
            }
        }
        
        return res;
    }
}