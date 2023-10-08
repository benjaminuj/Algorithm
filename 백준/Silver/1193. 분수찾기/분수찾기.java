import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());
		int count = 1;
		int x = 0;
		int y =0;
		boolean isUp = false;
		
		while (count < input) {	
			if (y == 0 && (x%2 == 0)) {
				x++;
				isUp = false;
				count++;
				continue;
			} else if (x == 0 && (y%2 == 1)) {
				y++;
				isUp = true;
				count++;
				continue;
			} 
			
			if(isUp) {
				x++;
				y--;
			} else {
				x--;
				y++;
			}
			count++;
		}
		
		System.out.print(++y + "/" + ++x);
		
	}
}
