https://leetcode.com/problems/split-linked-list-in-parts/

// 思路：Two-pass traversal
//         第一次遍历：统计ListNode个数total
//         第二次遍历：根据total和k以及余数确定每个部分的ListNode个数count，然后将分割点的ListNode放入res
// 时间复杂度：O(n)
// 空间复杂度：O(k)
// 犯错点：1.审题错误：不能只简单地将分割点的ListNode放入res。根据题意，还需要将分割点和之前的分割点的ListNode分割，即设为null
//         2.细节错误：要更新k

class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        int total = 0;
        ListNode curr = root, pre = null;
        while (curr != null) {
            total++;
            curr = curr.next;
        }
        
        int count = 0, index = 0;
        curr = root;
        while (curr != null) {
            if (count == 0) {
                res[index++] = curr;
                if (pre != null) {
                    pre.next = null;
                }
                count = total / k;
                if (total % k != 0) {
                    count++;
                }
                total -= count;
                k--;
            }
            count--;
            pre = curr;
            curr = curr.next;
        }
        
        return res;
    }
}