https://leetcode.com/problems/maximum-profit-in-job-scheduling/

思路：Sort + Dynamic Programming + Binary Search (search in index)
        首先，构建class Job，存放每一个任务的信息，然后构建Job数组jobs。
        然后，将所有任务排序，先按endtime排，越大的越靠后；再按profit排，越大的越靠后；最后按
        starttime排，越大的越靠后。
        接下来遍历jobs，进行DP。
        状态函数定义：dp[i]表示当时间来到第i个任务时能获得的最大收益。注意：在这个时间点，任务i【可能
            会被执行，也可能不被执行】，只强调了时间为i。
        初始值：dp[0] = jobs[0].profit;
        状态转移方程：
时间复杂度：O(n log n)
空间复杂度：O(n)

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = startTime.length;
        Job[] jobs = new Job[len];
        for (int i = 0; i < len; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job j1, Job j2) {
                if (j1.end == j2.end) {
                    return j1.profit == j2.profit ? j1.start - j2.start : j1.profit - j2.profit;
                }
                return j1.end - j2.end;
            }
        });
        
        int[] dp = new int[len];
        dp[0] = jobs[0].profit;
        for (int i = 1; i < len; i++) {
            int preIndex = binarySearch(jobs, jobs[i].start);
            int preProfit = preIndex < 0 ? 0 : dp[preIndex];
            dp[i] = Math.max(dp[i - 1], jobs[i].profit + preProfit);
        }
        
        return dp[len - 1];
    }
    
    private int binarySearch(Job[] jobs, int start) {
        int low = 0, high = jobs.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (jobs[mid].end > start) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low - 1;
    }
    
    class Job {
        int start, end, profit;
        public Job(int s, int e, int p) {
            start = s;
            end = e;
            profit = p;
        }
    }
}