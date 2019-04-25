https://leetcode.com/problems/maximal-rectangle/

// 难度极大，需要多练。解法有多种。

// 解法一：DP，维护3个一维数组,时间复杂度为O(m * n) = O(N^2)
// 1）维护3个一维数组：height表示从上到下包含当前元素能形成的最大高度；left表示【和同样位置的height相关的矩形的左边界】；
//     right表示【和同样位置的height相关的矩形的右边界（不包含）】，初始化为【colLen】。
// 2）对于每一行，更新这三个一维数组
// 3）维护两个临时变量：currLeft表示当前行中【从左到右】最近的1的位置；currRight表示当前行中【从右到左】最近的1的位置的【下一位】
// 4）【从左往右】更新left数组：如果当前matrix元素为1，left[col]为来自当前行的currLeft和继承自上一行的left[col]的【较大值】，以保证一定能形成矩阵，且和height[col]相对应；
//     如果当前matrix元素为0，left[col]为0，currLeft为col + 1，因为最近的1一定至少从下一位开始。
// 5）【从右往左】更新right数组：如果当前matrix元素为1，right[col]为来自当前行的currRight和继承自上一行的right[col]的【较小值】，以保证一定能形成矩阵，且和height[col]相对应；
//     如果当前matrix元素为0，rightt[col]为colLen，currRight为col，因为最近的1一定至多到当前位置为止【不包含当前位置】。
// 6）更新完三个数组，再一次遍历这一列，根据面积公式更新maxArea。

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0? 0 : matrix[0].length;
        int[] height = new int[colLen], left = new int[colLen], right = new int[colLen];
        int maxArea = 0;
        Arrays.fill(right, colLen);
        for (int row = 0; row < rowLen; row++) {
            int currLeft = 0, currRight = colLen;
            for (int col = 0; col < colLen; col++) {
                if (matrix[row][col] == '1') height[col]++;
                else height[col] = 0;
            }
            for (int col = 0; col < colLen; col++) {
                if (matrix[row][col] == '1') left[col] = Math.max(left[col], currLeft);
                else {
                    left[col] = 0;
                    currLeft = col + 1;
                }
            }
            for (int col = colLen - 1; col >= 0; col--) {
                if (matrix[row][col] == '1') right[col] = Math.min(right[col], currRight);
                else {
                    right[col] = colLen;
                    currRight = col;
                }
            }
            for (int col = 0; col < colLen; col++) {
                maxArea = Math.max(maxArea, (right[col] - left[col]) * height[col]);   
            }
        }
        return maxArea;
    }
}

// 解法二：DP，利用Stack（类似84. Largest Rectangle in Histogram），时间复杂度变为O(m * n) = O(N^2)
// 视频详解：https://www.youtube.com/watch?v=g8bSdXCG-lA
// 1）维护一维heights数组，表示从第一行到当前行所能组成的包含当前行元素在内的histogram的高度
// 2）如果当前元素为'0'，histogram的高度只能为0，所以heights[col] = 0;否则heights[col]++;
// 3）更新完一行元素后，利用求取histogram最大面积的方法，求得当前的最大面积，更新maxArea

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0? 0 : matrix[0].length;
        int maxArea = 0;
        int[] heights = new int[colLen];
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (matrix[row][col] == '0') heights[col] = 0;
                else heights[col]++;
            }
            maxArea = Math.max(maxArea, findArea(heights));
        }
        return maxArea;
    }
    private int findArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int right = 0; right <= heights.length; right++) {
            int currHeight = right == heights.length? 0 : heights[right];
            while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                int topIndex = stack.pop();
                int left = stack.isEmpty()? 0 : stack.peek() + 1;
                maxArea = Math.max(maxArea, heights[topIndex] * (right - left));
            }
            stack.push(right);
        }
        return maxArea;
    }
}


// 解法三：DP，计数法，是解法一的简化版，只维护height数组，所以时间复杂度变为O(m * n * m) = O(N^3)
// 详见LeetCode disucss：https://leetcode.com/problems/maximal-rectangle/discuss/225690/Java-solution-with-explanations-in-Chinese

public static int maximalRectangle3(char[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[i][j] == '1') {
                matrix[i][j] = (char) (matrix[i][j-1] + 1);
            } else {
                matrix[i][j] = '0';
            }
        }
    }
    int max = 0;
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            int min = matrix[i][j] - '0';
            if (min > 0) {
                max = Math.max(max, min);
                for (int k = i-1; k >= 0 && matrix[k][j] != '0'; k--) {
                    min = Math.min(min, matrix[k][j] - '0');
                    max = Math.max(max, min * (i-k+1));
                }
            }
        }
    }
    return max;
}