import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        Arrays.fill(answer, Integer.MAX_VALUE);
        
        Integer[] gatesTemp = Arrays.stream(gates).boxed().toArray(Integer[]::new); // int[] -> Integer[] 방법 암기
        Integer[] summitsTemp = Arrays.stream(summits).boxed().toArray(Integer[]::new);
        Set<Integer> gateSet = new HashSet<>(Arrays.asList(gatesTemp));
        Set<Integer> summitSet = new HashSet<>(Arrays.asList(summitsTemp)); // 배열 -> 리스트: Arrays.asList(배열);
        
        Map<Integer, List<Integer[]>> graph = new HashMap<>(); // key = 시작 노드, value = {도착노드, 두 노드 사이 소요시간}
        for (int[] path : paths) {
            graph.putIfAbsent(path[0], new ArrayList<>());
            graph.get(path[0]).add(new Integer[]{path[1], path[2]});
            
            graph.putIfAbsent(path[1], new ArrayList<>());
            graph.get(path[1]).add(new Integer[]{path[0], path[2]});
        }
        
        Arrays.sort(summits); // !!봉우리 넘버 작은 순서: 정렬!!!!
        for (int summit : summits) {
            int[] result = new int[2];
            result = disktra(n, summit, graph, summitSet, gateSet);
            
            // 새로 구한 intensity가 더 작은 경우, 정답값 업데이트
            if (result[1] < answer[1]) {
                answer[0] = result[0];
                answer[1] = result[1];
            }
        }
    
        return answer;
    }
    
    public int[] disktra(int n, int start, Map<Integer, List<Integer[]>> graph, Set<Integer> summitSet, Set<Integer> gateSet) {
        Queue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(0, start));
        
        int[] answer = new int[2];
        boolean visited[] = new boolean[n+1];
        
        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            
            // 방문한 노드면, 이미 최소 intensity로 업데이트되어있기 때문에 이후 로직x
            if (visited[cur.node]) continue;
            
            // pq에서 뺀 후 방문체크
            visited[cur.node] = true; 
            
            // 현재 노드가 게이트 && 첫 게이트라 answer이 초기값이면: 해당 node랑 intensity를 정답으로
            if (gateSet.contains(cur.node) && answer[0] == 0) {
                answer[0] = start;
                answer[1] = cur.intensity;
                return answer;
            }
            
            for (Integer[] next: graph.get(cur.node)) {
                // 봉우리는 하나만 가능: 다음 노드도 봉우리이면 그 루트는 제외
                if (summitSet.contains(next[0])) continue;
                
                if (!visited[next[0]]) pq.offer(new Info(Math.max(cur.intensity, next[1]), next[0]));    
                

            }
        } 
        if (answer[0] == 0) {
            answer[0] = Integer.MAX_VALUE;
            answer[1] = Integer.MAX_VALUE;
        }
        return answer;
    }
    
    public class Info implements Comparable<Info> {
        int intensity;
        int node;
        
        public Info(int intensity, int node) {
            this.intensity = intensity;
            this.node = node;
        }
        
        @Override
        public int compareTo(Info o) {            
            return this.intensity - o.intensity;
        }
    }
}