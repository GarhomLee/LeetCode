https://leetcode.com/problems/perfect-rectangle/

// 思路：Math，找图形规律。视频讲解：https://www.youtube.com/watch?v=8JM_dyOu_JY
//         维护几个变量：Set<String> set，记录出现单数次的点转换成的String，即row坐标+","+col坐标；
//         int[] bottomLeft，表示所有点中最左下角的点，初始化为Integer.MAX_VALUE；int[] topRight，
//         表示所有点中最右上角的点，初始化为Integer.MIN_VALUE；area，表示所有小矩形的面积总和。
//         遍历rectangles数组中的所有矩形，得到【四个顶点】，转换成String后如果已经在set中存在就删除，否则
//         就加入set中，维持其中记录的点出现的是单数次（【四个顶点都要判断】）。同时，利用当前小矩形的左下
//         顶点和右上顶点更新全局最左下角的点bottomLeft和全局最右上角的点topRight，并更新area。
//         遍历结束后，符合题意的结果需要满足以下条件：
//         1）出现单数次的点有且只有4个，即set.size() == 4
//         2）小矩形面积和等于bottomLeft和topRight所围成的矩形的和
//         3）【bottomLeft和topRight存在于set中】
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.Java内置数据结构使用错误：无法利用Set.contains()来判断是否有相同的int[]存在，因为Set利用
//             hashCode来判断，而从rectangles数组中构造的点int[]得到的hashCode基本都是不同的。
//             因此，要么转换成String，要么用List
//         2.条件遗漏错误：rectangles数组只提供了每个小矩形的左下顶点和右上顶点，而左上顶点和右下顶点也
//             需要用Set判断出现的次数，所以左上顶点和右下顶点也要构造。
//         3.判断条件遗漏错误：如果最后漏掉判断bottomLeft和topRight是否也在Set中，那么可能会出现这种情况：
//             _____________
//             |           |
//             |___________|

//             _____________
//             |     |     |   
//          (  |_____|_____|  ) X 2
            
//             也符合set.size() == 4和area == bottomLeft和topRight所围成的矩形的和这两个条件。
//             因此，需要加上bottomLeft和topRight也都同时存在于set中的条件。

class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length == 0 || rectangles[0].length == 0) return false;
        
        //Set<int[]> set = new HashSet<>();  // {Mistake 1}
        Set<String> set = new HashSet<>();  // {Correction 1}
        int[] bottomLeft = new int[2];
        Arrays.fill(bottomLeft, Integer.MAX_VALUE);
        int[] topRight = new int[2];
        Arrays.fill(topRight, Integer.MIN_VALUE);
        int area = 0;
        for (int[] point : rectangles) {
            int[] point1 = new int[]{point[0], point[1]};  // bottom left
            int[] point2 = new int[]{point[2], point[3]};  // top right
            // {Mistake 2}
            int[] point3 = new int[]{point[0], point[3]};  // bottom right  // {Correction 2}
            int[] point4 = new int[]{point[2], point[1]};  // top left

            String s1 = point1[0]+","+point1[1];
            String s2 = point2[0]+","+point2[1];
            String s3 = point3[0]+","+point3[1];
            String s4 = point4[0]+","+point4[1];
            
            /* remove the duplicate point, or add the single point */
            updateSet(set, s1);
            updateSet(set, s2);
            updateSet(set, s3);
            updateSet(set, s4);
            
            /* update bottomLeft and topRight */
            updateTwoPoints(point1, bottomLeft, topRight);
            updateTwoPoints(point2, bottomLeft, topRight);
            
            /* update total area */
            area += (point2[0] - point1[0]) * (point2[1] - point1[1]);
        }
        
        // return set.size() == 4 && area == (topRight[0] - bottomLeft[0]) * (topRight[1] - bottomLeft[1]);  // {Mistake 3}
        return set.size() == 4 && set.contains(bottomLeft[0]+","+bottomLeft[1]) && set.contains(topRight[0]+","+topRight[1]) && area == (topRight[0] - bottomLeft[0]) * (topRight[1] - bottomLeft[1]);  // {Correction 3}
    }
    
    private void updateSet(Set<String> set, String s) {
        if (set.contains(s)) {
            set.remove(s);
        } else {
            set.add(s);
        }
    }
    
    private void updateTwoPoints(int[] point, int[] bottomLeft, int[] topRight) {
        if (point[0] <= bottomLeft[0] && point[1] <= bottomLeft[1]) {
            bottomLeft[0] = point[0];
            bottomLeft[1] = point[1];
        } 
        if (point[0] >= topRight[0] && point[1] >= topRight[1]) {
            topRight[0] = point[0];
            topRight[1] = point[1];
        }
    }
}