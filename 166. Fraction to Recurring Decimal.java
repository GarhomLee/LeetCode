https://leetcode.com/problems/fraction-to-recurring-decimal/

// 比较复杂，需要注意的点比较多。
// 1）根据给定的数判断最终结果的正负号，注意【先转换成long防止数据过界】
// 2）分子和分母都要取【绝对值】，且赋值为【long】
// 3）如果第一次除完余数为0，可以直接返回
// 4）否则，用Map记录，key为【余数】，value为【这个余数乘以10后和分母相除得到的商在字符串中的位置】
// 5）如果余数重复出现或者为0，可以跳出循环。余数重复出现表示这个余数乘以10后和分母相除得到的商也会重复出现，是循环小数
// 6）跳出循环后，如果余数为0，说明不是循环小数，直接返回；否则，根据Map中key的信息，在【第一个开始循环的位置】插入"("，在最后插入")"

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        if ((long) numerator * denominator < 0) sb.append("-");
        long num = Math.abs((long) numerator);
        long denom = Math.abs((long) denominator);
        
        sb.append(num / denom);
        num = num % denom;
        if (num == 0) {
            return sb.toString();
        }
        
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        
        while (!map.containsKey(num) && num != 0) {
            map.put(num, sb.length());
            sb.append(num * 10 / denom);
            num = num * 10 % denom;
        }
        
        if (num == 0) return sb.toString();
        else return sb.insert(map.get(num), "(").append(")").toString();
    }
}