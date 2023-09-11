import java.util.*;
import java.io.*;

public class Main {
		static Stack<Character> stackToReverse = new Stack<>();
		static String answer= "";
	    public static void main(String[] args) throws IOException {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String s = br.readLine();
	        Boolean excep = false;
	        
	        for(int i=0; i<s.length(); i++) {
	        	char nowChar = s.charAt(i);
	        	
	        	if(isAlpha(nowChar) && !excep) {
	        		stackToReverse.add(nowChar);
	        		if(i == s.length()-1) reverseString();
	        		continue;
	        	}
	        	reverseString();
	        	if(nowChar == '<') {
	        		excep = true;
	        		stackToReverse.clear();
	        	}
	        	if(nowChar == '>') excep =false;
	        	
	        	if(nowChar == ' ') {
	        		stackToReverse.clear();
	        	}
	        	answer += nowChar;
	        }
	        System.out.print(answer);
	    }
	    
	    public static Boolean isAlpha(char c) {
	    	if((c >= 65 && c<=122) || (c >= 48 && c<=57)) {
	    		return true;
	    	}
	    	return false;
	    }
	    
	    public static void reverseString() {
	    	while(!stackToReverse.isEmpty()) {
	    		answer += stackToReverse.pop();
	    	}
	    }
	    
}
