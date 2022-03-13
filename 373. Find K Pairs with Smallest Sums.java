https://leetcode.com/problems/find-k-pairs-with-smallest-sums/

// 解法一：maxHeap
//         将所有pair放入maxHeap，维护maxHeap的最大size为k。
// 时间复杂度：O(n1.length * n2.length * log k)
// 空间复杂度：O(m log m), m = min(n1.length * n2.length, k)

// 解法二：优化时间复杂度，用minHeap。
//         先将所有的(nums1[i],nums2[0])配对放入minHeap，同时记录对应的nums2的index，初始值为0（都是nums2[0]）。
//         依次取出minHeap的顶端元素。记这个元素记录的nums2的index为j，由于做了预处理，那么可知所有nums2[0:j-1]都被处理过了，
//         所以只需要将(nums1[i],nums2[j+1])配对放入minHeap，同时记录对应的nums2的index为j+1
// 时间复杂度：O((n1 + k) * log n1)
// 空间复杂度：O(n1)
// 犯错点：1.要注意nums1为空，nums2为空，k==0的特殊情况
//        2.不能调换nums1和nums2，结果中List第一个元素一定来自于input的nums1，第二个元素一定来自于input的nums2

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList();
        if(nums1.length == 0 || nums2.length == 0 || k == 0) return res;  // {Mistake 1} {Correction 1: corner cases}
        
        /*if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }*/  // {Mistake 2: it is not allow to switch two nums array} {Correction 2}
        
        PriorityQueue<int[]> pq = new PriorityQueue(1, new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return (pair1[0] + pair1[1]) - (pair2[0] + pair2[1]);
            }
        });
        
        for (int i = 0; i < nums1.length; i++) {  // preprocess pair (nums1[i], nums2[0]) with index info of nums2 element
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }
        
        while (k > 0 && !pq.isEmpty()) {
            int[] pair = pq.poll();
            k--;
            res.add(Arrays.asList(pair[0], pair[1]));
            
            if (pair[2] < nums2.length - 1) {
                int nextIndex = pair[2] + 1;  // get the next index of nums2 element
                pq.offer(new int[]{pair[0], nums2[nextIndex], nextIndex});  // put new pair into minHeap, with index info of the next nums2 element
            }
        }
        return res;
    }
}