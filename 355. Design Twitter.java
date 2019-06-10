https://leetcode.com/problems/design-twitter/

// 思路：用OOD的思想，将相关的Object包装成一个class，在这个class里实现有关这个Object的所有function。外部只需调用这些function即可。
//     这里将User和Tweet包装成class，分别实现相应的功能。
//     User: 1）信息：int id，关注了谁Set<Integer> followees，发了什么推文Tweet dummy，这里【将Tweet信息以linked list形式存储】，只需要存一个dummy head，最近发的Tweet靠近dummy head。
//           2）功能：follow(), unfollow(), post()
//     Tweet: 1)信息：int id，发送时间int time，前一条推文Tweet next
//            2)功能：无特别功能
//     在class里实现了相应功能后，外部只需要调用这些功能。
// 关键点：将Tweet信息以linked list形式存储。因此，在getNewsFeed()时，只需将所有followees对应的User存储的Tweet linked list的头部
//     放入maxHeap，时间越近，time越大。每从maxHeap取一个Tweet，把它非空的next放入maxHeap，即可高效完成getNewsFeed()功能。

class Twitter {
    int timeStamp;
    Map<Integer, User> usersMapping;  // key: userId; value: the corresponding User Object
        
    /** Initialize your data structure here. */
    public Twitter() {
        timeStamp = 0;
        usersMapping = new HashMap<Integer, User>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        verify(userId);  // make sure userId exists
        
        User user = usersMapping.get(userId);
        user.post(tweetId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsList = new ArrayList();
        if (!usersMapping.containsKey(userId)) return newsList;
        
        PriorityQueue<Tweet> pq = new PriorityQueue(1, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet tweet1, Tweet tweet2) {
                return tweet2.time - tweet1.time;
            }
        });
        
        User user = usersMapping.get(userId);
        for (Integer followeeId: user.followees) {
            User followee = usersMapping.get(followeeId);
            if (followee.dummy.next != null) pq.offer(followee.dummy.next);
        }
        
        int k = 10;
        while (k > 0 && !pq.isEmpty()) {
            Tweet tweet = pq.poll();
            k--;
            newsList.add(tweet.id);
            if (tweet.next != null) pq.offer(tweet.next);
        }
        
        return newsList;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        verify(followerId);
        verify(followeeId);
        
        User follower = usersMapping.get(followerId);
        follower.follow(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        verify(followerId);
        verify(followeeId);
        
        User follower = usersMapping.get(followerId);
        follower.unfollow(followeeId);
    }
    
    /** If a userId appears, there should be a record in usersMapping, even it has no activities. */
    private void verify(int userId) {
        if (!usersMapping.containsKey(userId)) {
            usersMapping.put(userId, new User(userId));
        }
    }
    
    /** User class contains all info (property) about a user:
     *  1) id
     *  2) the users he follows
     *  3) all tweets he posts
     *
     *  Also, User class contains all functions a user should use:
     *  1) follow another user
     *  2) unfollow another user
     *  3) post a new tweet
     */
    class User {
        int id;
        Set<Integer> followees;
        Tweet dummy;

        public User(int userId) {
            id = userId;
            followees = new HashSet();
            dummy = new Tweet(-1);
            follow(userId);
        }
        public void follow(int followeeId) {
            followees.add(followeeId);
        }
        public void unfollow(int followeeId) {
            if (id == followeeId || !followees.contains(followeeId)) return;
            followees.remove(followeeId);
        }
        public void post(int tweetId) {
            Tweet tweet = new Tweet(tweetId);
            tweet.next = dummy.next;
            dummy.next = tweet;
        }
    }
    
    /** Tweet class contains all info (property) about a tweet:
     *  1) id
     *  2) when this tweet is posted
     *  3) the link to its previous tweet. This link makes getNewsFeed() more efficient by using maxHeap
     */
    class Tweet {
        int time;
        int id;
        Tweet next;
        public Tweet(int tweetId) {
            id = tweetId;
            time = timeStamp++;
            next = null;
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */