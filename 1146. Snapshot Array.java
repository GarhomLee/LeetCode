https://leetcode.com/problems/snapshot-array/

// 思路：Design + TreeMap
//         维护变量：
//         1）snapId，表示当前的snap
//         2）TreeMap<Integer, Integer>[] arr，其index为SnapshotArray中元素的index。
//             TreeMap的key为snapId，value为该元素在snapId时的值。
//         实现以下功能：
//         1）void set(int index, int val)，在当前snapId下，将index元素的值设为val，也就是找到
//             arr[index]对应的TreeMap新增一个key-value pair。
//         2）int snap()，只需要更新snapId即可，返回更新前的snapId。
//         3）int get(int index, int snap_id)，找到arr[index]对应的TreeMap中snap_id的floor key，
//             表示snap_id往前的最近更新的值。也就是说，从floor key到snap_id，这个值都没有更新过，因此
//             返回这个值。
// 时间复杂度：set(): O(1); snap(): O(1); get: O(log n)
// 空间复杂度：O(n)

class SnapshotArray {
    TreeMap<Integer, Integer>[] arr;
    int snapId;
    
    public SnapshotArray(int length) {
        snapId = 0;
        arr = new TreeMap[length];
        /* initialize all values at snapId == 0 */
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new TreeMap<>();
            arr[i].put(0, 0);
        }
    }
    
    public void set(int index, int val) {
        arr[index].put(snapId, val);
    }
    
    public int snap() {
        snapId++;
        return snapId - 1;
    }
    
    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> map = arr[index];
        int floorId = map.floorKey(snap_id);
        return map.get(floorId);
    }
}
