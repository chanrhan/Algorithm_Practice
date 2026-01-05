import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static final String Fizz = "Fizz";
    static final String Buzz = "Buzz";
    static final String FizzBuzz = "FizzBuzz";

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] _s) throws IOException, IllegalAccessException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        String str = null;
        int lastNum = 0;
        int i = 0;
        for (; i < 3; ++i) {
            str = br.readLine();
            if (isInteger(str)) {
                lastNum = Integer.parseInt(str);
                break;
            }
        }
        int targetNumber = lastNum + (3 - i);
        if (targetNumber % 3 == 0 && targetNumber % 5 == 0) {
            bw.write(FizzBuzz);
        } else if (targetNumber % 3 == 0) {
            bw.write(Fizz);
        } else if (targetNumber % 5 == 0) {
            bw.write(Buzz);
        } else {
            bw.write(Integer.toString(targetNumber));
        }

        // close
        bw.close();
        br.close();
    }
}
