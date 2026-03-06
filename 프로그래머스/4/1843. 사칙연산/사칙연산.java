import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int answer = -1;
        int size = arr.length / 2 + 1;
        long[][] maxDp = new long[size][size];
        long[][] minDp = new long[size][size];
    
        for(int i=0;i<size;++i){
            maxDp[i][i] = Long.parseLong(arr[i*2]);
            minDp[i][i] = Long.parseLong(arr[i*2]);
        }
        
        long minValue, maxValue;
        for(int e=1;e<size;++e){ // end
            for(int s=e-1;s>=0;--s){ // start
                maxDp[s][e] = Long.MIN_VALUE;
                minDp[s][e] = Long.MAX_VALUE;
                
                for(int k=s;k<e;++k){ // k
                    // System.out.printf("(%d, %d) : %d\n",s,e,k);
                    
                    if(arr[k * 2 + 1].equals("+")){
                        maxValue = maxDp[s][k] + maxDp[k+1][e];
                        minValue = minDp[s][k] + minDp[k+1][e];
                    }else{
                        maxValue = maxDp[s][k] - minDp[k+1][e];
                        minValue = minDp[s][k] - maxDp[k+1][e];
                    }
                    maxDp[s][e] = Math.max(maxDp[s][e], maxValue);
                    minDp[s][e] = Math.min(minDp[s][e], minValue);
                }
            }
        }
        answer = (int)maxDp[0][size-1];
        
        return answer;
    }
}