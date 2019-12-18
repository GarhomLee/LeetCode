https://leetcode.com/problems/the-skyline-problem/

// 总体思路：用sweep line的思想。
//         把几何图形的端点看作一个event，左上端点和右上端点分别对应entering event和exiting event，【在height前加上正负号来区别】。
//         先扫描一遍所有端点，然后sort这些端点。重点在于sort的选择：【由于height前的正负号】，当遇到两个端点x值相同，如果两个都是entering
//         先处理高的；如果两个都是exiting先处理低的；一个entering一个exiting先处理entering。经过sort后，后面用maxHeap的时候代码
//         可以很简洁。
//         sort完成后，依次处理event。如果是entering，且比当前maxHeap记录的最高高度高，说明是"key points"，要加入res列表；不管是否加入了
//         res列表，当前event的height都要加入maxHeap。如果是exiting，操作完全镜像对称，先从maxHeap移除当前高度（绝对值）；然后判断，如果
//         当前event要比移除后的maxHeap记录的最高高度高，说明当前event是"key points"，要加入res列表。
//         exiting，
//         最后返回res列表。
//         注意：Java里PriorityQueue的remove()要花费O(n)时间，可以使用TreeSet优化（how？）

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Event> listOfEvents = new ArrayList();
        for (int[] building: buildings) {
            listOfEvents.add(new Event(building[0], building[2]));
            listOfEvents.add(new Event(building[1], -building[2]));  // {Trick1: mark height as positive number if it is an entering event, or negative number if it is an exiting event}
        }
        
        Collections.sort(listOfEvents, new Comparator<Event>() {
            @Override
            public int compare(Event event1, Event event2) {
                if (event1.edge != event2.edge) {  // edge point is not the same, sort normally
                    return event1.edge - event2.edge;
                } else {
                    return event2.height - event1.height;  // {Trick1: if two events are both entering event: will process higher one first;
                                                           //          if two events are both exiting event: will process lower one first;
                                                           //          if one is entering and the other is exiting: will process entering first even with the same absolute height
                                                           // }
                }
            }
        });
        
        PriorityQueue<Integer> pq = new PriorityQueue(1, new Comparator<Integer>() {
            /* use a maxHeap */
            @Override
            public int compare(Integer height1, Integer height2) {
                return height2 - height1;
            }
        });
        
        List<List<Integer>> res = new ArrayList();
        for (Event event: listOfEvents) {
            if (event.height > 0) {
                if (pq.isEmpty() || event.height > pq.peek()) res.add(Arrays.asList(event.edge, event.height));
                pq.offer(event.height);
            } else {
                pq.remove(-event.height);
                if (pq.isEmpty() || -event.height > pq.peek()) {
                    res.add(Arrays.asList(event.edge, pq.isEmpty() ? 0 : pq.peek()));
                }
            }
        }
        return res;
    }
    
    class Event {
        int edge;
        int height;
        Event(int e, int h) {
            edge = e;
            height = h;
        }
    }
}


12/17 二刷
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        for (int[] building : buildings) {
            int left = building[0], right = building[1], height = building[2];
            pq.offer(new int[]{left, height});
            pq.offer(new int[]{right, -height});
        }
        
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<Integer> heights = new PriorityQueue<>((a, b) -> b - a);
        heights.offer(0);
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int x = curr[0], y = curr[1];
            if (y >= 0) {
                int top = heights.peek();
                if (y > top) {
                    res.add(Arrays.asList(x, y));
                }
                heights.offer(y);
            } else {
                y = -y;
                heights.remove(y);
                int top = heights.peek();
                if (y > top) {
                    res.add(Arrays.asList(x, top));
                }
            }
        }
        
        return res;
    }
}


用TreeMap优化remove()的时间复杂度：（不能用TreeSet，因为可能存在多个相同的height，移除其中一个后剩下的依然能起作用）
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        for (int[] building : buildings) {
            int left = building[0], right = building[1], height = building[2];
            pq.offer(new int[]{left, height});
            pq.offer(new int[]{right, -height});
        }
        
        List<List<Integer>> res = new ArrayList<>();
        // PriorityQueue<Integer> heights = new PriorityQueue<>((a, b) -> b - a);
        // heights.offer(0);
        TreeMap<Integer, Integer> heights = new TreeMap<>();
        heights.put(0, 1);
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int x = curr[0], y = curr[1];
            if (y >= 0) {
                // int top = heights.peek();
                int top = heights.lastKey();
                if (y > top) {
                    res.add(Arrays.asList(x, y));
                }
                // heights.offer(y);
                heights.put(y, heights.getOrDefault(y, 0) + 1);
            } else {
                y = -y;
                // heights.remove(y);
                heights.put(y, heights.get(y) - 1);
                if (heights.get(y) == 0) {
                    heights.remove(y);
                }
                // int top = heights.peek();
                int top = heights.lastKey();
                if (y > top) {
                    res.add(Arrays.asList(x, top));
                }
            }
        }
        
        return res;
    }
}
