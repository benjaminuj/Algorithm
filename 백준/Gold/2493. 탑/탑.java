import java.io.*;
import java.util.*;

public class Main { 
	 public static void main(String args[]) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 int N = Integer.parseInt(br.readLine());
		 String[] arr = br.readLine().split(" ");
		 
		 StringBuilder answer = new StringBuilder();
		 Deque<Info> stack = new ArrayDeque<>();
		 
		 for (int i = 0; i < N; i++) {
			 int cur = Integer.parseInt(arr[i]);
			 boolean found = false;
			 
			 while (!stack.isEmpty()) {
				 Info top = stack.peek();
				 
				 if (top.height >= cur) {
					 answer.append(" " + top.idx);
					 found = true;
					 break;
				 } 
				 if (top.height < cur) {
					 stack.pop();
				 }
				 
			 }
			 if (!found && stack.isEmpty()) {
				 answer.append(" 0");
			 }
			 
			 stack.push(new Info(cur, i+1));
	     }
		 stack = null;
		 answer.deleteCharAt(0);
		 
		 System.out.print(answer.toString());
	 }
	 
	 static class Info{
		 int height;
		 int idx;
		 
		 public Info(int height, int idx) {
			 this.height = height;
			 this.idx = idx;
		 }
	 }
}