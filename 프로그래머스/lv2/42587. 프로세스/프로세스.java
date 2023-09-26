import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        Queue<Info> q = new LinkedList<>();
        
        for(int i=0; i<priorities.length; i++) {
            pq.add(new Info(priorities[i], i));
            q.add(new Info(priorities[i], i));
        }
        
        while(!q.isEmpty()) {
            int priority = pq.peek().priority;
            
            while(priority != q.peek().priority) {
                Info temp = q.poll();
                q.add(temp);
            }
            Info here = q.poll();
            pq.poll();
            answer++;
            
            if(here.index == location) {
                break;
            }
            
        }
        return answer;
    }
    
    class Info implements Comparable<Info> {
        int priority;
        int index;
        
        public Info(int priority, int index) {
            this.priority = priority;
            this.index = index;
        }
        
        @Override
        public int compareTo(Info o) {
            if(o.priority < this.priority) return -1;
            else if(o.priority == this.priority) {
                return this.index - o.index;
            } else {
                return 1;
            }
        }
    }
}