class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        for(int i=0; i<babbling.length; i++) {
            babbling[i] = babbling[i].replaceAll("aya", "---");
            babbling[i] = babbling[i].replaceAll("ye", "--");
            babbling[i] = babbling[i].replaceAll("woo", "---");
            babbling[i] = babbling[i].replaceAll("ma", "--");
        }
        for(int i=0; i<babbling.length; i++) {
            System.out.println(babbling[i]);
            if(babbling[i].matches(".*[a-zA-Z].*")) answer++;
        }
        answer = babbling.length - answer;
        return answer;
    }
}