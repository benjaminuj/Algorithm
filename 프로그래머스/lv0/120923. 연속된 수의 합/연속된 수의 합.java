class Solution {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        int sum=0;
        int index =1;
        for(int i=1; i<=num; i++) {
            sum += i;
        }
        while(sum != total) {
            if(sum < total) {
                index++;
                sum += num;
            } else if(sum > total) {
                index--;
                sum -= num;
            }
        }
        for(int i=0; i<num;i++) {
            answer[i] = index++;
        }
        return answer;
    }
}