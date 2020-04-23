https://leetcode.com/problems/cinema-seat-allocation/

-idea: Greedy
    First, record the reserved seats for each row.
    Then, for each row reserved seats, determine if the middle 4 seats are empty; if so,
    it means at least 1 family can fit in, then determine if left side and right side 
    have enough space to maximize and let 1 more family to fit in.
    If at least one of the middle 4 seats are taken, simply check left and midLeft, midRight
    and right.
-time complexity: O(r), r=num of reserved seats
-space complexity: O(r), r=num of reserved seats

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int count = 0;
        Map<Integer, Set<Integer>> reservedMap = new HashMap<>();
        for (int[] seat : reservedSeats) {
            int row = seat[0], seatNum = seat[1];
            reservedMap.putIfAbsent(row, new HashSet<>());
            reservedMap.get(row).add(seatNum);
        }
        
        for (int row : reservedMap.keySet()) {
            Set<Integer> set = reservedMap.get(row);
            boolean fitLeft = !set.contains(2) && !set.contains(3);
            boolean fitMidLeft = !set.contains(4) && !set.contains(5);
            boolean fitMidRight = !set.contains(6) && !set.contains(7);
            boolean fitRight = !set.contains(8) && !set.contains(9);
            
            if (fitMidLeft && fitMidRight) {
                // at least middle can fit
                count += (fitLeft && fitRight ? 2 : 1);
            } else if (fitLeft && fitMidLeft) {
                // only fit left side separated by an aisle
                count += 1;
            } else if (fitMidRight && fitRight) {
                // only fit right side separated by an aisle
                count += 1;
            }
        }
        
        count += (n - reservedMap.size()) * 2;
        return count;
    }
}