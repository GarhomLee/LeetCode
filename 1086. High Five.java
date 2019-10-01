https://leetcode.com/problems/high-five/

// 思路：Sort
//         step1:将items数组按id从小到大、id相同时分数从大到小排序。
//         step2:扫描id相同的元素，取前5个元素，求平均，放入List。
//         step3:将List转换为int[]，返回之。
// 时间复杂度：O(m * n * log(m * n))
// 空间复杂度：O(m * n)

class Solution {
    public int[][] highFive(int[][] items) {
        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] == i2[0] ? i2[1] - i1[1] : i1[0] - i2[0];
            }
        });
        
        List<int[]> list = new ArrayList<>();
        int start = 0;
        while (start < items.length) {
            int id = items[start][0];
            int end = start;
            while (end + 1 < items.length && items[end + 1][0] == id) {
                end++;
            }
            
            int sum = 0;
            for (int i = start; i < start + 5; i++) {
                sum += items[i][1];
            }
            
            list.add(new int[]{id, sum / 5});
            start = end + 1;
        }
        
        int[][] res = new int[list.size()][2];
        for (int j = 0; j < res.length; j++) {
            res[j] = list.get(j);
        }
        
        return res;
    }
}