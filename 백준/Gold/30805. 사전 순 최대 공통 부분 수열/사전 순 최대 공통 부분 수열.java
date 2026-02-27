import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] seq1; // r
    static int[] seq2; // c

    public static class Number implements Comparable<Number> {
        int index;
        int value;

        public Number(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Number o) {
            if (value == o.value) {
                return index - o.index;
            }
            return o.value - value;
        }
    }

    public static void main(String[] _s) throws IOException {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Number> A = new PriorityQueue<>();

        StringTokenizer tokenizer = null;
        N = Integer.parseInt(br.readLine());
        seq1 = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            seq1[i] = Integer.parseInt(tokenizer.nextToken());
            A.add(new Number(i, seq1[i]));
        }
        M = Integer.parseInt(br.readLine());
        seq2 = new int[M];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            seq2[i] = Integer.parseInt(tokenizer.nextToken());
        }

        List<Integer> result = new ArrayList<>();

        Number curr;
        int a_start = -1;
        int b_start = 0;
        while (!A.isEmpty()) {
            curr = A.poll();
            if (curr.index <= a_start) {
                continue;
            }
            for (int i = b_start; i < M; ++i) {
                if (curr.value == seq2[i]) {
                    result.add(curr.value);
                    a_start = curr.index;
                    b_start = i + 1;
                    break;
                }
            }
        }
        sb.append(result.size()).append('\n');
        for (int r : result) {
            sb.append(r).append(' ');
        }
        bw.write(sb.toString());

        bw.close();
        br.close();
    }

}
