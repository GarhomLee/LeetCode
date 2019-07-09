https://leetcode.com/problems/ipo/

// 思路：PriorityQueue + Greedy
//      利用两个PriorityQueue，存放配对{Capital[i], Profits[i]}。第一个pqCapital是minHeap，靠近顶部的是
//      capital小的配对。第一个pqProfit是maxHeap，靠近顶部的是profit大的配对。
//      外层while循环进行最多k次操作，内层while循环将符合finalCapital > minHeap.top的所有配对都放入maxHeap中。
//      内层循环结束后，【先进行一次判断，如果maxHeap为空，说明没有符合所需capital比finalCapital小的配对】，因此
//      跳出外层循环。
//      然后从maxHeap中拿出顶部元素，加到finalCapital，这里用了Greedy的思想，找局部最优解。maxHeap的顶部元素可能
//      来自于之前几次循环，但必然符合当前capital的条件，因为finalCapital是递增的。
// 犯错点：1.终止条件错误：在选择终止条件时，不需要和k>0同时判断是否pqProfit.isEmpty()，否则即使内层while循环
//          仍可以进行，即仍可以有元素从pqCapital进入pqProfit，也会被跳出循环。
//          应该改为在将pqCapital中所有合适的拿完放入pqProfit后，判断是否pqProfit.isEmpty()。如果为true，
//          才意味着没有合适的project了，这时候才break。

class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        if (k <= 0) return W;
        /* customize PriorityQueue */
        PriorityQueue<int[]> pqCapital = new PriorityQueue(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] - pair2[0];
            }
        });
        PriorityQueue<int[]> pqProfit = new PriorityQueue(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair2[1] - pair1[1];
            }
        });
        /* initialize pqCapital */
        for (int i = 0; i < Profits.length; i++) {
            pqCapital.offer(new int[]{Capital[i], Profits[i]});
        }
        int finalCapital = W;
        
        /* search */
        //while (k-- > 0 && !pqProfit.isEmpty())  // {Mistake 1}
        while (k-- > 0) {  // {Correction 1}
            while (!pqCapital.isEmpty() && finalCapital >= pqCapital.peek()[0]) {
                pqProfit.offer(pqCapital.poll());
            }
            if (pqProfit.isEmpty()) break;  // {Correction 1}
            
            finalCapital += pqProfit.poll()[1];
        }
        
        return finalCapital;
    }
}