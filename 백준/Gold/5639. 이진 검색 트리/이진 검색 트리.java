import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final StringBuilder sb = new StringBuilder();

    public static class Node {
        int value;
        Node left, right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(int value) {
            this(value, null, null);
        }

        public void insert(int n) {
            if (n < value) {
                // left
                if (left == null) {
                    left = new Node(n);
                } else {
                    left.insert(n);
                }
            } else {
                // right
                if (right == null) {
                    right = new Node(n);
                } else {
                    right.insert(n);
                }
            }
        }
    }

    public static void postOrder(Node node) {
        if (node.left != null) {
            postOrder(node.left);
        }
        if (node.right != null) {
            postOrder(node.right);
        }
        sb.append(node.value).append('\n');
    }

    public static boolean isEOF(String str) {
        return str == null || str.isEmpty();
    }

    public static void main(String[] _s) throws IOException {
        Node root = new Node(Integer.parseInt(br.readLine()));

        String input;
        while (true) {
            input = br.readLine();
            if (isEOF(input)) {
                break;
            }
            root.insert(Integer.parseInt(input));
        }

        postOrder(root);
        bw.write(sb.toString());

        bw.close();
        br.close();
    }
}
