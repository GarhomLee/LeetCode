https://leetcode.com/problems/iterator-for-combination/

// 思路：Backtracking

// 时间复杂度：dfs(): O(2^combinationLength)?; next(): O(1); hasNext(): O(1)
// 空间复杂度：O(combinationLength)

class CombinationIterator {
    List<String> combinations;
    int index = 0;
    
    private void dfs(String characters, int start, String currStr, int currCount, int totalCount) {
        if (currCount == totalCount) {
            combinations.add(currStr);
            return;
        }
        
        for (int i = start; i < characters.length(); i++) {
            dfs(characters, i + 1, currStr + characters.charAt(i), currCount + 1, totalCount);
        }
    }
    
    public CombinationIterator(String characters, int combinationLength) {
        combinations = new ArrayList<>();
        dfs(characters, 0, "", 0, combinationLength);
        Collections.sort(combinations);
    }
    
    public String next() {
        return combinations.get(index++);
    }
    
    public boolean hasNext() {
        return index < combinations.size();
    }
}