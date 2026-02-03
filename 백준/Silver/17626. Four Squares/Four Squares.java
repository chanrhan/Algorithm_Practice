import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    public static int calc(int N) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(N);
        int size, curr, num, pow, count = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size > 0) {
                curr = queue.poll();

                if (curr == 0) {
                    return count;
                }
                --size;
                num = (int) Math.floor(Math.sqrt(curr));
                do {
                    pow = (int) Math.pow(num, 2);
                    if (curr == pow) {
                        return count + 1;
                    }
                    if (curr - pow > 0) {
                        queue.add(curr - pow);
                    }
                    --num;
                } while (num > 0);
            }
            ++count;
        }
        return count;
    }

    public static void main(String[] _s) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int N = Integer.parseInt(br.readLine());

        bw.write(Integer.toString(calc(N)));

        bw.close();
        br.close();
    }
}
