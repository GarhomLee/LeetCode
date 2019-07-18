https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/

// 解法一：Binary Search。可以看作是528. Random Pick with Weight的follow-up。
//         因为所有矩形不重叠，所以可以用辅助数组记录累加和。
//         先随机得到某一个面积，找到这个面积对应的index，然后再随机长度和宽度，求得最终的横纵坐标。
// 注意：题目要找的不是面积，而是矩形范围内的点数，因此[1,0,3,0]虽然是一条线，但是是3个点，也要算进去。所以，在求和
//     的时候，矩形范围内的点数是(x2-x1+1)*(y2-y1+1)。
// 犯错点：1.Java内置函数使用错误：Arrays.binarySearch()只有在能找到target时才会返回正数index，否则返回一个负数，
//             根据这个负数来得到可以插入的位置。

class Solution {
    Random rand;
    int[] area;
    int sum;
    int[][] rects;
    
    public Solution(int[][] rects) {
        this.rects = rects;
        rand = new Random();
        area = new int[rects.length];
        sum = 0;
        for (int i = 0; i < rects.length; i++) {
            sum += (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
            area[i] = sum;
        }
    }
    
    public int[] pick() {
        int pick = rand.nextInt(sum) + 1;
        int index = Arrays.binarySearch(area, pick);
        // {Mistake 1}
        if (index < 0) index = -index - 1;  // {Correction 1}
        
        int width = rects[index][2] - rects[index][0] + 1;
        int height = rects[index][3] - rects[index][1] + 1;
        int x = rects[index][0] + rand.nextInt(width);
        int y = rects[index][1] + rand.nextInt(height);
        return new int[]{x, y};
    }
}


解法二：TreeMap