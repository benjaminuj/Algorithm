import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, HashSet<String>> reportMap = new HashMap<>(); // 신고 당한 id , Set(신고 한 id)
        HashMap<String, Integer> beReportedCnt = new HashMap<>(); // 신고 당한 id, 신고 당한 횟수
        HashMap<String, Integer> result = new HashMap<>(); // id, 결과 메일 받을 횟수
        
        for (int i= 0; i < report.length; i++) {
            String[] info = report[i].split(" ");
            
            reportMap.putIfAbsent(info[1], new HashSet<>());
            if (reportMap.get(info[1]).add(info[0])) {
                beReportedCnt.putIfAbsent(info[1], 0);
                beReportedCnt.put(info[1], beReportedCnt.get(info[1])+1);
            }
            
            
        }
        
        for (String name : beReportedCnt.keySet()) {
            if(beReportedCnt.get(name) >= k) {
                for (String rName : reportMap.get(name)) {
                    result.putIfAbsent(rName, 0);
                    result.put(rName, result.get(rName) +1);
                }
            }
            
        }
        
        
        for (int i =0 ; i < id_list.length; i++) {
            if (result.containsKey(id_list[i])) {
                answer[i] = result.get(id_list[i]);
            }
        }
        return answer;
    }
}