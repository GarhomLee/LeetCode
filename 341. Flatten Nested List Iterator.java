https://leetcode.com/problems/flatten-nested-list-iterator/

// 思路：385. Mini Parser的相反过程。
//     Depth-first search，用recursion将所有的NestedInteger全部放入List<Integer>里。
//     利用index指针来指示next()返回的元素。

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    List<Integer> flattenedList = new ArrayList();
    int index = 0;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        /* dfs, flatten nestedList into flattenedList which is a List of Integers */
        for (NestedInteger ni : nestedList) {
            flatten(ni);
        }
    }
    
    /* helper method, depth-first search */
    private void flatten(NestedInteger ni) {
        /* termination condition */
        if (ni.isInteger()) {
            flattenedList.add(ni.getInteger());
            return;
        }
        /* dfs */
        for (NestedInteger child : ni.getList()) {
            flatten(child);
        }
    }
    
    @Override
    public Integer next() {
        return flattenedList.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < flattenedList.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */