import java.util.*;
import java.io.*;

public class Main {
	public static List<Integer > deque = new ArrayList<>();
	public static List<Integer> temp = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
	
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			switch(cmd) {
				case "push_front" :
					push_front(Integer.parseInt(st.nextToken()));
					break;
				case "push_back" :
					push_back(Integer.parseInt(st.nextToken()));
					break;
				case "pop_front" :
					sb.append(pop_front()).append("\n");
					break;
				case "pop_back" :
					sb.append(pop_back()).append("\n");
					break;
				case "size" :
					sb.append(deque.size()).append("\n");
					break;
				case "empty" :
					sb.append(deque.isEmpty()?1:0).append("\n");
					break;
				case "front" :
					sb.append(front()).append("\n");
					break;
				case "back" :
					sb.append(back()).append("\n");
					break; 
			}
		}
		
		System.out.print(sb);
	}
	
	public static void push_front(int X) {
		temp.add(X);
		while(!deque.isEmpty()) {
			temp.add(deque.remove(0));
		}
		while(!temp.isEmpty()) {
			deque.add(temp.remove(0));
		}
	}
	
	public static void push_back(int X) {
		deque.add(X);
	}
	
	public static int pop_front() {
		if(deque.size() == 0) return -1;
		return deque.remove(0);
	}
	
	public static int pop_back() {
		if(deque.size() == 0) return -1;
		return deque.remove(deque.size() -1);
	}

	public static int front() {
		if(deque.size() == 0) return -1;
		return deque.get(0);
	}
	
	public static int back() {
		if(deque.size() == 0) return -1;
		return deque.get(deque.size()-1);	
	}
}