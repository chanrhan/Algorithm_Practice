import java.util.*;

class Solution {
    public int rob(int[] money, int start, int end){
        int size = money.length;
        int[] dp = new int[size];
        dp[start] = money[start];
        dp[start+1] = money[start+1];
        dp[start+2] = dp[start] + money[start+2];
        
        for(int i=start+3;i<end;++i){
            dp[i] = Math.max(dp[i-2], dp[i-3]) + money[i];
        }
        int max = Integer.MIN_VALUE;
        for(int i=start;i<end;++i){
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    
    public int solution(int[] money) {
        int size = money.length;
        return Math.max(rob(money, 0, size-1), rob(money, 1, size));
    }
}