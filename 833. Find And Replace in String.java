https://leetcode.com/problems/find-and-replace-in-string/

idea: HashMap
    HashMap is necessary because input indices arr might not be sorted.
time comp: O(n)
space comp: O(n)

class Solution {    
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        Map<Integer, Integer> table = new HashMap<>();
        for (int i=0; i<indices.length; i++) {
            // if a match is found in the original string, record it
            if (s.startsWith(sources[i], indices[i])) {
                table.put(indices[i], i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); ) {
            if (table.containsKey(i)) {
                // if a replacement was recorded before
                sb.append(targets[table.get(i)]);
                i+=sources[table.get(i)].length();
            } else {
                // if no replacement happened at this index
                sb.append(s.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}