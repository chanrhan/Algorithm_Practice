import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] _s) throws Exception {
        int N = Integer.parseInt(br.readLine());

        int curr = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        for (int i = 1; i < N; ++i) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int count = 0;
        while (!pq.isEmpty()) {
            if (curr > pq.peek()) {
                break;
            }
            pq.add(pq.poll() - 1);
            ++curr;
            ++count;
        }

        bw.write(Integer.toString(count));

        bw.close();
        br.close();
    }

}

