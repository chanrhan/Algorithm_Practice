import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        boolean[][] water = new boolean[n+1][m+1];
        dp[0][1] = 1;
        
        if(puddles.length > 0 && puddles[0].length == 2){
            for(int[] p : puddles){
                water[p[1]][p[0]] = true;                    
            }
        }
        
        
        for(int r = 1; r <= n; ++r){
            for(int c = 1;c <= m; ++c){
                if(water[r][c]) continue;
                dp[r][c] = (dp[r-1][c] + dp[r][c-1]) % 1_000_000_007;
            }
        }
        return dp[n][m];
    }
}