https://leetcode.com/problems/mini-parser/

// 思路：341. Flatten Nested List Iterator的相反过程。用Iteration，遍历扫描。
//     利用Stack存储上一层NestedInteger的情况。
//     遍历String s，可能遇到5种情况：
//     1）'-'，sign标记成-1
//     2）'['，遇到一个新的List，将上层List放进Stack，curr更新成新的List。
//         注意：上层List放进Stack前，先判断上层List（即更新前的curr）是否为null。如果为null，说明更新后的curr是最外层List，所以没有上层List，不用放进Stack。
//         同时更新value，isValue，sign。
//     3）']'，List结束，那么是不是正在处理Integer value。如果是，那么加入curr中。否则，意味着当前List处理完毕，需要加入上层List中。
//         先进进行一次判断，如果存在上层List，那么Stack不为空，将curr加入上层List。否则，Stack为空，curr为最外层List，什么都不需要做。
//         同时更新value，isValue，sign
//     4）','，表示当前List的其中一个并列元素处理完成，只需要看完成的是不是Integer value。如果是，加入curr。否则完成的是List，已经在']'中处理过，所以现在什么都不用做。
//         同时更新value，isValue，sign
//     5）其他情况，即数字，更新value，同时isValue标记成true。
//     因为本题可以出现"123"和"[123]"均为合法的情况，所以最后表示List的curr可能为空，返回前那么需要进行一次判断。
//     如果s[0]!='['，说明整个s是一个数字，那么要用NestedInteger(int value)这个constructor。否则，s包含List，返回curr即可。
// 时间复杂度：O(m), m=s.length
// 空间复杂度：O(n), n=the depth of NestedInteger
// 犯错点：1.NestedInteger curr需要初始化为null，因为curr表示当前的List，而初始状态没有遇到'['，所以应该为null
//        2.如果c == '['，说明需要产生新的List，所以curr初始化为new NestedInteger()。
//          而在用Stack临时存储上一层List之前，需要先判断上一层List是否为null，如果不为null才能push进Stack。
//        3.处理到c == ']'，需要更新curr为上一层List。如果Stack为空，那么说明当前curr的List就是最外层List。否则更新之。
// 注意：跟394. Decode String相比，本题可以出现"123"和"[123]"均为合法的情况，所以要多做一次curr是否为null和Stack是否为空的判断。

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    boolean isValue = false;  // indicates whether it is an Integer or a List
    int value = 0;  // the value of this Integer
    int sign = 1;  // the sign of this Integer
    
    public NestedInteger deserialize(String s) {
        Stack<NestedInteger> stack = new Stack();
        //NestedInteger curr = new NestedInteger();  // {Mistake 1}
        NestedInteger curr = null;  // {Correction 1}
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '-') {  // encounter negative number
                sign = -1;
            } else if (c == '[') {
                if (curr != null) {  // {Mistake 2: if no such evaluation of curr, "[[]]" and "[[[]]]" will also be [], even though they should've been [[]] and [[[]]]} {Correction 2}
                    stack.push(curr);
                }
                curr = new NestedInteger();  // {Correction 2: once there is a '[', there must be a NestedInteger, so from here we can safely initialize curr = new NestedInteger()}
                reset();  // reset info about Integer value

            } else if (c == ']') {
                if (isValue) curr.add(new NestedInteger(sign * value));  // if we are processing an Integer value in curr List, we should add Integer value into curr List
                
                /* done with current [] List range, return to the outside List. If stack is already empty, curr List is the most outside List */
                if (!stack.isEmpty()) {  // {Mistake 3} {Correction 3: if stack is not empty, return to the outside List}
                    stack.peek().add(curr);
                    curr = stack.pop();
                }
                reset();  // reset info about Integer value

            } else if (c == ',') {  // processing parallel elements in curr
                if (isValue) curr.add(new NestedInteger(sign * value));  // if it is an Integer, add to curr List. Otherwise, it would be a [] List and already processed.
                reset();  // reset info about Integer value

            } else {
                isValue = true;  // this is an Integer
                value = value * 10 + (c - '0');  // update Integer value
            }
        }
        
        return s.charAt(0) == '[' ? curr : new NestedInteger(sign * value);  // evaluate if the whole String can directly convert to an Integer
    }
    
    /* reset boolean isValue, int value, int sign */
    private void reset() {
        isValue = false;
        value = 0;
        sign = 1;
    }
}