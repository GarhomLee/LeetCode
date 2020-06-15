https://leetcode.com/problems/sum-of-subarray-minimums/solution/

solution1: Monotonic Queue
    -Maintain an increasingly Monotonic Queue with current last index i and any prev index j, 
     where mq[j] indicates the min element in A[j:i].
    -With each A[i], loop to calculate the sum in Monotonic Queue.
    -Problem: TLE 
time complexity: O(n^2) -> TLE
space complexity: O(n)

class Solution {
    final int MOD = 1_000_000_007;
    
    public int sumSubarrayMins(int[] A) {
        int res = 0, n = A.length;
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            while (top >= 0 && A[stack[top]] >= A[i]) {
                top--;
            }
            
            stack[++top] = i;
            for (int j = top; j >= 0; j--) {
                int prevPos = j == 0 ? -1 : stack[j - 1];
                res += (A[stack[j]] * (stack[j] - prevPos)) % MOD;
                res %= MOD;
            }
        }
        
        return res;
    }
}


solution2: Monotonic Queue + Math + Info Cache. Referring to: https://leetcode.com/problems/sum-of-subarray-minimums/solution/
    -In addition to Monotonic Queue, use a dot product to cache and update the sum in mq[0:i-1].
    -If the index pops out from mq, update dot product by decreasing the product of element and the its spanning length.
    -When adding new A[i], update dot product by increasing the product of element and the its spanning length.
time complexity: O(n)
space complexity: O(n)

class Solution {
    final int MOD = 1_000_000_007;
    
    public int sumSubarrayMins(int[] A) {
        int res = 0, n = A.length, dot = 0;
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            int prevPos = -1;
            while (top >= 0 && A[stack[top]] >= A[i]) {
                prevPos = top == 0 ? -1 : stack[top - 1];
                dot -= A[stack[top]] * (stack[top] - prevPos);
                top--;
            }
            
            stack[++top] = i;
            prevPos = top == 0 ? -1 : stack[top - 1];
            dot += A[stack[top]] * (stack[top] - prevPos);
            res += dot % MOD;
            res %= MOD;
            
        }
        
        return res;
    }
}