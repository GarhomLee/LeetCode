https://leetcode.com/problems/erect-the-fence/

// 思路：Geometry + Math + Stack。视频讲解：https://www.youtube.com/watch?v=ccxFHzL2aok
//         关键点在于不断【向逆时针方向】寻找到当前点Point的最优的convex hull。
//         step0:特殊情况，如果点的个数小于等于3个，不能形成有效凸多边形或只能形成一种凸多边形，直接返回。
//         step1:将点的int[]表示转为自建class Point，【便于后续的查重去重】
//         step2:将所有点按【x坐标从小到大、y坐标从大到小】排序。y坐标从大到小排序是为了遍历的每个新的点都属于潜在最优解，
//              一定可以加入Stack，因此可以从Stack中去除无效的点。
//         step3:维护Stack，存储当前找到的有效点，顶部元素为最近找到的点。
//             从最左边的点开始，不断走逆时针方向，找到所有能形成convex hull下半部分的点。
//             要加入当前搜索的点points[i]来构成有效的路径，需要用到已知的存在Stack里的最近两个点，判断从倒数第二近的点
//             reference走到最近的点point1再到当前点point2（即points[i]）是否走逆时针方向。
//             利用helper method，以reference为原点，分别计算point1到原点的斜率和point2到原点的斜率。
//             如果point2的斜率更大，说明reference->point1->point2为逆时针，符合题意；否则为顺时针，不符合题意。
//             由于用除法求斜率会导致浮点数精度的问题，所以可以用cross product将除法转换成乘法，来比较point1和
//             point2的斜率大小。
//             对于当前点points[i]，将Stack中不能和points[i]组成有效的路径的点全部移除。然后，将points[i]放入Stack。
//         step4:找完所有能形成convex hull下半部分的点后，用同样的逻辑，从后往前遍历points数组，找到所有能形成convex hull
//             上半部分的点。注意，不需要从最右边点points[i-1]开始，因为已经在Stack中，要从倒数第二个点points[i-2]开始。
//         step5:用HashSet去除可能重复的点（如points[0]）
//         step6:将去重后的结果转换成int[]
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int[][] outerTrees(int[][] ps) {
        /* corner case */
        if (ps.length <= 3) {
            return ps;
        }
        /* convert int[] into object Point */
        Point[] points = new Point[ps.length];
        for (int i = 0; i < ps.length; i++) {
            points[i] = new Point(ps[i][0], ps[i][1]);
        }
        /* sort points array by x ascendingly, and by y descendingly if x coordinate equals */
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return p1.x == p2.x ? p2.y - p1.y : p1.x - p2.x;
            }
        });
        
        Stack<Point> stack = new Stack();
        /* build the lower hull counter-clockwise */
        for (int i = 0; i < points.length; i++) {
            while (stack.size() >= 2 && !isCounterClockWise(stack.get(stack.size() - 2), stack.peek(), points[i])) {
                stack.pop();
            }
            stack.push(points[i]);
        }
        /* build the upper hull counter-clockwise */
        for (int i = points.length - 2; i >= 0; i--) {  // start from the last second point instead of the last point
            while (stack.size() >= 2 && !isCounterClockWise(stack.get(stack.size() - 2), stack.peek(), points[i])) {
                stack.pop();
            }
            stack.push(points[i]);
        }
        /* deduplication */
        List<Point> list = new ArrayList<>(new HashSet<>(stack));
        /* convert elements in List into res array */
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i][0] = list.get(i).x;
            res[i][1] = list.get(i).y;
        }
        
        return res;
    }
    
    /** return true if point2 is on the left side (same or more counter clockwise) of vector (reference, point1), or false otherwise */
    private boolean isCounterClockWise(Point reference, Point point1, Point point2) {
        /* use equation of cross product instead of slope calculation by division to avoid floating point accuracy problem */
        return (point1.y - reference.y) * (point2.x - reference.x) - (point2.y - reference.y) * (point1.x - reference.x) <= 0;
    }
    
    class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}