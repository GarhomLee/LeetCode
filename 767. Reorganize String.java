https://leetcode.com/problems/reorganize-string/

// 思路：Greedy + maxHeap
//         step1: 遍历String s，用数组count统计字符出现次数，并记录出现最多的次数maxCount。
//         step1.5: 特殊情况：如果最多的次数maxCount > (S.length() + 1) / 2，由于数量多，所以一定会出现相邻字符相同
//                 的情况，所以直接返回空字符串""。
//         step2: 将count数组中出现次数大于0的字符和其次数转换成Node加入PriorityQueue，重写Node中的compareTo()
//                 来【实现maxHeap】。
//         step3: 维护Node pre，记录上一次用过的且【剩余数量大于0】的字符。
//                 不断取出PriorityQueue顶部元素，记为curr，将curr.letter加到StringBuilder中，同时更新curr.count--。
//                 如果pre != null，那么要将pre加入PriorityQueue，在下一轮循环中被考虑。
//                 如果curr.count == 0，那么不需要用pre记录，pre赋值为null；否则，用pre来记录它，避免出现相邻字符相同
//                 的情况。
//         step3.5: 循环结束，判断一下pre是否还有letter，有的话要加入StringBuilder中
//         step4: 返回String
// 时间复杂度：O(n log k), n=S.length(), k=出现的字符个数
// 空间复杂度：O(n), n=S.length()
// 犯错点：1.细节错误：如果curr在拿掉一个字符放入StringBuilder后，没有剩余字符了，那么就不需要用pre来记录，
//             所以这时pre赋值为null；否则pre赋值为curr。
//         2.细节错误：这题要用maxHeap而不是minHeap，所以compareTo()要写对。

class Solution {
    public String reorganizeString(String S) {
        /* count the frequency of each char */
        int maxCount = 0;
        int[] count = new int[26];
        for (char c: S.toCharArray()) {
            count[c - 'a']++;
            maxCount = Math.max(maxCount, count[c - 'a']);
        }
        /* special case*/
        if (maxCount > (S.length() + 1) / 2) {
            return "";
        }
        /* put all existing chars into PriorityQueue as Nodes */
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                pq.offer(new Node((char) (i + 'a'), count[i]));
            }
        }
        /* construct String */
        StringBuilder sb = new StringBuilder();
        Node pre = null;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            sb.append(curr.letter);
            curr.count--;
            if (pre != null) {
                pq.offer(pre);
            }
            /* record curr if it has counts */
            //pre = curr;  // {Mistake 1}
            pre = curr.count > 0 ? curr : null;  // {Correction 1}
        }
        if (pre != null) {
            sb.append(pre.letter);
        }
        
        return sb.toString();
    }
    
    class Node implements Comparable<Node> {
        char letter;
        int count;
        public Node(char l, int c) {
            this.letter = l;
            this.count = c;
        }
        
        public int compareTo(Node other) {
            //return this.count == other.count ? this.letter - other.letter : this.count - other.count;  // {Mistake 2}
            return this.count == other.count ? this.letter - other.letter : other.count - this.count;  // {Correction 2}
        }
    }
}