https://leetcode.com/problems/dinner-plate-stacks/

// 思路：List of Stack + 2 TreeSet
//         要理解题意，如果位于中间位置的某个Stack变成了空，也不需要删除。可以将空Stack留着，不需要将右边的Stack左移。
//         维护以下变量：
//         1）int capacity，记录给定的Stack的最大容量。
//         2）List<Stack<Integer>> stacks，存放所有的Stack。
//         3）两个TreeSet，一个是pushable，记录可以进行push()的Stack的位置；一个是popable，记录
//             可以进行pop()的Stack的位置。由于使用TreeSet，所以这些位置都已经排好序。
//         实现以下功能：
//         1）void push(int val)，将val放进最左边的有可用空间的Stack中。
//             先判断pushable中是否存在有可用空间的Stack，如果都满了，那么需要在List末尾增加一个Stack，同时将它的位置放入pushable。
//             从pushable中取出最小的index，将val放入对应的Stack。因为这个Stack一定有了元素，所以要将它的index放入popable。
//             再判断放了val之后这个Stack是否满了。如果满了，那么需要从pushable中将它的index删除。
//         2）int pop()，取出最右边的非空Stack的顶部元素，如果没有则返回-1。
//             先判断popable中是否存在非空Stack，如果没有，那么返回-1。
//             如果有，从popable中得到最大的index，直接调用popAtStack()。
//         3）int popAtStack(int index)，取出指定位置的Stack的顶部元素，如果没有则返回-1。
//             先判断popable中是否存在指定的index，如果没有，那么返回-1。
//             从指定的index的Stack中取出顶部元素，因为这个Stack一定有空间了，所以要将它的index放入pushable。
//             还需要判断取出元素后这个Stack是否为空。如果为空，那么需要从popable中将它的index删除。
// 时间复杂度：push(): O(log s); pop(): O(log s); popAtStack(): O(log s)
// 空间复杂度：O(s), s=num of Stacks=n / capacity, n=num of elements

class DinnerPlates {
    int capacity;
    List<Stack<Integer>> stacks;
    TreeSet<Integer> pushable;
    TreeSet<Integer> popable;
    
    
    public DinnerPlates(int cap) {
        capacity = cap;
        stacks = new ArrayList<>();
        pushable = new TreeSet<>();
        popable = new TreeSet<>();
    }
    
    public void push(int val) {
        if (pushable.isEmpty()) {
            pushable.add(stacks.size());
            stacks.add(new Stack<>());
        }
        int index = pushable.first();
        Stack<Integer> stack = stacks.get(index);
        stack.push(val);
        popable.add(index);
        if (stack.size() == capacity) {
            pushable.remove(index);
        }
    }
    
    public int pop() {
        if (popable.size() == 0) {
            return -1;
        }
        int index = popable.last();
        return popAtStack(index);
    }
    
    public int popAtStack(int index) {
        if (!popable.contains(index)) {
            return -1;
        }
        Stack<Integer> stack = stacks.get(index);
        int res = stack.pop();
        pushable.add(index);
        if (stack.size() == 0) {
            popable.remove(index);
        }
        return res;
    }
}