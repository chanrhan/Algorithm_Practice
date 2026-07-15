import java.util.*;

class Solution {
    int n,m;
    int[][] q;
    int[] ans;
    int[] pick = new int[5];
    int count=0;
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n=n; this.m=ans.length;
        this.q=q; this.ans =ans;
        combine(1, 0);
        return count;
    }
    
    public void combine(int start, int depth){
        if(depth == 5){
            if(isValid()) ++count;
            return;
        }
        
        for(int i=start; i<=n; ++i){
            pick[depth] = i;
            combine(i + 1, depth + 1);
        }
    }
    
    public boolean isValid(){
        Set<Integer> code = new HashSet<>();
        for(int x : pick) code.add(x);
        for(int i=0; i<m; ++i){
            int match = 0;
            for(int x : q[i]) if(code.contains(x)) ++match;
            if(match != ans[i]) return false;
        }
        return true;
    }
}