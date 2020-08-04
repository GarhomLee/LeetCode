https://leetcode.com/problems/find-the-index-of-the-large-integer/

idea: Binary Search
time complexity: O(log n)
space complexity: O(1)

class Solution {
    public int getIndex(ArrayReader reader) {
        int low = 0, high = reader.length() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2, midLeft = mid, midRight = mid + 1;
            if ((high - low + 1) % 2 == 1) {
                midLeft = mid - 1;
            }
            
            int flag = reader.compareSub(low, midLeft, midRight, high);
            if (flag == 0) {
                return mid;
            } else if (flag > 0) {
                high = midLeft;
            } else {
                low = midRight;
            }
        }
        
        return low;
    }
}