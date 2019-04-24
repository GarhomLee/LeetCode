https://leetcode.com/problems/minimum-window-substring/submissions/

// 属于sliding window的经典题目。解法可分为两种：可以用HashMap储存T中出现的字符和次数，那么在S的遍历中可以不考虑其他字符；也可以用长度为128（或256）的数组，这时要注意除了T中出现的字符，其他字符的次数最大为0。

// 解法一：HashMap
// 1）遍历T，在HashMap中key为T的字符，value为字符出现的次数。维护一个total变量，表示T字符总数。total的意义：和HashMap中的value共同确定是不是找到了所有的所要求字符。如果不用total，只能不停遍历key来询问是不是所有value都<=0（即所有字符都出现了），效率很低。
// 2）维护left和right两个pointer，作为sliding window的边界；同时维护minLen来获得最短长度的信息，维护minLeft，minRight找到最短长度对应的substring起止位置
// 3）right右移，遇到T中的字符，对应的value--。如果这时value >= 0，说明还没找完（或刚找完）所有字符，那么total--
// 4）当total == 0时，说明在window当中已经包含了所有T的字符，那么开始通过移动left来尝试缩小window
// 5）进行while循环，left左移。遇到T中的字符，对应的value++。如果这时value >= 0，说明还没找完（或刚找完）所有字符，那么total++，这时候total将会大于0，window不满足要求，会跳出循环
// 6）比较当前找到的window和minLen
// 7）Time complexity: O(S的长度)，因为每个元素被扫描了两遍; Space complexity: O(T的长度)

class Solution {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) return "";
        String minWindow = "";
        //int[] mapping = new int[128];
        char[] sArray = s.toCharArray(), tArray = t.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
         
        for (int i = 0; i < tArray.length; i++) map.put(tArray[i], map.getOrDefault(tArray[i], 0) + 1);
        int total = tArray.length;
         
        int left = 0, right = 0;
        int minLen = sArray.length + 10, minLeft = -1, minRight = -1;
        while (right < sArray.length) {
            if (map.containsKey(sArray[right])) {
                if (map.get(sArray[right]) > 0) total--;
                map.put(sArray[right], map.get(sArray[right]) - 1);
            }
            right++;
                 
            while (left < right && total == 0) {
                if (map.containsKey(sArray[left])) {
                    map.put(sArray[left], map.get(sArray[left]) + 1);
                    if (map.get(sArray[left]) > 0) total++;
                }
                left++;
                     
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left - 1;
                    minRight = right;
                    //System.out.println(minLeft + ", " + minRight + ", minLen: " + minLen);
                }
            }
        }
        if (minLeft != -1 && minRight != -1) minWindow = s.substring(minLeft, minRight);
        return minWindow;
    }
}

// 解法二：数组，套用sliding window模板。
// 1）基本过程和解法一相同。唯一不同点在于，没有查找S字符是否在T中，而是通过对应的数组value来判断。这是因为，只有T中出现的字符value才会大于0，其他字符的次数初始都为0。只有数组value > 0，才能对total进行++和--操作，原因还是在于total记录的是与T中的字符有关的信息。
// 2）Time complexity: O(S的长度)，因为每个元素被扫描了两遍; Space complexity: O(1)，与长度无关

class Solution {
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) return "";
        int[] count = new int[128];
        int total = 0;
        for (char c: t.toCharArray()) {
            count[c]++;
            total++;
        }
        int minLeft = 0, minRight = 0, minLen = s.length() + 10;
        for (int left = 0, right = 0; right < s.length(); right++) {
            if (count[s.charAt(right)] > 0) total--;
            count[s.charAt(right)]--;
            while (total == 0) {
                if (right - left + 1 < minLen) {
                    minLeft = left;
                    minRight = right + 1;
                    minLen = right - left + 1;
                } 
                count[s.charAt(left)]++;
                if (count[s.charAt(left)] > 0) total++;
                left++;
            }
        }
        return minLen > s.length() ? "" : s.substring(minLeft, minRight);
    }
}