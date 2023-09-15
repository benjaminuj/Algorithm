import java.util.*;

class Solution {
    static boolean[] visited;
    static Queue<Info> q;
    
    public class Info {
        String str;
        int depth;
        public Info(String str, int depth) {
            this.str = str;
            this.depth = depth;
        }
    }
    
    public int solution(String begin, String target, String[] words) {        
        visited = new boolean[words.length];
        q = new LinkedList<>();
        
        q.add(new Info(begin,0));
        return bfs(words, target);
    }
    
    public int bfs(String[] words, String target) {
        int lenOfStr = target.length();
        
        while(!q.isEmpty()) {
            Info info = q.poll();
            for(int i=0; i<words.length; i++) {
                if(info.str.equals(words[i]) || visited[i]) continue;
                
                int diffCnt = 0;
                
                for(int k=0; k<lenOfStr; k++) {
                    if(info.str.charAt(k) != words[i].charAt(k)) diffCnt++;  
                }
                
                if(diffCnt == 1) {
                    if(words[i].equals(target)) return info.depth+1;
                    q.add(new Info(words[i], info.depth+1));
                    visited[i] = true;
                }
            }
        }
        return 0;
    }
}