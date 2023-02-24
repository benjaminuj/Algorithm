class Solution {
    public int solution(String A, String B) {
        int answer = -1;
        char[] temp = new char[A.length()];
        if(A.equals(B)) answer = 0;
        else {
            for(int i=1; i<A.length(); i++) {
                for(int j=0; j<A.length(); j++) {
                    if(j+i < A.length()) temp[j+i] = A.charAt(j);
                    else temp[j+i - A.length()] = A.charAt(j);
                }
                if(String.valueOf(temp).equals(B)) {
                    answer = i;
                    break;
                }
            }
        }
        return answer;
    }
}