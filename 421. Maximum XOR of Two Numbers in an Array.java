https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/

// 关键点：和数字n异或后的结果最大的数是!n
// 思路：Trie，用Trie来找到nums数组中和数字n在二进制位中差别最大的数字
//      先自建class TrieNode，利用TrieNode来构建Trie，将nums数组每个元素的二进制表示的每一位都作为TrieNode。
//      然后再次遍历nums数组，对于每个元素都从Trie里找到二进制中差别最大的bit。如果当前位是1，则找0；是0，则找1；
//      如果没有差别的bit，则按当前bit往下找。找到数currNum，和当前元素n做异或后更新res。
//      在构建Trie的过程中，已经保证了Trie的深度一定为32，即每一位都会存在。
// 犯错点：1.数据结构错误：TrieNode里的next应该是TrieNode[]而不是int[]

class Solution {
    int res = 0;
    
    public int findMaximumXOR(int[] nums) {
        TrieNode root = build(nums);
        search(nums, root);
        return res;
    }
    /** use all elements in nums array to build Trie */
    private TrieNode build(int[] nums) {
        TrieNode root = new TrieNode();
        for (int n: nums) {
            TrieNode curr = root;
            for (int i = 31; i >= 0; i--) {
                int digit = (n >> i) & 1;
                if (curr.next[digit] == null) curr.next[digit] = new TrieNode();
                curr = curr.next[digit];
            }
        }
        return root;
    }
    /** search the most distinct binary num in Trie */
    private void search(int[] nums, TrieNode root) {
        for (int n: nums) {
            TrieNode curr = root;
            int currNum = 0;
            for (int i = 31; i >= 0; i--) {
                int digit = (n >> i) & 1;
                if (curr.next[1 - digit] != null) {
                    currNum = currNum | ((1 - digit) << i);
                    curr = curr.next[1 - digit];
                } else {
                    currNum = currNum | (digit << i);
                    curr = curr.next[digit];
                }
            }
            res = Math.max(res, currNum ^ n);
        }
    }
    
    class TrieNode {
        //int[] next;  // {Mistake 1}
        TrieNode[] next;  // {Correction 1}
        TrieNode() {
            next = new TrieNode[2];
        }
    }
}