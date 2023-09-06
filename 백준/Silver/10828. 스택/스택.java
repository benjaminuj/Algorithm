import java.util.*;
import java.io.*;

public class Main {
	public static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			String cmd = br.readLine();
			
			switch(cmd) {
				case "pop" :
					System.out.println(pop());
					break;
				case "empty" :
					System.out.println(empty());
					break;
				case "top" :
					System.out.println(top());
					break;
				case "size" :
					System.out.println(list.size());
					break;
				default:
					String[] tempOfPush = cmd.split(" ");
					list.add(Integer.parseInt(tempOfPush[1]));
			}
		}
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


