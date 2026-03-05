import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] arr;
    static Integer[] mem;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;
    static int cm = Integer.MIN_VALUE;

    public static void dp(int i, int count) {
        int next = arr[i];
        if (visited[next]) {
            cm = Math.max(cm, count);
            return;
        }
        visited[next] = true;
        dp(next, count + 1);
    }

    public static void main(String[] _s) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        mem = new Integer[N + 1];

        for (int i = 1; i <= N; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int result = -1;
        for (int i = 1; i <= N; ++i) {
            visited = new boolean[N + 1];
            visited[i] = true;
            dp(i, 1);
            if (cm > max) {
                max = cm;
                result = i;
            } else if (cm == max && i < result) {
                result = i;
            }
        }
        bw.write(Integer.toString(result));

        bw.close();
        br.close();
    }
}

