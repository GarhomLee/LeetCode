https://leetcode.com/problems/random-flip-matrix/

// 对比：380. Insert Delete GetRandom O(1)

// 思路：HashMap，Fisher-Yates shuffle algo，参考https://www.w3xue.com/exp/article/201810/6024.html
//         利用HashMap来模拟在数组中随机选一个数，然后跟当前末尾元素交换并缩小数组可选范围，最后得到随机数组的过程。
//         维护变量total，表示当前可选的index总数，那么可选范围为[0:total)。
//         HashMap中，key为[0:total)范围内的任意index，value为跟这个index做了交换后的index。
//         实现以下功能：
//         1）int[] flip()，首先，从当前范围[0:total)中随机抽取一个数字randIndex。如果randIndex在之前已经被
//             抽到过，那么跟它交换的index被记录在map里，所以实际上应该抽的数字是actualIndex是map.get(randIndex)；
//             如果randIndex没被抽到过，那么actualIndex就是randIndex。
//             既然randIndex被抽到了，那么就需要交换位置，更新map。如果位置total（这里total已经自减1）被抽到过了，
//             那么实际上要和randIndex交换的位置应该是map.get(total)；如果位置total没有被抽到过，那么和randIndex
//             交换的位置就是total自己。
//             返回值是根据一维坐标actualIndex和列数colLen转换成的二维坐标。
//         2）void reset()，将HashMap清空，同时total重置成rowLen*colLen
// 时间复杂度：flip(): O(k), k=times of calling flip(); reset(): O(n)
// 空间复杂度：O(k), k=times of calling flip()

class Solution {
    int rowLen;
    int colLen;
    int total;
    Map<Integer, Integer> map;
    Random rand;
    
    public Solution(int n_rows, int n_cols) {
        rowLen = n_rows;
        colLen = n_cols;
        total = rowLen * colLen;
        map = new HashMap<>();
        rand = new Random();
    }
    
    public int[] flip() {
        int randIndex = rand.nextInt(total--);  // get a random index from [0:total), than update total
        int actualIndex = map.getOrDefault(randIndex, randIndex);  // get the actual index by asking if randIndex has been used
        map.put(randIndex, map.getOrDefault(total, total));  // link randIndex to another index which is not used, so that next time when randIndex is picked we can find the actual unused index
        return new int[]{actualIndex / colLen, actualIndex % colLen};
    }
    
    public void reset() {
        map.clear();
        total = rowLen * colLen;
    }
}