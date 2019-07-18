https://leetcode.com/problems/random-pick-with-weight/

// 解法一：Binary Search
//         利用辅助数组记录每个位置i对应的和，然后查找随机生成的[1:sum]的数，转化为35. Search Insert Position。
// 犯错点：1.Java内置函数使用错误：rand.nextInt(sum)返回的是[0:sum-1]，如果sum数组为[1,2,3]那么3永远不会被选到，
//             而1则有2/3的概率被选到。因此，pick应该为rand.nextInt(sum)+1.
//         2.数据结构知识错误：high=mid-1应该是在pick<copy[mid]时更新。

class Solution {
    Random rand;
    int sum;
    int[] copy;
    
    public Solution(int[] w) {
        rand = new Random();
        sum = 0;
        copy = new int[w.length];
        
        for (int i = 0; i < w.length; i++) {
            if (i != 0) w[i] += w[i - 1];
            copy[i] = w[i];
        }
        sum = copy[copy.length - 1];
    }
    
    public int pickIndex() {
        int low = 0, high = copy.length - 1;
        //int pick = rand.nextInt(sum);  // {Mistake 1}
        int pick = rand.nextInt(sum) + 1;  // {Correction 1}
        while (low <= high) {
            int mid = low + (high - low) / 2;
            //if (pick >= copy[mid]) high = mid - 1;  // {Mistake 2}
            if (pick <= copy[mid]) high = mid - 1;  // {Correction 2}
            else low = mid + 1;
        }
        
        return low;
    }
}


// 解法二：TreeMap
//         利用TreeMap内部的BST来查找。

class Solution {
    TreeMap<Integer, Integer> map;
    Random rand;
    int sum;
    
    public Solution(int[] w) {
        map = new TreeMap();
        rand = new Random();
        sum = 0;
        
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            map.put(sum, i);
        }
    }
    
    public int pickIndex() {
        Integer ceiling = map.ceilingKey(rand.nextInt(sum) + 1);

        /* 另一种写法 */
        //Integer pick = map.higherKey(rand.nextInt(sum));

        return map.get(ceiling);
    }
}