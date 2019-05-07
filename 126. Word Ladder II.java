https://leetcode.com/problems/word-ladder-ii/

// 比较难的一道题，步骤繁琐，不好debug。

总体思路：单向BFS构建graph，然后用backtracking进行DFS把graph转变成List。
        维护两个global variable：resultList，最后返回的List；children，用来构建有向图，key是parent，value是list of children。
        在BFS时，维护steps Map，key为word，value为word所在的level（或者说step）。同时，利用Queue进行BFS。
        维护boolean变量found，表示有无到达endWord。在最外层while loop，当found为true或者Queue为空时，都可以跳出循环。如果是因为found == true跳出的循环，就需要进行DFS构建List。
        从Queue中取word，这个word一定没有被搜索过，所以在children Map里给它pair一个空的List。
        改变word中的每个字母，记为child。当steps Map中没有child的信息，说明child未被搜索到，所以在steps Map和Queue中加入child的信息。
        只有当steps Map中没有child的信息，或者有child且child对应的step和currWord的step相差1，说明currWord是child的valid parent，加入children Map。每搜索完一个currWord，都要从wordDict中删去。
        注意：以这种方式构建的有向图，跟endWord处于同一个level的word（包括endWord）都没有children，在DFS时需要进行判断。

class Solution {
    List<List<String>> resultList = new ArrayList<>();  // the final result list
    Map<String, List<String>> children = new HashMap<>();  // the graph of children
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        /* transform wordList into Set */
        Set<String> wordDict = new HashSet<String>(wordList);
        if (!wordDict.contains(endWord)) return resultList;
        
        /* use bfs to construct the graph of children */
        bfs(beginWord, endWord, wordDict);
        
        return resultList;
    }
    
    private void bfs(String beginWord, String endWord, Set<String> wordDict) {
        Map<String, Integer> steps = new HashMap<>();  // record the candidate words and their step (level)
        Queue<String> queue = new ArrayDeque<>();  // store the word to search
        boolean found = false;  // indicate if it reaches the endWord
        int step = 1;
        
        queue.offer(beginWord);  // initialize the queue
        steps.put(beginWord, step); 
        
        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {  // search the words in queue each round
                StringBuilder currWord = new StringBuilder(queue.poll());  // get currWord from the queue
                StringBuilder child = new StringBuilder(currWord.toString());  // get a copy of currWord, and will change as child
                children.put(currWord.toString(), new ArrayList<String>());  // initialize when it gets the new word
                
                for (int j = 0; j < currWord.length(); j++) {  // search every position of currWord
                    char currChar = currWord.charAt(j);  // store the original char as currChar
                    for (char c = 'a'; c <= 'z'; c++) {  // search 26 letters except for currChar
                        if (c == currChar) continue;
                        child.setCharAt(j, c);  // change letter in child instead of currWord
                        
                        if (!wordDict.contains(child.toString())) {  // child not in wordDict, do nothing and continue to change next letter
                            continue;
                        }
                        
                        if (child.toString().equals(endWord)) {  // endWord found
                            found = true;
                        }
                        
                        if (!steps.containsKey(child.toString())) {  // if the child is not visited
                            steps.put(child.toString(), step + 1);
                            queue.offer(child.toString());
                            children.get(currWord.toString()).add(child.toString());
                        } else if (step == steps.get(child.toString()) - 1) {  // if the child is visited, and it is one step ahead of currWord
                            children.get(currWord.toString()).add(child.toString());
                        }
                    }
                    child.setCharAt(j, currChar);  // reset the char
                }
                wordDict.remove(currWord.toString());
            }
            step++;  // update step to next level before entering
        }
        
        /* use backtracking (dfs) to get all the results */
        if (found) {  // if the loop is terminated because of the endWord is found
            dfs(beginWord, endWord, new ArrayList<String>());
        }
    }
    
    private void dfs(String currWord, String endWord, List<String> list) {
        list.add(currWord);  // notice: currWord might be the words at the same level of endWord, all of which will not have children List in childrens Map
        
        if (currWord.equals(endWord)) {  // reach the GOAL: currWord is endWord
            resultList.add(new ArrayList<String>(list));
            // no need to return
        } else if (children.containsKey(currWord)) {  // CONSTRANTS: if currWord is still parents, which means it is not other words at the same level of endWord
            for (String child: children.get(currWord)) {  // CHOICES: children in the children List paired with the parent
                dfs(child, endWord, list);
            }
        }
        list.remove(list.size() - 1);  // reset step in backtracking
    }
}