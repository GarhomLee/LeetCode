https://leetcode.com/problems/largest-rectangle-in-histogram/

// 类似402. Remove K Digits，316. Remove Duplicate Letters,构建单调非减序列。利用Stack，有两种不同写法/

// 写法一：视频解说：https://www.youtube.com/watch?v=TH9UaZ6JGcA
// 1）利用Stack从低到高存储heights【对应的index】。维护maxArea作为最终返回结果。
// 2）right指针从0开始，到heights.length结束【包含heights.length】
// 3）维护临时变量currHeight表示在right位置的高度，注意【当right == heights.length时currHeight要设为0】，使得一定能够进入接下来的while循环
// 4）如果当前的currHeight比Stack的top中的height小，那么需要出栈。维护left指针作为求取area的左边界，这时需要做一个判断，如果出栈后栈空，说明
//     这个元素是最后一个元素，且【高度一定最小】，所以left为最左即为0；否则为出栈后留在栈顶的元素+1
// 5）根据出栈拿到的元素heights[topIndex]，左边界left和右边界right更新maxArea
// 6）循环结束后，【栈为空，或栈顶元素的高度比right指向的元素高度currHeight小】，那么将【right这个index入栈】

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int right = 0; right <= heights.length; right++) {
            int currHeight = right == heights.length? 0 : heights[right];  // when right==heights.length, currHeight will be 0 and the following while loop will be forced to start
            while (!stack.isEmpty() && heights[stack.peek()] > currHeight) {
                int topIndex = stack.pop();
                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                maxArea = Math.max(maxArea, heights[topIndex] * (right - left));
            }
            stack.push(right);
        }
        return maxArea;
    }
}

// 优化：利用数组+top指针模拟Stack
// 犯错点：1.heights.length==0的corner case要考虑，要么单独讨论，要么把stack数组的长度设长一些

class Solution {
    public int largestRectangleArea(int[] heights) {
        //int[] stack = new int[heights.length];  // {Mistake 1: corner case heights.length==0, where stack[++top] = right will be out of boundary}
        //if (heights.length == 0) return 0;  // {Correction 1}
        int[] stack = new int[heights.length + 10];  // {Correction 1}
        int top = -1;
        int maxArea = 0;
        for (int right = 0; right <= heights.length; right++) {
            int currHeight = right == heights.length? 0 : heights[right];  // when right==heights.length, currHeight will be 0 and the following while loop will be forced to start
            while (top >= 0 && currHeight < heights[stack[top]]) {
                int height = heights[stack[top--]], left = top >= 0 ? stack[top] + 1: 0;
                maxArea = Math.max(maxArea, height * (right - left));
            }
            stack[++top] = right;
        }
        return maxArea;
    }
}


// 写法二：用Stack同时存【高度和index的配对】这两个信息，构建单调非减序列。
//        如果遇到当前heights[right]比stack.top的height小，那么右边界就是当前的right，左边界是stack.top的【前一个元素】的【index+1】。
//        如果没有前一个元素，那么stack.top为当前最低点，所以从index=0开始都可以用来计算面积。
//        遍历结束后，按同样的思路处理Stack中的单调非减序列。
// 犯错点：1.如果用Stack来先存height，再存index right，那么在求左边界时要用到前一个元素，不方便得到前一个元素的index
//        2.左边界不是stack.top的index，是stack.top的【前一个元素】的【index+1】

class Solution {
    public int largestRectangleArea(int[] heights) {
        //Stack<Integer> stack = new Stack();  // {Mistake 1: do not store height and its index as two separate integers}
        Stack<int[]> stack = new Stack();  // {Correction 1: store height and its index as a pair in an int array}
        int maxArea = 0;
        for (int right = 0; right < heights.length; right++) {
            while (!stack.isEmpty() && heights[right] < stack.peek()[0]) {  // maintain a monotonic increasing sequence in the Stack
                int[] pair = stack.pop();
                //int height = pair[0], left = stack.isEmpty() ? 0 : pair[1];  // {Mistake 2}
                int height = pair[0], left = stack.isEmpty() ? 0 : stack.peek()[1] + 1;  // {Correction 2: the left boundary should be the previous element in the Stack}
                int area = height * (right - left);
                maxArea = Math.max(maxArea, area);
            }
            stack.push(new int[]{heights[right], right});
        }
        
        while (!stack.isEmpty()) {
            int[] pair = stack.pop();
            //int height = pair[0], left = stack.isEmpty() ? 0 : pair[1];  // {Mistake 2}
            int height = pair[0], left = stack.isEmpty() ? 0 : stack.peek()[1] + 1;  // {Correction 2: the left boundary should be the previous element in the Stack}
            int area = height * (heights.length - left);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}