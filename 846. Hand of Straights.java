https://leetcode.com/problems/hand-of-straights/

idea: TreeMap
time complexity:O(nlogn + n*W)
space complexity: O(n)

class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0) {
            return false;
        }
        
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : hand) {
            treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
        }
        
        for (int key : treeMap.keySet()) {
            if (treeMap.get(key) == 0) continue;
            
            int count = treeMap.get(key);
            for (int i = key; i < key + W; i++) {
                if (!treeMap.containsKey(i) || treeMap.get(i) < count) {
                    return false;
                }
                
                treeMap.put(i, treeMap.get(i) - count);
            }
        }
        
        return true;
    }
}