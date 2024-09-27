import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {

        // key: 준 사람, value = 받은 사람 및 받은 수
        Map<String, Map<String, Integer>> info = new HashMap<>();
        Map<String, Total> total = new HashMap<>(); // key : 이름 / value : (준 수, 받은 수)
        Map<String, Integer> result = new HashMap<>(); // key : 이름 / value : 받는 수 
        
        for (int i = 0; i < friends.length; i++) {
            for (int j = 0; j < friends.length; j++) {
                if (i == j) continue;
                
                info.putIfAbsent(friends[i], new HashMap<>());
                info.get(friends[i]).putIfAbsent(friends[j], 0);   
            }
            total.put(friends[i], new Total(0,0));
            result.put(friends[i], 0);
        }
        
        for (int i = 0; i < gifts.length; i++) {
            String[] arr = gifts[i].split(" ");
            String giver = arr[0];
            String recipient = arr[1];

            info.get(giver).put(recipient, info.get(giver).get(recipient) + 1);
        }
        
        // 이름별로, 준 수와 받은 수 저장
        for (String name : info.keySet()) {
            // name이 준 사람 정보들
             Map<String, Integer> giveInfo = info.get(name);
            
            for (String recipientName : giveInfo.keySet() ) {
                total.put(name, new Total(total.get(name).giveCnt + giveInfo.get(recipientName),
                                         total.get(name).reciveCnt));
                
                total.put(recipientName, new Total(total.get(recipientName).giveCnt, 
                                                  total.get(recipientName).reciveCnt + giveInfo.get(recipientName)));
            }
        }
        
        // 지수 구하기
        Map<String, Integer> diff = new HashMap<>();
        for (String name : total.keySet()) {
            diff.put(name, total.get(name).giveCnt - total.get(name).reciveCnt);
        }
        
        // 사람별로 최종 받을 수 계산  
        for (int i = 0; i < friends.length - 1; i++) {
            for (int j = i+1 ; j < friends.length; j++) {
                String f1 = friends[i];
                String f2 = friends[j];
                
                int cntToF2FromF1 = info.get(f1).get(f2);
                int cntToF1FromF2 = info.get(f2).get(f1);
                
                // 주고받은 수 동일 (전적 없는 경우도) 한 경우
                if (cntToF2FromF1 == cntToF1FromF2) {
                    // 지수 비교
                    int diffF1 = diff.getOrDefault(f1, 0);
                    int diffF2 = diff.getOrDefault(f2, 0);
                    
                    if (diffF1 == diffF2) {
                        continue;
                    }
                    if (diffF1 < diffF2) {
                        result.put(f2, result.get(f2) + 1);
                    }
                    if (diffF1 > diffF2) {
                        result.put(f1, result.get(f1) + 1);
                    }
                    
                }
                
                if (cntToF2FromF1 > cntToF1FromF2) {
                    result.put(f1, result.get(f1) + 1);
                }
                if (cntToF2FromF1 < cntToF1FromF2) {
                    result.put(f2, result.get(f2) + 1);
                }
            }
        }
        
        int maxCnt = Integer.MIN_VALUE;
        for (String name : result.keySet()) {
            maxCnt = maxCnt < result.get(name) ? result.get(name) : maxCnt;
        }
        
        return maxCnt;
    }
    
    class Total {
        int giveCnt;
        int reciveCnt;
        
         public Total(int giveCnt, int reciveCnt) {
            this.giveCnt = giveCnt;
            this.reciveCnt = reciveCnt;
        }
    }
}