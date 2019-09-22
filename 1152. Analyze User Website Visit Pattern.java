https://leetcode.com/problems/analyze-user-website-visit-pattern/

// 思路：HashMap + TreeMap + HashSet，要理解题意
//         维护第一个HashMap，key为不同的username，value为内层的TreeMap，将该用户浏览的网页
//         按时间顺序排列。TreeMap中，key为不同的timestamp，value为该用户在该时间浏览的网页String。
//         维护第二个HashMap作为counter，key为不同的3-sequence，value为该3-sequence被
//         【不同用户】浏览的次数。
//         step1:同时遍历username数组，timestamp数组，website数组，将每一个不同的user和对应的TreeMap
//             放入第一个HashMap，而将和该user有关的time和web放入TreeMap。
//         step2:遍历构建好的第一个HashMap中每个user对应的TreeMap，用三重for循环找到和这个user有关的
//             不同的3-sequence，记录为包含','的String，用HashSet去重。
//             同时，记录每种3-sequence出现的总次数，更新最大的次数maxCount。
//         step3:找到出现次数为maxCount的所有3-sequence，排序，取次序最小的一个String，以','分割后放入
//             List并返回。
// 时间复杂度：O(n^3)
// 空间复杂度：O(n^3)
// 犯错点：1.题意理解错误：按时间顺序排列的任意3个website都可以组成3-sequence，不一定需要连续的3个website。
//         2.细节错误：HashSet要加入未搜索过的str。

class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<String> res = new ArrayList<>();
        int len = username.length;

        /* construct nameMap */
        Map<String, Map<Integer, String>> nameMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            nameMap.putIfAbsent(username[i], new TreeMap<>());
            nameMap.get(username[i]).put(timestamp[i], website[i]);
        }
        
        /* construct countMap */
        Map<String, Integer> countMap = new HashMap<>();
        int maxCount = 0;
        for (String user: nameMap.keySet()) {
            if (nameMap.get(user).size() < 3) continue;
            
            List<String> webList = new ArrayList<>(nameMap.get(user).values());
            Set<String> seen = new HashSet<>();
            
            /*for (int i = 0; i + 2 < webList.size(); i++) {
                if (seen.contains(str)) continue;
                
                seen.add(str);
                countMap.put(str, countMap.getOrDefault(str, 0) + 1);
                maxCount = Math.max(maxCount, countMap.get(str));
            }*/  // {Mistake 1}

            for (int i = 0; i + 2 < webList.size(); i++) {
                for (int j = i + 1; j + 1 < webList.size(); j++) {
                    for (int k = j + 1; k < webList.size(); k++) {
                        String str = webList.get(i) + "," + webList.get(j) + "," + webList.get(k);
                        if (seen.contains(str)) {
                            //System.out.println("user="+user+",str="+str);
                            continue;
                        }
                        // {Mistake 2}
                        seen.add(str);  // {Correction 2}
                        countMap.put(str, countMap.getOrDefault(str, 0) + 1);
                        maxCount = Math.max(maxCount, countMap.get(str));
                    }
                }
            }
        }
        
        /* find possible results from countMap */
        for (String str: countMap.keySet()) {
            //System.out.println("key="+str+",value="+countMap.get(str));
            if (countMap.get(str) == maxCount) {
                res.add(str);
            }
        }
        
        Collections.sort(res);
        return Arrays.asList(res.get(0).split(","));
        //return null;
    }
}