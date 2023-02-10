class Solution {
    public String solution(String polynomial) {
        String answer = "";
        int num = 0;
        int var = 0;
        String[] temp = polynomial.split(" ");
        for(int i=0; i<temp.length; i+=2) {
            if(temp[i].contains("x")) {
                if(temp[i].length() != 1) var += Integer.parseInt(temp[i].substring(0, temp[i].length() - 1));
                else var++;
            } else {
                num += Integer.parseInt(temp[i]);
            }
        }
        String numStr = num >0 ? String.valueOf(num) : "";
        String varStr = var>0 ? var == 1 ? "x" : var+"x" : "";
        if(varStr.equals("")) {
            answer = numStr;
        } 
        else if(numStr.equals("")) {
            answer = varStr;
        }
        else answer = varStr + " + " + numStr;
        return answer;
    }
}