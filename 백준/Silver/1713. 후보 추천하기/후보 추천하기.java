import java.util.*;
import java.io.*;

public class Main {
	public static class Info {
		int index;
		int number;
		
		public Info(int index, int number) {
			this.index = index;
			this.number = number;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int totalRecommand = Integer.parseInt(br.readLine());
		String[] recommand = br.readLine().split(" ");
		List<Integer> generatedAt = new ArrayList<>();
		for (int i = 0; i < 101; i++) {
			generatedAt.add(0);
		}
		
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < totalRecommand; i++) {
			int number = Integer.parseInt(recommand[i]);
			
			if(map.containsKey(number)) {
				int count = map.get(number);
				map.put(number, ++count);
			} else {
				int lastIndex = Collections.max(generatedAt);
				if(map.size() == N) {
					List<Integer> numbers = new ArrayList<>();
					int minReco = Collections.min(map.values());
					for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
						if(entry.getValue() == minReco) {
							numbers.add(entry.getKey());
						}
					}
					int numberToDelete = numbers.get(0);
					if(numbers.size() != 1) {
						for(Integer num : numbers) {
							if(generatedAt.get(numberToDelete) > generatedAt.get(num)) {
								numberToDelete = num;
							}
						}
					} 
					map.remove(numberToDelete);
					generatedAt.set(numberToDelete, 0);
				}
				map.put(number, 1);
				generatedAt.set(number, ++lastIndex);
			}
		}
		Object[] result = map.keySet().toArray();
		Arrays.sort(result);
		
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
		
	}
}
