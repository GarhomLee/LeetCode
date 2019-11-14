https://leetcode.com/problems/find-the-celebrity/

// Brute Force: 每两个node都调用knows()来确定入度和出度
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)


// 优化思路：Greedy, 2-pass。视频讲解：https://www.laioffer.com/en/videos/2018-05-07-277-find-the-celebrity/
//         第一次遍历：维护变量candidate，表示当前搜索到的可能是celebrity的节点，初始化为0。
//                 在遍历时，对于当前candidate和其后续的节点i，可能有两种情况：
//                 1）candidate认识i，根据题意，这个candidate一定不会是celebrity，更新candidate为i；
//                 2）candidate不认识i，根据题意，他能保持candidate身份，而i则一定不是celebrity，因为celebrity
//                     需要被其他所有人认识。
//                 因此，每次调用调用knows()，都能确定其中一个节点【一定不是celebrity】。
//         第二次遍历：对于第一次遍历得到的candidate，只能确定在他之后的i他不认识，不能确定他之后的i是否认识他，也不能
//                 确定在他之前的i和他是否互相认识。因此，需要再一次遍历来确定：
//                 1）其他人都认识candidate
//                 2）candidate不认识其他所有人
//                 如果有任意节点违反这两个条件之一，那么说明candidate也不是celebrity，返回-1。
//                 否则，candidate符合题意，是celebrity，返回之。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

public class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n <= 1) {
            return n;
        }
        
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                // if this person knows another person, he/she is not a candidate, then update candidate
                candidate = i;
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (i == candidate) continue;
            
            // make sure that the candidate should not know anybody else but should be known by others
            if (knows(candidate, i) || !knows(i, candidate)) {
                return -1;
            }
        }
        
        return candidate;
    }
}