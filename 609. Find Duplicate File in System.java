https://leetcode.com/problems/find-duplicate-file-in-system/

// 思路：HashMap
//         HashMap中key为文件的内容content，value为有同样content的文件完整路径的Set，完整路径为path加上
//         文件名中间插入"/"。
//         step1: 遍历paths数组，将每个path按空格分割成files数组，其中files[0]为路径，其他为文件名和content
//         step2: 将files数组中按content为key，把path加上文件名中间插入"/"为放入HashMap中对应的Set。
//         step3: 遍历HashMap，将size > 1的Set转为List后放入外层List，最后返回结果res。
// 时间复杂度：O(n*l), n=paths.length, l=average num of files in each path
// 空间复杂度：O(n*l), n=paths.length, l=average num of files in each path

class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, Set<String>> map = new HashMap<>();
        for (String path: paths) {
            String[] files = path.split("\\s+");
            for (int i = 1; i < files.length; i++) {
                int left = files[i].indexOf('('), right = files[i].indexOf(')');
                String content = files[i].substring(left, right + 1);
                String output = files[0] + "/" + files[i].substring(0, left);
                map.putIfAbsent(content, new HashSet<>());
                map.get(content).add(output);
            }
        }
        
        List<List<String>> res = new ArrayList<>();
        for (Set<String> set: map.values()) {
            if (set.size() > 1) {
                res.add(new ArrayList<>(set));
            }
        }
        
        return res;
    }
}