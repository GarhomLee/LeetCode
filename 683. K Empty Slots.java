https://leetcode.com/problems/k-empty-slots/

// 解法一：TreeSet (Insertion Sort)
//         遍历bulbs数组，对于当前bulbs[i]，在TreeSet中搜索floor和ceiling，检查是否ceiling - bulbs[i]或
//         bulbs[i] - floor为【k + 1】。
//         如果有符合题意的i，返回【i + 1】。否则，遍历完整个bulbs数组都没有返回，则最后返回-1.
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)
// 犯错点：1.细节错误：相邻位置中间相隔k个灯泡，那么位置差值应该是k+1而不是k。
//         2.细节错误：返回1-based的天数，而不是0-based。

class Solution {
    public int kEmptySlots(int[] bulbs, int K) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < bulbs.length; i++) {
            int pos = bulbs[i];
            Integer floor = treeSet.floor(pos), ceiling = treeSet.ceiling(pos);
            //if (floor != null && pos - floor == K)  // {Mistake 1}
            if (floor != null && pos - floor == K + 1) {  // {Correction 1}
                //return i;  // {Mistake 2}
                return i + 1;  // {Correction 2}
            } else if (ceiling != null && ceiling - pos == K + 1) {
                return i + 1;
            }
            
            treeSet.add(pos);
            
        }
        
        return -1;
    }
}


解法二：Bucket Sort，视频讲解：youtube.com/watch?v=K8Nk0AiIX4s

时间复杂度：O(n)
空间复杂度：O(n)


解法三：Brute Force。更快？