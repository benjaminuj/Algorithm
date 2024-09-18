class Solution {
    
    public int convertToSec(String time) {
        String[] arr = time.split(":");
        
        return Integer.parseInt(arr[0])*3600 + Integer.parseInt(arr[1])*60 + Integer.parseInt(arr[2]);
    }
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int playSec = convertToSec(play_time);
        int advSec = convertToSec(adv_time);
        
        int[] arr = new int[100*60*60];
        
        for (int i =0 ; i < logs.length; i++) {
            int startSec = convertToSec(logs[i].substring(0,8));
            int endSec = convertToSec(logs[i].substring(9,17));
            
            for (int j = startSec; j < endSec; j++) {
                arr[j]++;
            }
        }
        
        long sum = 0;
        int time = 0;
        for (int i = 0 ; i < advSec; i++) {
            sum += arr[i];
        }
        
        long temp = sum;
        for (int i = advSec; i < playSec; i++) {
            temp += arr[i];
            temp -= arr[i - advSec];
            
            if(temp > sum) {
                sum = temp;
                time = i - advSec + 1;
            }
        }
        return String.format("%02d:%02d:%02d", time/3600, time%3600/60, time%3600%60);
    }
}