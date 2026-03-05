import java.util.*;

class Solution {
    public int solution(int[][] t) {
        int answer = 0;
        int size = t.length;
        int[][] dp = new int[size][size];
        dp[0][0] = t[0][0];
    
        for(int r=1;r<size;++r){
            dp[r][0] = dp[r-1][0] + t[r][0];
            for(int c = 1; c <= r; ++c){
                dp[r][c] = Math.max(dp[r-1][c], dp[r-1][c-1]) + t[r][c];
                if(r == size - 1){
                    answer = Math.max(answer, dp[r][c]);
                }
            }
        }
        
        return answer;
    }
}