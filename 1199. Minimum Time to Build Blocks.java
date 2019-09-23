https://leetcode.com/problems/minimum-time-to-build-blocks/

// 思路：minHeap，利用Huffman coding tree的思想。关于Huffman tree，见：https://leetcode.com/problems/minimum-time-to-build-blocks/discuss/387035/Python%3A-O(n-log-n)-using-Huffman's-Algorithm-(priority-queue)-with-explanation.
//                                                                 https://www.youtube.com/watch?v=dM6us854Jk0
//                                                                 https://blog.csdn.net/google19890102/article/details/54848262
//         将blocks数组的元素作为树的leaf node，而split作为inner node。
//         对于有2个blocks而只有1个worker时，总时间最少的选择应该是block的较大值加上split的时间，这个总时间作为完成这2个block所需的最小时间。
//         而要得到完成所有block（leaf node）的最小时间，也就是得到带权路径长度最小的二叉树，那么可以利用构建Huffman tree的思想，利用minHeap，
//         将两个node（不管是leaf node还是inner node）从minHeap取出，同时合并，合并后的值应该为两个node的较小值+split的时间，表示【完成这个
//         子树的所有leaf node所需的最小时间】，并加入minHeap中。因此，完成所有leaf node的结果会储存在root node中。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)

class Solution {
    public int minBuildTime(int[] blocks, int split) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int block: blocks) {
            pq.offer(block);
        }
        
        while (pq.size() > 1) {
            int num1 = pq.poll(), num2 = pq.poll();
            pq.offer(num2 + split);
        }
        return pq.poll();
    }
}