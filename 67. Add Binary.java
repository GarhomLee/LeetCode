https://leetcode.com/problems/add-binary/

// 两个数（或数组）相加的模板。

class Solution {
    public String addBinary(String a, String b) {
        char[] aArray = a.toCharArray(), bArray = b.toCharArray();
        int i = a.length() - 1, j = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) sum += aArray[i--] - '0';
            if (j >= 0) sum += bArray[j--] - '0';
            sb.insert(0, sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.insert(0, carry);
        return sb.toString();
    }
}