import java.util.*;

class Solution {
    public boolean isInteger(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public int solution(String arr[]) {
        int answer = -1;
        List<Integer> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        for(String el : arr){
            if(isInteger(el)){
                numbers.add(Integer.parseInt(el));
            }else{
                operators.add(el);
            }
        }
        int numberSize = numbers.size();
        int[][] maxDp = new int[numberSize][numberSize];
        int[][] minDp = new int[numberSize][numberSize];
        for(int i=0;i<numberSize; ++i){
            maxDp[i][i] = numbers.get(i);
            minDp[i][i] = numbers.get(i);
        }
        
        for(int e=1;e<numbers.size();++e){ // s : start, e : end, d : divide
            for(int s = e-1; s >=0; --s){
                maxDp[s][e] = Integer.MIN_VALUE;
                minDp[s][e] = Integer.MAX_VALUE;
                for(int d=s; d < e; ++d){
                    // System.out.printf("(%d -|%d|- %d)\n", s,d,e);
                    String op = operators.get(d);
                    
                    if(op.equals("+")){
                        maxDp[s][e] = Math.max(maxDp[s][e], maxDp[s][d] + maxDp[d+1][e]);
                        minDp[s][e] = Math.min(minDp[s][e], minDp[s][d] + minDp[d+1][e]);
                    }else{
                        maxDp[s][e] = Math.max(maxDp[s][e], maxDp[s][d] - minDp[d+1][e]);
                        minDp[s][e] = Math.min(minDp[s][e], minDp[s][d] - maxDp[d+1][e]);
                    }
                }
            }
        }
        
        return maxDp[0][numberSize-1];
    }
}