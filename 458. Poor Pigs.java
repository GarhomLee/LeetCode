https://leetcode.com/problems/poor-pigs/

// 思路：数学题，本质上相当于信息论中关于信息编码长度的问题，是“小白鼠试毒”问题的加强版。
//     首先，要找到状态数：对于本题，一只猪有5种状态，即15min死了、30min死了、45min死了、60死了、一直没有死，可以发现
//     状态数stateNum = minutesToTest / minutesToDie + 1（取下整）。（对于小白鼠问题，有“活”和“死”两种状态）
//     假设一只猪占领一个数位，那么一只猪在规定时间内利用5种状态能从5个桶确定结果。如果有两只猪，每只猪占领一个数位，那么
//     能最多从25个桶确定结果，将桶编号为00，01，02，03，04；10，11，...，40，41，42，43，44。为什么？因为如果第一只
//     猪（占第一位）在状态3死了，即确定了桶号码的第一位为3，如果第二只猪在状态2死了，即确定了桶号码的第二位为2，因此可以
//     确定桶32有毒药。
//     因此，题目转化为了给定状态数stateNum和总信息个数buckets，问要多长的编码（位数）digittNum能唯一确定一条信息。
//     例如本题，给定5个状态数，如果要从100个信息中确定某一条，那么至少需要3位编码，因为3位编码最多能确定5^3=125条信息。
//     例如初级版小白鼠试毒问题，小鼠有2个状态，确定1000杯毒药中某一杯，即2^x=1000，那么至少需要x=log2(1000)只小鼠。

class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int stateNum = minutesToTest / minutesToDie + 1;
        int digitNum = 0, testableBuckets = 1;
        while (testableBuckets < buckets) {
            digitNum++;
            testableBuckets *= stateNum;
        }
        
        return digitNum;
    }
}