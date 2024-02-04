import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Arrays.sort(summits);
        Set<Integer> summitSet = IntStream.of(summits).boxed().collect(Collectors.toSet());
        Map<Integer, List<Info>> graph = new HashMap<>();
        
        int[] answer = new int[]{0, Integer.MAX_VALUE};
        
        int[] visited = new int[n+1];
        Queue<Info> pq = new PriorityQueue<>();
        Arrays.fill(visited, Integer.MAX_VALUE);
        
        for (int i =0 ; i < paths.length; i++) {
            graph.putIfAbsent(paths[i][0], new ArrayList<>());
            graph.putIfAbsent(paths[i][1], new ArrayList<>());
            
            graph.get(paths[i][0]).add(new Info(paths[i][1], paths[i][2]));
            graph.get(paths[i][1]).add(new Info(paths[i][0], paths[i][2]));
        }
        
        for (int i = 0; i < gates.length; i++) {
            pq.offer(new Info(gates[i], 0));
            visited[gates[i]] = 0;
        }
        
    
            
        while (!pq.isEmpty()) {
            Info cur = pq.poll();

            if (visited[cur.node] < cur.time || summitSet.contains(cur.node)) {
                continue;
            }

            for (Info info : graph.get(cur.node)) {
                int nextIntensity = Math.max(info.time, cur.time);
                
                if (nextIntensity < visited[info.node]) {
                    visited[info.node] = nextIntensity;
                    pq.offer(new Info(info.node, nextIntensity));
                }
            }
        }

        for (int summit : summits) {
            if (visited[summit] < answer[1]) {
                answer[1] = visited[summit];
                answer[0] = summit;
            }
        }
        
        return answer;
    }
    
    class Info implements Comparable<Info> {
        int node;
        int time;
        
        public Info(int node, int time) {
            this.node = node;
            this.time = time;
        }
        
        @Override
        public int compareTo(Info o) {
            return this.time - o.time;
        }
    }
}