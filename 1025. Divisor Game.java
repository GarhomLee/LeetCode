https://leetcode.com/problems/divisor-game/

solution1: Recursion with Memoization
time complexity: O(N)
space complexity: O(N)

class Solution {
    Map<Integer, Boolean> map = new HashMap<>();
    
    private boolean dfs(int N) {
        if (map.containsKey(N)) {
            return map.get(N);
        }
        
        boolean canWin = false;
        for (int i = 1; i <= N / 2; i++) {
            if (N % i == 0 && !dfs(N - i)) {
                canWin = true;
            }
        }
        
        map.put(N, canWin);
        return canWin;
    }
    
    public boolean divisorGame(int N) {
        return dfs(N);
    }
}


solution2: Math


class Solution {
    public boolean divisorGame(int N) {
        return N % 2;
    }
}