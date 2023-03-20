import java.util.Arrays;
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        Arrays.fill(answer, "");
        String[] temp1 = new String[n];
        String[] temp2 = new String[n];
        for(int i=0; i<n; i++) {
            temp1[i] = Integer.toString(arr1[i],2);
            temp2[i] = Integer.toString(arr2[i],2);
        }
        for(int i=0; i<n; i++) {
            while(temp1[i].length() < n){
                temp1[i] = '0' + temp1[i]; 
            }
            while(temp2[i].length() < n){
                temp2[i] = '0' + temp2[i]; 
            }
        }
        for(int i=0; i<n; i++) {
            for(int k=0; k<n; k++) {
                if(temp1[i].charAt(k) == '1' || temp2[i].charAt(k) == '1') answer[i] += '#';
                else answer[i] += ' ';
            }
        }
        return answer;
    }
}