https://leetcode.com/problems/permutation-sequence/

// 1）需要预先计算从0到n的所有i!的值，作为每一层的permutation的个数
// 2）维护一个List，将index和value一一对应（由于是0-based，所以off by 1）
// 3）k首先减去1是为了把它转换成0-based的index
// 4）维护临时变量currIndex，表示当前的k会落在哪一个组，每个组都有(n - 1)!即f[n - 1]个数
// 5）根据nums List找到跟这个组对应的数字，然后把这个数字从nums中删除，表示已经使用过了
// 6）更新k为k % f[n - 1]，表示在新的组（范围更小）所处的位置，同时n--表示这一位数已经确定

class Solution {
    public String getPermutation(int n, int k) {
        int[] f = new int[n + 1];
        f[0] = 1;
        List<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1] * i;
            nums.add(i);
        }
        StringBuilder sb = new StringBuilder();
        k--;
        while (n >= 1) {
            int currIndex = k / f[n - 1];
            sb.append(nums.get(currIndex));
            nums.remove(currIndex);
            k = k % f[n - 1];
            n--;
        }
        return sb.toString();
    }
}