https://leetcode.com/problems/the-k-strongest-values-in-an-array/

idea: Customized Sort
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    public int[] getStrongest(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);
        int median = arr[(n - 1) / 2];
        
        Integer[] sorted = Arrays.stream( arr ).boxed().toArray( Integer[]::new );
        Arrays.sort(sorted, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int abs1 = Math.abs(a - median), abs2 = Math.abs(b - median);
                return abs1 == abs2 ? a - b : abs1 - abs2;
            }
        });
        
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = sorted[n - 1 - i];
        }
        return ret;
    }
}
