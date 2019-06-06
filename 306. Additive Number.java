https://leetcode.com/problems/additive-number/

// 总体思路：DFS，要先确定DFS的起始值
//         先确定前两个数。首先，要利用几个性质：
//         1）至少有3个数，且第三个数长度为至少为前两个数较大数的长度（eg. 1+99=100）。根据这个性质可以确定第二个数secondNum的长度范围
//         2）根据性质1），第一个数firstNum的长度范围一定不会超过num.length的一半
//         对于第一个数，如果有leading 0s，直接返回false；对第二个数，如果有leading 0s，要break重新选新的第一个数。
//         当前两个数确定后，利用helper method进行递归求解。
//         这里利用一个小技巧，先求出第三个数thirdNum，转换成String，先判断num中当前位置是否包含thirdNum作为prefix，如果不是，可以直接
//         返回false，如果是，继续递归求解。
// 时间复杂度：O(n!), n=num.length
// 空间复杂度：O(n*k), n=num.length, k=thirdString.length

class Solution {
    public boolean isAdditiveNumber(String num) { 
        if (num == null || num.length() < 3) return false;
        
        /* determine two start nums for dfs */
        long firstNum = 0;  // for large input integers
        for (int firstLen = 1; firstLen <= num.length() / 2; firstLen++) {
            if (num.charAt(0) == '0' && firstLen >= 2) return false;  // leading 0 at the first num, ie '0XXXX', is not valid
            firstNum = firstNum * 10 + (num.charAt(firstLen - 1) - '0');  // get the first num
            
            long secondNum = 0;  // for large input integers
            for (int secondLen = 1; num.length() - firstLen - secondLen >= Math.max(firstLen, secondLen); secondLen++) {  // the length of the third num should be at least the longer one of the first two nums
                if (num.charAt(firstLen) == '0' && secondLen >= 2) break;  // leading 0 at the second num, ie '0XXXX', is also not valid, but it will have a second chance since there would be another first num
                secondNum = secondNum * 10 + (num.charAt(firstLen + secondLen - 1) - '0');  // get the second num
                
                /* depth-first search */
                if (isValid(num, firstLen + secondLen, firstNum, secondNum)) return true;
            }
        }
        return false;
    }
    
    /** helper method for dfs */
    private boolean isValid(String num, int start, long firstNum, long secondNum) {
        if (start == num.length()) return true;
        long thirdNum = firstNum + secondNum;
        String thirdString = Long.toString(thirdNum);
        /* The rest of String num should starts with the sum of firstNum and secondNum which is thirdNum. Then we can recursively search the rest of String num, updating firstNum and secondNum */
        return (num.startsWith(thirdString, start) && isValid(num, start + thirdString.length(), secondNum, thirdNum));
    }
}