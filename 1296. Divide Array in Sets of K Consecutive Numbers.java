https://leetcode.com/problems/split-array-into-consecutive-subsequences/

solution1: TreeMap
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        int len = nums.length;
        if (len < k || len % k != 0) {
            return false;
        }
        
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : nums) {
            treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
        }
        
        for (int key : treeMap.keySet()) {
            // int count = treeMap.get(key);
            while (treeMap.get(key) > 0) {
                for (int i = 0; i < k; i++) {
                    if (!treeMap.containsKey(key + i) || treeMap.get(key + i) == 0) {
                        return false;
                    }
                    
                    treeMap.put(key + i, treeMap.get(key + i) - 1);
                }
            }
        }
        
        return true;
    }
}


solution2: HashMap. Similar to 659. Split Array into Consecutive Subsequences
time complexity: O(n)
space complexity: O(n)

class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();    // num -> count of num
        Map<Integer, Integer> flagMap = new HashMap<>();    // num -> count of flags this same num can use to form consecutive seq
        
        for (int num: nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (countMap.get(num) == 0) continue;
            
            if (flagMap.getOrDefault(num, 0) > 0) {
                flagMap.put(num, flagMap.get(num) - 1);
            } else {
                boolean isStart = true;
                for (int j = 1; j < k; j++) {
                    isStart = isStart && (countMap.getOrDefault(nums[i] + j, 0) > 0);
                }
                
                if (isStart) {
                   for (int j = 1; j < k; j++) {
                       countMap.put(nums[i] + j, countMap.get(nums[i] + j) - 1);
                    } 
                } else {
                    return false;
                }
            }
            
            countMap.put(num, countMap.get(num) - 1);
        }
        
        return true;
    }
}