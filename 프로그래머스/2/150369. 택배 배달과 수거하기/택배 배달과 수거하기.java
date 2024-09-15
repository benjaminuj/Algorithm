class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        boolean allZero = true;
        for (int i =0; i < n; i++) {
            if (deliveries[i] != 0 || pickups[i] != 0) {
                allZero = false;
                break;
            }
        }
        if (allZero) return 0;
        
        int sum = 0;
        int deliveryMaxIdx = 0;
        int pickupMaxIdx = 0;
        
        // 가장 멀리있는 집부터 배달. 배열 가장 뒤부터 체크
        // cap만큼 배달 상자 채운 후, 얼마나 물류센터에 가까운 집까지 한 번에 배달 가능한지 체크
        int dIdx = n-1;
        int pIdx = n-1;
        
        // --- 배달 ---
        loop1:
        while (dIdx >= 0) {
            deliveryMaxIdx = Math.max(deliveryMaxIdx, dIdx+1);
            
            // 배달할 것들 cap만큼 실어야 함
            if (sum + deliveries[dIdx] <= cap) {
                // i번째 집에 필요한 개수만큼 전부 실음
                sum += deliveries[dIdx]; 
                deliveries[dIdx] = 0;
                dIdx--;
                continue loop1;
            }

            // 이번 i번째 집에 필요한 개수를 다 채우면 cap을 초과함: 가능한만큼 일부라도 배달
            deliveries[dIdx] -= (cap - sum);
            
            sum = 0;
            // --- 수거 --- 
            loop2:
            while (pIdx >= 0) {
                pickupMaxIdx = Math.max(pickupMaxIdx, pIdx+1);
                
                // 수거할 것들 cap만큼 실어야 함
                if (sum + pickups[pIdx] <= cap) {
                    // i번째 집의 수거할 개수만큼 전부 실음
                    sum += pickups[pIdx]; 
                    pickups[pIdx] = 0;
                    pIdx--;
                    continue loop2;
                }

                // 이번 i번째 집의 수거해야 할걸 다 수거하면 cap을 초과함: 가능한만큼 일부라도 수거
                pickups[pIdx] -= (cap - sum);
                break;
            }
            
            // 이번에 최대로 먼 집까지 움직인 거리
            answer += (2 * Math.max(deliveryMaxIdx, pickupMaxIdx));
            
            sum = 0;
            deliveryMaxIdx = 0;
            pickupMaxIdx = 0;
        }
        
        // 배달의 최대 지점은 다 체크됐는데, cap이 여유있어서 loop1이 빠져나와져서 수거의 최대지점은 체크하지 못한 경우
        sum = 0;
        pickupMaxIdx = 0;
        boolean calculationComplete = false;
        if ((pIdx >= 0) || (dIdx < 0))  {
            while (pIdx >= 0) {
                calculationComplete = false;
                pickupMaxIdx = Math.max(pickupMaxIdx, pIdx+1);

                // 수거할 것들 cap만큼 실어야 함
                if (sum + pickups[pIdx] <= cap) {
                    // i번째 집의 수거할 개수만큼 전부 실음
                    sum += pickups[pIdx]; 
                    pickups[pIdx] = 0;
                    pIdx--;
                    continue;
                }

                // 이번 i번째 집의 수거해야 할걸 다 수거하면 cap을 초과함: 가능한만큼 일부라도 수거
                pickups[pIdx] -= (cap - sum);

                // 이번에 최대로 먼 집까지 움직인 거리
                answer += (2 * Math.max(deliveryMaxIdx, pickupMaxIdx));
                calculationComplete = true; 
                sum = 0;
                deliveryMaxIdx = 0;
                pickupMaxIdx = 0;
                
            }

            if (!calculationComplete) {
                 answer += (2 * Math.max(deliveryMaxIdx, pickupMaxIdx));
            }
        }

        return answer;
    }
}