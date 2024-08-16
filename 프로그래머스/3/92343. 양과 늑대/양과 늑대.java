class Solution {
    public int solution(int[] info, int[][] edges) {
        int answer = 1;
        
        boolean[] visited = new boolean[info.length];
        visited[0] = true;

        return dfs(info, edges, visited, 1, 0);
        
        
    }
    
    int dfs(int[] info, int[][] edges, boolean[] visited, int sheep, int wolf) {
        //✅ 방문표시를 여기서 하지않는다!!
        if (sheep == wolf) return sheep;
        int maxSheep = sheep;
        
        //✅ 모든 edge를 확인한다.
        for (int[] edge : edges) {
            final int parent = edge[0];
            final int child = edge[1];
            
            if (visited[parent] && !visited[child]) {
                //✅ 다음 노드를 방문표시한다.
                visited[child] = true;
                
                if (info[child] == 0) {
                    maxSheep = Math.max(maxSheep, dfs(info, edges, visited, sheep + 1, wolf));
                
                } else {
                    maxSheep = Math.max(maxSheep, dfs(info, edges, visited, sheep, wolf + 1));
                }
                //✅ 다음 노드를 방문표시 해제한다.
                visited[child] = false;
            }
        }
        
        return maxSheep;
    }
}