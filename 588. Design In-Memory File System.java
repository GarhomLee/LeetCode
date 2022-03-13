https://leetcode.com/problems/design-in-memory-file-system/


class FileSystem {
    class Node {
        Map<String, Node> next; // dir name -> node
        boolean isFile;
        StringBuilder fileContent;
        
        public Node(boolean isFile) {
            this.next = new HashMap<>();
            this.isFile = isFile;
        }
    }
    
    Node root;
    
    public FileSystem() {
        root = new Node(false);
    }
    
    public List<String> ls(String path) {
        Node curr = nagivate(path);
        List<String> ret = new ArrayList<>();
        if (curr.isFile) {
            String[] dirs = getDir(path);
            ret.add(dirs[dirs.length - 1]);
        } else {
            ret.addAll(curr.next.keySet());
            Collections.sort(ret);
        }
        return ret;        
    }
    
    public void mkdir(String path) {
        nagivate(path);
    }
    
    public void addContentToFile(String filePath, String content) {
        Node curr = nagivate(filePath);
        if (!curr.isFile) {
            curr.isFile = true;
            curr.fileContent = new StringBuilder();
        }
        
        curr.fileContent.append(content);
    }
    
    public String readContentFromFile(String filePath) {
        Node curr = nagivate(filePath);
        return curr.fileContent.toString();
    }
    
    private String[] getDir(String path) {        
        // "/" will return an array that has one empty string [""]
        String[] ret = path.substring(1).split("/");
        return ret;
    }
    
    // navigate and create dir if not exists
    private Node nagivate(String path) {
        String[] dirs = getDir(path);
        Node curr = root;
        for (String dir : dirs) {
            // "/" will return an array that has one empty string [""]
            if (dir.isEmpty()) {
                return curr;
            }
            curr.next.putIfAbsent(dir, new Node(false));
            curr = curr.next.get(dir);
        }
        return curr;
    }
}