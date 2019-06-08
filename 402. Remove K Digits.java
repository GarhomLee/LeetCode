https://leetcode.com/problems/remove-k-digits/

// 思路：Greedy+Stack
//     遍历String num，使得在index i，要移除num[0:i-1]中比num[i]大的k个字符。可以借助Stack来存储当前剩下的字符。
//     遍历完成，如果还有k>0，那么从后往前移除剩下的k个字符。
//     将Stack中的字符转化为String，是逆序的，先将末尾的0去掉（剩1个0时除外），这样reverse成正序后就不会用leading 0s。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public String removeKdigits(String num, int k) {
        if (k <= 0) return num;
        if (k == num.length()) return "0";
        
        /* remove the larger numbers from left to right */
        Stack<Character> stack = new Stack();
        for (char c: num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && c < stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        
        /* remove the remaining numbers from right to left */
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        /* build the String reversely from getting each char from top of Stack */
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        /* remove the leading 0s except for the only 0 */
        //for (int i = sb.length() - 1; i >= 0; i--)  // {Mistake 1: test case: "10", k = 1} 
        for (int i = sb.length() - 1; i >= 1; i--) {  // {Correction 1: if the '0' is the only character, it should not be deleted}
            if (sb.charAt(i) == '0') sb.deleteCharAt(i);
            else break;
        }
        
        /* reverse the String back to forwards */
        return sb.reverse().toString();
    }
}

// 优化：利用char array来模拟Stack，空间复杂度O(n) -> O(1)?
//     维护top指针，指向当前剩下的n-k个数的末尾char，并且拿num[i]去跟num[top]比较，删除k个比num[i]大的num[top]并top--。
//     删除完成后，num[i]成为新的num[top]，即num[++top]=num[i]。
// 不同点：如果用的是char array，那么在去除leading 0s的时候不需要把char从后往前加入StringBuilder，而可以维护start指针，
//     将num[0:top)左闭右开中的leading 0s删掉，然后将num[start:top]左闭右闭转换成String返回结果。
// 犯错点：1.需要把String num转化为char array
//        2.start index要及时更新

class Solution {
    public String removeKdigits(String num, int k) {
        if (k <= 0) return num;
        if (k == num.length()) return "0";
        
        int top = -1;
        char[] numArray = num.toCharArray();  // {Mistake 1} {Correction 1}
        for (char c: numArray) {
            while (k > 0 && top >= 0 && c < numArray[top]) {
                top--;
                k--;
            }
            numArray[++top] = c;
        }
        
        while (k > 0) {
            top--;
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        int start = 0;
        while (start < top && numArray[start] == '0') {
            start++;
        }
        while (start <= top) {
            //sb.append(numArray[start]);  // {Mistake 2: start index not updated, memory limit exceed}
            sb.append(numArray[start++]);  // {Correction 2: update start index}
        }
        
        return sb.toString();
    }
}