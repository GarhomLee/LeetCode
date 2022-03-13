https://leetcode.com/problems/dot-product-of-two-sparse-vectors/

idea: HashMap

class SparseVector {
    Map<Integer, Integer> map;
    
    SparseVector(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                map.put(i, nums[i]);
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int ret = 0;
        for (int idx: map.keySet()) {
            ret += map.get(idx) * vec.map.getOrDefault(idx, 0);
        }
        return ret;
    }
}