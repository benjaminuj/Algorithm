import java.util.*;

class Solution {
    int maxSheep = 0;
    public int solution(int[] info, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
        }
        
        boolean[] visited = new boolean[info.length];
        visited[0] = true;
        search(info, graph, 0, 0,0, visited);
        
        return maxSheep;
    }
    
    void search(int[] info, Map<Integer, List<Integer>> graph, int index, int sheep, int wolf, boolean[] visited) {
        if (info[index] == 0) {
            maxSheep = Math.max(maxSheep, ++sheep);
        } else {
            if(wolf + 1 >= sheep) {
                return;
            } else {
                wolf += 1;
            }
        }
        
        for (int i =0 ; i < visited.length; i++) {
            if (visited[i]) {
                if(graph.containsKey(i)) {
                    for (int next: graph.get(i)) {
                        if(!visited[next]) {
                            visited[next] = true;
                            search(info, graph, next, sheep, wolf, visited);
                            visited[next] = false;
                        }
                    }
                }
            }
        }
    }
}