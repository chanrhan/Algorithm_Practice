import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<List<Integer>> dp = new ArrayList<>();
        dp.add(new ArrayList<>());
        
        for(int i=1;i<=8;++i){
            List<Integer> curr = new ArrayList<>();
            
            for(int k=1;k<i; ++k){
                List<Integer> A = dp.get(k);
                List<Integer> B = dp.get(i - k);
                for(int a : A){
                    for(int b : B){
                        curr.add(a + b);
                        curr.add(a * b);
                        curr.add(a - b);
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
            dp.add(curr);
        }
        
        return -1;
    }
}