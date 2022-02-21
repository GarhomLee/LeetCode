https://leetcode.com/problems/permutations-ii/

idea1: Backtracking + Sort

class Solution {
    List<List<Integer>> ret = new ArrayList<>();
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, new ArrayList<>());
        
        return ret;
    }
    
    private void dfs(int[] nums, boolean[] used, List<Integer> list) {
        if (list.size() == nums.length) {
            ret.add(new ArrayList<>(list));
            return;
        }
        
        int i = 0;
        while (i < nums.length) {
            if (used[i]) {
                // update idx
                i++;
            } else {
                used[i] = true;
                list.add(nums[i]);
                dfs(nums, used, list);
                used[i] = false;
                list.remove(list.size() - 1);

                // update idx with deduplication, as same level can only pick one same unused num
                do {
                    i++;
                } while (i < nums.length && nums[i] == nums[i - 1]);
            }            
        }
    }
}


idea2: Backtracking + HashMap

class Solution {
    List<List<Integer>> ret = new ArrayList<>();
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        // take advantage of the Map to deduplicate
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        dfs(countMap, nums.length, new ArrayList<>());
        
        return ret;
    }
    
    private void dfs(Map<Integer, Integer> countMap, int n, List<Integer> list) {
        if (list.size() == n) {
            ret.add(new ArrayList<>(list));
            return;
        }
        
        for (int num : countMap.keySet()) {
            int count = countMap.get(num);
            if (count == 0) continue;
            
            list.add(num);
            countMap.put(num, count - 1);
            dfs(countMap, n, list);
            list.remove(list.size() - 1);
            countMap.put(num, count);
        }
    }
}