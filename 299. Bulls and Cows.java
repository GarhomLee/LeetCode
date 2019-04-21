https://leetcode.com/problems/bulls-and-cows/

// 解法一：
// 1）维护count数组，表示secret中数字的出现次数
// 2）遍历guess，如果相同位置的元素相同，bulls++;此时判断count[g - '0']是否大于0，如果大于0，count[g - '0']--；如果小于等于0，说明
//     之前加到了cows里，所以cows--
// 3）如果相同位置的元素不同，但guess中的元素在count中出现过且大于0，那么找到了一个mismatch，所以cows++，同时count[g - '0']--

class Solution {
    public String getHint(String secret, String guess) {
        int[] count = new int[10];
        int bulls = 0, cows = 0;
        for (char c: secret.toCharArray()) {
            count[c - '0']++;
        }
        for (int i = 0; i < guess.length(); i++) {
            char g = guess.charAt(i), s = secret.charAt(i);
            if (g == s) {
                bulls++;
                if (count[g - '0'] > 0) {
                    count[g - '0']--;
                } else cows--;
            } else if (count[g - '0'] > 0) {
                cows++;
                count[g - '0']--;
            }
        }
        return bulls + "A" + cows + "B";
    }
}

// 解法二：
// 1）维护ACount, BCount分别表示bulls和cows
// 2）维护sCount, gCount分别表示secret和guess中【mismatch元素】的出现次数
// 3）遍历数组，如果s和g相同位置的元素相同，找到了bulls，所以ACount++;否则sCount, gCount分别自加
// 4）遍历完后，还需要确定cows，只需遍历sCount和gCount并在每个位置取较小值即可

class Solution {
    public String getHint(String secret, String guess) {
        int length = secret.length(), ACount = 0, BCount = 0;
        int[] sCount = new int[10], gCount = new int[10];
        
        for (int i = 0; i < length; i++) {
            char s = secret.charAt(i), g = guess.charAt(i);
            if (s == g) ACount++;
            else {
                sCount[s - '0']++;
                gCount[g - '0']++;
            }
        }
        
        for (int i = 0; i < 10; i++) {
            BCount += Math.min(sCount[i], gCount[i]);
            
        }
        return ACount + "A" + BCount + "B";
    }
}