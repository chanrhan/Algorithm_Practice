import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = 0;
        List<Set<Integer>> dp = new ArrayList<>();
        for(int i=0;i<=8;++i){
            dp.add(new HashSet<>());
        }
        
        for(int i=1;i<=8;++i){
            Set<Integer> curr = dp.get(i);
            
            for(int k=1;k<i;++k){
                for(int a : dp.get(k)){
                    for(int b : dp.get(i - k)){
                        curr.add(a + b);
                        curr.add(a - b);
                        curr.add(a * b);
                        if(b != 0){
                            curr.add(a / b);                        
                        }
                    }
                }
            }
            
            curr.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            if(curr.contains(number)){
                return i;
            }
        }
        
        
        
        return -1;
    }
}