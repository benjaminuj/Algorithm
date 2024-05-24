import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);
        Queue<int[]> pq = new PriorityQueue<>((j1, j2) -> j1[1] - j2[1]); // 큐 타입을 배열로 선언해서
        
        int idx = 0;
        int sum = 0;
        int endTime = 0;
        int completedJobs = 0;
        
        while (completedJobs < jobs.length) {
            while(idx < jobs.length && jobs[idx][0] <= endTime) {
                pq.add(jobs[idx]); // 배열 통채로 추가하는 방법.
                idx++;
            }
            
            if (!pq.isEmpty()) {
                int[] job = pq.remove();
                endTime += job[1]; // 연속적으로 처리하는 거라 이렇게해도 시간 업데이트 됨
                sum += endTime - job[0];
                completedJobs++;
            } else {
                endTime = jobs[idx][0];
            }

        }
        return sum / jobs.length;
    }
}