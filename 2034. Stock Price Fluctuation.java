https://leetcode.com/problems/stock-price-fluctuation/

idea: Two TreeMaps
    timePriceMap:  time -> price, can find the latest price
    priceTimeMap: price -> a set of time with this price, can find the max and min price

class StockPrice {
    TreeMap<Integer, Integer> timePriceMap; // time -> price
    TreeMap<Integer, Set<Integer>> priceTimeMap;    // price -> a set of time with this price
    
    public StockPrice() {
        timePriceMap = new TreeMap<>();
        priceTimeMap = new TreeMap<>();
    }
    
    public void update(int timestamp, int price) {
        if (timePriceMap.containsKey(timestamp)) {
            int currPrice = timePriceMap.get(timestamp);
            priceTimeMap.get(currPrice).remove(timestamp);
            if (priceTimeMap.get(currPrice).isEmpty()) {
                priceTimeMap.remove(currPrice);
            }
        }
        
        timePriceMap.put(timestamp, price);
        
        priceTimeMap.putIfAbsent(price, new HashSet<>());
        priceTimeMap.get(price).add(timestamp);
    }
    
    public int current() {
        if (timePriceMap.isEmpty()) return -1;
        return timePriceMap.lastEntry().getValue();
    }
    
    public int maximum() {
        if (priceTimeMap.isEmpty()) return -1;
        return priceTimeMap.lastKey();
    }
    
    public int minimum() {
        if (priceTimeMap.isEmpty()) return -1;
        return priceTimeMap.firstKey();
    }
}