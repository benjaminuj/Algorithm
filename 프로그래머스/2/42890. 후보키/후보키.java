import java.util.*;

class Solution {
    List<Set<Integer>> com = new ArrayList<>(); // 가능한 모든 조합
    int colLen, rowLen;
    
    public int solution(String[][] relation) {
        int answer = 0;
        rowLen = relation.length;
        colLen = relation[0].length;
        List<Set<Integer>> candidateKeys = new ArrayList<>(); // 가능한 모든 조합중에서 유일성 만족한 값들

        back(0, new ArrayList<>());
        
        // 유일성 체크
        for (Set<Integer> c : com) {
            Set<String> unique = new HashSet<>();
            for (String[] a : relation) {
                StringBuilder sb = new StringBuilder();
                for (int i : c) {
                    sb.append(a[i]);
                }
                unique.add(sb.toString());
            }
            if (unique.size() == rowLen) {
                candidateKeys.add(new HashSet<>(c));
            }
        }
        
        boolean[] isMini = new boolean[candidateKeys.size()]; // candidateKeys의 인덱스와 동일한 인덱스 사용. 해당 조합이 최소성을 만족하는지 
        Arrays.fill(isMini, true);
        
        // 최소성 체크
        for (int i = 0 ; i < candidateKeys.size(); i++) {
            for (int j = i+1; j < candidateKeys.size(); j++) {
                if (candidateKeys.get(j).containsAll(candidateKeys.get(i))) {
                    isMini[j] = false;
                } 
                
                if (candidateKeys.get(i).containsAll(candidateKeys.get(j))) {
                    isMini[i] = false;
                }
            }
        }
        
        for (boolean b : isMini) {
            System.out.println(b);
            if (b) answer++;
        }

        return answer;
    }
    
    // 가능한 부분조합
    void back(int s, List<Integer> cur) {
        if (cur.size() != 0) {
            com.add(new HashSet<>(cur));
        } 
        
        if (s >= colLen) {
            return;
        }
        
        for (int i=s ; i< colLen; i++) {
            cur.add(i);
            back(i+1, cur);
            cur.remove(cur.size()-1);
        }
    }
}