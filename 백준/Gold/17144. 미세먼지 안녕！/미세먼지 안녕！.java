import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int R, C, T;
    static int[][] board;
    static int[][] lazy;
    static int[] cleaner = new int[2];
    static int[][] directions = new int[][]{
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    public static void rotate() {
        // 위 반시계 순환
        for (int r = cleaner[0] - 1; r > 0; --r) {
            board[r][0] = board[r - 1][0];
        }
        for (int c = 0; c < C - 1; ++c) {
            board[0][c] = board[0][c + 1];
        }
        for (int r = 0; r < cleaner[0]; ++r) {
            board[r][C - 1] = board[r + 1][C - 1];
        }
        for (int c = C - 1; c > 1; --c) {
            board[cleaner[0]][c] = board[cleaner[0]][c - 1];
        }

        // 아래 시계 순환
        for (int r = cleaner[1] + 1; r < R - 1; ++r) {
            board[r][0] = board[r + 1][0];
        }
        for (int c = 0; c < C - 1; ++c) {
            board[R - 1][c] = board[R - 1][c + 1];
        }
        for (int r = R - 1; r > cleaner[1]; --r) {
            board[r][C - 1] = board[r - 1][C - 1];
        }
        for (int c = C - 1; c > 1; --c) {
            board[cleaner[1]][c] = board[cleaner[1]][c - 1];
        }
        board[cleaner[0]][0] = -1;
        board[cleaner[1]][0] = -1;
        board[cleaner[0]][1] = 0;
        board[cleaner[1]][1] = 0;
    }

    public static void print() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (board[r][c] == -1) {
                    sb.append('X');
                } else {
                    sb.append(board[r][c]);
                }
                sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static int[][] deepCopy(int[][] org) {
        int[][] copies = new int[R][C];
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                copies[r][c] = org[r][c];
            }
        }
        return copies;
    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        T = Integer.parseInt(tokenizer.nextToken());

        int ci = 0;
        board = new int[R][C];

        for (int r = 0; r < R; ++r) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; ++c) {
                board[r][c] = Integer.parseInt(tokenizer.nextToken());
                if (board[r][c] == -1) {
                    cleaner[ci++] = r;
                }
            }
        }

        int remained, expand, nextRow, nextCol;
        for (int t = 0; t < T; ++t) {
            lazy = new int[R][C];
            for (int r = 0; r < R; ++r) {
                for (int c = 0; c < C; ++c) {
                    if (board[r][c] <= 0) {
                        continue;
                    }
                    remained = board[r][c];
                    expand = remained / 5;
                    if (expand == 0) {
                        lazy[r][c] += remained;
                        continue;
                    }
                    for (int i = 0; i < 4; ++i) {
                        nextRow = r + directions[i][0];
                        nextCol = c + directions[i][1];
                        if (nextRow >= 0 && nextCol >= 0 && nextRow < R && nextCol < C
                                && board[nextRow][nextCol] != -1) {
                            lazy[nextRow][nextCol] += expand;
                            remained -= expand;
                        }
                    }
                    lazy[r][c] += remained;
                }
            }
            board = deepCopy(lazy);
            rotate();
//            print();
        }

        int sum = 0;
//        for (Point p : queue) {
//            sum += board[p.r][p.c];
//        }
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (board[r][c] > 0) {
                    sum += board[r][c];
                }
            }
        }

        bw.write(Integer.toString(sum));

        bw.close();
        br.close();
    }

}
