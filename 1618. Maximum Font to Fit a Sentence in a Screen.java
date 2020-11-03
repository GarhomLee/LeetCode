https://leetcode.com/problems/maximum-font-to-fit-a-sentence-in-a-screen/

idea: Binary Search
time complexity: O(t log n), t=text.length(), n=fonts.length
space complexity: O(1)

/**
 * // This is the FontInfo's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface FontInfo {
 *     // Return the width of char ch when fontSize is used.
 *     public int getWidth(int fontSize, char ch) {}
 *     // Return Height of any char when fontSize is used.
 *     public int getHeight(int fontSize)
 * }
 */
class Solution {
    public boolean canFit(String text, int w, int h, int font, FontInfo fontInfo) {
        int sumWidth = 0;
        for (char c : text.toCharArray()) {
            sumWidth += fontInfo.getWidth(font, c);
        }
        
        return fontInfo.getHeight(font) <= h && sumWidth <= w;
    }
    
    public int maxFont(String text, int w, int h, int[] fonts, FontInfo fontInfo) {
        int low = 0, high = fonts.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (!canFit(text, w, h, fonts[mid], fontInfo)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low - 1 >= 0 ? fonts[low - 1] : -1;
    }
}