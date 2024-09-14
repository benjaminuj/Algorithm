import java.util.*;

class Solution {
    static int n = 2500;
    static int[] parent;
    static String[] value;
    
    public String[] solution(String[] commands) {
        List<String> answers = new ArrayList<>();
        
        value = new String[n];
        parent = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        StringTokenizer st;
        for(int i = 0; i < commands.length; i++){
            st = new StringTokenizer(commands[i]);
            
            int r, c;
            int root;
            int idx;
            switch(st.nextToken()){
                case "UPDATE" :
                    String v1 = st.nextToken();
                    String v2 = st.nextToken();
                    
                    // UPDATE r c value 형식
                    if (st.hasMoreTokens()) {
                        String v = st.nextToken();
                        r = Integer.parseInt(v1);
                        c = Integer.parseInt(v2);
                        idx = ((r-1) * 50) + (c-1);
                        
                        root = find(idx);
                        value[root] = v;
                    } else { // UPDATE value1 value2 형식
                        Set<Integer> roots = new HashSet<>();
                        
                        // v1을 값으로 가지고 있는 셀 인덱스 조회
                        for (int k = 0; k < n; k++) {
                            if (value[k] != null && value[k].equals(v1)) {
                                value[k] = v2;
                            }
                        }
                    }
                    break;
                    
                case "MERGE" :
                    int r1 = Integer.parseInt(st.nextToken());
                    int c1 = Integer.parseInt(st.nextToken());
                    int r2 = Integer.parseInt(st.nextToken());
                    int c2 = Integer.parseInt(st.nextToken());
                    
                    int idx1 = ((r1-1) * 50) + (c1-1);
                    int idx2 = ((r2-1) * 50) + (c2-1);
                    
                    int root1 = find(idx1);
                    int root2 = find(idx2);
                    
                    if (root1 == root2) continue;
                    
                    if (value[root1] == null && value[root2] != null) {
                        int temp = root2;
                        root2 = root1;
                        root1 = temp;
                    }
                    
                    union(root1, root2);
                    find(idx2); // 기존에 root2에 속했었던 집합들 root1이랑 다이렉트로 연결되게 업데이트
                    
                    break;
                    
                case "UNMERGE" :
                    r = Integer.parseInt(st.nextToken());
                    c = Integer.parseInt(st.nextToken());
                    
                    idx = ((r-1) * 50) + (c-1);
                    root = find(idx);
                    
                    for (int j =0 ; j < n; j++) {
                        find(j);
                    }
                    
                    value[idx] = value[root];
                    for (int k = 0; k < n; k++) {
                        if(parent[k] == root) {
                            parent[k] = k;
                            
                            if (k != idx) {
                                value[k] = null;
                            }
                        }
                    }
                    break;
                    
                case "PRINT" :
                    r = Integer.parseInt(st.nextToken());
                    c = Integer.parseInt(st.nextToken());
                    
                    idx = ((r-1) * 50) + (c-1);
                    root = find(idx);
                    
                    if (value[root] != null) {
                        answers.add(value[root]);
                    } else if (value[root] == null) {
                        answers.add("EMPTY");
                    }
                    break;
            }
        }
        return answers.toArray(new String[answers.size()]);
    }
        
    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        int y = find(parent[x]);
        parent[x] = y;
        return y;
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }
}