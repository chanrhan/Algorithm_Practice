import java.util.*;

class Solution {
    int n;
    int[] diffs;
    int[] times;
    long limit;
    
    public int solution(int[] diffs, int[] times, long limit) {
        this.n = diffs.length; this.limit = limit;
        this.diffs = diffs; this.times = times;
        
        int max = Integer.MIN_VALUE;
        for(int d : diffs){
            max = Math.max(max, d);
        }
        return binarySearch(1, max);
    }
    
    public int binarySearch(int lo, int hi){
        while(lo < hi){
            int mid = (lo + hi) / 2;
            if(solve(mid)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
    
    public boolean solve(int level){
        long sum=0;
        for(int i=0; i<n; ++i){
            if(level >= diffs[i]){
                sum += times[i];
            }else{
                sum += (diffs[i] - level) * (times[i] + times[i-1]) + times[i];
            }
            if(sum > limit) return false;
        }
        return true;
    }
}