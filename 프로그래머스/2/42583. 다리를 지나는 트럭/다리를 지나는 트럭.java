import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Info> q = new ArrayDeque<>();
        int idx = 0;
        int sumw = 0;
        int time = 1;
        
        while (idx < truck_weights.length) {
            if (!q.isEmpty()) {
                while(time - q.peek().t == bridge_length) {
                    sumw -= q.peek().w;
                    q.poll();
                    
                    if (q.isEmpty()) break;
                }
            }

            if (sumw + truck_weights[idx] <= weight) {
                q.add(new Info(truck_weights[idx], 1, time));
                sumw += truck_weights[idx];
                idx++;
                
            } 
            time++;
        }
        
        while (!q.isEmpty()) {
            while(time - q.peek().t == bridge_length) {    
                    q.poll();
                    
                    if (q.isEmpty()) break;
            }
            time++;
        }
        
        
        return time-1;
    }
    
    class Info {
        int w;
        int dis;
        int t;
        
        public Info(int w, int dis, int t) {
            this.w = w;
            this.dis = dis;
            this.t = t;
        }
    }
}