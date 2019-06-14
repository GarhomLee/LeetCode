https://leetcode.com/problems/largest-number/

// 思路：将nums[i]转换成String，重点在于String的比较规则：
//     "3" > "30"
//     "3" == "33"
//     "3" < "34"
//     实际上，不需要将相同前缀移除再比较，而是可以【将String a和b分别concatenate成ab和ba，然后比较ab和ba】：
//     "330" > "303"
//     "333" == "333"
//     "334" < "343"
//     这样的两个String长度就会相等。然后根据比较结果从大到小排列【原String】即可。
// 犯错点：1.本题特殊的比较规则，比较的是两个String的相互concatenation结果
//        2.如果有leading 0s，要删除。注意从头删除0的写法。

class Solution {
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // {Mistake 1}
                String s1s2 = s1 + s2, s2s1 = s2 + s1;  // {Correction 1}
                return s2s1.compareTo(s1s2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String s: strs) {
            sb.append(s);
        }
        /*for (int i = 0; i < sb.length() - 1 && sb.charAt(i) == '0'; i++) {
            sb.deleteCharAt(i);
        }*/  // {Mistake 2: wrong way to delete leading 0s}
        while (sb.length() > 1 && sb.charAt(0) == '0') {  // {Correction 2: the correct way to delete leading 0s}
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}