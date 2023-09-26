import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        PriorityQueue<Info> pq = new PriorityQueue<>(); // 1순위, 2순위, 합
        HashMap<String, int[]> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        
        int[] temp = new int[3];
        for(int i=0; i<genres.length; i++) {
            if(map.containsKey(genres[i])) {
                int first = map.get(genres[i])[0];
                int twice = map.get(genres[i])[1];
                int sum = map.get(genres[i])[2];
                
                sum += plays[i];
                
                if(twice == -1) {
                    twice = i;
                } 
                
                if(plays[twice] < plays[i]) {
                    twice = i;
                }
                            
                if(plays[first] < plays[i]) {
                    int g = first;
                    first = i;
                    twice = g;
                } 
                
                temp = new int[] {first, twice, sum};
                map.put(genres[i], temp);
            } else {
                temp = new int[] {i,-1,plays[i]};
                map.put(genres[i], temp);
            }
            
        }
        
        Iterator iter = map.keySet().iterator();
        while(iter.hasNext()) {
            String gen = iter.next().toString();
        
            pq.add(new Info(gen, map.get(gen)[2]));
        }
        
        while(!pq.isEmpty()) {
            String gen = pq.poll().genre;
            
            result.add(map.get(gen)[0]);
            
            if(map.get(gen)[1]==-1) {
                continue;
            }   
            result.add(map.get(gen)[1]);
        }
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
    class Info implements Comparable<Info> {
        String genre;
        int cnt;
        public Info(String genre, int cnt) {
            this.genre = genre;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Info o) {
            return o.cnt - this.cnt;
        }
    }
}