https://leetcode.com/problems/task-scheduler/

// 思路：Greedy（需要证明），视频讲解：https://www.youtube.com/watch?v=YCD_iYxyXoo
//         遍历tasks数组，统计字母出现的最高频率，以及同是最高频率的字母个数。
//         按照贪心的做法，总共需要花的时间受到出现频率最高的字母的限制，那么以这个字母占用时间1+冷却时间n为间隔，
//         先将前k-1次安排上。最后一次不需要等冷却时间，只要把频率最高的这些字母的再排满即可。
//         注意：这种情况是假设会出现idle时所需的最少时间，因为即使排不满，也需要等待冷却时间。
//         如果task总数大于ans，那么一定能全部紧密安排上，不会出现有idle的情况，因此直接返回task总数。（如何证明？）
// 犯错点：1.情况考虑不全：ans给出的是出现idle的情况时的最少时间。如果task总数大于ans，那么一定能全部紧密安排上，
//             不会出现有idle的情况，因此直接返回task总数。（如何证明？）

class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }
        
        int[] count = new int[26];
        int maxFrequency = 0, sameGroup = 0;
        int sum = 0;
        for (char task: tasks) {
            sum++;
            count[task - 'A']++;
            if (count[task - 'A'] > maxFrequency) {
                maxFrequency = count[task - 'A'];
                sameGroup = 1;
            } else if (count[task - 'A'] == maxFrequency) {
                sameGroup++;
            }
        }

        int ans = (maxFrequency - 1) * (1 + n) + sameGroup;  // minimum time if idle exists
        //return ans;  // {Mistake 1}
        return sum > ans ? sum : ans; // {Correction 1}
    }
}