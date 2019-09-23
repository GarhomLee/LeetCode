https://leetcode.com/problems/ugly-number-iii/

// 对比：264. Ugly Number II，本题只要求可被a或b或c整除也就是被a，b，c整除后还能被别的树整除，而不要求只能被a，b，c整除。

// 思路：Math + Binary Search，视频讲解：https://leetcode.com/problems/ugly-number-iii/discuss/387673/Java-Binary-Search-Solution-with-Illustration-and-Explanation-Video
//         Binary Search：在当前范围[low:high]，取中间数mid，根据数学原理得到小于等于mid的、且为a或b或c的倍数的所有数的个数count。
//             可以发现，由于可被a或b或c整除的数的列表是从小到大排列的，因此mid越小count越小，mid越大count越大。
//             利用Binary Search不断搜索mid，直到找到某个点，使得大于这个点count >= n，小于这个点count < n。
//         Math：接下来的问题是：如何求出小于等于mid的、且为a或b或c的倍数的所有数的个数count？
//             假设小于等于mid的且为a的倍数的数集合为A，小于等于mid的且为b的倍数的数集合为B，小于等于mid的且为c的倍数的数集合为C，
//             那么就是要求出A ∪ B ∪ C = A + B + C - (A ∩ B) - (B ∩ C) - (A ∩ C) + (A ∩ B ∩ C)。
//             集合A中的数总共有(mid/a)个，而集合(A ∩ B)中的数共有(mid/a和b的最小公倍数)个，集合(A ∩ B ∩ C)中的数共有(mid/a和b和c的最小公倍数)个。
//             公式1: 求a和b的最小公倍数lcm，【需要得到a和b的最大公约数gcd】，结果 = a * b / gcd(a, b)。
//             公式2: 求a和b的最大公约数gcd，使用递归函数，需要记住。
// 时间复杂度：O(log(Integer.MAX_VALUE) * log(a+b+c))
// 空间复杂度：O(1)

class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        int low = 0, high = Integer.MAX_VALUE - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = count(mid, a, b, c);
            if (count >= n) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
    
    private int count(long num, long a, long b, long c) {
        return (int) (num / a + num / b + num / c - num / lcm(a, b) - num / lcm(b, c) - num / lcm(a, c) + num / lcm(a, lcm(b, c)));
    }
    
    private long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
    
    private long gcd(long a, long b) {
        if (a == 0) {
            return b;
        }
        
        return gcd(b % a, a);
    }
}