https://leetcode.com/problems/design-hashset/

// 思路：根据数据规模和数据类型(int)，利用boolean array来模拟hash。

class MyHashSet {
    boolean[] contains;
    /** Initialize your data structure here. */
    public MyHashSet() {
        contains = new boolean[1000001];
    }
    
    public void add(int key) {
        contains[key] = true;
    }
    
    public void remove(int key) {
        contains[key] = false;
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return contains[key];
    }
}