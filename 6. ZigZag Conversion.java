https://leetcode.com/problems/zigzag-conversion/

// 总体思路：观察题目可知，可以利用List来保存每个row的String，row的更新方向可下（增加）可上（减少），当碰到row == 0
//         或者row == numRows - 1这两个边界时改变更新方向。需要boolean变量goDown来指示row的更新方向。

class Solution {
    public String convert(String s, int numRows) {
        if (s == null || s.length() <= 1 || numRows == 1) return s;  // corner cases
        
        List<String> list = new ArrayList<>();
        boolean goDown = false;  // goDown indicates if row is increasing or decreasing, and initialize as false
        int row = 0;  // row or index in the List of String
        for (char c: s.toCharArray()) {
            if (list.size() == row) list.add("");  // if it is the first time to visit this row or index in List, add an empty String as initialization
            
            list.set(row, list.get(row) + c);  // update the String in the List
            if (row == 0 || row == numRows - 1) goDown = !goDown;  // when it reaches the upper and lower boundary, change goDown to reverse the direction
            row += goDown ? 1 : -1;  // update the next row to visit according to goDown
        }
        
        StringBuilder sb = new StringBuilder();
        for (String str: list) {
            sb.append(str);  // concatenate all substrings
        }
        return sb.toString();
    }
}