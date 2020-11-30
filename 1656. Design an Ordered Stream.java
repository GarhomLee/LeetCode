https://leetcode.com/problems/design-an-ordered-stream/

idea: Record the start idx of the next chunk
time complexity: O(n)
space complexity: O(1)

class OrderedStream {
    String[] arr;
    int n;
    int currIdx;
    
    public OrderedStream(int n) {
        this.arr = new String[n+1];
        this.n = n;
        this.currIdx = 1;
    }
    
    public List<String> insert(int id, String value) {
        this.arr[id] = value;
        List<String> res = new ArrayList<>();
        if (id == this.currIdx) {
            while (this.currIdx <= this.n && this.arr[this.currIdx] != null) {
                res.add(this.arr[this.currIdx++]);
            }
        }
        return res;
    }
}
