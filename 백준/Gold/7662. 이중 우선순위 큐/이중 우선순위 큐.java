import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] _s) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = null;

        final int T = Integer.parseInt(br.readLine());
        int Q, num;
        String func;
        for (int t = 0; t < T; ++t) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            Q = Integer.parseInt(br.readLine());
            for (int q = 0; q < Q; ++q) {
                tokenizer = new StringTokenizer(br.readLine());
                func = tokenizer.nextToken();
                num = Integer.parseInt(tokenizer.nextToken());
                if (func.equals("I")) {
                    treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
                } else {
                    if (treeMap.isEmpty()) {
                        continue;
                    }
                    int key;
                    if (num == 1) {
                        key = treeMap.lastKey();
                    } else {
                        key = treeMap.firstKey();
                    }
                    treeMap.put(key, treeMap.get(key) - 1);
                    if (treeMap.get(key) == 0) {
                        treeMap.remove(key);
                    }
                }
            }
            if (treeMap.isEmpty()) {
                bw.write("EMPTY\n");
            } else {
                bw.write(treeMap.lastKey() + " " + treeMap.firstKey() + "\n");
            }
        }

        bw.close();
        br.close();
    }
}
