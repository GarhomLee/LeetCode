https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/

// 对比：157. Read N Characters Given Read4的follow-up，允许read()调用多次，每次从上次读取的最后一个字符的下一位开始。

// 思路：Count with global variable
//         维护以下【全局变量】，使得新的read()的调用可以从read4()结果中还没被使用的字符开始读取：
//         1）int LEN = 4，这是因为read4()返回最多4个字符的长度。
//         2）char[] temp，存放read4()从file中读取的结果，初始化为长度LEN的字符串数组。
//         3）count1，当前temp数组被读取过的字符个数，同时也指示temp数组下一个可以被读取的字符位置。初始化为4.
//         4）size1，当前temp数组最多可被读取的字符个数，为read4()的返回值。可初始化为任意数。
//         在实现read()时，维护的变量isValid，count2等是临时变量，只和这一次的read()调用有关。
//         step1:如果count1==LEN，说明目前得到的temp数组已经被读取完，因此需要重新调用read4()得到新的字符，
//             同时更新size1和count1。
//         step2:从temp数组中读取最多size1个字符，或者读取字符个数到达给定的n个，存入buf数组中，同时更新count2.
//         step3:如果发现size1 < LEN，说明file中的字符已经读取完；或者count2==n，已经读取完足够多的字符，那么
//             isValid设为false，从而跳出循环。
//         最后返回值为count2。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

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

    /* all variables related to read4() should be set as global variables */
    final int LEN = 4;
    char[] temp = new char[LEN];
    int count1 = 4;  // indicate both the number and the position of current char in temp array
    int size1 = 0;
    
    public int read(char[] buf, int n) {
        boolean isValid = true;
        int count2 = 0;  // record num of chars that have been read into buf array
        while (isValid) {
            if (start == LEN) {
                size1 = read4(temp);
                count1 = 0;
            }
            
            /* loop if chars can be read from temp array and can be filled into buf array */
            while (count1 < size1 && count2 < n) {  // use n as size2
                buf[count2++] = temp[count1++];
            }
            
            /* set the flag isValid as false if it reaches the end of either temp array or buf array */
            if (size1 < LEN || count2 == n) {
                isValid = false;
            }
            
        }
        
        return count2;
    }
}