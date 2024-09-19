import java.util.*;

class Solution {
    public int convertToMinute(String time) {
        String[] arr = time.split(":");
        
        return Integer.parseInt(arr[0]) * 60 +
            Integer.parseInt(arr[1]);
    }
    
    public int[] solution(int[] fees, String[] records) {
        List<Integer> answer = new ArrayList<>();
        Map<String, List<String>> in = new HashMap<>();
        Map<String, List<String>> out = new HashMap<>();
        
        Queue<Info> pq = new PriorityQueue<>();
        
        for (int i = 0; i < records.length; i++) {
            String[] arr = records[i].split(" ");
            if (arr[2].equals("IN")) {
                in.putIfAbsent(arr[1], new ArrayList<>());
                
                in.get(arr[1]).add(arr[0]);
            }
            
            if (arr[2].equals("OUT")) {
                out.putIfAbsent(arr[1], new ArrayList<>());
                
                out.get(arr[1]).add(arr[0]);
            }    
        }
        
        for (String carNum : in.keySet()) {
            List<String> timesOfIn = in.get(carNum);
            List<String> timesOfOut = out.getOrDefault(carNum, new ArrayList<>());
            boolean allOut = timesOfIn.size() == timesOfOut.size() ? true : false;
            
            // 차량의 총 이용 시간 구하기
            int totalTime = 0;
            for (int i = 0; i < timesOfIn.size()-1; i++) {
                int inMinute = convertToMinute(timesOfIn.get(i));
                int outMinute = convertToMinute(timesOfOut.get(i));

                totalTime += outMinute - inMinute;    
            }
            
            // 마지막 입차에 대한 출차 정보 없음
            if (!allOut) {
                // 마지막 입차는 23:59 출차 처리
                totalTime += 
                    convertToMinute("23:59") - convertToMinute(timesOfIn.get(timesOfIn.size()-1));
            }
            
            // 입차 출차 전부 매치됨
            if (allOut) {
                int len = timesOfIn.size();
                totalTime += 
                    convertToMinute(timesOfOut.get(len-1)) - convertToMinute(timesOfIn.get(len-1));
            }
            
            int fee = getFee(totalTime, fees[0], fees[1], fees[2], fees[3]);
            int carNumber = Integer.parseInt(carNum);
            pq.offer(new Info(carNumber, fee));
        }
        
        while (!pq.isEmpty()) {
            answer.add(pq.poll().fee);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private int getFee(int totalTime, int basicTime, int basicFee, int additionalTime, int additionalFee) {
        if (totalTime <= basicTime) {
            return basicFee;
        }
        
        int addedTime = (int)Math.ceil((totalTime - basicTime) / (additionalTime*1.0));
        return basicFee + (addedTime * additionalFee);
    }
    
    class Info implements Comparable<Info> {
        int carNumber;
        int fee;
        
        public Info(int carNumber, int fee) {
            this.carNumber = carNumber;
            this.fee = fee;
        }
        
        @Override
        public int compareTo(Info o) {
            return this.carNumber - o.carNumber;
        }
    }
}