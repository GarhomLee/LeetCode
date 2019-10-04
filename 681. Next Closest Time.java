https://leetcode.com/problems/next-closest-time/

// 思路：Brute Force，尝试所有数字的可能组合。
// 时间复杂度：O(4^4)=O(1)
// 空间复杂度：O(1)
// 犯错点：1.细节错误：如果time中只有一个数字，如"00:00"，"11:11"，那么直接返回time即可。
//         2.细节错误：需要排除掉time本身后，才能进行比较。
//         3.题意理解错误：next closest time需要比当前的time更大，如果更小，说明时间到了第二天。

class Solution {
    public String nextClosestTime(String time) {
        Set<Character> set = new HashSet<>();
        for (char c: time.toCharArray()) {
            if (c == ':') continue;
            
            set.add(c);
        }
        
        // {Mistake 1}
        if (set.size() == 1) {
            return time;
        }  // {Correction 1}
        
        String minStr = "00:00";
        int min = Integer.MAX_VALUE;
        for (char c1: set) {
            for (char c2: set) {
                for (char c3: set) {
                    for (char c4: set) {
                        String curr = "" + c1 + c2 + ":" + c3 + c4;
                        //if (!isValid(curr)) continue;  // {Mistake 2}
                        if (curr.equals(time) || !isValid(curr)) continue;  // {Correction 2}
                        
                        int compare = compare(curr, time);
                        if (compare < min) {
                            minStr = curr;
                            min = compare;
                        }
                    }
                }
            }
        }
        
        return minStr;
    }
    
    private int compare(String s1, String s2) {
        String[] split1 = s1.split(":"), split2 = s2.split(":");
        int h1 = Integer.parseInt(split1[0]), m1 = Integer.parseInt(split1[1]);
        int h2 = Integer.parseInt(split2[0]), m2 = Integer.parseInt(split2[1]);
        //return Math.abs((h1 * 60 + m1) - (h2 * 60 + m2));  // {Mistake 3}
        int compare = (h1 * 60 + m1) - (h2 * 60 + m2);  // {Correction 3}
        if (compare < 0) {
            compare += 1440;
        }
        return compare;
    }
    
    private boolean isValid(String s) {
        String[] split = s.split(":");
        return Integer.parseInt(split[0]) < 24 && Integer.parseInt(split[1]) < 60;
    }
}