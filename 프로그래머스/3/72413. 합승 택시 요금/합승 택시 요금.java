import java.util.*;

class Solution {
    int[][] dis;
    Map<Integer, List<Edge>> g;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        dis = new int[n+1][3];
        for (int[] d : dis) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        
        g = new HashMap<>();
        
        for (int[] f : fares) {
            g.putIfAbsent(f[0], new ArrayList<>());
            g.putIfAbsent(f[1], new ArrayList<>());
            
            g.get(f[0]).add(new Edge(f[1], f[2]));
            g.get(f[1]).add(new Edge(f[0], f[2]));
        }
        
        int[] starts = {s, a, b};
        for (int i = 0; i<3; i++) {
            di(starts[i], i);
        }
        
        int max = Integer.MAX_VALUE;
        for (int i= 1;  i< n+1; i++) {
            int sum = dis[i][0] + dis[i][1] + dis[i][2];
            max  = max > sum ? sum : max; 
        }
        return max;
    }
    
    public void di(int s,int idx) {
        Queue<Edge> q = new PriorityQueue<>();
        q.offer(new Edge(s, 0));
        dis[s][idx] = 0;
        
        while (!q.isEmpty()) {
            Edge c = q.poll();
            
            if (dis[c.node][idx] < c.w) continue;
            
            for (Edge e : g.get(c.node)) {
                int nW = e.w + c.w;
                if (nW < dis[e.node][idx]) {
                    dis[e.node][idx] = nW;
                    q.offer(new Edge(e.node, nW));
                }
            }
        }
    }
    
    class Edge implements Comparable<Edge> {
        int node;
        int w;
        
        public Edge(int node, int w) {
            this.node = node;
            this.w = w;
        }
        
        @Override
        public int compareTo(Edge e) {
            return this.w - e.w;
        }
    }
}