https://leetcode.com/problems/duplicate-zeros/

// 思路：Queue
//         利用Queue的FIFO特性，将当前元素arr[i]放进Queue。如果遇到0，那么再多放一个0。然后将arr[i]替换成
//         Queue的头部元素，Queue中剩下的元素将会在后续过程中取出。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public void duplicateZeros(int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            queue.offer(arr[i]);
            if (arr[i] == 0) {
                queue.offer(arr[i]);
            }
            arr[i] = queue.poll();
        }
    }
}