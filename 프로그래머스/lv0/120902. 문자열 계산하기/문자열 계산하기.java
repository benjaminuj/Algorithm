class Solution {
    public int solution(String my_string) {
        String[] math = my_string.split(" ");
        int answer = Integer.parseInt(math[0]);
        for(int i=1; i<math.length-1; i += 2) {
            String operator = math[i];
            switch(operator) {
                case "+" : answer += Integer.parseInt(math[i+1]); break;
                case "-" : answer -= Integer.parseInt(math[i+1]); break;
            }
        }
        return answer;
    }
}