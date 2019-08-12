https://leetcode.com/problems/day-of-the-year/

// 思路：Math，闰年的定义
//         step1:将日期字符串D以"-"分割成3部分，分别为year，month，date
//         step1.5:判断特殊情况：1月份不涉及闰年，所以如果month == 1，直接返回date
//         step2:用变量isLeap表示year是否为闰年。闰年的条件为：【可以被400整除，或者能被4整除且不能被100整除】。
//                 数组days表示3月到12月各个月份的天数。
//                 total表示总天数，初始化为1月天数31+最后一个月份不满整月的天数date。
//         step3:从2月到month-1月，累加所有整月的天数。对2月进行闰年的判断。
// 时间复杂度：O(month)
// 空间复杂度：O(1)
// 犯错点：1.定义错误：对闰年的定义不熟悉

class Solution {
    public int dayOfYear(String D) {
        String[] split = D.split("-");
        int year = Integer.valueOf(split[0]), month = Integer.valueOf(split[1]), date = Integer.valueOf(split[2]);
        
        if (month == 1) {
            return date;
        }

        boolean isLeap = (year %  400 == 0) || (year %  4 == 0 && year % 100 != 0) ? true : false;
        int[] days = new int[]{31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int total = 31 + date;
        for (int i = 2; i < month; i++) {
            if (i == 2) {
                total += isLeap ? 29 : 28;
            } else {
                total += days[i - 3];
            }
        }
        
        return total;
    }
}