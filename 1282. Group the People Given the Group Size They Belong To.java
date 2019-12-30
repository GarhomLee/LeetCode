https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/

思路：Hash Table

时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int id = 0; id < groupSizes.length; id++) {
            int size = groupSizes[id];
            map.putIfAbsent(size, new ArrayList<>());
            map.get(size).add(id);
        }
        
        for (int size : map.keySet()) {
            int i = 0;
            List<Integer> curr = map.get(size);
            while (i < curr.size()) {
                List<Integer> list = new ArrayList<>();
                for (int count = 0; count < size && i < curr.size(); count++) {
                    list.add(curr.get(i++));
                }
                res.add(list);
            }
        }
        
        return res;
    }
}