class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        StringBuffer buffer = new StringBuffer(s);
        String revOfS = buffer.reverse().toString();
        
        for(int i=0; i<s.length(); i++) {
            String now = String.valueOf(revOfS.charAt(i));
            String partOfS = revOfS.substring(i+1, revOfS.length());
            
            
            answer[revOfS.length()-i-1] = partOfS.indexOf(now) == -1 ? -1 : partOfS.indexOf(now)+1;
        }
        return answer;
    }
}