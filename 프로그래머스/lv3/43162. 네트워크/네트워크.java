import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[computers.length];
        
        for(int i=0; i<computers.length; i++) {
            if(!visited[i]) {
                answer++;
                bfs(i, computers);
            }
        }
        return answer;
    }
    
    public void bfs(int start, int[][] computers) {
        visited[start] = true;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        
        while(!q.isEmpty()) {
            int here = q.poll();
            
            for(int i=0; i<computers.length; i++) {
                if(here == i) continue;
                if(computers[here][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        
        
    }
}