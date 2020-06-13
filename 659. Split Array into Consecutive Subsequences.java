https://leetcode.com/problems/split-array-into-consecutive-subsequences/

solution1: PriorityQueue (minHeap). Referring to: https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/130452/20ms-Java-PriorityQueue-with-Explanations
time complexity: O(n log n)
space complexity: O(n) (eg. [1,1,1,1,1,2,2,2,2,2,3,3,3,3,3])

class Solution {
    public boolean isPossible(int[] nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1]) {
                    return (a[1] - a[0]) - (b[1] - b[0]);
                }
                return a[1] - b[1];
            }
        });
        
        for (int num : nums) {
            while (!pq.isEmpty() && pq.peek()[1] + 1 < num) {
                int[] curr = pq.poll();
                if (curr[1] - curr[0] + 1 < 3) {
                    return false;
                }
            }
            
            if (pq.isEmpty() || pq.peek()[1] == num) {
                pq.offer(new int[]{num, num});
            } else {    // pq.peek()[1] + 1 == num
                int[] curr = pq.poll();
                curr[1] = num;
                pq.offer(curr);
            }
        }
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (curr[1] - curr[0] + 1 < 3) {
                return false;
            }
        }
        
        return true;
    }
}


solution2: HashMap. Referring to: https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106496/Java-O(n)-Time-O(n)-Space
    -Use one HashMap to record the frequency count of each num, and another HashMap to record the count of flags of each num,
     where the flag means this num can be the new end of a valid consecutive seq.
time complexity: O(n)
space complexity: O(n)


class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();    // num -> count of num
        Map<Integer, Integer> flagMap = new HashMap<>();    // num -> count of flags this same num can use to form consecutive seq
        
        for (int num: nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        for (int num: nums) {
            if (countMap.get(num) == 0) continue;
            
            if (flagMap.getOrDefault(num, 0) > 0) {
                // the new end of an existing valid consecutive seq
                flagMap.put(num, flagMap.get(num) - 1);
                flagMap.put(num + 1, flagMap.getOrDefault(num + 1, 0) + 1);
            } else if (countMap.getOrDefault(num + 1, 0) > 0 && countMap.getOrDefault(num + 2, 0) > 0) {
                // start of a new valid consecutive seq
                countMap.put(num + 1, countMap.get(num + 1) - 1);
                countMap.put(num + 2, countMap.get(num + 2) - 1);
                flagMap.put(num + 3, flagMap.getOrDefault(num + 3, 0) + 1);
            } else {
                return false;
            }
            
            countMap.put(num, countMap.get(num) - 1);
        }
        
        return true;
    }
}