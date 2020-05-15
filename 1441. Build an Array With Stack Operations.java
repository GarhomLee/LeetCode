https://leetcode.com/problems/build-an-array-with-stack-operations/

idea: Stack Simulation
    Add "Push" for elements that are kept in target array, and add both 
    "Push" and "Pop" for those discarded.
time complexity: O(n)
space complexity: O(n)

class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> res = new ArrayList<>();
        int curr = 1;
        for (int num : target) {
            while (curr != num) {
                res.add("Push");
                res.add("Pop");
                curr++;
            } 
            
            res.add("Push");
            curr++;
        }
        
        return res;
    }
}