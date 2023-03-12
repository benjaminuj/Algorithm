class Solution {
    public int[] solution(int[] arr) {
        int[] answer;
        int min = Integer.MAX_VALUE;
        int index = 0;
        int answerIndex=0;
        if(arr.length ==1)  {
            answer = new int[] {-1};
        }
        else {
            for(int i=0; i<arr.length; i++) {
                if(min > arr[i]) {
                    min = arr[i]; 
                    index =i;
                }
            }
            answer= new int[arr.length-1];
            for(int i=0; i<arr.length; i++) {
                if(i != index) {
                    answer[answerIndex] = arr[i];
                    answerIndex++;
                }
            }
        }
        return answer;
    }
}