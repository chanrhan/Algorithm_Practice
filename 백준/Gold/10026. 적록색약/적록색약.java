import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    final static int[][] dir = new int[][]{
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    public static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", r, c);
        }
    }

    static int N;
    static char[][] map;
    static List<Point> redList = new ArrayList<>();
    static List<Point> greenList = new ArrayList<>();
    static List<Point> blueList = new ArrayList<>();
    static Map<Character, List<Point>> colors = new HashMap<>();

    public static int countSections(char c) {
        boolean[][] visited = new boolean[N][N];
        Queue<Point> deque = new ArrayDeque<>();
        List<Point> list = colors.get(c);
        int index = 0;
        int sections = 0;
        int count;

        Point curr, next;
        while (index < list.size()) {
            deque.add(list.get(index++));
            count = 0;

            while (!deque.isEmpty()) {
                curr = deque.poll();
                if (visited[curr.r][curr.c]) {
                    continue;
                }
//                System.out.println(curr);
                ++count;
                visited[curr.r][curr.c] = true;
                for (int i = 0; i < 4; ++i) {
                    next = new Point(curr.r + dir[i][0], curr.c + dir[i][1]);
                    if (isAvailable(next) && map[next.r][next.c] == c) {
                        deque.add(next);
                    }
                }
            }
            if (count > 0) {
                ++sections;
//                System.out.printf("count: %d, sec: %d\n", count, sections);
            }
        }
//        System.out.printf("(%c) : %d\n", c, sections);
        return sections;
    }

    public static int countSectionsRG() {
        boolean[][] visited = new boolean[N][N];
        Queue<Point> deque = new ArrayDeque<>();
        int index = 0;
        int sections = 0;
        int count;

        Point curr, next;
        while (index < redList.size() + greenList.size()) {
            deque.add(getPointFromRedGreen(index++));
            count = 0;

            while (!deque.isEmpty()) {
                curr = deque.poll();
                if (visited[curr.r][curr.c]) {
                    continue;
                }
                ++count;
                visited[curr.r][curr.c] = true;
                for (int i = 0; i < 4; ++i) {
                    next = new Point(curr.r + dir[i][0], curr.c + dir[i][1]);
                    if (isAvailable(next) && (map[next.r][next.c] == 'R' || map[next.r][next.c] == 'G')) {
                        deque.add(next);
                    }
                }
            }
            if (count > 0) {
                ++sections;
            }
        }
        return sections;
    }

    public static Point getPointFromRedGreen(int index) {
        int redSize = colors.get('R') == null ? 0 : colors.get('R').size();

        if (index < redSize) {
            return colors.get('R').get(index);
        } else {
            return colors.get('G').get(index - redSize);
        }
    }

    public static boolean isAvailable(Point p) {
        return p.r >= 0 && p.c >= 0 && p.r < N && p.c < N;
    }

    public static void main(String[] _s) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int r = 0; r < N; ++r) {
            char[] inputs = br.readLine().toCharArray();
            for (int c = 0; c < N; ++c) {
                map[r][c] = inputs[c];
                switch (map[r][c]) {
                    case 'R':
                        redList.add(new Point(r, c));
                        break;
                    case 'G':
                        greenList.add(new Point(r, c));
                        break;
                    case 'B':
                        blueList.add(new Point(r, c));
                        break;
                }
            }
        }
        colors.put('R', redList);
        colors.put('G', greenList);
        colors.put('B', blueList);

        int normal = countSections('R') + countSections('G') + countSections('B');
        int rg = countSectionsRG() + countSections('B');

        bw.write(normal + " " + rg);

        br.close();
        bw.close();
    }
}