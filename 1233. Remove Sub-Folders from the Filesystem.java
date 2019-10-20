https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/

思路：遍历 + String.startsWith()

时间复杂度：O(n * k), n=folder.length, k=average length of each String
空间复杂度：O(n)

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        List<String> res = new ArrayList<>();
        Arrays.sort(folder);
        int left = 0;
        while (left < folder.length) {
            int right = left + 1;
            while (right < folder.length && folder[right].startsWith(folder[left])
                   && folder[right].charAt(folder[left].length()) == '/') {
                right++;
            }
            
            res.add(folder[left]);
            left = right;
        }
        
        return res;
    }
}