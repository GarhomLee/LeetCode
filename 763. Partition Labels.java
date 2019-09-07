https://leetcode.com/problems/partition-labels/

// 思路：Greedy，参考：https://leetcode.com/problems/partition-labels/solution/
//         step0: 维护数组last，表示每个字符在String S中最后出现的位置。维护变量start和end，表示能被分到一组的字符窗口
//             的左右边缘位置。
//         step1: 第一遍遍历String S，更新last数组。
//         step2: 再次遍历String S，对于每个字符c，从last数组中找到其最后出现的位置last[c]，并用last[c]来更新end，即
//             如果出现last[c]比end大，说明右边缘end需要至少在last[c]位置才能将S中的所有字符c包含在当前组中。
//         step3: 如果当前扫描到的位置i就是当前窗口的右边缘end，说明[start:end]能形成一个符合题意的partition，因此将
//             窗口长度end-start+1加入res列表。
//             同时，更新start为下一个窗口的起始位置。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.细节错误：用原来的end和last[c - 'a']来更新新的end，而不是用i来更新end。i只需要和end做比较。

class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int[] last = new int[26];
        /* update last array */
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            last[c - 'a'] = i;
        }
        /* find all partitions greedily */
        int start = 0, end = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            //end = Math.max(i, last[c - 'a']);  // {Mistake 1}
            end = Math.max(end, last[c - 'a']);  // {Correction 1}

            if (i == end) {  // when reaches the end of current window, we can take [start:end] as a group
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        
        return res;
    }
}