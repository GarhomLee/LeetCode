https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/

idea1: Use two for loops. For each position, calculate distance from all other positions.
time comp: O(n^2)
space comp: O(1)


idea2: Cache the result calculated from the left as well as from the right.
    leftMove[i] should equals to total #moves to position i-1 from its left (which is leftMove[i-1]), plus #moves to move 
    all current balls to position i (which is count).
    Same for rightMove.
time comp: O(n)
space comp: O(n)

class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] leftMove = new int[n], rightMove = new int[n];    // leftMove[i]: total #moves to position i from its left
        
        int count = boxes.charAt(0) - '0';  // #'1's at its left
        for (int i = 1; i < n; i++) {
            leftMove[i] = leftMove[i - 1] + count;
            count += boxes.charAt(i) - '0';
        }
        
        count = boxes.charAt(n - 1) - '0';
        for (int i = n - 2; i >= 0; i--) {
            rightMove[i] = rightMove[i + 1] + count;
            count += boxes.charAt(i) - '0';
        }
        
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = leftMove[i] + rightMove[i];
        }
        
        return ret;
    }
}