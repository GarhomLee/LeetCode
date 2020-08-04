https://leetcode.com/problems/minimum-swaps-to-arrange-a-binary-grid/

idea: Simulation + Selection Sort + Greedy
    -First count the trailing zeros in each row.
    -Then simlulate the process. From the 0th row the n-1-th, for current row i, use selection sort 
     to find the next qualified row j, and remove row j and insert in position i.
time complexity: O(n^2)
space complexity: O(n)

class Solution {
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        List<Integer> zeroList = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            int count = 0;
            for (int col = n - 1; col >= 0 && grid[row][col] == 0; col--) {
                count++;
            }
            zeroList.add(count);
        }
        
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            int minZero = n - 1 - i;
            while (j < n && zeroList.get(j) < minZero) {
                j++;
            }
            
            if (j == n) {
                return -1;
            }
            
            if (i != j) {
                int count = zeroList.get(j);
                zeroList.remove(j);
                zeroList.add(i, count);
                ret += j - i;
            }
        }
        
        return ret;
    }
}