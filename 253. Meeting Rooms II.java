https://leetcode.com/problems/meeting-rooms-ii/

// 对比：218. The Skyline Problem  

// 思路：Sweep line + Priority Queue
//         将start都记为非负数，end都记为负数。维护以绝对值来判断大小的minHeap，如果有一正一负的绝对值相同，那么
//         负数（即end）在前。
//         不断从minHeap取出头部元素，如果为正数，说明是start，那么当前同时在使用的房间数count++；否则，负数是end，
//         说明所需房间数减少，count--。利用count在每一步都更新最大值maxCount，最后结果返回maxCount。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)
// 犯错点：1.细节错误：对于两个数字的绝对值相同的情况，还需要加以区分，使得负数即end先处理，然后再处理start即正数。


class Solution {
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                //return Math.abs(i1) - Math.abs(i2);
                int abs1 = Math.abs(i1), abs2 = Math.abs(i2);
                return abs1 == abs2 ? i1 - i2 : abs1 - abs2;
            }
        });
        
        for (int[] interval: intervals) {
            pq.offer(interval[0]);
            pq.offer(-interval[1]);
        }
        
        int maxCount = 0, count = 0;
        while (!pq.isEmpty()) {
            if (pq.poll() >= 0) {
                count++;
            } else {
                count--;
            }
            
            maxCount = Math.max(maxCount, count);
        }
        
        return maxCount;
    }
}


// 二刷：Sort
//     转成List，起始时间为非负数，终止时间为负数，排序，求最大重叠数量。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        List<Integer> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(interval[0]);
            list.add(-interval[1]);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return Math.abs(i1) == Math.abs(i2) ? i1 - i2 : Math.abs(i1) - Math.abs(i2);
            }
        });
        
        int max = 0, curr = 0;
        for (int num : list) {
            curr += num >= 0 ? 1 : -1;
            max = Math.max(max, curr);
        }
        
        return max;
    }
}


三刷：
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);  // a: {timepoint, start/end}
        for (int[] interval: intervals) {
            pq.offer(new int[]{interval[0], 1});    // start
            pq.offer(new int[]{interval[1], -1});   // end
        }
        
        int count = 0, max = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            count += curr[1];
            max = Math.max(max, count);
        }
        
        return max;
    }
}