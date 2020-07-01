https://leetcode.com/problems/reveal-cards-in-increasing-order/

idea: Simulation. Referring to: https://leetcode.com/problems/reveal-cards-in-increasing-order/discuss/201574/C%2B%2B-with-picture-skip-over-empty-spaces

class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        int[] res = new int[n];
        Arrays.sort(deck);
        boolean skip = false;
        int i = 0, j = 0;
        while (i < n) {
            while (res[j] > 0) {
                j = (j + 1) %n;
            }
            
            if (!skip) {
                res[j] = deck[i++];
            } else {
                j = (j + 1) %n;
            }
            
            skip = !skip;
        }
        
        return res;
    }
}