import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static String solution(int N, String sangCard, int M, String input) {
		String[] sangTemp = sangCard.split(" ");
		int[] sangArray = new int[sangTemp.length];
		String[] inputTemp = input.split(" ");
		int[] inputArray = new int[inputTemp.length];
		
		for(int i=0; i<sangTemp.length; i++) {
			sangArray[i] = Integer.parseInt(sangTemp[i]);
		}
		
		for(int i=0; i<inputArray.length; i++) {
			inputArray[i] = Integer.parseInt(inputTemp[i]);
		}
		
	
		Arrays.sort(sangArray);
		
		return binarySearch(sangArray, inputArray);
	}
	
	public static String binarySearch(int[] sangArray, int[] inputArray) {
		StringBuilder answer = new StringBuilder();
		Boolean firstAdd = true;
		
		for(int i=0; i<inputArray.length; i++) {
			Boolean none = true;
			int min = 0;
			int max = sangArray.length-1;
			int target = inputArray[i];
			
			while(min <= max) {
				int middle = (min+max)/2;
				
				if(sangArray[middle] == target) {
					if(firstAdd) {
						answer.append("1");
						firstAdd = false;
					}
					else answer.append(" 1");
					none = false;
					break;
				} else if(sangArray[middle] < target) {
					min = middle+1;
				} else {
					max = middle -1;
				}
			}
			if(none) {
				if(firstAdd) {
					answer.append("0");
					firstAdd = false;
				}
				else answer.append(" 0");
			}
		}
		return answer.toString();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		sc.nextLine();
		String sangCard = sc.nextLine();
		int M = sc.nextInt();
		sc.nextLine();
		String input1 = sc.nextLine();
		
		sc.close();
		
		System.out.println(solution(N, sangCard, M, input1));
		
	}
}



