https://leetcode.com/problems/trapping-rain-water/

// 属于two pointers问题。
// 1）维护两个pointer：left，right；同时维护两个变量：leftMax，表示当前能得到的最大height[left]；rightMax，表示当前能得到的最大height[right]
// 2）比较height[left]和height[right]，由于短板效应，最大盛水量由短板决定，所以先移动较小的height（极端情况：height从左至右递增）。
// 3）移动后，比较leftMax（或rightMax）和height[left]（或height[right]），并更新leftMax（或rightMax）为比较后的较大值，那么在这一步中可承载的水的unit即为leftMax - height[left]（或rightMax - height[right]）。
// 4）更新pointer，直至left和right相遇。

class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, res = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                res += leftMax - height[left++];
            } else {
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right--];
            }
        }
        return res;
    }
}

// 做完407. Trapping Rain Water II回头在做本题，利用minHeap的思想。
// 先将端点放入minHeap，然后取头部元素（较小的元素）。如果比当前maxHeight大，更新maxHeight；否则，当前元素处于低点，更新water。
// 然后，将当前元素的所有neighbor放进minHeap。

class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n == 1) return 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((i, j) -> height[i] - height[j]);
        boolean[] visited = new boolean[n];
        pq.offer(0);
        visited[0] = true;
        pq.offer(n - 1);
        visited[n - 1] = true;
        
        int currMax = 0, ret = 0;
        while (!pq.isEmpty()) {
            int idx = pq.poll();
            currMax = Math.max(currMax, height[idx]);
            ret += currMax - height[idx];
            
            int leftIdx = idx - 1, rightIdx = idx + 1;
            if (leftIdx >= 0 && !visited[leftIdx]) {
                pq.offer(leftIdx);
                visited[leftIdx] = true;
            }
            if (rightIdx < n && !visited[rightIdx]) {
                pq.offer(rightIdx);
                visited[rightIdx] = true;
            }
        }
        
        return ret;
    }
}

// 不用PriorityQueue的写法
// 犯错点：1.先处理完当前元素，才能挪动pointer（表示将neighbor放进minHeap）

class Solution {
    public int trap(int[] height) {
        if (height.length == 0) return 0;
        
        int left = 0, right = height.length - 1;
        int maxHeight = -1;
        int water = 0;
        while (left < right) {  // it's ok to use left <= right
            if (height[left] < height[right]) {
                //left++;  // {Mistake 1}
                maxHeight = Math.max(maxHeight, height[left]);
                //water += maxHeight - height[left];  // {Mistake 1}
                water += maxHeight - height[left++];  // {Correction 1}
            } else {
                //right--;  // {Mistake 1}
                maxHeight = Math.max(maxHeight, height[right]);
                //water += maxHeight - height[right];  // {Mistake 1}
                water += maxHeight - height[right--];  // {Correction 1}
            }
        }
        return water;
    }
}


idea2: DP

class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] fromLeft = new int[n], fromRight = new int[n];
        fromLeft[0] = height[0];
        fromRight[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            // non-decreasing max height from left/right
            fromLeft[i] = Math.max(fromLeft[i - 1], height[i]);
            fromRight[n - i - 1] = Math.max(fromRight[n - i], height[n - i - 1]);
        }
        
        int ret = 0;
        for (int i = 0; i < n; i++) {
            // find the overlap in min
            ret += Math.min(fromLeft[i], fromRight[i]) - height[i];
        }
        
        return ret;
    }
}