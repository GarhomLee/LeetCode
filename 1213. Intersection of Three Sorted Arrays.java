https://leetcode.com/problems/intersection-of-three-sorted-arrays/

// 解法一：Binary Search
// 时间复杂度：O(n1 * (log n2 + log n3)), n1=arr1.length, n2=arr2.length, n3=arr3.length
// 空间复杂度：O(1)

class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> res = new ArrayList<>();
        for (int n: arr1) {
            int index2 = Arrays.binarySearch(arr2, n);
            int index3 = Arrays.binarySearch(arr3, n);
            if (index2 >= 0 && index3 >= 0) {
                res.add(n);
            }
        }
        
        return res;
    }
}


解法二：Three pointers
时间复杂度：O(max(n1, n2, n3)), n1=arr1.length, n2=arr2.length, n3=arr3.length
空间复杂度：O(1)


解法三：Hash Table
时间复杂度：O(n1 + n2 + n3), n1=arr1.length, n2=arr2.length, n3=arr3.length
空间复杂度：O(k), k=num of total elements