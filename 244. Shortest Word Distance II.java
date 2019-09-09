https://leetcode.com/problems/shortest-word-distance-ii/

// 对比：243. Shortest Word Distance，本题允许函数被多次调用。

// 思路：HashMap + Two poiners
//         step1:在constructor中，遍历words数组，构建HashMap，key为出现过的字符串，value为List，从小到大
//             依次储存当前字符串出现的位置。
//         step2:遍历从HashMap中得到word1和word2的位置的List，计算位置的绝对差值|list1.get(i)-list2.get(j)|。
//         step3:更新i或j。要使得word1和word2的位置尽量进，那么只需要右移list1.get(i)和list2.get(j)的较小值，
//             也就是说，当list1.get(i) < list2.get(j)，右移i使它们更靠近；否则，右移j使他们更靠近。
// 时间复杂度：shortest(): O(n), n=words.length
// 空间复杂度：O(n), n=words.length

class WordDistance {
    Map<String, List<Integer>> map;
    
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.putIfAbsent(words[i], new ArrayList<>());
            map.get(words[i]).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        int minDist = Integer.MAX_VALUE;
        List<Integer> list1 = map.get(word1), list2 = map.get(word2);
        int len1 = list1.size(), len2 = list2.size();
        int i = 0, j = 0;
        while (i < len1 && j < len2) {
            minDist = Math.min(minDist, Math.abs(list1.get(i) - list2.get(j)));
            if (list1.get(i) < list2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        
        return minDist;
    }
}