import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String args[]) throws Exception {
	StringTokenizer tokenizer = null;
	StringBuilder sb = new StringBuilder();

//	int T = Integer.parseInt(br.readLine());

	ArrayDeque<Integer>[] dq = null;
	for (int tc = 1; tc <= 10; ++tc) {
	    int T = Integer.parseInt(br.readLine());
	    dq = new ArrayDeque[100];
	    for (int c = 0; c < 100; ++c) {
		dq[c] = new ArrayDeque<Integer>();
	    }

	    for (int r = 0; r < 100; ++r) {
		tokenizer = new StringTokenizer(br.readLine());
		for (int c = 0; c < 100; ++c) {
		    int n = Integer.parseInt(tokenizer.nextToken());
		    if (n != 0) {
			dq[c].addLast(n);
		    }
		}
	    }
	    boolean flag;
	    int count = 0;
	    for (int c = 0; c < 100; ++c) {
		while (!dq[c].isEmpty()) {
		    flag = false;
		    if (dq[c].peekFirst() == 2) {
			dq[c].pollFirst();
			flag = true;
		    }
		    if (dq[c].peekLast() == 1) {
			dq[c].pollLast();
			flag = true;
		    }
		    if (!flag) {
			break;
		    }
		}
		Integer prev = null;
		while (!dq[c].isEmpty()) {
		    int p = dq[c].pollFirst();
		    if (prev != null && p != prev) {
			++count;
		    }
		    prev = p;
		}
	    }

	    count = (count - 100) / 2 + 100;
	    sb.append('#').append(tc).append(' ').append(count).append('\n');

	}
	System.out.println(sb);
    }
}
