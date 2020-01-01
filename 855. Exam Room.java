https://leetcode.com/problems/exam-room/

思路：PriorityQueue

时间复杂度：seat(): O(log n); leave(): O(n)
空间复杂度：O(n)
犯错点：1.细节错误：不能将两端为正常位置[0:len-1]和两端没有座位[-1:curr]或[curr:len]的距离按同样的标准比较
        2.坐的位置应该是start加上距离dist，而不仅仅是距离dist

class ExamRoom {
    PriorityQueue<Interval> pq;
    int len;
    
    class Interval {
        int start, end, dist;
        
        public Interval(int s, int e) {
            start = s;
            end = e;
            //dist = end - start; // {Mistake 1}
            dist = (end - start) / 2; // the closest distance to start or end, NOT the dist between start and end
            if (s == -1) {
                dist = e;
            } else if (e == len) {
                dist = len - 1 - s;
            } // {Correction 1}
        }
    }
    
    public ExamRoom(int N) {
        len = N;
        pq = new PriorityQueue<>((a, b) -> (a.dist == b.dist ? a.start - b.start : b.dist - a.dist));
        pq.offer(new Interval(-1, N));
    }
    
    public int seat() {
        Interval curr = pq.poll();
        //int mid = curr.dist; // {Mistake 2}
        int mid = curr.start + curr.dist; // {Correction 2}
        if (curr.start == -1) {
            mid = 0;
        } else if (curr.end == len) {
            mid = len - 1;
        }
        
        pq.offer(new Interval(curr.start, mid));
        pq.offer(new Interval(mid, curr.end));
        
        return mid;
    }
    
    public void leave(int p) {
        Interval left = null, right = null;
        Iterator<Interval> itr = pq.iterator();
        while (itr.hasNext()) {
            Interval curr = itr.next();
            if (curr.end == p) {
                left = curr;
            }
            if (curr.start == p) {
                right = curr;
            }
            
            if (left != null && right != null) break;
        }
        
        pq.remove(left);
        pq.remove(right);
        
        pq.offer(new Interval(left.start, right.end));
    }
}
