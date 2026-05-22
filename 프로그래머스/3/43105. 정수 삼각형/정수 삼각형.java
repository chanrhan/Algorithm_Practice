import java.util.*;

class Solution {
    public int solution(int[][] tr) {
        int answer = 0;
        int R = tr.length;
        int[][] dp = new int[R][R];
        
        dp[0][0] = tr[0][0];
        
        for(int r = 1; r < R; ++r){
            for(int c = 0; c <= r; ++c){
                if(c == 0){
                    dp[r][0] = dp[r-1][0] + tr[r][0];
                }else{
                    dp[r][c] = Math.max(dp[r-1][c-1], dp[r-1][c]) + tr[r][c];
                }            
                if(r == R-1){
                    answer = Math.max(answer, dp[r][c]);
                }
            }
            // System.out.println(Arrays.toString(dp[r]));
        }
        
        return answer;
    }
}