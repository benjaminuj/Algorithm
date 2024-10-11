import java.util.*;

class Solution {
    static int n;
    Map<String, List<Integer>> allCases = new HashMap<>(); // 가능한 모든 조합, {가능한 모든 수의 합}
    Map<String, String> pair = new HashMap<>(); // 대진짝
    int maxWin = -1;
    String candidate;
    
    public int[] solution(int[][] dice) {
        n = dice.length;
        List<Integer> answer = new ArrayList<>();
        
        combi(new StringBuilder(), 0, dice);
        
        match();
        
        for (int i = 0; i < candidate.length(); i++) {
            answer.add((int)(candidate.charAt(i) - '0') + 1); // 주사위 숫자는 1부터
        }
        Collections.sort(answer);
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public void match() {
        for (String me : allCases.keySet()) {
            if (me.length() != n/2) continue;
            
            List<Integer> meList = allCases.get(me);
            List<Integer> opList = allCases.get(pair.get(me));
            
            int meWinCnt = 0;
            int opWinCnt = 0;
            
            // optimization!! 정렬 후 누적합을 사용해서 O(N)으로 처리하기
            Collections.sort(meList);
            Collections.sort(opList);
            
            int[] sum = new int[meList.size() + 1];
            int opIdx = 0;
            int endIdx = 0;
            for (int i = 1; i <= meList.size(); i++) {
                int cur = meList.get(i-1);
                int cnt = 0;
                
                while (opIdx < opList.size() && cur > opList.get(opIdx)) {
                    cnt++;
                    opIdx++;
                }
                if (i == 1) {
                    sum[i] = 2 * sum[i-1] + cnt;
                } else {
                    sum[i] = (cnt + (sum[i-1] - sum[i-2])) + sum[i-1];
                }
                
                endIdx = i;
            }
            // 처리 안된 부분 예외처리
            if (endIdx < meList.size()-1) {
                while (endIdx < meList.size()) {
                    sum[endIdx] = sum[endIdx-1];
                    endIdx++;
                }
            }
            
            int winCnt = sum[meList.size()];
            if (maxWin < winCnt) {
                maxWin = winCnt;
                candidate = me;
            }
        }
    }
    
    public void combi(StringBuilder idxs, int sIdx, int[][] dice) {
        // 가능한 모든 조합 
        if (idxs.length() != 0) {
            String str = new String(idxs);
            allCases.putIfAbsent(str, new ArrayList<>());
            // 키의 조합으로 가능한 모든 숫자 합 구하기 
            allCases.put(str, calculator(str, dice)); 
        }
        
        if (idxs.length() == n/2) {
            // 대진짝 구하기
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                String iStr = String.valueOf(i);
                List<String> arr = Arrays.asList(idxs.toString().split(""));
                if (!arr.contains(iStr)) { // String배열에 int 타입 넣으면 NPE 발생***
                    sb.append(i);
                }
            }
            
            String s1 = new String(sb);
            String s2 = new String(idxs);
            pair.put(s1, s2);
            pair.put(s2, s1);
            
            // 탐색 끝
            return;
        }
        
        for (int i = sIdx; i < n; i++) {
            idxs.append(i);
            combi(idxs, i+1, dice);
            idxs.deleteCharAt(idxs.length()-1);
        }
    }
    
    public List<Integer> calculator(String str, int[][] dice) {
        if (allCases.get(str).size() != 0) {
            return allCases.get(str);
        }
        
        List<Integer> result = new ArrayList<>();
        
        if (str.length() == 1) {
            for (int i : dice[Integer.parseInt(str)]) {
                result.add(i);
            }
            return result;
        }
        
        List<Integer> befores = calculator(str.substring(0, str.length()-1), dice);
        for (int value : befores) {
            int idx = (int)(str.charAt(str.length()-1) -'0');
            for (int j : dice[idx]) {
                result.add(value + j);
            }
        }
        
        return result;
    }
}