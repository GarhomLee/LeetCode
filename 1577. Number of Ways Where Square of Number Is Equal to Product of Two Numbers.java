https://leetcode.com/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers/

idea: HashMap + TreeMap. Similar to Two Sum.
    -Count the frequency of each element, for later calculation of the number of triplets.
time complexity: O(n * sqrt(n))
space complexity: O(n)

class Solution {
    private int find(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();   // num in nums1 -> count
        TreeMap<Integer, Integer> map2 = new TreeMap<>();   // num in nums2 -> count
        for (int num : nums1) {
            map1.put(num, map1.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            map2.put(num, map2.getOrDefault(num, 0) + 1);
        }
        
        int res = 0;
        for (int key1 : map1.keySet()) {
            long product = ((long) key1) * key1;
            for (int key2 : map2.keySet()) {
                if (key2 > key1) break;
                
                int quo2 = (int) (product / key2);
                if (!map2.containsKey(quo2) || quo2 * ((long) key2) != product) continue;
                
                if (quo2 == key2) {
                    int count2 = map2.get(key2);
                    res += map1.get(key1) * (count2 * (count2 - 1) / 2);
                } else {
                    res += map1.get(key1) * map2.get(key2) * map2.get(quo2);
                }
            }
        }
        return res;
    }
    
    public int numTriplets(int[] nums1, int[] nums2) {
        int res = find(nums1, nums2);
        res += find(nums2, nums1);
        return res;
    }
}