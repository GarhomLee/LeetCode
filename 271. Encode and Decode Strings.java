https://leetcode.com/problems/encode-and-decode-strings/

// 解法一：利用non-ASCII字符作为分割，即大于等于257的字符
//         String encode(List<String> strs)：
//             如果input为空，那么直接返回空字符串""。
//             否则，在strs中的每个字符串后，加上(char) 257，类似'å'，作为分隔符。也就是说，即使strs是空字符串列表，也至少会有一个"å"。
//         List<String> decode(String s)：
//             如果s为空字符串""，那么说明encode前的列表为空，直接返回空列表。
//             否则，以(char) 257为分割点将s分割，注意String.split()的参数limit要选负数，如-1，使得【分割尽可能多，且前后空串不被去除】。
//             这样的结果是最后一个'å'后也会有一个空字符串""，是多余的，需要remove。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.Java内置数据结构使用错误：参考https://blog.csdn.net/Tracycater/article/details/77592472
//             调用Arrays.asList()产生的List中add、remove方法时报异常，这是由于Arrays.asList()返回的是Arrays的内部类ArrayList，
//             而不是java.util.ArrayList。Arrays的内部类ArrayList和java.util.ArrayList都是继承AbstractList，remove、add等方法
//             在AbstractList中是默认throw UnsupportedOperationException而且不作任何操作。java.util.ArrayList重写这些方法
//             而Arrays的内部类ArrayList没有重写，所以会抛出异常。
//             因此，需要再将Arrays.asList()的结果放入新new出来的ArrayList中。


public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs.isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        char c = (char) 257;
        for (String str: strs) {
            sb.append(str).append(c);
        }
        
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        if (s.length() == 0) {
            return new ArrayList<String>();
        }
        
        String delimiter = Character.toString((char) 257);
        String[] split = s.split(delimiter, -1);
        //List<String> res = Arrays.asList(split);  // {Mistake 1}
        List<String> res = new ArrayList<>(Arrays.asList(split));
        res.remove(res.size() - 1);
        return res;
    }
}


解法二：Chunked Transfer Encoding，用byte[]储存每个String的长度。参考：https://leetcode.com/problems/encode-and-decode-strings/solution/
