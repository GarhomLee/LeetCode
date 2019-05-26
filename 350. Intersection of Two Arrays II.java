https://leetcode.com/problems/intersection-of-two-arrays-ii/

// 解法一：HashMap
//         利用Map来存取元素和出现次数的映射，key为nums1数组中的元素，value为各元素出现的次数。
//         当nums2出现相同元素，且Map中次数大于0，加入List，然后次数减少1.
// 时间复杂度：O(max(m,n)), m=nums1.length, n=nums2.length
// 空间复杂度：O(m), m=nums1.length
// 犯错点：1.只有variable才能用++或--操作符，而map.get(n)得到的是value而不是variable，所以不能用--。

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[0];
        List<Integer> list = new ArrayList<>();
        
        Map<Integer, Integer> map = new HashMap();
        for (int n: nums1) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        for (int n: nums2) {
            if (!map.containsKey(n) || map.get(n) == 0) continue;
            list.add(n);
            //map.put(n, map.get(n)--);  // {Mistake 1: only variable can use increment operator (++) or decrement operator (--), and map.get(n) returns a value instead of a variable}
            map.put(n, map.get(n) - 1);  // {Correction 1}
        }
        
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}

// 解法二：Two pointers
// 时间复杂度：O(max(m,n)), m=nums1.length, n=nums2.length
// 空间复杂度：O(1)

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[0];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) i++;
            else if (nums1[i] > nums2[j]) j++;
            else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] array = new int[list.size()];
        for (int k = 0; k < list.size(); k++) array[k] = list.get(k);
        return array;
    }
}