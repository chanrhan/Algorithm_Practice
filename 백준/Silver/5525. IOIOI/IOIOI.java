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
        char[] str = br.readLine().toCharArray();

        int count = 0;
        int result = 0;
        for (int i = 0; i < M - 2; ++i) {
            if (str[i] == 'I' && str[i + 1] == 'O' && str[i + 2] == 'I') {
                ++count;
                if (count == N) {
                    --count;
                    ++result;
                }
                ++i;
            } else {
                count = 0;
            }
        }

        bw.write(Integer.toString(result));

        // close
        bw.close();
        br.close();
    }
}
