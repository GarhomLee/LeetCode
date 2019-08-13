https://leetcode.com/problems/time-based-key-value-store/

// 对比：1146. Snapshot Array

// 思路：HashMap + TreeMap
//         利用HashMap存放input的key和对应的TreeMap；TreeMap当中，key为input给定的timestamp，value为和
//         这个timestamp相对应的input的value。
//         实现以下功能：
//         1）void set(String key, String value, int timestamp)，从HashMap找到key对应的TreeMap，
//             然后在TreeMap中放入timestamp和对应的value。
//         2）String get(String key, int timestamp)，从HashMap找到key对应的TreeMap，然后从TreeMap中
//             利用BST的性质找出参数timestamp的floor值（小于等于timestamp的最大值），返回floor值对应的value。
//             如果floor==null，也就是说没有小于等于timestamp的值，所以返回空字符串""。
// 时间复杂度：set(): O(log n); get(): O(log n)
// 空间复杂度：O(n)

class TimeMap {
    Map<String, TreeMap<Integer, String>> keyMap;
    /** Initialize your data structure here. */
    public TimeMap() {
        keyMap = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        keyMap.putIfAbsent(key, new TreeMap<>());
        keyMap.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!keyMap.containsKey(key)) {
            return "";
        }
        
        TreeMap<Integer, String> treeMap = keyMap.get(key);
        Integer floor = treeMap.floorKey(timestamp);
        return floor == null ? "" : treeMap.get(floor);
    }
}