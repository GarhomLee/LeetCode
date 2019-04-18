https://leetcode.com/problems/third-maximum-number/

// 解法一：Set + PriorityQueue
// 1）用Set来防止重复
// 2）用size == 3的PriorityQueue来找到第三大的数
// 3）遍历一遍数组后，取出头部元素。接着判断，PriorityQueue如果size为0或2，均符合题意；只有当size为1时，说明原来的PriorityQueue
//     只有2个元素，需要再取一次头部元素。

class Solution {
    public int thirdMax(int[] nums) {
        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n: nums) {
            if (!set.contains(n)) {
                set.add(n);
                pq.offer(n);
                if (pq.size() > 3) pq.poll();
            }
        }
        int res = pq.poll();
        if (pq.size() == 1) res = pq.poll();
        return res;
    }
}

// 解法二：用3个变量分别表示当前找到的第一、二、三大数。遍历数组，依情况更新之。
//   注意：变量需要declare为【long】，避免数组内有Integer.MIN_VALUE的情况

class Solution {
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE, second = Long.MIN_VALUE, third = Long.MIN_VALUE;
        for (int n: nums) {
            if (n > first) {
                third = second;
                second = first;
                first = n;
            } else if (n > second && n < first) {
                third = second;
                second = n;
            } else if (n > third && n < second) {
                third = n;
            }
        }
        return third == Long.MIN_VALUE ? (int) first : (int) third;
    }
}