https://leetcode.com/problems/design-a-file-sharing-system/

idea: 1 TreeSet + 2 HashMaps
time complexity:
    -FileSharing(): O(m)
    -join(): O(m + log n)
    -leave(): O(m + log n)
    -request(): O(log n)
space complexity: O(m * n)
optimization: PriorityQueue + 1 TreeMap

class FileSharing {
    private int maxChunk;
    private TreeSet<Integer> availUser;
    private Map<Integer, Set<Integer>> userMap;    // user id -> set of chunks he holds
    private Map<Integer, Set<Integer>> chunkMap;    // chunk id -> set of users holding this chunk
    
    public FileSharing(int m) {
        maxChunk = m;
        availUser = new TreeSet<>();
        availUser.add(1);
        chunkMap = new HashMap<>();
        userMap = new HashMap<>();
        for (int i = 1; i <= m; i++) {
            chunkMap.put(i, new HashSet<>());
        }
    }
    
    public int join(List<Integer> ownedChunks) {
        Integer user = availUser.pollFirst();
        if (availUser.isEmpty()) {
            availUser.add(user + 1);
        }
        userMap.put(user, new HashSet<>(ownedChunks));
        for (int chunk : ownedChunks) {
            // System.out.println("new chunk="+chunk);
            chunkMap.get(chunk).add(user);
        }
        
        // System.out.println("new user="+user);
        return user;
    }
    
    public void leave(int userID) {
        if (!userMap.containsKey(userID)) return;
        
        for (int chunk : userMap.get(userID)) {
            chunkMap.get(chunk).remove(userID);
        }
        userMap.remove(userID);
        availUser.add(userID);
    }
    
    public List<Integer> request(int userID, int chunkID) {
        List<Integer> ret = new ArrayList<>(chunkMap.get(chunkID));
        if (!ret.isEmpty()) {
            Collections.sort(ret);
            userMap.get(userID).add(chunkID);
            chunkMap.get(chunkID).add(userID);
        }
        
        return ret;
    }
}

/**
 * Your FileSharing object will be instantiated and called as such:
 * FileSharing obj = new FileSharing(m);
 * int param_1 = obj.join(ownedChunks);
 * obj.leave(userID);
 * List<Integer> param_3 = obj.request(userID,chunkID);
 */