import java.util.Scanner;
import java.util.HashMap;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        
        HashMap<String, Integer> wordIndex = new HashMap<>();
        String[] words = new String[N];
        
        for (int i = 0; i < N; i++) {
            String word = sc.next();
            wordIndex.put(word, i);
            words[i] = word;
        }
        
        String S = "";
        String T = "";
        int maxPrefixLength = 0;
        
        for (int i = 0; i < N; i++) {
            String currentWord = words[i];
            for (int j = i + 1; j < N; j++) {
                String nextWord = words[j];
                int prefixLength = getPrefixLength(currentWord, nextWord);
                // 최대 접두사 길이 갱신 시 S와 T 업데이트
                if (prefixLength > maxPrefixLength) {
                    maxPrefixLength = prefixLength;
                    S = currentWord;
                    T = nextWord;
                }
            }
        }

        System.out.println(S);
        System.out.println(T);
    }
    
    private static int getPrefixLength(String word1, String word2) {
        int length = Math.min(word1.length(), word2.length());
        int prefixLength = 0;

        for (int i = 0; i < length; i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                prefixLength++;
            } else {
                break;
            }
        }
        return prefixLength;
    }
}