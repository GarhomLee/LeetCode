https://leetcode.com/problems/find-all-anagrams-in-a-string/

// 涉及到substring问题，大概率可以用sliding window的模板。
// 1）维护一个ASCII码长度的数组，存储p中出现的字符及出现的次数
// 2）维护total变量，表示p中字符总数
// 3）right指针扫描数组，如果pCount > 0，表示在p中出现过，那么total--。将遇到的元素的pCount--。
// 4）total == 0时，表示[left ... right]之间的substring包含了p中所有字符，开始逐渐缩小window
// 5）将遇到的元素的pCount++。如果pCount > 0，说明是在p中出现的元素，那么total++，下一次循环将不会进行
// 6）当循环仍在进行时，必定有right - left + 1 >=  p.length()。如果right - left + 1 == p.length()，
//     说明从left - 1开始到right的substring是包含了p中所有字符的anagram，加入list。
//     反证法：如果total > 0，或者right - left + 1 < p.length()，那么从left到right必然不包含p中所有字符。

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<Integer>();
        int[] pCount = new int[128];
        for (char c: p.toCharArray()) pCount[c]++;
        int total = p.length();
        char[] sArray = s.toCharArray();
        
        int left = 0, right = 0;
        while (right < sArray.length) {
            char c = sArray[right];
            if (pCount[c] > 0) total--;
            pCount[c]--;
            right++;
            
            while (total == 0) {
                pCount[sArray[left]]++;
                if (pCount[sArray[left]] > 0) total++;
                left++;
                if (right - left + 1 == p.length()) list.add (left - 1);
            }
        }
        return list;
    }
}