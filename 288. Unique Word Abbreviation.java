https://leetcode.com/problems/unique-word-abbreviation/

// 思路：Hash Table。关键是要理解题意，A word's abbreviation is unique的意思是这个word的abbreviation在dict里不存在；或存在，且dict里这个abbreviation只出现一次，且这个word和dict里的单词是同一个单词。
//         在constructor：维护HashMap，key为压缩后的abbreviation，value为HashSet，表示dict里跟这个abbreviation
//             对应的原字符串。
//             对于dict里的每一个String，调用transfer()得到压缩后的abbreviation，存入HashMap和HashSet中。
//         在boolean isUnique(String word)：首先调用transfer()得到将word压缩后的abbreviation。
//             如果这个abbreviation不在HashMap里，那么符合unique定义，返回true；
//             如果这个abbreviation在HashMap里，且dict里对应的String只有1个，同时和word是同一个String，那么也
//             符合unique定义，返回true；
//             其他情况，均返回false。
// 时间复杂度：O(n), n=length of dictionary array
// 空间复杂度：O(n), n=length of dictionary array

class ValidWordAbbr {
    Map<String, Set<String>> map;
    
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for (String word: dictionary) {
            int len = word.length();
            String s = transfer(word);
            map.putIfAbsent(s, new HashSet<>());
            map.get(s).add(word);
            
        }
    }
    
    public boolean isUnique(String word) {
        String s = transfer(word);
        return !map.containsKey(s) || (map.get(s).size() == 1 && map.get(s).contains(word));
    }
    
    private String transfer(String word) {
        int len = word.length();
        if (len < 2) {
            return word;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(0)).append(len - 2).append(word.charAt(len - 1));
        
        return sb.toString();
    }
}