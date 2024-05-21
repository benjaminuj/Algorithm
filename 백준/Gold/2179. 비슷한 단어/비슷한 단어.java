import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        List<String> stringList = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            String inputString = bufferedReader.readLine();
            if (!stringList.contains(inputString)) {
                stringList.add(inputString);
            }
        }

        int resultIndex1 = 0;
        int resultIndex2 = 0;
        int maxCount = 0;
        
        for (int i = 0; i < n; i++) {
            String str1 = stringList.get(i);
            
            for (int j = i + 1; j < n; j++) {
                int count = 0;
                String str2 = stringList.get(j);
                int size = Math.min(str1.length(), str2.length());
                
                for (int z = 0; z < size; z++) {
                    if (str1.charAt(z) == str2.charAt(z)) {
                        count++;
                    } else {
                        break;
                    }
                }
                
                if (count > maxCount) {
                    resultIndex1 = i;
                    resultIndex2 = j;
                    maxCount = count;
                }
            }
        }

        System.out.println(stringList.get(resultIndex1));
        System.out.println(stringList.get(resultIndex2));
    }
}