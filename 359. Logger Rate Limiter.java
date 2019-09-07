https://leetcode.com/problems/logger-rate-limiter/

// 思路：HashMap
//         HashMap的key为出现过的message，value为最近一次打印的时间timestamp。
//         只有当符合打印要求时，HashMap中对应key的value才会更新，且返回true。
//         否则返回false，且HashMap不更新。
// 时间复杂度：shouldPrintMessage(): O(1)
// 空间复杂度：O(n), n=number of calls

class Logger {
    Map<String, Integer> map;
    
    /** Initialize your data structure here. */
    public Logger() {
        map = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message) || timestamp >= map.get(message) + 10) {
            map.put(message, timestamp);
            return true;
        }
        
        return false;
    }
}