class Solution {
    public int solution(int[][] dots) {
        int answer = 0;
        int[][] line = new int[3][4];

        line[0][0] = Math.abs(dots[0][0] - dots[1][0]);
        line[0][1] = Math.abs(dots[0][1] - dots[1][1]);
        line[0][2] = Math.abs(dots[2][0] - dots[3][0]);
        line[0][3] = Math.abs(dots[2][1] - dots[3][1]);
        
        line[1][0] = Math.abs(dots[0][0] - dots[2][0]);
        line[1][1] = Math.abs(dots[0][1] - dots[2][1]);
        line[1][2] = Math.abs(dots[1][0] - dots[3][0]);
        line[1][3] = Math.abs(dots[1][1] - dots[3][1]);
        
        line[2][0] = Math.abs(dots[0][0] - dots[3][0]);
        line[2][1] = Math.abs(dots[0][1] - dots[3][1]);
        line[2][2] = Math.abs(dots[1][0] - dots[2][0]);
        line[2][3] = Math.abs(dots[1][1] - dots[2][1]);
           
        for(int i =0; i<3;i++) {
            
            int gcd1 = GCD(line[i][0] ,line[i][1]);
            if(gcd1 != 1) {
                line[i][0] = line[i][0]/gcd1;
                line[i][1] = line[i][1]/gcd1;
            }
            int gcd2 = GCD(line[i][2] ,line[i][3]);
            if(gcd2 != 1) {
                line[i][2] = line[i][2]/gcd2;
                line[i][3] = line[i][3]/gcd2;
            }
            
            if(line[i][0] == line[i][2] && line[i][1] == line[i][3]) {
                answer=1;
                break;
            }
        }   
        return answer;
    }
    
     public int GCD(int a,int b) {
        int max = 1;
        for(int i=1; i<=a && i<=b; i++) {
            if(a%i ==0 && b%i==0) {
                max = i;
            }
        }
        return max;
    }
            
}
 
    
    
  