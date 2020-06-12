https://leetcode.com/problems/first-unique-number/

idea: HashMap + Queue
time complexity: 
    FirstUnique(): O(n)
    add(): O(1)
    showFirstUnique(): O(a), a=num of add() ops -> amortized O(1)
space complexity: O(n)

class FirstUnique {
    Map<Integer, Integer> map;
    Queue<Integer> queue;

    public FirstUnique(int[] nums) {
        map = new HashMap<>();
        queue = new LinkedList<>();
        for (int num : nums) {
            add(num);
        }
    }
    
    public int showFirstUnique() {
        while (!queue.isEmpty() && map.get(queue.peek()) > 1) {
            queue.poll();
        }
        
        return queue.isEmpty() ? -1 : queue.peek();
    }
    
    public void add(int value) {
        if (!map.containsKey(value)) {
            queue.offer(value);
        }
        map.put(value, map.getOrDefault(value, 0) + 1);
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */