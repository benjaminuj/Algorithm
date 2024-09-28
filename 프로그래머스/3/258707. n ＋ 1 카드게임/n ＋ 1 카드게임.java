import java.util.*;

class Solution {
    static int n;
    static int target;
    
    public int solution(int coin, int[] cards) {
        int answer = 0;
        n = cards.length;
        target = n+1;
        
        List<Integer> nums = new ArrayList<>(); // 새로 뽑아야하는 카드들
        List<Integer> origins = new ArrayList<>(); // 처음에 뽑은 카드들. n/3 장만
        for (int i = 0; i < n/3; i++) {
            origins.add(cards[i]);
        }
        Collections.sort(origins);
        
        int idx = n/3 -2;
        int l = 0;
        int r = 0;
        while (true) {
            answer++; // 라운드 시작
            boolean success = false;
            
            // 이번 라운드에 새로 뽑을 수 있는 카드들 추가
            idx += 2;
            // 예외처리. 더 이상 진행못함
            if (idx >= n-1) {
                break;
            }
            nums.add(cards[idx]);
            nums.add(cards[idx+1]);

            Collections.sort(nums);
            
            // 1. 카드 안뽑고 되는지 검사
            // 투포인터로 검색 시작 
            l = 0;
            r = origins.size() -1;
            while (l < r) {
                int sum = origins.get(l) + origins.get(r);

                if (sum == target) {       
                    origins.remove(r);
                    origins.remove(l);

                    success = true;               

                    break;
                } 
                if (sum < target) {
                    l++;
                }
                if (sum > target) {
                    r--;
                }
            }

            // 이번 라운드에서 카드 안뽑고 가능
            if (success) {
                continue;
            }
            
            
            // 2. 카드 한 장만 뽑고 가능
            if (coin <= 0) break; // 예외처리. 카드 못 뽑음
            
            for (int i = 0 ; i < origins.size(); i++) {
                for (int j = 0; j < nums.size(); j++) {
                    int sum = origins.get(i) + nums.get(j);
                    
                    if (sum == target) {
                        coin -= 1;
                        
                        origins.remove(i);
                        nums.remove(j);

                        success = true;
                        break;
                    }
                }
                if (success) break;
            }
            
            if (success) continue;
            
            // 3. 두 장 뽑아
            if (coin <= 1) break; // 예외처리. 카드 못 뽑음
            
            // 투포인터로 검색 시작 
            l = 0;
            r = nums.size()-1;
            while (l < r) {
                int sum = nums.get(l) + nums.get(r);

                if (sum == target) {
                    coin -= 2;
                    
                    nums.remove(r);
                    nums.remove(l); 

                    success = true;

                    break;
                } 
                if (sum < target) {
                    l++;
                }
                if (sum > target) {
                    r--;
                }
            }
            
            if (!success) break; // 이번 라운드에서 어떤 경우로도 가능하지 않을 때.
        }

        return answer;
    }
}