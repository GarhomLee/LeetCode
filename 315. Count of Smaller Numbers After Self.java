https://leetcode.com/problems/count-of-smaller-numbers-after-self/

// 解法一：Binary Search Tree
//         只计算比当前数小的数的个数，可以利用BST的性质。
//         新建class Node，存储3个信息：当前Node的值val，有多少相同（或者说重复）的val值dupCount，和nums数组中在val左边的
//         且比val小的（即位于BST中val的左子树）的元素的个数（包含重复元素）leftCount。
//         从右往左扫描nums数组，并且依次用递归的方式构建BST，在这个过程中得到计数值存在res数组的相应位置，最后返回res数组（转换成List）。
//         在递归过程中，最重要的是两点：
//         （1）比大小，决定val插入的位置和更新Node的attribute。
//         （2）维护recursion function中的参数count，指的是对于当前扫描到的node的右边在nums数组中比val小的元素个数。
//         如果nums数组有比当前扫描到的node小的数在它相应数组位置的左边，就会被记录在leftCount中。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)
// 犯错点：1.root要显式地初始化为null
//         2.当要插入的val存在于BST中，res[i]是count和leftCount的和，因为要算上nums数组中当前val和前一个val所处位置之间的比val小的元素个数，即leftCount

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        Integer[] res = new Integer[nums.length];  // Attention: declare res array as an Integer array so that it can be a parameter of Arrays.asList() function
        //Node root;  // {Mistake 1: root should be explicitly initialized, even though it would be initialized as null by default}
        Node root = null;  // {Correction 1}
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(root, nums[i], i, res, 0);
        }
        return Arrays.asList(res);
    }
    /** insert the val into the tree where the root is Node node, and return the root of result tree.
        node: the root of the tree; 
        val: the element to be inserted; index: the index of val in nums array;
        res: the result array  where res[i] is the number of smaller elements to the right of val, which is nums[i];
        count: the number of smaller elements than val to the right of currently visiting node in nums array
     */
    private Node insert(Node node, int val, int index, Integer[] res, int count) {
        if (node == null) {  // val does not exist in the tree
            Node root = new Node(val);
            res[index] = count + root.leftCount;
            return root;
        }
        
        if (val == node.val) {  // val has already existed in the tree
            node.dupCount++;  // increase dupCount
            //res[index] = count;  // {Mistake 2}
            res[index] = count + node.leftCount;  // {Correction 2: when the val to be inserted has existed in the BST, the number of smaller elements to the right of val in nums array should be sum of count (the number of smaller elements in the tree which are its parents and are visited) and node.leftCount (the number of smaller elements in the tree which are its children and are NOT visited)}
        } else if (val < node.val) {  // val should be inserted in the left child tree of current node
            node.leftCount++;  // increase leftCount because val would become part of its left child tree
            node.left = insert(node.left, val, index, res, count);
        } else {  // val should be inserted in the right child tree of current node
            node.right = insert(node.right, val, index, res, count + node.dupCount + node.leftCount);  // pass new count number because
        }
        return node;
    }
    
    class Node {
        int val;  // the value of current Node
        int dupCount;  // the number of the same value of current Node in the tree
        int leftCount;  // the number of the elements smaller than value of current Node in the left child tree
        Node left;
        Node right;
        public Node(int v) {
            val = v;
            dupCount = 1;
            leftCount = 0;
        }
    }
}

解法二：Binary Indexed Tree


解法三：Divide & Conquer

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Node[] arr = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new Node(i, nums[i], 0);
        }
        
        mergesort(arr, 0, nums.length - 1);
        
        Arrays.sort(arr, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.index - n2.index;
            }
        });
        for (Node node : arr) {
            res.add(node.count);
        }
        return res;
    }
    
    private void mergesort(Node[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        
        int mid = low + (high - low) / 2;
        mergesort(arr, low, mid);
        mergesort(arr, mid + 1, high);
        
        for (int left = low, right = mid + 1; left <= mid; left++) {
            while (right <= high && arr[right].val < arr[left].val) {
                right++;
            }
            
            arr[left].count += (right - (mid + 1));
        }
        
        Arrays.sort(arr, low, high + 1, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.val - n2.val;
            }
        });
    }
    
    class Node {
        int index, count, val;
        public Node(int i, int v, int cnt) {
            index = i;
            val = v;
            count = cnt;
        }
    }
}