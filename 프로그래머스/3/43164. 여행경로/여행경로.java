import java.util.*;

class Solution {
    static  Map<String, List<String>> map = new HashMap<>();
    static  Map<String, List<String>> visite = new HashMap<>();
    static int total =0;
    static List<String> candidate = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        for (int i = 0; i < tickets.length; i++) {
            map.putIfAbsent(tickets[i][0], new ArrayList<>());
            List<String> temp = map.get(tickets[i][0]);
            temp.add(tickets[i][1]);
            
            visite.putIfAbsent(tickets[i][0], new ArrayList<>());
            List<String> temp1 = visite.get(tickets[i][0]);
            temp1.add(tickets[i][1]);
            
            total++;
        }
        
        String[] answer = new String[total+1];

        dfs("ICN", "ICN", 0);
        
        Collections.sort(candidate);
        
        String c = candidate.get(0);
        int idx = 0;
        for (int i =0; i < c.length(); i=i+3) {
            answer[idx++] = c.substring(i, i+3);
        }
        return answer;
    }
    
    public void dfs(String s, String root, int count) {
        if (count == total) {
            candidate.add(root);
            return;
        } 
        
        if(!map.containsKey(s) || visite.get(s).size() == 0) return;
        
        for(String n : map.get(s)) {
            if (!visite.get(s).contains(n)) continue;
            
            visite.get(s).remove(n);
            root += n;
            
            dfs(n, root, count+1);
            
            visite.get(s).add(n);
            root = root.substring(0, root.length()-3);
        }
    }
}