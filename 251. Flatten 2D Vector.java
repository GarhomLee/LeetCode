https://leetcode.com/problems/flatten-2d-vector/

// 解法一：常规遍历
//         维护全局变量row，col，指向下一次调用next()时的返回值的位置，初始化为row=0，col=0。
//         利用自定义的void update()，将当前row和col更新为非空的合法元素位置。
//         实现以下功能：
//         1）int next()，得到下一个合法元素。
//             首先，调用update()得到最新的非空的合法元素位置。然后，直接返回matrix[row][col]，
//             同时要更新col++得到下一个可能的元素位置。
//         2）boolean hasNext()，判断是否存在下一个合法元素。
//             首先，调用update()得到最新的非空的合法元素位置。判断这个位置的v是否不超过边界matrix.length。
// 时间复杂度：O(n)
// 空间复杂度：O(1)
// 犯错点：1.边界条件错误：实现update()时，通过比较col和matrix[row].length来得到合法元素位置，
//             要确保row在合法范围内。

class Vector2D {
    int[][] matrix;
    int row;
    int col;
    
    public Vector2D(int[][] v) {
        matrix = v;
        row = 0;
        col = 0;
    }
    
    public int next() {
        update();
        return matrix[row][col++];
    }
    
    public boolean hasNext() {
        update();
        return row < matrix.length;
    }
    
    private void update() {
        //while (row < matrix.length && col == matrix[row].length)  // {Mistake 1}
        while (row < matrix.length && col == matrix[row].length) {  // {Correction 1}
            row++;
            col = 0;
        }
    }
}


// 解法二：List + Iterator
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Vector2D {
    List<Integer> list;
    Iterator<Integer> iterator;
    
    public Vector2D(int[][] v) {
        list = new ArrayList<>();
        for (int[] row: v) {
            for (int value: row) {
                list.add(value);
            }
        }
        
        iterator = list.iterator();
    }
    
    public int next() {
        return iterator.next();
    }
    
    public boolean hasNext() {
        return iterator.hasNext();
    }
}