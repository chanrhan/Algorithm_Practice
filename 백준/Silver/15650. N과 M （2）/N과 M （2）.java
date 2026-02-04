import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static List<List<Integer>> result = new ArrayList<>();
    static int N, M;

    public static class Node {
        List<Integer> list;
        int num;

        public Node(int num, List<Integer> list) {
            this.num = num;
            this.list = list;
        }

        public Node no() {
            return new Node(num + 1, new ArrayList<>(list));
        }

        public Node yes() {
            List<Integer> newList = new ArrayList<>(list);
            newList.add(num);
            return new Node(num + 1, newList);
        }
    }

    public static String print(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] _s) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        Stack<Node> stack = new Stack<>();
        stack.add(new Node(1, new ArrayList<>()));

        Node curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            if (curr.list.size() == M) {
                result.add(curr.list);
                continue;
            }
            if (curr.num > N || curr.list.size() > M) {
                continue;
            }
            stack.push(curr.no());
            stack.push(curr.yes());
        }

        for (int i = 0; i < result.size(); ++i) {
            bw.write(print(result.get(i)) + '\n');
        }

        bw.close();
        br.close();
    }
}
