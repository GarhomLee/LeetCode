https://leetcode.com/problems/exclusive-time-of-functions/

// 思路：Stack
//         step1: 维护变量：res数组，长度为n，res[i]表示id为i的任务所用的总时间；currId，表示当前进行的任务的id，
//             初始化为-1；currStart，表示当前进行的任务的最近起始时间，且规定为【一定是某个unit的start而不是
//             前一个unit的end】，初始化为-1；stack，顶部记录最近一个被中断的任务id。
//         step2: 遍历logs，将每个log从":"分为id，state，timestamp三段。根据state的两种情况，分别进行入栈和出栈：
//             1）state为"start"，入栈前先判断当前是不是第一个log。如果不是第一个log，那么有被中断的任务，将被中断的
//                 任务currId入栈，将当前运行的时间段timestamp - currStart累加加到res[currId]，同时更新currId
//                 和currStart；如果是第一个log，没有被中断的任务，那么只需要更新currId和currStart而不用入栈。
//             2）state为"end"，那么要将当前运行的时间段timestamp + 1 - currStart累加加到res[currId]，同时【更新
//                 currStart为timestamp + 1，符合currStart定义】。接下来要判断，只有Stack不为空，即还有被中断的
//                 任务，才需要将其恢复继续运行，currId更新为stack.pop()。
//         step3: 返回结果res数组
// 时间复杂度：O(l), l=logs.size()
// 空间复杂度：O(l), l=logs.size()。可以用int[] + top指针模拟stack，注意要【将数组长度初始化为logs.size()而不是n】。
// 犯错点：1.初始化错误：用int[] + top指针模拟stack时，要将数组长度初始化为logs.size()而不是n。
//         2.特殊情况判断错误：将currId初始化为-1，在状态为"start"时，说明要运行新的function，只有当currId >= 0，
//             即当前至少有一个function正在运行，才需要将时间累加到res[currId]，将currId放入stack。
//             也就是说，当"start"为第一个log时，没有正在运行的function，因此只需要更新currId。
//             如果判断条件为!stack.isEmpty()，那么stack.push()永远不会被执行。
//         3.越界错误：用int[] + top指针模拟stack时，当top为-1，说明stack为空，当前没有被中断的function，因此
//             不需要更新currId。
//         4.细节错误：当状态为"end"时，要将为了保持currStart的定义，要将currStart更新为timestamp + 1。

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        //int currId = 0, currStart = 0;  // {Mistake 2}
        int currId = -1, currStart = -1;
        
        //int[] stack = new int[n];  // {Mistake 1}
        //int[] stack = new int[logs.size()];  // {Correction 1}
        //int top = -1;
        Stack<Integer> stack = new Stack();
        for(String log: logs) {
            String[] split = log.split(":");  // split a log into three parts: currId, state, and timestamp
            String state = split[1];
            int timestamp = Integer.valueOf(split[2]);
            if (state.equals("start")) {  // "start" state
                //if (!stack.isEmpty())  // {Mistake 2}
                if (currId >= 0) {  // {Correction 2}
                    res[currId] += timestamp - currStart;
                    stack.push(currId);
                }
                
                currId = Integer.valueOf(split[0]);
                currStart = timestamp;
            } else if (state.equals("end")) {  // "end" state
                res[currId] += timestamp + 1 - currStart;
                // {Mistake 3}
                if (!stack.isEmpty()) {  // {Correction 3}
                    currId = stack.pop();  // resume the interrupted function if there is one
                }
                //currStart = timestamp;  // {Mistake 4}
                currStart = timestamp + 1;  // {Correction 4}
            }
        }
        
        return res;
    }
}


二刷：Stack top存的是当前运行的func id

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {       
        int[] ret = new int[n];
        int prevTime = 0;
        Deque<Integer> stack = new LinkedList<>();  // stack of func id
        for (String log: logs) {
            String[] split = log.split(":");
            int currId = Integer.valueOf(split[0]);
            boolean isStart = split[1].equals("start");
            int currTime = Integer.valueOf(split[2]) + (isStart ? 0 : 1);  // transform from time unit to time point
            int deltaTime = currTime - prevTime;
            
            if (isStart) {
                if (!stack.isEmpty()) {
                    int prevId = stack.peek();
                    ret[prevId] += deltaTime;
                }
                
                stack.push(currId);
            } else {
                stack.pop();
                ret[currId] += deltaTime;
            }
            
            prevTime = currTime;
        }
        
        return ret;
    }
}