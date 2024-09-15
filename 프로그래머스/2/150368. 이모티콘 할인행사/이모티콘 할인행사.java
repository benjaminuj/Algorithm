import java.util.*;

class Solution {
    static int[] salePercent = new int[]{10, 20, 30, 40};
    List<Integer[]> percentCombi = new ArrayList<>();
    static int n;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        n = emoticons.length;
        
        // 이모티콘 할인율 모든 조합 추출
        getSalePercent(0, new Integer[n]);
        
        int joinCntMax = 0;
        int salesAmountMax = 0;
        
        // 이모티콘 할인율 모든 조합에 대해 검사
        for (int i = 0; i < percentCombi.size(); i++) {
            Integer[] sale = percentCombi.get(i);
            
            int joinCnt = 0; // 플러스 서비스 가입자 수
            int salesAmount = 0; // 이모티콘 판매액 
            
        //  for(int y = 0; y < n; y++) {
        //     System.out.print(sale[y] + "%, ");
        // }
        //     System.out.println("");
            
            
            // 모든 유저 순회
            for (int j = 0; j < users.length; j++) {
                int cost = 0; // 한 유저가 구매하는 총 비용
                // System.out.print(j+ "번째 유저 = ");
                // 한 유저당 모든 이모티콘의 구매 여부 검사
                for (int k = 0; k < n; k++) {
                    // 할인 기준 이상이면, 구매
                    if (users[j][0] <= sale[k]) {
                        cost += emoticons[k] * (100-sale[k]) / 100; // '/100'을 최대한 나중에 해야함***
                        // System.out.println(k + "번째 이모티콘 구매 : " + cost);
                    }
                }
                
                // 이모티콘 구매 vs 플러스 서비스 가입
                if (cost >= users[j][1]) {
                    joinCnt++; // 플러스 서비스 가입
                    // System.out.println("최종 서비스 가입 ! " + joinCnt);
                }
                if (cost < users[j][1]) {
                    salesAmount += cost; // 이모티콘 구매
                    // System.out.println("최종 이모티콘 구매 ! " + salesAmount);
                }
            }
            
            if (joinCntMax < joinCnt) {
                joinCntMax = joinCnt;
                salesAmountMax = salesAmount;
            } else if (joinCntMax == joinCnt && salesAmountMax < salesAmount) {
                salesAmountMax = salesAmount;
            } 
        }
        
        answer[0] = joinCntMax;
        answer[1] = salesAmountMax;
        
        return answer;
    }
    
    public void getSalePercent(int cnt, Integer[] arr) {
        if (cnt == n) {
            percentCombi.add(arr.clone()); // 깊은 복사 해야함! 주의!
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            arr[cnt] = salePercent[i];
            getSalePercent(cnt+1, arr);
            arr[cnt] = 0;
        }
    }
}