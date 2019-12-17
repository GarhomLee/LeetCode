https://leetcode.com/problems/online-majority-element-in-subarray/

解法一：Boyer–Moore majority vote algo
时间复杂度：O(n)
空间复杂度：O(1)

class MajorityChecker {
    int[] arr;
    
    public MajorityChecker(int[] arr) {
        this.arr = arr;
    }
    
    public int query(int left, int right, int threshold) {
        int candidate = arr[left];
        int count = 1;
        for (int i = left + 1; i <= right; i++) {
            if (count == 0) {
                candidate = arr[i];
            }
            
            count += candidate == arr[i] ? 1 : -1;
        }
        
        count = 0;
        for (int i = left; i <= right; i++) {
            count += candidate == arr[i] ? 1 : 0;
        }
        
        return count >= threshold ? candidate : -1;
    }
}


解法二：Binary Search