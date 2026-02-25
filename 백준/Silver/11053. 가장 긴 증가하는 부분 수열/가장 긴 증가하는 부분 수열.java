import java.io.*;
import java.util.*;

// 256 MB = 256 * 10^6
public class Main{
    static BufferedReader br;
    static BufferedWriter bw;
    static int[] arr;
    static int N;

    static int[] mem;

    public static int calc(int curr) throws IOException {
        if(curr == N-1){
            mem[curr] = 1;
        }

        if(mem[curr] > 0){
            return mem[curr];
        }

        int max = 1;
        for(int i=curr+1; i<N;++i){
            if(arr[i] > arr[curr]){
                max = Math.max(max, calc(i)+1);
            }
        }
        mem[curr] = max;
        return mem[curr];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        // ---

        N = Integer.parseInt(tokenizer.nextToken());
        arr = new int[N];
        mem = new int[N];
        tokenizer = new StringTokenizer(br.readLine());

        for(int i=0;i<N;++i){
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int max=0;
        for(int i=0;i<N;++i){
            max = Math.max(max, calc(i));
        }

        bw.write(Integer.toString(max));

        // ---

        br.close();
        bw.close();
    }
}