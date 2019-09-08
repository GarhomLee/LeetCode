https://leetcode.com/problems/day-of-the-week/

// 解法一：Brute Force
// 时间复杂度：O(year + month)
// 空间复杂度：O(1)

class Solution {
    String[] days = new String[]{"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
    
    public String dayOfTheWeek(int day, int month, int year) {
        int totalDays = 0;
        for (int i = 1971; i < year; i++) {
            totalDays += isLeap(i) ? 366 : 365;
        }
        
        int[] months = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 2) {
            totalDays += 31;
        } else if (month >= 3) {
            totalDays += 31 + (isLeap(year) ? 29 : 28);
            for (int i = 3; i < month; i++) {
                totalDays += months[i - 1];
            }
        }
        totalDays += day - 1;
        
        return days[totalDays % 7];
    }
    
    
    
    private boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}


// 解法二：调用Java内置API

import java.time.*;

class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
        String[] weeks = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int weekOfDay = Year.of(year).atMonth(Month.of(month)).atDay(day).getDayOfWeek().getValue() - 1;
        return weeks[weekOfDay];
    }
}


// 解法三：Zeller公式

class Solution {
    String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    
    public String dayOfTheWeek(int d, int m, int y) {
        if (m < 3) {
            m += 12;
            y -= 1;
        }
        int c = y / 100;
        y = y % 100;
        int w = (c / 4 - 2 * c + y + y / 4 + 13 * (m + 1) / 5 + d - 1) % 7;
        return days[(w + 7) % 7];
    }
}