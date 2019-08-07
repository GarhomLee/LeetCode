https://leetcode.com/problems/construct-the-rectangle/

// 思路：Math，观察规律。
//         根据题意可以发现，将area的平方根取整，这个数再求平方会小于等于area。这个数不适合做length，
//         但是【可以做width】。
//         接下来找到一个length，使得length * width == area且length >= width。因此只需要将width
//         逐渐递减找到第一个area % width == 0即可。
// 时间复杂度：O(sqrt(n))
// 空间复杂度：O(1)

class Solution {
    public int[] constructRectangle(int area) {
        int width = (int) Math.sqrt(area); 
        while (area % width != 0) {
            width--;
        }
        
        return new int[]{area / width, width};
    }
}