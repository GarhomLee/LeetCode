https://leetcode.com/problems/reconstruct-a-2-row-binary-matrix/

思路：Greedy
        
时间复杂度：O(n)
空间复杂度：O(n)
优化：先存在array里，然后再存进List
犯错点：1.细节错误：如果在sum==1时总是先安排upper，因为在sum==2时要同时安排upper和lower，那么可能造成u<0或l<0
            导致提前结束，但实际上lower还能安排在sum==1的位置。
        2.细节错误：可能会出现多个sum==2但upper或lower不能同时满足的情况，所以要增加u<0或l<0的判断。

class Solution {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> res = new ArrayList<>();
        int[][] arr = new int[2][colsum.length];
        
        int total = 0;
        int u = upper, l = lower;  // remaining elements in upper and lower
        for (int i = 0; i < colsum.length; i++) {
            int sum = colsum[i];
            total += sum;
            if (sum == 2) {
                arr[0][i] = 1;
                arr[1][i] = 1;
                u--;
                l--;
            } //else if (sum == 1 && u > 0)  // {Mistake 1}
            else if (sum == 1 && u > l) {  // {Correction 1}
                arr[0][i] = 1;
                u--;
            }  //else if (sum == 1 && l > 0)  // {Mistake 1}
            else if (sum == 1 && u <= l) {  // {Correction 1}
                arr[1][i] = 1;
                l--;
            }
            
            // {Mistake 2}
            if (u < 0 || l < 0) {
                return res;
            }  // {Correction 2}
        }
        
        if (total != upper + lower) {
            return res;
        }
        
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        for (int i = 0; i < colsum.length; i++) {
            res.get(0).add(arr[0][i]);
        }
        for (int i = 0; i < colsum.length; i++) {
            res.get(1).add(arr[1][i]);
        }
        return res;
    }
}