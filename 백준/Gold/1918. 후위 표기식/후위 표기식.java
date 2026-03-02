import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static boolean isPrior(char org, char other) {
        if (org == '+' || org == '-') {
            if (other == '(' || other == ')') {
                return false;
            }
            return true;
        }
        if (org == '*' || org == '/') {
            if (other == '(' || other == ')') {
                return false;
            }
            if (other == '+' || other == '-') {
                return false;
            }
            return true;
        }
        return false;
    }

    public static void main(String[] _s) throws Exception {
        char[] inOrders = br.readLine().toCharArray();

        StringBuilder sb = new StringBuilder();
        Deque<Character> deque = new ArrayDeque<>();

        char c;
        Character p;
        for (int i = 0; i < inOrders.length; ++i) {
            c = inOrders[i];
            if (c >= 'A' && c <= 'Z') {
                sb.append(c);
                continue;
            }

            if (c == '(') {
                deque.addLast(c);
            } else if (c == ')') {
                while (!deque.isEmpty() && deque.peekLast() != '(') {
                    sb.append(deque.pollLast());
                }
                deque.pollLast();
            } else {
                while (!deque.isEmpty() && isPrior(c, deque.peekLast())) {
                    sb.append(deque.pollLast());
                }
                deque.addLast(c);
            }
        }
        while (!deque.isEmpty()) {
            sb.append(deque.pollLast());
        }
        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

}
