import java.util.*;
import java.io.*;

public class Main {
	static class Info {
		int direction;
		int len;
		
		public Info(int direction, int len) {
			this.direction = direction;
			this.len = len;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> direc = new LinkedList<>();
		Queue<Info> info = new LinkedList<>();
		int K = Integer.parseInt(br.readLine());
		
		direc.add(1);
		direc.add(4);
		direc.add(2);
		direc.add(3);
		
		int maxHeight = 0;
		int maxWidth = 0;
		String[] arr = new String[2];
		for (int i = 0; i < 6; i++) {
			arr = br.readLine().split(" ");
			int dire = Integer.parseInt(arr[0]);
			int sideLen = Integer.parseInt(arr[1]);
			
			if(dire == 1 || dire == 2) {
				if(maxWidth < sideLen) maxWidth = sideLen;
			} else if (dire == 3 || dire == 4) {
				if(maxHeight < sideLen) maxHeight = sideLen;
			}
			info.add(new Info(dire, sideLen));
		}
		
		while(info.peek().direction != direc.peek()) {
			Integer temp = direc.poll();
			direc.add(temp);
		}
		
		int len1 = 0;
		int firstLen = info.peek().len;
		while(info.peek().direction == direc.peek() && info.size() != 1) {
			len1 = info.poll().len;
			int temp = direc.poll();
			direc.add(temp);
		}
		if (info.size() == 1 && info.peek().direction == direc.peek()) {
			len1 = firstLen;
		}
		int len2 = info.poll().len;
		
		int range = (maxHeight*maxWidth) - (len1*len2);
		System.out.print(range*K);
	}
}
