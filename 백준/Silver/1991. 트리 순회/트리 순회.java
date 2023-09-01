import java.util.*;
import java.io.*;

public class Main {
	
	static class Node {
		int left;
		int right;
		
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
	
	static Node[] list;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		list = new Node[N+1];

		for(int i =1; i<=N; i++) {
			String[] line = br.readLine().split(" ");
		
			int root = line[0].charAt(0)-64;
			int left = line[1].charAt(0)-64;
			int right = line[2].charAt(0)-64;
			
			list[root] = new Node(left, right);
		}
		
		preOrder(1);
		sb.append("\n");
		inOrder(1);
		sb.append("\n");
		postOrder(1);
		System.out.println(sb.toString());
	
		
	}

	public static void preOrder(int start) {
		int l = list[start].left;
		int r = list[start].right;
		
		sb.append((char)(start +64));
		if(l > 0 && l < 27) preOrder(l);
		if(r > 0 && r < 27) preOrder(r);
		
	}
	
	public static void inOrder(int start) {
		int l = list[start].left;
		int r = list[start].right;
		
		if(l > 0 && l < 27) inOrder(l);
		sb.append((char)(start +64));
		if(r > 0 && r < 27) inOrder(r);
	}
	
	public static void postOrder(int start) {
		int l = list[start].left;
		int r = list[start].right;
		
		if(l > 0 && l < 27) postOrder(l);
		if(r > 0 && r < 27) postOrder(r);
		sb.append((char)(start +64));
		
	}

}


