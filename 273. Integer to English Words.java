https://leetcode.com/problems/integer-to-english-words/

思路：Math
        每1000分段，分为100，10两段
时间复杂度：O(log n)
空间复杂度：O(log n)
犯错点：1.判断是否需要加THOUSANDS，利用num % 1000是否等于0

class Solution {
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        
        String[] ONES = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] LESS_THAN_TWENTY = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] TENS = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] THOUSANDS = new String[]{"", "Thousand", "Million", "Billion", "Trillion"};
        String SPACE = " ";
        
        StringBuilder sb = new StringBuilder();
        int segment = 0;
        while (num > 0) {
            if (num % 1000 != 0) {
                sb.insert(0, THOUSANDS[segment] + SPACE);
            }
            
            int temp = num % 100;
            if (temp > 0 && temp <= 9) {
                sb.insert(0, ONES[temp] + SPACE);
            } else if (temp >= 10 && temp < 20) {
                sb.insert(0, LESS_THAN_TWENTY[temp - 10] + SPACE);
            } else if (temp >= 20 && temp <= 99) {
                if (temp % 10 > 0) {
                    sb.insert(0, ONES[temp % 10] + SPACE);
                }
                sb.insert(0, TENS[temp / 10] + SPACE);
            }
            
            temp = (num % 1000) / 100;
            if (temp > 0) {
                sb.insert(0, "Hundred" + SPACE);
                sb.insert(0, ONES[temp] + SPACE);
            }
            
            num /= 1000;
            segment++;
            
        }
        return sb.toString().trim();
    }
}