https://leetcode.com/problems/k-closest-points-to-origin/

// 解法一：Sort
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        if (K == points.length) {
            return points;
        }
        
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                return (p1[0] * p1[0] + p1[1] * p1[1]) - (p2[0] * p2[0] + p2[1] * p2[1]);
            }
        });
        
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i][0] = points[i][0];
            res[i][1] = points[i][1];
        }
        
        return res;
    }
}


// 解法二：Quick Select，题目转化为找出前k小的元素。
//         step0:特殊情况，K == points.length，直接返回所有points
//         step1:主体框架：维护指针low和high，表示从范围points[low:high]中找到第K小的元素所在的位置pivot。
//             如果位置pivot == K - 1，说明找到了，跳出循环；
//             如果pivot > K - 1，说明第K小的元素在pivot前，所以更新high = pivot - 1；
//             如果pivot < K - 1，说明第K小的元素在pivot后，所以更新low = pivot + 1
//         step2:利用helper method来将points[low:high]以points[low]为pivotNum排序，小于等于pivotNum的
//             放在左边，大于pivotNum的放在右边。
//             维护指针：left指向第一个可以放置小于等于pivotNum的元素的位置；right指向可以放置大于pivotNum
//             的元素的位置，待搜索的范围为[left:right]。
//             排序完成，【将pivotNum和points[right]交换位置，因为此时points[low:right]都是小于等于pivotNum
//             的元素】。
//             结果返回pivotNum的实际位置right。
//         step3:取points数组前K个元素，放到新数组res，最后返回res数组。
// 时间复杂度：O(n) on average
// 空间复杂度：O(n)

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        if (K == points.length) {
            return points;
        }
        /* find the pivot index by quick sort so that [0:pivot] contains smallest K elements */
        int low = 0, high = points.length - 1;
        while (low <= high) {
            int pivot = quickSelect(points, low, high);
            if (pivot == K - 1) {
                break;
            } else if (pivot > K - 1) {
                high = pivot - 1;
            } else {
                low = pivot + 1;
            }
        }
        
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i][0] = points[i][0];
            res[i][1] = points[i][1];
        }
        
        return res;
    }
    
    private int quickSelect(int[][] points, int low, int high) {
        int pivotNum = distance(points[low]);
        int left = low + 1, right = high;
        while (left <= right) {
            if (distance(points[left]) > pivotNum) {
                swap(points, left, right);
                right--;
            } else {
                left++;
            }
        }
        swap(points, low, right);
        return right;
    }
    
    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
    
    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}