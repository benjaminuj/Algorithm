import java.util.*;

class Solution {
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        Set<Integer>[] graph = new HashSet[n];
        for (int i = 0 ; i < n ; i++) {
            graph[i] = new HashSet<>();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0 ; j < n; j++) {
                if (i == j) continue;
                
                if (computers[i][j] == 1) {
                    graph[i].add(j);
                }
                
            }
        }
    
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(graph, i);
                
                answer++;
            }
        }
        
        
        return answer;
    }
    
    public void bfs(Set<Integer>[] graph, int start) {
        Queue<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.add(start);
        
        while (!q.isEmpty()) {
            int node = q.poll(); 
            Set<Integer> set = graph[node];
            Iterator<Integer> iter = set.iterator();
            
            while (iter.hasNext()) {
                int n = iter.next();
                if (!visited[n]) {
                    visited[n] = true;
                    q.add(n);
                }
            }
        }
    }
}