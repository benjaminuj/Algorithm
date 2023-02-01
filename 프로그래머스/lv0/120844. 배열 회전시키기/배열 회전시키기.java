class Solution {
    public int[] solution(int[] numbers, String direction) {
        int[] answer = new int[numbers.length];
        if(direction.equals("right")){
            for(int i=0; i <numbers.length; i++) {
                int index = i-1;
                if(index<0) index = numbers.length-1;
                answer[i] = numbers[index];
            }
        }
        else {
            for(int i=0; i <numbers.length; i++) {
                int index = i+1;
                if(index>numbers.length-1) index = 0;
                answer[i] = numbers[index];
            }
        }
        return answer;
    }
}