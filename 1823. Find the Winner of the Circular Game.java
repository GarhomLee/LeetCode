https://leetcode.com/problems/find-the-winner-of-the-circular-game/

idea: Simulation with LinkedList and ListIterator
    Documentation for LIstIterator: https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/util/ListIterator.html#nextIndex()
time comp: O(n*k)
space comp: O(1)

class Solution {
    public int findTheWinner(int n, int k) {
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        
        ListIterator<Integer> iter = list.listIterator();
        while (list.size() > 1) {
            for (int i = 0; i < k; i++) {
                if (!iter.hasNext()) {
                    // reset iterator to the beginning of the list
                    iter = list.listIterator();
                }
                iter.next();
            }
            
            iter.remove();
        }
        
        return list.get(0);
    }
}