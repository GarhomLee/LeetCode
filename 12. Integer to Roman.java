https://leetcode.com/problems/integer-to-roman/

// 解法一：TreeMap，要利用【floor】的性质。
// 根据当前的num，从TreeMap中取对应的floor（小于num的最大数），得到对应的String，加到已有的String之后。同时更新num。

class Solution {
    public String intToRoman(int num) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            Integer floor = map.floorKey(num);
            sb.append(map.get(floor));
            num -= floor;
        }
        return sb.toString();
    }
}

// 解法二：
// 维护两个数组：romans，将罗马数字从大到小排列，包括CM，CD等；integers，和romans中的罗马数字一一对应的阿拉伯数字。
// 两层循环：外层循环判断当前处理的数字的级别，对应的是TreeMap的floor的作用；内层循环将这个级别的罗马数字加入String，同时更新num，直至num比当前可处理的integers[i]小为止

class Solution {
    public String intToRoman(int num) {
        int[] integers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String [] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < integers.length; i += 1) {
            while (num >= integers[i]) {
                sb.append(romans[i]);
                num -= integers[i];
            } 
        }
        return sb.toString();
    }
}