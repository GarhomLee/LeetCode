https://leetcode.com/problems/kth-smallest-instructions/

idea1: Math
time complexity: O((H + V) * max(H, V))
space compleixty: O(1)

idea2: DP

/** Math solution */
class Solution {
    
    public int choose(int n, int k) {
        long numer = 1, denom = 1;
        for (int i = 0; i < k; i++) {
            numer *= (n - i);
            denom *= (k - i);
        }
        
        return (int) (numer/denom);
    }
    
    public String kthSmallestPath(int[] destination, int k) {
        int V = destination[0], H = destination[1];
        StringBuilder sb = new StringBuilder();
        while (H > 0 || V > 0) {
            if (H == 0) {
                for (int i = 0; i < V; i++) {
                    sb.append('V');
                }
                V = 0;
            } else if (V == 0) {
                for (int i = 0; i < H; i++) {
                    sb.append('H');
                }
                H = 0;
            } else {
                int chooseH = choose(H + V - 1, H - 1); // get the num of combinations if the next one puts 'H'
                if (chooseH >= k) {
                    // put 'H' and it is still possible to be the kth
                    sb.append('H');
                    H--;
                } else {
                    // putting 'H' is not enough to be the kth, so it has to put 'V'
                    // and update the remaining k
                    k -= chooseH;
                    sb.append('V');
                    V--;
                }
            }
        }
        return sb.toString();
    }
}