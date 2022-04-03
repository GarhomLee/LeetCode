https://leetcode.com/problems/detect-squares/

idea: Math + HashMap. See: https://leetcode.com/problems/detect-squares/discuss/1471958/C%2B%2BJavaPython-2-approaches-using-HashMap-with-Picture-Clean-and-Concise
    For count(): first, iterate all existing points to find all p3 that is diagonal to p1 to form a square;
        then, count all other two points p2 and p4 that form a square together with p1 and p3, and add their
        product to total count.

class DetectSquares {
    Map<Integer, Map<Integer, Integer>> xyMap;  // x -> {y -> count}
    List<int[]> pointList;
    
    public DetectSquares() {
        xyMap = new HashMap<>();
        pointList = new ArrayList<>();
    }
    
    public void add(int[] point) {
        xyMap.putIfAbsent(point[0], new HashMap<>());
        Map<Integer, Integer> countMap = xyMap.get(point[0]);
        countMap.put(point[1], countMap.getOrDefault(point[1], 0) + 1);
        
        pointList.add(point);
    }
    
    public int count(int[] p1) {
        int total = 0;
        for (int[] p3: pointList) {
            if (p1[0] == p3[0] || p1[1] == p3[1] || Math.abs(p1[0] - p3[0]) != Math.abs(p1[1] - p3[1])) continue;
            if (!xyMap.containsKey(p1[0])) continue;
            
            int count2 = xyMap.get(p1[0]).getOrDefault(p3[1], 0);   // count of p2
            int count4 = xyMap.get(p3[0]).getOrDefault(p1[1], 0);   // count of p4
            total += count2 * count4;
        }
        
        return total;
    }
}