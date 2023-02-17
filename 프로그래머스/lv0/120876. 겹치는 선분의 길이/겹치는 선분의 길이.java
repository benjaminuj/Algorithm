import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[][] lines) {
        int answer = 0;

        Map<String, Integer> map = new HashMap<String, Integer>();

        for (int[] line : lines) {
            int tempMin = Math.min(line[0], line[1]);
            int tempMax = Math.max(line[0], line[1]);
            for (int i = tempMin + 1; i < tempMax + 1; i++) {
                String str = (i - 1) + "/" + i;
                map.put(str, map.getOrDefault(str, 0) + 1);
            }
        }

        for (Integer value : map.values()) {
            if (value > 1) answer++;
        }

        return answer;
    }
}
