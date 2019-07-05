https://leetcode.com/problems/encode-and-decode-tinyurl/

// 思路：HashMap
// 关键：理解题意，将input的longUrl变成任意tinyurl，还能解码成原来的input的longUrl。因此，可以直接用例子中的"http://tinyurl.com/"
//      加上数字id作为唯一标识存在Map里，解码时拿出和这个标识对应的longUrl。
//      因为这题是stateful的，所以可以用Map来存储。

public class Codec {
    int id = 0;
    Map<String, String> map = new HashMap();
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String shortUrl = "http://tinyurl.com/" + id++;
        map.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.containsKey(shortUrl) ? map.get(shortUrl) : "";
    }
}
