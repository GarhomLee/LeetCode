https://leetcode.com/problems/frog-jump/

// 思路：HashMap
//         HashMap中key为stones[i]，value为一个HashSet，表示从stones[i]可以跳到的距离。
//         遍历stones数组的每个元素，然后遍历对应的从当前元素stone可以跳到的距离map.get(stone)。
//         记下一个到达的元素为nextStone，如果nextStone就是末尾的元素，直接返回true；否则如果nextStone不在
//         HashMap里，不需要考虑，跳过；否则，在nextStone对应的可以跳到的距离的HashSet加入step-1，step和step+1.
// 注意：要判断step - 1是否大于0，因为不能往回跳或者原地不动
// 犯错点：1.细节错误：HashMap中key为stones[i]，而不是位置i

class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        int lastStone = stones[n - 1];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int stone: stones) {
            map.put(stone, new HashSet<>());
        }
        
        map.get(0).add(1);
        //for (int i = 0; i < n; i++)  // {Mistake 1}
        for (int stone: stones) {  // {Correction 1}
            //for (int step: map.get(i))  // {Mistake 1}
            for (int step: map.get(stone)) {  // {Correction 1}
                int nextStone = stone + step;  // the next stone if jumping from current stone with step
                /* the next stone is the last stone, done */
                if (nextStone == lastStone) return true;
                /* the next stone does not in stones array, ignore it */
                if (!map.containsKey(nextStone)) continue;
                
                if (step - 1 > 0) map.get(nextStone).add(step - 1);  // {Attention: we don't want to jump back or stay the same position}
                map.get(nextStone).add(step);
                map.get(nextStone).add(step + 1);
            }
        }
        
        return false;
    }
}