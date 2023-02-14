import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(String[] spell, String[] dic) {
        List<String> list = new ArrayList<>();
        for(int i=0; i<dic.length; i++) {
            list.add(dic[i]);
        }
        List<String> temp = new ArrayList<>();
        for(int i=0; i<spell.length; i++) {
            for(int j=0; j<list.size(); j++) {
                if(list.get(j).contains(spell[i])) {
                    temp.add(list.get(j));
                }
            }
            list.clear();
            for(int k=0; k<temp.size(); k++) {
                list.add(temp.get(k));
            }
            temp.clear();
        }
        int answer = 2;
        if(list.size() >=1) answer =1;
        return answer;
    }
}