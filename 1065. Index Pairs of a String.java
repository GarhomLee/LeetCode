https://leetcode.com/problems/index-pairs-of-a-string/

// 思路：String.indexOf() + Sort
// 时间复杂度：O(w * t), w=words.length, t=text.length()
// 空间复杂度：O(w * t), w=words.length, t=text.length()

class Solution {
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> list = new ArrayList<>();
        for (String word: words) {
            int len = word.length();
            int index = text.indexOf(word);
            while (index >= 0) {
                list.add(new int[]{index, index + len - 1});
                index = text.indexOf(word, index + 1);
            }
        }
        
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        Arrays.sort(res, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] == i2[0] ? i1[1] - i2[1] : i1[0] - i2[0];
            }
        });
        
        return res;
    }
}