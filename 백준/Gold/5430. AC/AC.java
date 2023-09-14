import java.util.*;
import java.io.*;

public class Main {
	
	static String[] arr;
	static Queue<Character> q = new LinkedList<>();
	static Queue<Character> answer = new LinkedList<>();
	static int start, end;
	static int isReverse;
	static boolean isError = false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = null;
		
		for(int i=0; i<T; i++) {
			sb = new StringBuilder();
			isReverse = -1;
			start = 0;
			isError = false;
			q.clear();
			String p = br.readLine();
			for(int j=0; j<p.length(); j++) {
				q.add(p.charAt(j));
				
			}
			int n = Integer.parseInt(br.readLine());
			end = n-1;
			arr = new String[n];
			String temp = br.readLine();
			arr = temp.substring(1, temp.length()-1).replace(",", " ").split(" ");
			
			readFunction();
			if(isError) {
				sb.append("error");
			} else {
				sb.append("[");
				if(isReverse == 1) {
					for(int k = start ; k>=end; k--) {
						sb.append(arr[k] + ",");
					}
				} else {
					for(int k = start ; k<=end; k++) {
						sb.append(arr[k] + ",");
					}
				}
				if(sb.length()>1)sb.deleteCharAt(sb.length()-1);
				sb.append("]");
			}
			System.out.println(sb);
		}
	}
	
	public static void readFunction() {
		while(!q.isEmpty()) {
			char cmd = q.poll();
			
			switch(cmd) {
				case 'R' : 
					reverseIdx();
					break;
				case 'D' : 
					if(isNone()) isError = true;
					deleteIdx();
					break;	
			}
		}
	}
	
	public static void reverseIdx() {
		int temp = start;
		start = end;
		end = temp;
		isReverse *= (-1);
	}
	
	public static void deleteIdx() {
		if(isReverse == 1) {
			start--;
		} else if(isReverse == -1) {
			start++;
		}
	}
	
	public static boolean isNone() {
		boolean answer= false;
		if(isReverse == 1) {
			if(start < end) {
				answer=  true;
			}
		}
		if(isReverse == -1) {
			if(start > end) answer = true;
		}
		return answer;
	}
}
