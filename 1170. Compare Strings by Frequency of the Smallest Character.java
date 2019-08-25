https://leetcode.com/problems/compare-strings-by-frequency-of-the-smallest-character/

// 思路：Sort + Binary Search
//         step1: 遍历queries数组，调用自定义函数count()得到queries[i]中顺序最小的字符的频数，记录在qCount数组。
//                 遍历words数组，调用自定义函数count()得到words[i]中顺序最小的字符的频数，wCount数组。
//         step2: 将wCount数组排序。
//         step3: 对于每个queries[i]，利用binary search找到频数大于qCount[i]的wCount[i]（即大于等于qCount[i] + 1），
//                 得到这样的words元素个数，记入res[i]。
// 时间复杂度：O(q * ql + w * wl + w log w), q=queries.length, ql=average length of String in queries array, w=words.length, wl=average length of String in words array
// 空间复杂度：O(q + w), q=queries.length, w=words.length
// 犯错点：1.Java内置函数使用错误：如果target存在duplicate，调用Arrays.binarySearch()的返回值【不一定是最左边的位置】。
//             因此，需要自己实现一个binarysearch()来得到duplicate中最左边的位置。
//         2.细节错误：找到更小的char时，需要更新minChar

class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        /* count the frequency of the smallest char in queries and words array */
        int[] qCount = new int[queries.length], wCount = new int[words.length];
        for (int i = 0; i < queries.length; i++) {
            qCount[i] = count(queries[i]);
        }
        for (int i = 0; i < words.length; i++) {
            wCount[i] = count(words[i]);
        }
        /* sort */
        Arrays.sort(wCount);
        /* apply binary search to find how many Strings in words array have more "counts" than queries[i] */
        int[] res = new int[qCount.length];
        for (int i = 0; i < qCount.length; i++) {
            //int index = Arrays.binarySearch(wCount, qCount[i] + 1);  // {Mistake 1}
            int index = binarysearch(wCount, qCount[i] + 1);  // {Correction 1}
            res[i] = words.length - index;
        }
        
        return res;
    }
    
    private int binarysearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    private int count(String s) {
        int[] count = new int[26];
        char minChar = 'z';
        int freq = 0;
        for (char c: s.toCharArray()) {
            count[c - 'a']++;
            if (c <= minChar) {
                // {Mistake 2}
                minChar = c;  // {Correction 2}
                freq = count[c - 'a'];
            }
        }
        
        return freq;
    }
}