import java.util.*;
import java.io.*;

public class Main {
	public static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
	
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			switch(cmd) {
				case "pop" :
					sb.append(pop()).append("\n");
					break;
				case "empty" :
					sb.append(empty()).append("\n");
					break;
				case "top" :
					sb.append(top()).append("\n");
					break;
				case "size" :
					sb.append(list.size()).append("\n");
					break;
				case "push" :
					list.add(Integer.parseInt(st.nextToken()));
					break;
			}
		}
		
		System.out.print(sb);
	}
	
	public static void push(int i) {
		list.add(i);
	}
	
	public static int pop() {
		if(list.size() == 0) return -1;
		return list.remove(list.size()-1);
	}
	
	public static int empty() {
		if(list.isEmpty()) {
			return 1;
		} 
		return 0;
	}
	
	public static int top() {
		if(list.size()>0) return list.get(list.size()-1);
		return -1;
	}	
}