import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int R, C;
    static int[][] board;
    static boolean[] alphabets = new boolean[26];
    static int max = Integer.MIN_VALUE;
    static int[][] directions = new int[][]{
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    public static class Point {
        int r;
        int c;
    }

    public static void bt(int r, int c, int dist) {
        int nr, nc, a, count = 0;
        for (int i = 0; i < 4; ++i) {
            nr = r + directions[i][0];
            nc = c + directions[i][1];
            if (nr >= 0 && nc >= 0 && nr < R && nc < C) {
                a = board[nr][nc];
                if (!alphabets[a]) {
                    alphabets[a] = true;
                    bt(nr, nc, dist + 1);
                    alphabets[a] = false;
                    ++count;
                }
            }
        }
        if (count == 0) {
            max = Math.max(max, dist);
        }
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        board = new int[R][C];
        char[] chars;
        for (int r = 0; r < R; ++r) {
            chars = br.readLine().toCharArray();
            for (int c = 0; c < C; ++c) {
                board[r][c] = chars[c] - 'A';
            }
        }

        alphabets[board[0][0]] = true;
        bt(0, 0, 1);
        bw.write(Integer.toString(max));

        bw.close();
        br.close();
    }
}
