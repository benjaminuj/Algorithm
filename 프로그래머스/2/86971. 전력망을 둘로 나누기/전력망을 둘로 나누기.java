import java.util.*;

class Solution {
    int count1 = 0;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        List<Integer>[] arr = new ArrayList[n+1];
        boolean[] visited = new boolean[n+1];
        
         for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<Integer>();
         }
        
        for (int i = 0; i < wires.length; i++) {
            arr[wires[i][0]].add(wires[i][1]);
            arr[wires[i][1]].add(wires[i][0]);
        }
        
        for (int i = 0; i < wires.length; i++) {
            int u = wires[i][0];
            int v = wires[i][1];

            // 전선을 끊음
            arr[u].remove((Integer) v); // 에러 해결 
            arr[v].remove((Integer) u);
            
            dfs(arr, visited, wires[i][0]);
            int count2 = n - count1; // 나머지 송전탑 수

            answer = Math.min(answer, Math.abs(count1 - count2));
            
            // 전선을 다시 연결
            arr[u].add(v);
            arr[v].add(u);
            visited = new boolean[n+1];
            
            answer = Math.min(answer, Math.abs(count1 - count2));
            count1 = 0;
        }
        
        
        return answer;
    }
    
    public void dfs(List<Integer>[] arr, boolean[] visited, int start) {
        visited[start] = true;
        count1++;
        
        for (int next : arr[start]) {
            if (!visited[next]) {
                dfs(arr, visited, next);
            }
        }
    }
}