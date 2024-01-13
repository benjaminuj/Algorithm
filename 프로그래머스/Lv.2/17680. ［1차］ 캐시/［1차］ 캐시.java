import java.util.*;

class Solution {
    PriorityQueue<Info> cache;
    PriorityQueue<Info> cacheTemp;
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0, i = 0;
        cache = new PriorityQueue<>();
        cacheTemp = new PriorityQueue<>();
        Set<String> cityInCache = new HashSet<>();
        
        for (String city : cities) {
            city = city.toUpperCase();
            i++;
            
            if (cityInCache.size() == cacheSize) {
                if (cityInCache.add(city)) {
                    answer += 5;
                    cache.offer(new Info(city, i));
                    cityInCache.remove(cache.poll().city);
                } else {
                    answer++;
                    exchangeCacheIdx(city, i);
                    
                }
            } else {
                if (cityInCache.add(city)) {
                    answer += 5;
                    cache.offer(new Info(city, i));
                } else {
                    answer++;
                    exchangeCacheIdx(city, i);
                }
            }
            Iterator<Info> iter = cache.iterator();
            while (iter.hasNext()) {
            Info info = iter.next();
            System.out.print(info.city + " " + info.idx + ", ");
        }
        System.out.println();
        }
        
        
        
        return answer;
    }
    
    public void exchangeCacheIdx(String targetCityName, int idx) {
        int len = cache.size();
        
        for (int i = 0; i < len; i++) {
            if (cache.peek().city.equals(targetCityName)) {
                cache.remove();
                cache.offer(new Info(targetCityName, idx));
                
                for (int j = 0; j < cacheTemp.size(); j++) {
                    cache.offer(cacheTemp.poll());
                }
                return;
            }
            cacheTemp.offer(cache.poll());
        }
    }
}

class Info implements Comparable<Info>{
    String city;
    int idx;
    
    public Info(String city, int idx) {
        this.city = city;
        this.idx = idx;
    }
    
    @Override
    public int compareTo(Info o) {
        return this.idx - o.idx;
    }
}