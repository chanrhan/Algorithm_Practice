import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] _s) throws Exception {
        char[] str = br.readLine().toCharArray();

        int c0 = 0;
        int c1 = 0;
        if (str[0] == '1') {
            ++c1;
        } else {
            ++c0;
        }
        for (int i = 1; i < str.length; ++i) {
            if (str[i] != str[i - 1]) {
                if (str[i] == '1') {
                    ++c1;
                } else {
                    ++c0;
                }
            }
        }
        bw.write(Integer.toString(Math.min(c0, c1)));

        bw.close();
        br.close();
    }

}

