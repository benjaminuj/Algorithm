import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sumQ1 = 0L;
        long sumQ2 = 0L;
        
        List<Integer> all = new ArrayList<>();
        all.addAll(Arrays.asList(
            Arrays.stream(queue1).boxed().toArray(Integer[]::new)));
        all.addAll(Arrays.asList(Arrays.stream(queue2).boxed().toArray(Integer[]::new)));
        Integer[] allArr = all.toArray(new Integer[all.size()]);
        
        for (int i : queue1) {
            sumQ1 += i;
        }
        for (int i : queue2) {
            sumQ2 += i;
        }
        
        long half = (sumQ1 + sumQ2)/2L;
        
        if ((sumQ1 + sumQ2) % 2 != 0) return -1; // !!홀수일 경우 예외처리!!
        
        int s = 0, e = queue1.length-1;
        int originS = s, originE = e;
        while (true) {
            if (sumQ1 < half) {
                e = (e+1) % allArr.length;
                
                sumQ1 += allArr[e];
                
                answer++;
            } else if (sumQ1 > half) {
                sumQ1 -= allArr[s];
                
                s = (s+1) % allArr.length; // !!빼는건 각 합을 계산한 후, 포인터 이동! 순서 주의!!!
                
                answer++;
            }
            
            // 이전에 했던 상황 중복. 더 이상 탐색 의미없음
            if (answer > allArr.length*2) break;
            
            if (sumQ1 == half) return answer;
        }
        
        return -1;
    }
}