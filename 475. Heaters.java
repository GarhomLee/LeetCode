https://leetcode.com/problems/heaters/

// 解法一：TreeSet
//     利用BST的性质，找到每个house对应的最近的两个heater位置，即floor heater和ceiling heater，所以要用到TreeSet。
//     在floor和ceiling和当前house的距离leftDist和rightDist中取较小的位置，即可以满足覆盖到这个house的要求。
//     又因为所有heater的覆盖范围统一为一个值，所以取所有满足条件的值中最大的一个，这样所有的house就都能被覆盖到了。
// 时间复杂度：O(m + nlog(m)), m=heaters.length, n=house.length
// 空间复杂度：O(m), m=heaters.length

class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        TreeSet<Integer> set = new TreeSet();
        for (int m: heaters) {
            set.add(m);
        }
        int minRadius = 0;
        for (int n: houses) {
            Integer floor = set.floor(n), ceiling = set.ceiling(n);
            int leftDist = floor == null? Integer.MAX_VALUE : n - floor;
            int rightDist = ceiling == null? Integer.MAX_VALUE : ceiling - n;
            minRadius = Math.max(minRadius, Math.min(leftDist, rightDist));
        }
        return minRadius;
    }
}

// 解法二：Binary Search (copy from @StefanPochmann)

public int findRadius(int[] houses, int[] heaters) {
    Arrays.sort(heaters);
    int result = 0;
    
    for (int house : houses) {
        int index = Arrays.binarySearch(heaters, house);
        if (index < 0) {
            index = ~index;
            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
            
            result = Math.max(result, Math.min(dist1, dist2));
        }
    }
    
    return result;
}