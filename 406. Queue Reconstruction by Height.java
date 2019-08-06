https://leetcode.com/problems/queue-reconstruction-by-height/

// 思路：Greedy，观察找规律，先确定个子高的，再确定个子低的。
//         可以发现，对于个子相同的人，按在他前面的人数升序排序，能组成符合题意的队列。而先排好个子高的，
//         再在其中穿插个子低的，对已经排好的队列不会产生影响。
//         同时，还可以发现，每次插入在队列的位置正好等于在他前面的人数。
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] person1, int[] person2) {
                return person1[0] == person2[0] ? person1[1] - person2[1] : person2[0] - person1[0];
            }
        });
        
        List<int[]> res = new ArrayList<>();
        for (int[] person : people) {
            res.add(person[1], person);
        }
        
        return res.toArray(new int[0][0]);
    }
}