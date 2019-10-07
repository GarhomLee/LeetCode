https://leetcode.com/problems/zigzag-iterator/

// 解法一：维护跟每个List对应的指针。
// 时间复杂度：O(l1 + l2), l1=l1.size(), l2=l2.size()
// 空间复杂度：O(1)

public class ZigzagIterator {
    int i, j;
    List<Integer> l1, l2;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i = 0;
        j = 0;
        l1 = v1;
        l2 = v2;
    }

    public int next() {
        if (i < l1.size() && j < l2.size()) {
            return i <= j ? l1.get(i++) : l2.get(j++);
        }
        
        if (i < l1.size()) {
            return l1.get(i++);
        }
        
        return l2.get(j++);
    }

    public boolean hasNext() {
        return i < l1.size() || j < l2.size();
    }
}


解法二：Queue，取出每个List的iterator加入Queue中，每次将Queue头部的iterator取出，调用hasNext()和next()后，放回Queue尾部。