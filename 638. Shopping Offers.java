https://leetcode.com/problems/shopping-offers/

idea: Backtracking


class Solution {
    int minSum = Integer.MAX_VALUE;
    
    public void helper(List<Integer> prices, List<List<Integer>> specials, List<Integer> needs, int sum) {
        boolean done = true;
        for (int need : needs) {
            done = done && need == 0;
        }
        if (done) {
            minSum = Math.min(minSum, sum);
            return;
        }
        
        int n = needs.size();
        // apply specials
        for (List<Integer> special : specials) {
            List<Integer> newNeeds = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (special.get(i) > needs.get(i)) break;   // cannot exeed the need
                
                newNeeds.add(needs.get(i) - special.get(i));
            }
            
            if (newNeeds.size() < n) continue;  // current special cannot be applied, skip
            
            helper(prices, specials, newNeeds, sum + special.get(n));
        }
        
        // not apply specials
        int newCost = 0;
        for (int i = 0; i < n; i++) {
            newCost += prices.get(i) * needs.get(i);
        }
         helper(prices, specials, new ArrayList<Integer>(), sum + newCost);
    }
    
    public int shoppingOffers(List<Integer> prices, List<List<Integer>> specials, List<Integer> needs) {
        helper(prices, specials, needs, 0);
        return minSum;
    }
}