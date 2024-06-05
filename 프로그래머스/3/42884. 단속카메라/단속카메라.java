import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < routes.length; i++) {
            //공통 단속구간 설정
            int start = routes[i][0];
            int end = routes[i][1];

            answer++;
            
            if (i+1 == routes.length) break;
            
            while (routes[i+1][0] <= end) {
                // 공통 단속구간 업데이트
                start = routes[i+1][0];
                if (routes[i+1][1] < end) end = routes[i+1][1];
                
                i++;
                if (i+1 == routes.length) break;
            }
        }
        
        return answer;
    }
}