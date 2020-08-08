https://leetcode.com/problems/bus-routes/

idea: BFS
time complexity: O(bus + station + busToStation)
space complexity: O(bus + station)
optimization: Start from station instead of bus. Referring to: https://leetcode.com/problems/bus-routes/discuss/122771/C%2B%2BJavaPython-BFS-Solution

class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
        
        int n = routes.length;
        Map<Integer, List<Integer>> stationMap = new HashMap<>();   // station id -> buses
        Queue<Integer> queue = new LinkedList<>();
        boolean[] takenBus = new boolean[n];
        Set<Integer> visitedStation = new HashSet<>();
        visitedStation.add(S);
        
        for (int bus = 0; bus < n; bus++) {
            for (int station : routes[bus]) {
                stationMap.putIfAbsent(station, new ArrayList<>());
                stationMap.get(station).add(bus);
                
                if (station == S) {
                    queue.offer(bus);
                    takenBus[bus] = true;
                }
            }
        }
        
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int currBus = queue.poll();
                for (int station : routes[currBus]) {
                    if (station == T) {
                        return count;
                    }
                    if (visitedStation.contains(station)) continue;
                    
                    visitedStation.add(station);
                    for (int nextBus : stationMap.get(station)) {
                        if (takenBus[nextBus]) continue;
                        
                        queue.offer(nextBus);
                        takenBus[nextBus] = true;
                    }
                }
            }
            count++;
        }
        
        return -1;
    }
}