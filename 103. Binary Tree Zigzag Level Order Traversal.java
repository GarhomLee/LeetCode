https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

// 解法一：Iteration with Two Stacks
//        一个Stack为inStack，接受push进的元素；另一个为outStack，将元素pop出来加入List。
//        这种写法【在push和pop元素的时候就已经确定了方向】。
//        规定root的level为0。如果level为偶数，那么将下一层元素从左往右加进inStack，那么下一层最右边的元素就会
//        最先pop出来，加入List。这时level为奇数。最先pop出的最右边元素的下一层从右往左加进inStack，那么它的下一层
//        最左边元素就会先pop出来加入List。

class Solution {
    List<List<Integer>> res = new ArrayList();
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return res;
        
        Stack<TreeNode> inStack = new Stack(), outStack = new Stack();
        int level = 0;
        outStack.push(root);  // initialize

        while (!outStack.isEmpty()) {  // there are still some elements to process
            List<Integer> list = new ArrayList();
            while (!outStack.isEmpty()) {  // processing the elements in each level
                TreeNode curr = outStack.pop();
                list.add(curr.val);
                if (level % 2 == 0) {  // determine the direction of next level by pushing and popping
                    if (curr.left != null) inStack.push(curr.left);  // push into inStack
                    if (curr.right != null) inStack.push(curr.right);
                } else {
                    if (curr.right != null) inStack.push(curr.right);
                    if (curr.left != null) inStack.push(curr.left);
                }  
            }
            /* swap and get outStack ready */
            Stack<TreeNode> temp = inStack;
            inStack = outStack;
            outStack = temp;
            
            level++;  // update level
            res.add(list);  // store current level of result
        }
        return res;
    }
}


// 解法二：Iteration with Queue
//        用Queue来存储每层的TreeNode，属于tree breadth-first traversal的常规做法。
//        和解法一不同的是，虽然每层元素都是从左往右加入Queue，但取出元素后加入List的方向依据level而变化，
//        也就是【将加入List的顺序判断放在了从Queue取出元素后】。

class Solution {
    List<List<Integer>> res = new ArrayList();
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return res;
        
        Queue<TreeNode> deque = new ArrayDeque();
        int level = 0;
        deque.offer(root);

        while (!deque.isEmpty()) {
            int size = deque.size();
            LinkedList<Integer> list = new LinkedList();  // we need addLast()
            while (size > 0) {
                TreeNode curr = deque.poll();
                size--;
                if (level % 2 == 0) {
                    list.addLast(curr.val);  // add into List from left to right
                } else {
                    list.addFirst(curr.val);  // add into List from right to left
                }  
                /* do not discriminate the directions to add into Queue */
                if (curr.left != null) deque.offer(curr.left);
                if (curr.right != null) deque.offer(curr.right);
            }
            level++;
            res.add(list);
        }
        return res;
    }
}


// 解法三：Recursion

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> listOfList = new ArrayList<>();
        zigzag(listOfList, root, 0);
        return listOfList;
    }
    private void zigzag(List<List<Integer>> listOfList, TreeNode root, int level) {
        if (root == null) return;
        if (level == listOfList.size()) listOfList.add(new LinkedList<Integer>());
        if (level % 2 == 0) listOfList.get(level).add(listOfList.get(level).size(), root.val);  // add from left to right
        else listOfList.get(level).add(0, root.val);  // add from right to left
        zigzag(listOfList, root.left, level + 1);
        zigzag(listOfList, root.right, level + 1);
    }
}