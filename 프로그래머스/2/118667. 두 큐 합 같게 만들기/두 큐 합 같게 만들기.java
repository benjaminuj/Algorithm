class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int n = queue1.length + queue2.length;
        int[] a = new int[n];
        long total = 0;
        long sum = 0;
        for (int i =0 ; i < queue1.length; i++) {
            a[i] = queue1[i];
            total += queue1[i];
        }
        sum = total;
        
        for (int j =0 ; j < queue2.length; j++) {
            a[queue1.length + j] = queue2[j];
            total += queue2[j];
        }
        
        if (total % 2 != 0) return -1;
        
        int s = 0, e = queue1.length;
        while (answer < 2*n) {
            if (sum < total/2) {
                sum += a[e];
                e = (e+1) % n;
                answer++;
            } else if (sum > total/2){
                sum -= a[s];
                s = (s+1) % n;
                answer++;
            } else {
                return answer;
            }
        }
        return -1;
    }
}