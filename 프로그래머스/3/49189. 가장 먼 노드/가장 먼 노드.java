import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        int[] dis = new int[edge.length];
        List<Integer>[] conn = new ArrayList[n+1];
        for(int i = 0 ; i <= n; i++) {
            conn[i] = new ArrayList<>();
        }
        
        for(int i = 0 ; i < edge.length; i++) {
            conn[edge[i][0]].add(edge[i][1]);
            conn[edge[i][1]].add(edge[i][0]);
        }
        bfs(conn, dis);
        int maxLen = Arrays.stream(dis).max().getAsInt();
        
        List<Integer> after = Arrays.stream(dis).boxed().collect(Collectors.toList());
        answer = Collections.frequency(after, maxLen);
        return answer;
    }
    
    public void bfs(List<Integer>[] conn, int[] dis) {
        Queue<Integer> q = new LinkedList<>();
        dis[1] = 1;
        q.add(1);
        
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : conn[now]) {
                if (dis[next] == 0) {
                    q.add(next);
                    dis[next] = dis[now]+1;
                }
            }
        }
    } 
}