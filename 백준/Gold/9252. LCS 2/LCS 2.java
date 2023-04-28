import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
static int[][] lcs;
static char[] charS1;
static char[] charS2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s1 = st.nextToken();
		st = new StringTokenizer(br.readLine());
		String s2 = st.nextToken();
		
		int lenS1 = s1.length();
		int lenS2 = s2.length();
		lcs = new int[lenS1+1][lenS2+1];
		charS1 = new char[lenS1+1];
		charS2 = new char[lenS2+1];
		for(int i=1; i<=lenS1; i++) {
			charS1[i] = s1.charAt(i-1);
		}
		for(int i=1; i<=lenS2; i++) {
			charS2[i] = s2.charAt(i-1);
		}
		int resultLen = dpLen(s1, s2, lenS1, lenS2);
		String resultString = dpString(lcs, lenS1, lenS2);
		System.out.println(resultLen);
		if(resultLen == 0) {
			return;
		}
		else {
			System.out.println(resultString);
		}
	}
	
	public static int dpLen(String s1, String s2, int lenS1, int lenS2) {
		for(int i=1; i<lenS1+1; i++) {
			for(int j=1; j<lenS2+1; j++) {
				if(charS1[i] == charS2[j]) lcs[i][j] = lcs[i-1][j-1] +1;
				else lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
			}
		}
		return lcs[lenS1][lenS2];
	}
	
	public static String dpString(int[][] lcs, int lenS1, int lenS2) {
		String answer = "";
		int xIndex = lenS1;
		int yIndex = lenS2;
		while(xIndex !=0 && yIndex !=0) {
			if(charS1[xIndex] == charS2[yIndex]) {
				answer += charS1[xIndex];
				xIndex--; 
				yIndex--;
			}
			else {
				if(lcs[xIndex][yIndex] == lcs[xIndex-1][yIndex]) {
					xIndex = --xIndex;
				}else {
					yIndex = --yIndex;
				}
			}
		}
		StringBuffer sb = new StringBuffer(answer);
		return sb.reverse().toString();
	}
}
