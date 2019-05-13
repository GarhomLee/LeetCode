https://leetcode.com/problems/guess-number-higher-or-lower/

// 思路：常规Binary search问题
// 时间复杂度：O(log n)
// 空间复杂度：O(1)

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

      public class Solution extends GuessGame {
        public int guessNumber(int n) {
            int low = 1, high = n;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int guess = guess(mid);
                if (guess == 0) return mid;
                else if (guess > 0) low = mid + 1;
                else high = mid - 1;
            }
            return -1;
        }
    }