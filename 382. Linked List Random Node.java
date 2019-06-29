https://leetcode.com/problems/linked-list-random-node/

// 解法一：Two-pass
//        先遍历整个链表，得到链表的长度，然后用Random函数得到随机位置，再遍历得到这个位置的值。
// 时间复杂度：O(n)
// 空间复杂度：O(1)


// 解法二：One-pass with Reservoir Sampling
//        只需要在getRandom()的时候遍历一次链表。
//        从第一个元素开始，记录当前元素的个数。假设当前有i个数，用Random函数“抽奖”看是否能抽到0（实际上可以设为[0:i]任意一个数），那么抽到0的概率为1/i。
//        如果抽到0，那么更新res为当前元素的值，否则不变。
//        可以从数学上证明这样得到的结果的概率为1/n，n为链表元素个数。
//        （简单证明：如果第一个数为1，那么在遍历完n个数后结果仍然为1没有变的概率为：
//             P(第一次抽到0)*P(第二次没抽到0)*P(第三次没抽到0)*P(第四次没抽到0)*...P(第n次没抽到0)
//             = 1/1 * 1/2 * 2/3 * 3/4 * ... (n-1)/n
//             = 1/n 
//         ）
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    ListNode dummy = new ListNode(-1);
    Random random = new Random();
    
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        dummy.next = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int count = 0;
        int res = Integer.MIN_VALUE;
        ListNode curr = dummy.next;
        while (curr != null) {
            count++;
            if (random.nextInt(count) == 0) {
                res = curr.val;
            }
            curr = curr.next;
        }
        return res;
    }
}