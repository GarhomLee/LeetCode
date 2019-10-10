https://leetcode.com/problems/maximum-frequency-stack/

// 思路：List of Stack + HashMap
//         维护变量：
//         1）List<Stack<Integer>>，其中List的index对应不同Stack的frequency。
//         2）HashMap，key为所有push进来的元素，value为该元素出现的次数frequency，用来在List里找到对应的Stack。
//         实现以下功能：
//         1）void push(int x)，将元素x放进maximum frequency stack。
//             首先，将元素x放进HashMap。
//             然后，根据HashMap对应的value决定放进List的哪个Stack，如果超过了当前的list.size()，则新增一个Stack。
//         2）int pop()，根据题意将frequency最大的所有元素中最近被push进来的元素移除。
//             取出List尾部的Stack中的顶部元素。如果取出后这个Stack空了，那么将这个Stack移除。同时，维护HashMap的
//             对应value减少1.
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class FreqStack {
    List<Stack<Integer>> list;
    Map<Integer, Integer> map;
    
    public FreqStack() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }
    
    public void push(int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);  // keep record of the requency of element x
        int freq = map.get(x);

        if (freq > list.size()) {  // the frequency of x is greater than all other elements, create a new Stack corresponding to this frequence
            list.add(new Stack<Integer>());
        }

        list.get(freq - 1).push(x);  // push x into the corresponding Stack
    }
    
    public int pop() {
        int size = list.size();
        int res = list.get(size - 1).pop();  // get the top
        map.put(res, map.get(res) - 1);  // update its frequency

        if (list.get(size - 1).isEmpty()) {  // remove the last empty Stack
            list.remove(size - 1);
        }
        
        return res;
    }
}