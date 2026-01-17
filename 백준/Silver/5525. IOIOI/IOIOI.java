import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] _s) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tk = null;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String str = br.readLine();

        String ioi = String.format("%sI", "IO".repeat(N));
        int ioiLen = (N * 2) + 1;
        int count = 0;

        char c;
        for (int i = 0; i < M - ioiLen + 1; ++i) {
            c = str.charAt(i);
            if (c == 'I') {
                String n = str.substring(i, i + ioiLen);
                if (n.equals(ioi)) {
                    ++count;
                    ++i;
                }
            }
        }

        bw.write(Integer.toString(count));

        // close
        bw.close();
        br.close();
    }
}
