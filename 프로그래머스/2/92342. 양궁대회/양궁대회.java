import java.util.*;

class Solution {
    List<Integer[]> result = new ArrayList<>();
    static int n;
    
    public int[] solution(int n, int[] info) {
        this.n = n;// 이렇게 같은변수로 할거면 this 안써주면 값 안들어감. 그래서 이후 다 이상하게 처리됨
        
        Integer[] cnt = new Integer[11];
        Arrays.fill(cnt, 0); // 안하면 NPE 발생!!!!***
        
        // 라이언이 가능한 모든 조합 구하기 (어피치 점수 상관없이)
        getCombination(0, cnt, 0);
        
        int maxDiff = Integer.MIN_VALUE;
        Integer[] answer = new Integer[11];
        Arrays.fill(answer, 0);
        
        
        // 모든 경우의 수에 대해 검사
        for(Integer[] arr : result) {
            // if (n == 5) System.out.println(arr[0] + "," + arr[1] + "," + arr[2] + "," + arr[3] + "," + arr[4] + "," + arr[5] + "," + arr[6]);
            
            int lionScore = 0;
            int apeachScore = 0;
            
            // 어피치와 라이언 점수 계산
            for (int i = 0; i < 11; i++) {
                if (info[i] == 0 && arr[i] == 0) continue;
                if (info[i] < arr[i]) {
                    lionScore+=(10-i);
                } else {
                    apeachScore+=(10-i);
                }
            }
            // if (n == 5) System.out.println(lionScore + " vs " + apeachScore);
            // 라이언 패배
            if (lionScore <= apeachScore) {
                continue;
            }
            

            // 라이언 우승
            int diff = lionScore - apeachScore;
            // 최대 차이 갱신
            if (maxDiff < diff) {
                maxDiff = diff;
                answer = Arrays.copyOfRange(arr, 0, 11);
            } else if (maxDiff == diff) { // 최대차이와 현재 차이가 동일할 경우
                // 가장 낮은 점수를 더 많이 맞힌것 구하기
                for (int j = 10; j >= 0; j--) {
                    if (arr[j] < answer[j]) {
                        break;
                    }
                    if (arr[j] > answer[j]) { 
                        maxDiff = diff;
                        answer = Arrays.copyOfRange(arr, 0, 11);
                        break;
                    }
                }
            }
            
            
        }
        
        if (maxDiff == Integer.MIN_VALUE) return new int[]{-1};
        
        int[] temp = new int[11];
        for (int i =0 ; i < 11; i++) {
            temp[i] = answer[i];
        }
        return temp;
    }
    
    public void getCombination(int start, Integer[] cnt, int total) {
        if (total == n) {
            result.add(cnt.clone());

            return;
        }
        
        for (int i = start; i < 11; i ++) {
            cnt[i]++;
            getCombination(i, cnt, total+1);
            cnt[i]--;
        }
    }
}