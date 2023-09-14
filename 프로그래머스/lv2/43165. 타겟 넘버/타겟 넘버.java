class Solution {
    static int answer;
    public int solution(int[] numbers, int target) {
        int sum = 0;
        dfs(numbers, target, -1, sum);

        return answer;
    }
    
    public void dfs(int[] numbers, int target, int depth, int sum) {
        if(depth == numbers.length-1) {
            if(sum == target) answer++;
        } else {
            dfs(numbers, target, depth+1, sum+numbers[depth+1]);
            dfs(numbers, target, depth+1, sum-numbers[depth+1]);
        }
    }
}