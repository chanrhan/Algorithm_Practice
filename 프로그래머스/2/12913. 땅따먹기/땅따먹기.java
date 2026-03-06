class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int R = land.length;
        int C = land[0].length;
        
        int[][] dp = new int[R][C];
        for(int c=0;c<C;++c){
            dp[0][c] = land[0][c];
        }
        
        for(int r=1;r<R;++r){
            for(int c=0;c<C;++c){
                int max = 0;
                for(int k=0;k<C;++k){
                    if(k == c) continue;
                    max = Math.max(max, dp[r-1][k]);
                }
                dp[r][c] = max + land[r][c];
            }
        }
        for(int c=0;c<C;++c){
            answer = Math.max(answer, dp[R-1][c]);
        }

        return answer;
    }
}