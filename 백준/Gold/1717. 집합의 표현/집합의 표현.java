import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[n+1];
		for(int i=0; i<n+1; i++) {
			parent[i] = -1;
		}
		for(int i=0 ;i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int calculate = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(calculate == 0) union(a,b);
			if(calculate == 1) {
				int aParent = find(a);
				int bParent = find(b);
				if(aParent == bParent) System.out.println("YES");
				else System.out.println("NO");
			}
		}
	}
	
	public static int find(int x) {
		if(parent[x] <0) return x;
		else {
			int y = find(parent[x]);
			parent[x] = y;
			return y;
		}
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x==y) {
			return;
		}
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		} else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}

}
