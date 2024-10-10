import java.util.*;

class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> nodeMax = new HashMap<>();
    Map<Integer, Integer> nodeMin = new HashMap<>();
    Map<Integer, Integer> nodeVisit = new HashMap<>();
    Map<Integer, Integer> nodeIdx = new HashMap<>();
    Map<Integer, Deque<Integer>> nodeNums = new HashMap<>();
    List<Integer> visit = new ArrayList<>();
    
    boolean can = true;
    int required = 0;
    
    public int[] solution(int[][] edges, int[] target) {
        List<Integer> result = new ArrayList<>();
        
        // 트리 생성
        createTree(edges);
        // 리프노드 가능한 최대값 최소값 구하기
        getNodeMinMax(target);
        // 노드별 방문횟수 구하기
        createNodeVisit();
        if (!can) return new int[]{-1};
        // 실제로 노드 방문하기
        createNodeNum(target);
        
        for (int node : visit) {
            result.add(nodeNums.get(node).pollLast());
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public void createTree(int[][] edges) {
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            
            graph.putIfAbsent(parent, new ArrayList<>());
            graph.get(parent).add(child);
        }
        for (int node : graph.keySet()) {
            Collections.sort(graph.get(node));
        }
    }
    
    public void getNodeMinMax(int[] target) {        
        for (int i =0; i < target.length; i++){
            int node = i+1;
            int t = target[i];
            
            int n = t/3;
            int r = t%3;
            
            if (r != 0) {
                n += 1;
            }
            
            nodeMin.put(node, n);
            nodeMax.put(node, t);
        }
    }
    
    public void createNodeVisit() {
        // 리프노드 방문해야하는 최소값 
        for (int key : nodeMin.keySet()) {
            required += nodeMin.get(key);
        }

        while (required > 0) {
            int root = 1;
            if (dfs(root) == -1) {
                can = false;
                return;
            }
        }
    }
    
    public int dfs(int node) {
        nodeIdx.putIfAbsent(node, 0);
        int idx = nodeIdx.get(node);
        int result = 0;
        
        // 리프 노드 
        if (!graph.containsKey(node)) {
            nodeVisit.putIfAbsent(node, 0);
            nodeVisit.put(node, nodeVisit.get(node)+1);
            visit.add(node);

            if (nodeVisit.get(node) <= nodeMin.get(node)) {
                required--;
            }

            if (nodeVisit.get(node) > nodeMax.get(node)) {
                return -1;
            }
            return 0;
        } else {
            int child = graph.get(node).get(idx);
            nodeIdx.put(node, (nodeIdx.get(node)+1)%graph.get(node).size());
            node = child;
            result = dfs(node);
        }
        if (result == -1) return -1;
        return 0;
    }
    
    public void createNodeNum(int[] target) {
        for (int i = 0; i < target.length; i++) {
            if (target[i] == 0) continue;
            
            Deque<Integer> nums = new ArrayDeque<>();
            
            int node = i+1;
            
            int three = (target[i] - nodeVisit.get(node))/2;
            int two = (target[i] - nodeVisit.get(node))%2;
            
            for (int k = 0; k < three; k++) {
                nums.add(3);
            }
            for (int k = 0; k < two; k++) {
                nums.add(2);
            }
            for (int k = 0; k < nodeVisit.get(node) - (three + two); k++) {
                nums.add(1);
            }
            
            nodeNums.put(node, nums);
        }
    }
}