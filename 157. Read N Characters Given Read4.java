https://leetcode.com/problems/read-n-characters-given-read4/

// 思路：Count
//         维护变量：count，表示目前得到的字符数量；isValid，表示是否能够获得更多字符（如果read4()还能得到4个字符）
//         或需要获得更多字符（如果count < n）。
//         当isValid为true时，不断循环，调用read4()从file中得到size1个字符，依次填进buf字符数组中，并判断isValid
//         是否需要更新为false。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.边界条件错误：不能以count是否达到n来作为循环结束条件，因为有可能本身file里的字符串长度也不足n，那么就会
//             进入死循环。因此，还需要判断是否在达到n个字符前就已经把file里的所有字符读完，如果是，那么isValid就
//             会设置成false，那么就跳出循环。

/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf);
 */
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int count = 0;
        boolean isValid = true;
        //while (count < n)  // {Mistake 1}
        while (isValid) {  // {Correction 1}
            char[] temp = new char[4];
            int size1 = read4(temp);
            int size2 = n - count;
            for (int i = 0; i < Math.min(size1, size2); i++) {
                buf[count++] = temp[i];
            }
            
            // {Mistake 1}
            if (size1 < 4 || count >= n) {
                isValid = false;
            }  // {Correction 1}
        }
        
        return count;
    }
}