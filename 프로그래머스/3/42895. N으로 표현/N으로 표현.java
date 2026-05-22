import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = 0;
        Set<Integer>[] set = new HashSet[9];
        
        for(int i = 1; i <= 8; ++i){
            set[i] = new HashSet<>();
        }
        set[1].add(N);
        
        for(int i = 1; i <= 8; ++i){
            set[i] = new HashSet<>();
            for(int k = 1; k < i; ++k){
                // System.out.printf("(%d) : [%d - %d]\n", i, i-k, k);
                for(int s : set[i - k]){
                    for(int e : set[k]){
                        set[i].add(s + e);
                        set[i].add(s - e);
                        set[i].add(s * e);
                        if(e != 0){
                            set[i].add(s / e);
                        }
                    }
                }
            }
            set[i].add(Integer.parseInt(String.format("%d",N).repeat(i)));
            if(set[i].contains(number)){
                return i;
            }
        }
        return -1;
    }
}