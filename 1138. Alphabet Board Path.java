https://leetcode.com/problems/alphabet-board-path/

// 思路：常规遍历搜索
//         将26个字母放置在5x5的矩阵中，多出来的'z'放在额外多出来的一行里。
//         从当前位置[row][col]到下一个字母的位置[nextRow][nextCol]，实际上走的是曼哈顿距离。只需要从一个方向走到底，
//         再换另一个方向走到底，直到到达[nextRow][nextCol]。然后更新[row][col]。
// 犯错点：1.对于到'z'即board[5][5]的情况比较特殊，所以要注意尝试的顺序，要兼顾从其他元素到'z'以及从'z'出发到其他元素。
//         可以用左、下、上、右的顺序。

class Solution {
    public String alphabetBoardPath(String target) {
        /* corner case */
        if (target == null || target.length() == 0) return "";

        // /* totally unnecessary */
        // List<List<Character>> board = new ArrayList<>();
        // board.add(Arrays.asList('a','b','c','d','e'));
        // board.add(Arrays.asList('f','g','h','i','j'));
        // board.add(Arrays.asList('k','l','m','n','o'));
        // board.add(Arrays.asList('p','q','r','s','t'));
        // board.add(Arrays.asList('u','v','w','x','y'));
        // board.add(Arrays.asList('z'));
        
        StringBuilder sb = new StringBuilder();
        int row = 0, col = 0;
        for (char c: target.toCharArray()) {
            int nextRow = (c - 'a') / 5, nextCol = (c - 'a') % 5;
            while (nextCol < col) {
                sb.append('L');
                col--;
            }
            while (nextRow > row) {
                sb.append('D');
                row++;
            }
            while (nextRow < row) {
                sb.append('U');
                row--;
            }
            while (nextCol > col) {
                sb.append('R');
                col++;
            }
            sb.append('!');
            row = nextRow;
            col = nextCol;
            
        }
        
        return sb.toString();
    }
}