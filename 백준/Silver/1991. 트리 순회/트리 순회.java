import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final char NULL = '.';

    public static class Pair {
        Character left;
        Character right;

        public Pair(Character left, Character right) {
            this.left = left;
            this.right = right;
        }
    }

    static Map<Character, Pair> map = new HashMap<>();
    static final StringBuilder sb = new StringBuilder();

    public static void preOrder(char curr) {
        sb.append(curr);
        Pair pair = map.get(curr);
        if (pair.left != NULL) {
            preOrder(pair.left);
        }
        if (pair.right != NULL) {
            preOrder(pair.right);
        }
    }

    public static void inOrder(char curr) {
        Pair pair = map.get(curr);
        if (pair.left != NULL) {
            inOrder(pair.left);
        }
        sb.append(curr);
        if (pair.right != NULL) {
            inOrder(pair.right);
        }
    }

    public static void postOrder(char curr) {
        Pair pair = map.get(curr);
        if (pair.left != NULL) {
            postOrder(pair.left);
        }
        if (pair.right != NULL) {
            postOrder(pair.right);
        }
        sb.append(curr);
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(tokenizer.nextToken());

        char a, b, c;
        for (int i = 0; i < N; ++i) {
            tokenizer = new StringTokenizer(br.readLine());
            a = tokenizer.nextToken().toCharArray()[0];
            b = tokenizer.nextToken().toCharArray()[0];
            c = tokenizer.nextToken().toCharArray()[0];
            map.put(a, new Pair(b, c));
        }
        preOrder('A');
        sb.append('\n');
        inOrder('A');
        sb.append('\n');
        postOrder('A');
        bw.write(sb.toString());

        bw.close();
        br.close();
    }
}
