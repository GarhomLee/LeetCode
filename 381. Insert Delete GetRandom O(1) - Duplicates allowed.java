https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/

// 这题是380. Insert Delete GetRandom O(1)的follow-up，允许duplicate的存在。
// 思路：同样是HashMap + List，要求所有操作平均时间复杂度O(1)
//      内部维护三个变量：
//      1）Map，key为Integer元素，value为元素在List中的位置的集合Set。
//         因为后续删除元素要求时间复杂度O(1)，所以用Set比用List好。
//      2）List，实际存放元素
//      3）Random，得到随机数
//      实现以下功能：
//      1）insert(int val)，在Map中记录val对应插入的位置，放入Set中，然后将val加在List末尾
//      2）remove(int val)，【注意操作的顺序（对比380. Insert Delete GetRandom O(1)）】。
//          从Map中得到val的位置index，从List中得到最末尾的元素lastElement。
//          先替换List中index的元素，然后【删除val位置集合中的index，再在lastElement的位置集合中加入index，
//          再在lastElement位置集合中删除最后元素】，最后List删除最末尾的元素。如果val位置集合为空，那么把val
//          从Map中删除。
//         （假设：val == lastElement，都在List最末尾，如果先在List中删除再加入，就会出错，所以要先更新再删除）
//      3）getRandom()，根据List的长度生成随机数，然后直接从List取
// 注意：没有处理当数据集为空时getRandom()的返回结果
// 犯错点：1.如果先把index加入lastElement的位置集合，再删除lastElement的位置集合中的末尾位置和val的位置集合中的index位置，
//          当val == lastElement但它们在不同位置时，index在lastElement的位置集合中已经存在，所以加入的操作没有发生任何变化；
//          但后面的两个删除操作会导致结果是删除两个元素，而非交换元素位置。
//          因此，要先删除val位置集合中的index，再在lastElement的位置集合中加入index，再在lastElement位置集合中删除最后元素。
//          另外，还要注意val == lastElement且val在最末尾的情况。

class RandomizedCollection {
    Map<Integer, Set<Integer>> map;
    List<Integer> list;
    Random random;
    
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap();
        list = new ArrayList();
        random = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean isNew = !map.containsKey(val);
        if (isNew) map.put(val, new HashSet());
        map.get(val).add(list.size());  // map val->its index
        list.add(val);  // add val to the end of List
        return isNew;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val).iterator().next();
        int lastElement = list.get(list.size() - 1);
        
        list.set(index, lastElement);

        /* pay attention to the sequence of the following three lines */
        map.get(val).remove(index);  // {Correction 1}
        map.get(lastElement).add(index); 
        map.get(lastElement).remove(list.size() - 1);
        //map.get(val).remove(index);  // {Mistake 1: the index of val should be deleted before the index is mapped to lastElement}
        
        list.remove(list.size() - 1); 
        
        if (map.get(val).isEmpty()) {
            map.remove(val);  // if there is no val in List, remove val from Map
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
