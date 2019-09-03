https://leetcode.com/problems/two-sum-iii-data-structure-design/

// 思路：HashMap
//         HashMap中key为出现的数字，value为出现的频数。实现以下功能：
//         1）void add(int number)
//             如果HashMap中出现过number，那么相应的value（即频数）加1；否则加入HashMap并初始化频数为1.
//         2）boolean find(int value)
// 优化：维护变量min和max，表示当前的全局最大最小值，在实现add()时更新。那么，在find()的实现中，如果value比两个min
//         的加和还小，或比两个max加和还大，那么说明一定不能找到合适的两个数，那么可以直接返回false。
// 时间复杂度：add(): O(1); find(): O(n)
// 空间复杂度：O(n)
// 犯错点：1.思路错误：如果使用HashSet，那么当HashSet中只存在1个元素n == value / 2时，set.contains(n)为true，
//             但实际上不存在两个n使得n + n == value。因此，不能用HashSet，而要用HashMap来统计出现的次数。

class TwoSum {
    //Set<Integer> set;  // {Mistake 1}
    Map<Integer, Integer> map;  // {Correction 1}
    int min;  // for optimization
    int max;  // for optimization

    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
        min = Math.min(min, number);
        max = Math.max(max, number);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        if (value < min * 2 || value > max * 2) {  // optimization
            return false;
        }
        
        for (int m: map.keySet()) {
            int n = value - m;
            if (m == n && map.get(m) > 1) {
                return true;
            } else if (m != n && map.containsKey(n)) {
                return true;
            }
        }
        
        return false;
    }
}