class Solution {
    public long solution(String numbers) {
        long answer = 0;
        String num = "";
        int i =0;
        while(i<numbers.length()) {
            char alpha = numbers.charAt(i);
            switch(alpha) {
                case 'o' : num += 1;
                    i += 3;
                    break;
                case 'z' : num += 0;
                    i += 4;
                    break;
                case 't' : if(numbers.charAt(i+1) == 'w') {
                        num += 2;
                        i += 3;
                    } else {
                        num += 3;
                        i+= 5;
                    }
                    break;
                case 'f' : if(numbers.charAt(i+1) == 'o') {
                        num += 4;
                    } else {
                        num += 5;
                    }
                    i += 4;
                    break;
                case 's' : if(numbers.charAt(i+1) == 'i') {
                        num += 6;
                        i += 3;
                    } else {
                        num += 7;
                        i += 5;
                    }
                    break;
                case 'e' : num += 8;
                    i += 5;
                    break;
                case 'n' : num += 9;
                    i+= 4;
                    break;
            }
        }
        answer = Long.parseLong(num);
        return answer;
    }
}