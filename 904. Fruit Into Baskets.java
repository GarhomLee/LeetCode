https://leetcode.com/problems/fruit-into-baskets/

// 思路：Sliding window with HashMap as the counter，转化为length of the longest substring with at most k characters, where k == 2 here
//         HashMap中，key是水果种类，即tree[i]；value为对应的出现次数。
//         step1: 遍历tree数组，将窗口右边缘的元素tree[right]加入HashMap。
//         step2: 如果HashMap中存在超过了2个元素，根据题意，需要将除当前元素tree[right]外的元素删除。
//                 因此，从left开始删除元素，当HashMap中tree[left]的个数为0时将其从HashMap中删掉，直到剩余2个元素。
//         step3: 在每一步中，都用left和right更新maxLen。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int totalFruit(int[] tree) {
        int maxLen = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int left = 0, right = 0; right < tree.length; right++) {
            map.put(tree[right], map.getOrDefault(tree[right], 0) + 1);
            while (map.size() > 2) {
                int temp = tree[left++];
                map.put(temp, map.get(temp) - 1);
                
                if (map.get(temp) == 0) {
                    map.remove(temp);
                }
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}


优化：由于题目给定了0 <= tree[i] < tree.length，所以可以利用array来模拟HashMap作为counter