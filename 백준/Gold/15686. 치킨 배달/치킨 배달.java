import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int[][] directions = new int[][]{
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    static int N, M;
    static Map<Point, Integer> houses = new HashMap<>();
    static List<Point> chickens = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    public static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;
            return p != null && r == p.r && c == p.c;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", r, c);
        }
    }


    public static void bt(int i, int count) {
        if (count == M) {
            min = Math.min(min, getSum());
            return;
        }
        if (i >= chickens.size()) {
            return;
        }
        bt(i + 1, count);

        Point chicken = chickens.get(i);
        Map<Point, Integer> rollback = new HashMap<>();
        int d;
        Integer org;
        for (Point h : houses.keySet()) {
            d = getDistance(chicken, h);
            org = houses.get(h);
            if (org == null || d < org) {
                houses.put(h, d);
                rollback.put(h, org);
            }
        }
//        print(i, count + 1);
        bt(i + 1, count + 1);
        for (Point r : rollback.keySet()) {
            houses.put(r, rollback.get(r));
        }
    }

    public static int getDistance(Point a, Point b) {
        return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
    }

    public static int getSum() {
        int sum = 0;
        for (Integer n : houses.values()) {
            if (n != null) {
                sum += n;
            }
        }
        return sum;
    }

//    public static void print(int i, int count) {
//        StringBuilder sb = new StringBuilder(String.format("--[ %d, %s : %d ]--\n", i, chickens.get(i), count));
//        for (Point h : houses.keySet()) {
//            sb.append(h).append(" : ").append(houses.get(h)).append('\n');
//        }
//        System.out.println(sb);
//    }

    public static void main(String[] _s) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        int n;
        for (int r = 0; r < N; ++r) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; ++c) {
                n = Integer.parseInt(tokenizer.nextToken());
                if (n == 2) {
                    chickens.add(new Point(r, c));
                } else if (n == 1) {
                    houses.put(new Point(r, c), null);
                }
            }
        }

        bt(0, 0);

        bw.write(Integer.toString(min));

        bw.close();
        br.close();
    }
}
