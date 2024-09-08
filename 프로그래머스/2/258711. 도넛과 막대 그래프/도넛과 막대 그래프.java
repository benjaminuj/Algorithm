import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int[] in = new int[1000001];
        int[] out = new int[1000001];
        List<Integer>[] graph = new ArrayList[1000001];
        
        for (int i = 1; i < 1000001; i++) {
            graph[i] = new ArrayList<>(); // 합치는 법 없나
        }
        for (int i = 0; i < edges.length; i++) {   
            graph[edges[i][0]].add(edges[i][1]);
            in[edges[i][1]]++;
            out[edges[i][0]]++;
        }
        
        // out만 있는 노드 찾기
        List<Integer> onlyOut = new ArrayList<>();
        for (int i = 1; i < 1000001; i++) {
            if (in[i] == 0) {
                onlyOut.add(i);
            }
        }
        
        // 임의의 정점 찾기
        int tempNode = 0;
        int max = Integer.MIN_VALUE;
        for (int n : onlyOut) {
            tempNode = out[tempNode] < out[n] ? n : tempNode;
        }
        // // out만 있는 노드가 1개: 해당 노드가 임의의 정점
        // if (onlyOut.size() == 1) {
        //     tempNode = onlyOut.get(0);
        // } 
        // // out만 있는 노드가 2개 이상: in이 있는 노드한테 뻗은 노드(out만 있는 노드)가 임의의 정점
        // if (onlyOut.size() > 1) {
        //     outer:
        //     for (int node : onlyOut) {
        //         for (int next : graph[node]) {
        //             if (in[next] != 0) {
        //                 tempNode = node;
        //                 break outer;
        //             }
        //         }
        //     }
        // }
        
        answer[0] = tempNode;
        
        int eight = 0;
        int doughnut = 0;
        int rod = 0;
        for (int start : graph[tempNode]) {
            int[] result = bfs(start, graph);
            
            int nodeCnt = result[0];
            int edgeCnt = result[1];
            
            if (nodeCnt == edgeCnt) {
                doughnut++;
            } else if (nodeCnt == edgeCnt + 1) {
                rod++;
            } else if (nodeCnt + 1 == edgeCnt) {
                eight++;
            } 
        }
        
        answer[1] = doughnut;
        answer[2] = rod;
        answer[3] = eight;

        return answer;
    }
    
    public int[] bfs(int start, List<Integer>[] graph) {
        Queue<Integer> q = new ArrayDeque<>();
        Set<Info> visited = new HashSet<>();
        q.add(start);
        
        Set<Integer> nodes = new HashSet<>();
        Set<Info> edges = new HashSet<>();
        nodes.add(start);
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for (int next : graph[cur]) {
                if (!visited.contains(new Info(cur, next))) {
                    q.add(next);
                    nodes.add(next);
                    visited.add(new Info(cur, next));
                    edges.add(new Info(cur, next));
                }
            }
        }
        
        return new int[]{nodes.size(), edges.size()};
    }
    
    class Info {
        int s;
        int e;
        
        public Info (int s, int e) {
            this.s = s;
            this.e = e;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o instanceof Info) {
                Info info = (Info)o;
                return info.s == this.s && info.e == this.e;
            }
            return false;
        }        
        
        @Override
        public int hashCode() {
            return Objects.hash(this.s, this.e);
        }
    }
}