https://leetcode.com/problems/sell-diminishing-valued-colored-balls/

idea: TreeMap (sorted) + Math
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    final int MOD = 1_000_000_007;
    
    public int maxProfit(int[] inventory, int orders) {
        long res = 0;
        TreeMap<Long, Long> countMap = new TreeMap<>();  // curr val -> curr count
        for (long colorMaxVal : inventory) {
            countMap.put(colorMaxVal, countMap.getOrDefault(colorMaxVal, 0l) + 1);
        }
        
        while (orders > 0) {
            Long curr = countMap.lastKey();
            long currCount = countMap.get(curr);
            Long next = countMap.lowerKey(curr);
            if (next == null) {
                next = 0l;
            }
            
            long intervalCount = (curr - next) * currCount;
            if (intervalCount <= orders) {
                // all values in the interval can fill the orders
                countMap.remove(curr);
                if (countMap.containsKey(next)) {
                    countMap.put(next, countMap.get(next) + currCount);
                }
                
                // get the total values in the interval
                long total = (curr + next + 1) * (curr - next) / 2 * currCount;
                res += (total % MOD);
                orders -= (int) intervalCount;  // update
            } else {
                long round = orders / currCount;    // how many full rounds
                long remainder = orders % currCount;    // the remaining orders
                
                long total = (curr + (curr - round + 1)) * round / 2 * currCount;
                total += remainder * (curr - round);
                res += (total % MOD);
                orders = 0; // update, and the loop should end
            }
            
            res %= MOD;
        }
        
        return (int) res;
    }
}