import java.util.Arrays;

class Solution {
    public int solution(int[] numbers) {
        Arrays.sort(numbers);
        int num1 = numbers[0]*numbers[1];
        int num2 = numbers[numbers.length-1]*numbers[numbers.length-2];
        int answer = num1>num2 ? num1 : num2;
        return answer;
    }
}