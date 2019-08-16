https://leetcode.com/problems/reorder-log-files/

// 思路：Customized Sort
//         step1: 将logs数组分成letterLogs和digitLogs分别存在两个ArrayList。
//         step2: 对letterLogs按题目要求进行自定义排序。将字符串在第一个空格位置分割为两部分，先比较右边部分，
//                 右边部分小的字符串在前。当右边部分相同，那么比较左边部分identifier，较小的在前。
//         step3: 将排好序的letterLogs先放入res数组，然后将digitLogs放入res数组，最后返回res。
// 时间复杂度：O(n log n)
// 时间复杂度：O(n)

class Solution {
    public String[] reorderLogFiles(String[] logs) {
        List<String> letterLogs = new ArrayList<>(), digitLogs = new ArrayList<>();
        for (String log: logs) {
            String[] split = log.split(" ", 2);
            if (Character.isDigit(split[1].charAt(0))) {
                digitLogs.add(log);
            } else {
                letterLogs.add(log);
            }
        }
        
        Collections.sort(letterLogs, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] split1 = s1.split(" ", 2), split2 = s2.split(" ", 2);
                return split1[1].equals(split2[1]) ? split1[0].compareTo(split2[0]) : split1[1].compareTo(split2[1]);
            }
        });
        
        String[] res = new String[logs.length];
        int n = letterLogs.size();
        for (int i = 0; i < res.length; i++) {
            res[i] = i < n ? letterLogs.get(i) : digitLogs.get(i - n);
        }
        return res;
    }
}