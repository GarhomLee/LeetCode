https://leetcode.com/problems/design-underground-system/

idea: HashMap
time complexity:
    checkIn(): O(1)
    checkOut(): O(1)
    getAverageTime(): O(1)
space complexity: O(P + S^2), where S is the number of stations on the network, 
    and P is the number of passengers making a journey concurrently during peak time.

class UndergroundSystem {
    Map<Integer, CheckInPair> checkinMap;   // id -> [checkin time, station]
    Map<String, Map<String, TimePair>> timeMap; // checkin station -> (checkout station -> [total time, count])
    
    class CheckInPair {
        int time;
        String station;
        public CheckInPair(int t, String s) {
            this.time = t;
            this.station = s;
        }
    }
    
    class TimePair {
        double totalTime;
        int count;
        public TimePair(double t, int cnt) {
            this.totalTime = t;
            this.count = cnt;
        }
    }
    
    public UndergroundSystem() {
        checkinMap = new HashMap<>();
        timeMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkinMap.put(id, new CheckInPair(t, stationName));
    }
    
    private void updateTimeMap(Map<String, TimePair> checkoutMap, String checkoutStation, int t) {
        checkoutMap.putIfAbsent(checkoutStation, new TimePair(0, 0));
        TimePair timePair = checkoutMap.get(checkoutStation);
        timePair.totalTime += t;
        timePair.count++;
    }
    
    public void checkOut(int id, String stationName, int t) {
        CheckInPair checkInPair = checkinMap.get(id);
        String checkinStation = checkInPair.station;
        int checkinTime = checkInPair.time;
        timeMap.putIfAbsent(checkinStation, new HashMap<>());
        updateTimeMap(timeMap.get(checkinStation), stationName, t - checkinTime);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        if (!timeMap.containsKey(startStation) || !timeMap.get(startStation).containsKey(endStation)) {
            return -1;
        }
        
        TimePair timePair = timeMap.get(startStation).get(endStation);
        return timePair.totalTime / timePair.count;
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */