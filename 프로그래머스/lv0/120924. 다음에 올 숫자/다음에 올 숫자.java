class Solution {
    public int solution(int[] common) {
        int answer = 0;
        boolean sameDiff = false;
        if(common[1]-common[0] == common[2]-common[1]) sameDiff = true;
        if(sameDiff) answer= common[common.length-1] + (common[1]-common[0]);
        else answer= common[common.length-1] * (common[1]/common[0]);
        return answer;
    }
}