import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);
        int answer = 0;
        int idx = 0;
        Queue<Job> jobsQ = new PriorityQueue<>();
        
        int now = jobs[idx][0];
        
        // 시작시간이 같은게 여러개일 경우
        while (idx < jobs.length && jobs[idx][0] == now) {
            jobsQ.add(new Job(jobs[idx][0], jobs[idx][1]));
            idx++;
        }
        idx--;
        
        int sum = 0;
        int endTime = 0;
        
        while (!jobsQ.isEmpty()) {
            Job cur = jobsQ.poll();
            System.out.println(cur.req + "," + idx);
            // 대기 o
            if (cur.req < endTime) {
                endTime += cur.runTime;
            } else { // 대기 x 
                endTime = cur.req + cur.runTime;
            }
            
            sum += endTime - cur.req;
            
            // System.out.println(endTime);
            
            // 추가할 프로세스 있는 경우
            while (idx + 1 < jobs.length && jobs[idx+1][0] <= endTime) {
                idx++;
                jobsQ.add(new Job(jobs[idx][0], jobs[idx][1]));
            }
            
            if (idx +1 < jobs.length && jobsQ.isEmpty()) {
                idx++;
                jobsQ.add(new Job(jobs[idx][0], jobs[idx][1]));
            }
        }
        answer = sum / jobs.length;
        
        return answer;
    }
    
    class Job implements Comparable<Job> {
        int req;
        int runTime;
        
        public Job(int req, int runTime) {
            this.req = req;
            this.runTime = runTime;
        }
        
        @Override
        public int compareTo(Job o) {
            return this.runTime - o.runTime;
        }
    }
}