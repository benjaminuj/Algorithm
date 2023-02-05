class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] arr = s.split(" ");
        for(int i=0; i<arr.length-1; i++) {
            if(arr[i+1].equals("Z")) {
                i++;
                continue;
            }
            else answer += Integer.parseInt(arr[i]);
        }
        if(!arr[arr.length-1].equals("Z")) answer += Integer.parseInt(arr[arr.length-1]);
        return answer;
    }
}