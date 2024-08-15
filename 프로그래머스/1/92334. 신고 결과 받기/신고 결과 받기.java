import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        Map<String, Set<String>> map = new HashMap<>(); // key = 신고 당한 id, value = 신고자들 id
        Map<String, Integer> mailCnt = new HashMap<>(); // key = 메일 받을 사람 id, value = 받을 메일 개수
        
        for (String r : report) {
            String[] info = r.split(" ");
            map.putIfAbsent(info[1], new HashSet<>());
            map.get(info[1]).add(info[0]);
        }
        
        for (String key : map.keySet()) {
            if (map.get(key).size() >= k) {
                List<String> reporters = new ArrayList<>(map.get(key));
                for (int i = 0; i < reporters.size(); i++) {
                    mailCnt.putIfAbsent(reporters.get(i), 0);
                    mailCnt.replace(reporters.get(i), mailCnt.get(reporters.get(i))+1);
                }
            }
        }
        
        for (int i = 0; i < id_list.length; i++) {
            if (mailCnt.containsKey(id_list[i])) {
                answer[i] = mailCnt.get(id_list[i]);
            }
        }
        
        return answer;
    }
}