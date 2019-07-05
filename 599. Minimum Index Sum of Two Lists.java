https://leetcode.com/problems/minimum-index-sum-of-two-lists/

// 思路：HashMap

// 写法一：Two-pass
//        HashMap中key为餐厅String，value为对应的index。
//        先遍历字符串数组list1，将字符串和位置放入HashMap。
//        然后遍历字符串数组list2，如果遇到在list1出现过的字符串，那么分三种情况：
//        1）如果它们的index sum比minIndexSum小，那么结果列表res需要清空，加入当前的字符串，同时更新minIndexSum。
//        2）如果它们的index sum等于minIndexSum，那么当前的字符串加入结果列表res。
//        3）如果它们的index sum比minIndexSum大，忽略不管。
// 优化：遍历字符串数组list2的时候，循环结束条件可以加上j <= minIndexSum，因为当j==minIndexSum时，对应list1的index
//      最大只能为0，那么当j++时，list1不可能有合法的index，所以可以结束循环。

class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        /* corner case */
        if (list1.length == 0 || list2.length == 0) return new String[0];
        
        Map<String, Integer> map = new HashMap();
        List<String> res = new ArrayList();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        int minIndexSum = Integer.MAX_VALUE;
        for (int j = 0; j < list2.length && j <= minIndexSum; j++) {
            if (map.containsKey(list2[j])) {
                int sum = j + map.get(list2[j]);
                if (sum < minIndexSum) {
                    res.clear();
                    res.add(list2[j]);
                    minIndexSum = sum;
                } else if (sum == minIndexSum)
                    res.add(list2[j]);
            }
        }
        
        return res.toArray(new String[0]);
    }
}


// 写法二：Three-pass
//        HashMap中key为餐厅String，value为对应的index，第一次遍历为负，第二次遍历为正。
//        遍历字符串数组list1，将字符串和位置（取负）放入HashMap。
//        然后遍历字符串数组list2，如果遇到的字符串在list1中出现过，那么现将HashMap的value取为正然后加上当前index，更新HashMap，
//        同时更新变量minIndexSum。
//        第三次遍历字符串数组list2，找到value为minIndexSum对应的key，加入结果列表res中。

class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        /* corner case */
        if (list1.length == 0 || list2.length == 0) return new String[0];
        /* put String in list1 and its index into HashMap, with negative index as the mark of list1 */
        Map<String, Integer> map = new HashMap();
        List<String> res = new ArrayList();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], -(i + 1));
        }
        /* find min index sum in common Strings */
        int minIndexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            /* skip Strings not common to both array */
            if (!map.containsKey(list2[i])) continue;
            /* update the value as index sum*/
            int indexSum = i - map.get(list2[i]);
            map.put(list2[i], indexSum);
            /* update min index sum */
            minIndexSum = Math.min(minIndexSum, indexSum);
        }
        /* find Strings with index sum equal to min index sum */
        for (String str: list2) {
            if (map.containsKey(str) && map.get(str) == minIndexSum) {
                res.add(str);
            }
        }
        
        return res.toArray(new String[0]);
    }
}

