https://leetcode.com/problems/design-a-leaderboard/

思路：HashMap + TreeMap
        用HashMap记录playerId->score，同时用TreeMap记录score->出现的次数。
时间复杂度：addScore(): O(log n); top: O(k); reset(): O(log n)
空间复杂度：O(n)

class Leaderboard {
    Map<Integer, Integer> map = new HashMap<>();
    TreeMap<Integer, Integer> scores;
    
    public Leaderboard() {
        map = new HashMap<>();
        scores = new TreeMap<>();
    }
    
    public void addScore(int playerId, int score) {
        if (map.containsKey(playerId)) {
            int temp = map.get(playerId);
            scores.put(temp, scores.get(temp) - 1);
            score += temp;
        }
        map.put(playerId, score);
        scores.put(score, scores.getOrDefault(score, 0) + 1);
    }
    
    public int top(int K) {
        int total = 0;
        for (int score : scores.descendingKeySet()) {
            if (K == 0) break;
            
            int times = Math.min(K, scores.get(score));
            total += score * times;
            K -= times;
        }
        
        return total;
    }
    
    public void reset(int playerId) {
        if (map.get(playerId) == 0) return;
        
        int score = map.get(playerId);
        map.put(playerId, 0);
        scores.put(score, scores.get(score) - 1);
        scores.put(0, scores.getOrDefault(0, 0) + 1);
    }
}