class Solution {
    static boolean[] peak;
    public int solution(int[] nums) {
        int answer = 0;
        int N = nums.length;
        Map<Long, Long> pocketmon = new HashMap<>();
        peak = new boolean[pocketmon.size()+1];
        for(int i=0; i<N; i++) {
            pocketmon.put(nums[i], i);
        }
        
        combination(pocketmon, N, 0, N/2);
        return answer;
    }
    
    public void combination(Map<Long, Long> pocketmon, int N, int index, int r) {
        if(r == 0) {
            for(int i=1; i<=N; i++) {
                if(peak[i]) count++;
            }
        }
        
        if(index == N) return;
        
        peak[index] = true;
        combination(pocketmon, N, index+1, r-1);
        
        peak[index] = false;
        combination(pocketmon, N, index+1, r);
    }
}