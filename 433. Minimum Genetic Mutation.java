https://leetcode.com/problems/minimum-genetic-mutation/

// 对比：和127. Word Ladder思路和代码基本一样。

// 思路：Bidirectional BFS
// 时间复杂度：O(n*4^l/2), n=bank.length, l = len(gene)
// 空间复杂度：O(n)

class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> dict = new HashSet<>(Arrays.asList(bank));
        /* corner case */
        if (!dict.contains(end)) {
            return -1;
        }
        
        Set<String> set1 = new HashSet<>(), set2 = new HashSet<>();
        set1.add(start);
        set2.add(end);
        int step = 1;
        while (!set1.isEmpty() && !set2.isEmpty()) {  // if either Set is empty, they will not share common words */
            if (set1.size() > set2.size()) {  // optimization: swap two Sets only when set1 has more elements
                Set<String> temp = set1;
                set1 = set2;
                set2 = temp;
            }
            
            Set<String> newSet = new HashSet<>();
            for (String gene: set1) {  // always check set1
                StringBuilder sb = new StringBuilder(gene);
                for (int i = 0; i < sb.length(); i++) {  // search for each char
                    char c = sb.charAt(i);  // keep record of the original char
                    for (char temp: "ATCG".toCharArray()) {  // try all possibilities in "ATCG"
                        if (temp == c) continue;
                        
                        sb.setCharAt(i, temp);
                        String s = sb.toString();
                        if (set2.contains(s)) {  // overlap found
                            return step;
                        } else if (dict.contains(s)){  // intermediate found
                            newSet.add(s);
                            dict.remove(s);
                        }
                    }
                    sb.setCharAt(i, c);  // reset
                }
            }
            step++;  // update step
            set1 = newSet;  // update set1
            
        }
        
        return -1;
    }
}