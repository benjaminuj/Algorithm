import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();
        String[] todayStr = today.split("\\.");
        int y = Integer.parseInt(todayStr[0]);
        int m = Integer.parseInt(todayStr[1]);
        int d = Integer.parseInt(todayStr[2]);

        
        Map<String, Integer> map = new HashMap<>();
        for (String term : terms) {
            String[] temp = term.split(" ");
            
            map.put(temp[0], Integer.parseInt(temp[1]));
        }
        int idx = 1;
        for (String ch : privacies) {
            String[] temp = ch.split(" ");
            String[] day = temp[0].split("\\.");
            String pri = temp[1];
            
            int yForCheck = Integer.parseInt(day[0]);
            int mForCheck = Integer.parseInt(day[1]);
            int dForCheck = Integer.parseInt(day[2]);
            
            int ava = map.get(pri);
            
            int addY = ava/12;
            int addM = ava%12;
            
            if (dForCheck == 1) {
                addM--;
                dForCheck = 28;
            } else {
                dForCheck--;
            }
            
            int dueY = yForCheck + addY;
            int dueM = mForCheck + addM;
            int dueD = dForCheck;
            
            if (dueM > 12) {
                dueY += dueM/12;
                dueM %= 12; 
            }
            
            if (dueY < y) {
                result.add(idx);
            } else if (dueY == y) {
                if (dueM < m) {
                    result.add(idx);
                } else if (dueM == m) {
                    if (dueD < d) {
                        result.add(idx);
                    }
                }
            }
            idx++;
        }
        int[] answer = result.stream().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }
}