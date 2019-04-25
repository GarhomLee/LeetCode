https://leetcode.com/problems/largest-rectangle-in-histogram/

// 视频解说：https://www.youtube.com/watch?v=TH9UaZ6JGcA
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
            int currHeight = right == heights.length? 0 : heights[right];
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