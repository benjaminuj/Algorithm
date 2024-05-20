import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> result = new ArrayList<>();
        
        HashMap<String, Integer> cntOfGenre = new HashMap<>();
        HashMap<String, List<Info>> music = new HashMap<>(); // 장르명, List<번호, 횟수>
        for (int i = 0; i < genres.length; i++) {
            cntOfGenre.putIfAbsent(genres[i], 0);
            cntOfGenre.replace(genres[i], cntOfGenre.get(genres[i]) + plays[i]);
        }
        
        List<String> keys = new ArrayList(cntOfGenre.keySet());
        Collections.sort(keys, (o1, o2) -> cntOfGenre.get(o2) - cntOfGenre.get(o1)); // 내림차순
        
        for (int i =0; i < genres.length; i++) {
            music.putIfAbsent(genres[i], new ArrayList<Info>());    
            music.get(genres[i]).add(new Info(i, plays[i]));
        }
        
        for(String key : keys) {
            Collections.sort(music.get(key));
        }
        
        for (String key : keys) {
            result.add(music.get(key).get(0).idx);
            
            if (music.get(key).size() > 1) {
                result.add(music.get(key).get(1).idx);
            }
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    class Info implements Comparable<Info> {
        int idx;
        int cnt;
        
        public Info(int idx, int cnt) {  
            this.idx = idx;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Info o) {
            if (this.cnt == o.cnt) {
                return this.idx - o.idx;
            }
            
            return o.cnt - this.cnt;
        }
    }
}