class Solution {
    public int solution(String before, String after) {
        StringBuffer sb = new StringBuffer(after);
        int answer = 1;
        String beforeAlpha="";
        for(int i=before.length()-1; i>=0; i--) {
            beforeAlpha =  String.valueOf(before.charAt(i));
            if(sb.indexOf(beforeAlpha) != -1) {
                int index = sb.indexOf(beforeAlpha);
                sb.deleteCharAt(index);
            }else {
                answer =0;
                break;
            }
        }
        return answer;
    }
}