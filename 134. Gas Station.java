https://leetcode.com/problems/gas-station/

思路：Greedy

犯错点：1.当tank<0时，重新确定start后，tank要重置为0
       2.最后判断是否有解要用sumGas和sumCost而不是tank

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0, tank = 0, sumCost = 0, sumGas = 0;
        for (int i = 0; i < gas.length; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {  // fuel is not enough for [start:i]
                start = (i + 1) % gas.length;  // set a new start
                tank = 0;  // {Mistake 1} {Correction 1: reset tank for the next search}
            }
        }
        //return tank < 0 ? -1 : start;  // {Mistake 2}
        return sumGas - sumCost < 0 ? -1 : start;  // {Correction 2}
    } 
}