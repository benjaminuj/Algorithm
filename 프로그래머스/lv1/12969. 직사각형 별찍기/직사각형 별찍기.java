import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        for(int i=0; i<m; i++) {
            for(int k=0; k<n; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}