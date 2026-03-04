import java.io.*;
import java.util.StringTokenizer;


public class Main {
    static BufferedReader br = null;
    static BufferedWriter bw = null;
    static StringTokenizer st = null;

    static int N;
    static int[] arr;
    static Integer[] mem;

    public static int dp(int index){
        if(mem[index] == null){
            mem[index] = Math.max(dp(index-2), dp(index-3) + arr[index-1]) + arr[index];
        }
        return mem[index];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        mem = new Integer[N+1];
        for(int i=1; i<=N; ++i){
//            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(br.readLine());
        }
        mem[0] = arr[0]; // 없는값
        mem[1] = arr[1];

        if(N >= 2){
            mem[2] = arr[1]+arr[2];
        }

        bw.write(Integer.toString(dp(N)));

        bw.close();
        br.close();
    }
}