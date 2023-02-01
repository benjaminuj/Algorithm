class Solution {
    public int solution(int[] numbers) {
        int max = 0;
        boolean advent = false;
        for(int i=0; i<numbers.length; i++) {
            if(numbers[i]> max) max = numbers[i];
        }
        int secondMax = 0;
        for(int i=0; i<numbers.length; i++) {
            if(numbers[i] == max && advent) secondMax = numbers[i];
            if(numbers[i] == max) advent = true;
            if(numbers[i]>secondMax && numbers[i] != max) secondMax = numbers[i];
        }
        return (max*secondMax);
    }
}