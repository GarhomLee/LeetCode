https://leetcode.com/problems/defanging-an-ip-address/

// 思路：String.replaceAll()
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public String defangIPaddr(String address) {
        return address.replaceAll("\\.", "[.]");
    }
}