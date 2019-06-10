https://leetcode.com/problems/find-median-from-data-stream/

// 解法一：Two Heaps
//         维护两个Heap，一个为maxHeap，放的是当前数据流里较小的一半int；另一个为minHeap，放的是当前数据流里较大的一半int。
//         同时，维护maxHeap.size == minHeap.size或maxHeap.size - minHeap.size == 1。
//         因此，median只需要取出maxHeap.top（或再取minHeap.top再求平均，取决于总元素个数）。
//         Trick：为了维护两个Heap的size的关系，先将要加入的元素放进maxHeap，然后把maxHeap.top取出放进minHeap。如果这时候minHeap
//         元素比maxHeap多，再把minHeap.top取出放进maxHeap。
//         这样做，如果加入元素前两个heap元素个数相同，加入后第一次取出操作后minHeap元素更多，会进行第二次取出操作，结果是maxHeap
//         元素更多（多1个）。如果加入元素前两个heap元素个数不同，即maxHeap元素更多（多1个），加入后第一次取出操作后两个heap元素个数
//         相同，因此不会进行第二次取出操作。
// 时间复杂度：addNum(): O(log n); findMedian(): O(1)
// 空间复杂度：O(n)

class MedianFinder {
    PriorityQueue<Integer> smallerHalf;  // maxHeap
    PriorityQueue<Integer> largerHalf;  // minHeap
    
    /** initialize your data structure here. */
    public MedianFinder() {
        largerHalf = new PriorityQueue<Integer>();
        smallerHalf = new PriorityQueue<Integer>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        smallerHalf.add(num);
        largerHalf.add(smallerHalf.poll());
        if (smallerHalf.size() < largerHalf.size()) {  // balance two heap so that smallerHalf always has 1 or 0 more element than largerHalf
            smallerHalf.add(largerHalf.poll());
        }
    }
    
    public double findMedian() {
        double first = smallerHalf.peek();
        double second = smallerHalf.size() == largerHalf.size() ? largerHalf.peek() : smallerHalf.peek();
        return (first + second) / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */