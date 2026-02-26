import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static final int HOLE = 9;
    static List<Point> virusList;

    static int max = Integer.MIN_VALUE;

    static final int[][] directions = new int[][]{
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    public static class Point{
        public int r;
        public int c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static boolean isOutOfBounds(int row, int col){
        return row < 0 || col < 0 || row >= N || col >= M;
    }

    // 3개의 벽을 세우는 모든 경우의 수
    public static void dfs(int wallCount){
        // 3개의 벽을 세웠다면, BFS 로 바이러스를 퍼트린다
        if(wallCount == 3){
            bfs();
            return;
        }

        for(int r=0;r<N;++r){
            for(int c=0;c<M;++c){
                if(map[r][c] == EMPTY){
                    map[r][c] = WALL; // lock
                    dfs(wallCount+1);
                    map[r][c] = EMPTY; // unlock
                }
            }
        }
    }

    public static void bfs(){
        Queue<Point> queue = new ArrayDeque<>();
        for(int r=0;r<N;++r){
            for(int c=0;c<M;++c){
                if(map[r][c] == VIRUS){
                    queue.add(new Point(r,c));
                }
            }
        }
//        queue.addAll(virusList);
        int[][] copyMap = new int[N][M];
        for(int r=0;r<N;++r){
            copyMap[r] = map[r].clone();
        }

        Point curr;
        while (!queue.isEmpty()){
            curr = queue.poll();

            for(int i=0;i<4;++i){
                int nr = curr.r + directions[i][0];
                int nc = curr.c + directions[i][1];

                if(isOutOfBounds(nr, nc) || copyMap[nr][nc] != EMPTY){
                    continue;
                }
//                System.out.printf("(%d, %d) : %d\n", nr, nc, copyMap[nr][nc]);
                copyMap[nr][nc] = VIRUS;

                queue.add(new Point(nr, nc));
            }
        }
        calcSafeZone(copyMap);
    }

    public static void calcSafeZone(int[][] _map){
        int count=0;
        for(int r=0;r<N;++r){
            for(int c=0;c<M;++c){
                if (_map[r][c] == EMPTY) {
                    ++count;
                }
            }
        }
        max = Math.max(max, count);
    }

    public static void main(String[] _s) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tk = null;

        // ---
        tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        virusList = new ArrayList<>();

        for(int r=0;r<N;++r){
            tk = new StringTokenizer(br.readLine());
            for(int c=0;c<M;++c){
                map[r][c] = Integer.parseInt(tk.nextToken());
                if(map[r][c] == VIRUS){
//                    System.out.printf("%d / %d\n", r, c);
                    virusList.add(new Point(r,c));
                }
            }
        }

        dfs(0);
        bw.write(max + "\n");

//        for(int r=0;r<N;++r){
//            for(int c=0;c<M;++c){
//                bw.write(map[r][c] + " ");
//            }
//            bw.write("\n");
//        }

        // ---

        br.close();
        bw.close();
    }
}