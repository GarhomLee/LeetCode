

时间复杂度：O(max(A.length, log K))
空间复杂度：O(max(A.length, log K))

class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList<>();
        int carry = 0;
        for (int i = A.length - 1; i >= 0 || K > 0; i--, K /= 10) {
            int digit = i >= 0 ? A[i] : 0;
            digit += K % 10 + carry;
            res.add(0, digit % 10);
            carry = digit / 10;
        }
        
        if (carry > 0) {
            res.add(0, carry);
        }
        return res;
    }
}