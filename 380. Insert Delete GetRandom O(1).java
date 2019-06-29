https://leetcode.com/problems/insert-delete-getrandom-o1/

// 思路：HashMap + List，要求所有操作平均时间复杂度O(1)
//      内部维护三个变量：
//      1）Map，key为Integer元素，value为元素在List中的位置
//      2）List，实际存放元素
//      3）Random，得到随机数
//      实现以下功能：
//      1）insert(int val)，在Map中记录val对应插入的位置，然后将val加在List末尾
//      2）remove(int val)，【注意操作的顺序（对比381. Insert Delete GetRandom O(1) - Duplicates allowed）】。
//         从Map中得到val的位置index，从List中得到最末尾的元素lastElement。
//         先更新Map中lastElement对应的位置，同时List中index的元素被lastElement替换，然后再删除Map中val的信息，List删除最末尾的元素。
//         （假设：val和lastElement是同一个元素，在List最末尾，如果先删除再加入，就会出错，所以要先更新再删除）
//      3）getRandom()，根据List的长度生成随机数，然后直接从List取
// 注意：没有处理当数据集为空时getRandom()的返回结果

class RandomizedSet {
    Map<Integer, Integer> map;  // key is Integer element, value is its index in List
    List<Integer> list;  // store all elements
    Random random;
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap();
        list = new ArrayList();
        random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, list.size());  // map the inserted value to its index in the List
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);  // get the index of the target element
        int lastElement = list.get(list.size() - 1);  // get the last element
        list.set(index, lastElement);  // move the last element to the position of the target element
        map.put(lastElement, index);  // update Map
        
        list.remove(list.size() - 1);  // remove the last position in List
        map.remove(val);  // remove the target element in Map
        
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}