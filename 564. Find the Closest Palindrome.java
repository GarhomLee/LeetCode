https://leetcode.com/problems/find-the-closest-palindrome/

// 思路：数学问题+考察细节。精简代码：https://leetcode.com/problems/find-the-closest-palindrome/discuss/102389/Java-solution-with-detailed-proof
//      对于一个数，假设它是5位数abxyz，那么可以证明将前半部分abx作为模版形成palindrome（即变成abxba），比用后半部分（即变成zyxyz）
//      得到的差值更小，因此只考虑以前半部分abx作为模版。
//      另外，还要考虑最中间的数的变化情况，如99800，最近的palindrome应该是99799而不是99899。同理129->131而不是121.
//      所以，abxyz最近的palindrome只会有三种可能情况：abxba, ab(x+1)ba, ab(x-1)ba。
//      对于ab(x+1)ba，要考虑位数变多的情况。
//      对于ab(x-1)ba，要考虑位数变少、或不变的情况，如1->0, 10->09。
//      得到abxba, ab(x+1)ba, ab(x-1)ba这三个数后，考虑和abxyz的差值。先考虑大的数，即ab(x+1)ba和abxyz的差值，
//      再考虑相近数abxba和abxyz的差值，最后考虑小的数ab(x-1)ba和abxyz的差值。最后取差值最小的数，差值相等时取本身值也最小的数。
// 犯错点：1.条件判断顺序错误：先考虑大的数，即ab(x+1)ba和abxyz的差值，再考虑相近数abxba和abxyz的差值，最后考虑小的数ab(x-1)ba和abxyz的差值。

class Solution {
    public String nearestPalindromic(String n) {
        if (n.isEmpty() || n.equals("0")) return "-1";
        
        int firstHalfLen = (n.length() + 1) / 2;
        long firstHalf = Long.parseLong(n.substring(0, firstHalfLen));
        long biggerFirstHalf = firstHalf + 1;
        long smallerFirstHalf = firstHalf - 1;
        
        String biggerString = "";
        if (Long.toString(biggerFirstHalf).length() > Long.toString(firstHalf).length()) {
            StringBuilder sb = new StringBuilder();
            sb.append(1);
            for (int i = 1; i < n.length(); i++) {
                sb.append(0);
            }
            sb.append(1);
            biggerString = sb.toString();
        } else {
            biggerString = getPalindrome(biggerFirstHalf, n.length());
        }
        
        String smallerString = "";
        //if (Long.toString(firstHalf).length() > Long.toString(smallerFirstHalf).length())  // {Mistake 1: this may not cause the changes in digit numbers}
        if ((firstHalf == 1 && smallerFirstHalf == 0) || (Long.toString(firstHalf).length() > Long.toString(smallerFirstHalf).length())) {  // {Correction 1}
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < n.length(); i++) {
                sb.append(9);
            }
            smallerString = sb.length() == 0 ? "0" : sb.toString();  // 1->0
        } else {
            smallerString = getPalindrome(smallerFirstHalf, n.length());
        }
        
        long num = Long.parseLong(n);
        long smallerNum = Long.parseLong(smallerString);
        long diff1= Math.abs(smallerNum - num);
        long palindromeNum = Long.parseLong(getPalindrome(firstHalf, n.length()));
        long diff2 = num == palindromeNum ? Integer.MAX_VALUE : Math.abs(palindromeNum - num);
        long biggerNum = Long.parseLong(biggerString);
        long diff3= Math.abs(biggerNum - num);
        
        //if (diff1 < diff2 && diff1 < diff3) return palindromeNum + "";
        //if (diff2 < diff3) return Long.toString(biggerNum);
        if (diff3 < diff2 && diff3 < diff1) return biggerNum + "";
        if (diff2 < diff1) return palindromeNum + "";
        return smallerNum + "";
    }
    
    private String getPalindrome(long firstHalf, int len) {
        StringBuilder sb = new StringBuilder(Long.toString(firstHalf));
        sb = sb.reverse();
        if (len % 2 == 1) sb = sb.deleteCharAt(0);
        return firstHalf + sb.toString();
    }
}

// 得到相邻的palindrome num的精简代码：
private String nearestPalindrom(String num, boolean dir) {
    int leftLen = (num.length() + 1) / 2, rightLen = num.length() - leftLen;
    int firstHalf = Integer.valueOf(num.substring(0, leftLen));
    firstHalf += (dir ? 1 : -1);
    	
    if (firstHalf == 0) return rightLen == 0 ? "0" : "9";  // num has 1 or 2 digits
    	
    firstHalf += (dir ? 1 : -1);  // increase/decrease by 1
    StringBuilder leftPart = new StringBuilder(String.valueOf(firstHalf));  // get the left part of palindrome number
    StringBuilder rightPart = new StringBuilder(leftPart).reverse();  //  get the candidate right part of palindrome number
    if (rightLen > rightPart.length()) rightPart.append("9");  // eg. 1000->999, right length should not change
    
    /* append right part to left part, with desired right part length rightLen from the end */
    return leftPart.append(rightPart.substring(rightPart.length() - rightLen)).toString();
}