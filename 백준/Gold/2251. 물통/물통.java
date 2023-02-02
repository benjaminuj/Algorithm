import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
static boolean visited[][];
static int[] sender = {0,0,1,1,2,2};
static int[] receiver = {1,2,0,2,0,1};
static boolean answer[];
static int[] now;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		now = new int[3];
		now[0] = Integer.parseInt(st.nextToken());
		now[1] = Integer.parseInt(st.nextToken());
		now[2] = Integer.parseInt(st.nextToken());
		visited = new boolean[201][201];
		answer = new boolean[201];
		BFS();
		for(int i=0; i<answer.length; i++) {
			if(answer[i]) System.out.print(i+ " ");
		}

	}
	
	public static void BFS() {
		Queue<AB> queue = new LinkedList<>();
		queue.add(new AB(0,0));
		visited[0][0] = true;
		answer[now[2]] = true;
		while(!queue.isEmpty()) {
			AB p = queue.poll();
			int A = p.A;
			int B = p.B;
			int C = now[2] - A- B;
			for(int k=0; k<6; k++) {
				int[] next = {A,B,C};
				next[receiver[k]] += next[sender[k]];
				next[sender[k]] = 0;
				if(next[receiver[k]] > now[receiver[k]]) {
					next[sender[k]] = next[receiver[k]] - now[receiver[k]];
					next[receiver[k]] = now[receiver[k]];
				}
				if(!visited[next[0]][next[1]]) {
					visited[next[0]][next[1]] = true;
					queue.add(new AB(next[0], next[1]));
					if(next[0] == 0) answer[next[2]] = true;
				}
			}
		}
	}
}
class AB {
	int A;
	int B;
	public AB(int A, int B) {
		this.A=A;
		this.B=B;
	}
}
