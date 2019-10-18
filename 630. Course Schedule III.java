https://leetcode.com/problems/course-schedule-iii/

// 思路：Greedy + maxHeap。视频讲解：https://www.youtube.com/watch?v=Ugy9pTcCeWg
//         step0: 维护变量curr，表示当前能上的所有课紧凑安排后最早能开始上新课的时间。【注意维护curr一定不超过deadline】。
//                 维护maxHeap，放记录在curr中的所有能上的课的时长，顶部元素为最长的时长。
//         step1: 将courses数组按deadline升序排序，便于使用贪心算法。
//         step2: 遍历排序后的courses数组，根据curr和当前课程时长duration的和，有以下可能情况：
//             1）curr + duration <= ddl，说明当前课程可以安排上，那么更新curr += duration，同时将duration放入maxHeap
//             2）curr + duration > ddl，但pq.peek() > duration，说明如果将已经被安排的课程里时长最长的一门去掉（贪心），
//                 换成当前课程，那么仍然可以保证在ddl里上完，且这样的课程数是最多的。因此，将maxHeap顶部元素移除，加入当前
//                 duration，同时更新curr。
//             3）curr + duration <= ddl，且pq.peek() <= duration，那么无法保证将pq中最长的课程移除后安排当前课程能不超过
//                 ddl，因此不安排这个课程，跳过。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)

class Solution {
    public int scheduleCourse(int[][] courses) {
        /* sort ascendingly by course deadline */
        Arrays.sort(courses, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                return p1[1] - p2[1];
            }
        });
        
        /* maxHeap */
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        
        int curr = 0;
        for (int[] course: courses) {
            int duration = course[0], ddl = course[1];
            if (curr + duration <= ddl) {  // this course can be taken
                curr += duration;
                pq.offer(duration);
            } else if (!pq.isEmpty() && duration < pq.peek()) {  // the course already in the arrangement with 
                                                                // the longest duration can be removed to let 
                                                                // current course fit in
                curr = curr - pq.poll() + duration;
                pq.offer(duration);
            }
        }
        
        return pq.size();
    }
}