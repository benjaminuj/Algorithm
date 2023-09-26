import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        
        Queue<Info> q = new LinkedList<>();
        
        for(int i=1; i<progresses.length; i++) {
            q.add(new Info(progresses[i], speeds[i]));
        }
        
        int day = (100-progresses[0])/speeds[0];
        day = (100-progresses[0])%speeds[0] == 0 ? day : day+1;
        int count = 1;
        while(!q.isEmpty()) {
            Info now = q.poll();
            
            if((now.speed * day) + now.progress >= 100) {
                count++;
            } else {
                day = (100-now.progress)/now.speed;
                day = (100-progresses[0])%speeds[0] == 0 ? day : day+1;
                answer.add(count);
                count=1;
            }
        }
        answer.add(count);
        int[] result = new int[answer.size()];
        for(int i=0; i<answer.size(); i++) {
            result[i] = answer.get(i);
        }
        return result;
    }
    
    public class Info {
        int progress;
        int speed;
        
        public Info(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }
    }
}