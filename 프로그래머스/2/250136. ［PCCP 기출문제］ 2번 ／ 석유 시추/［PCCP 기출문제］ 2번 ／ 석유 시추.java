import java.util.*;

class Solution {
    final int[][] dir = new int[][]{
        {0,1}, {1,0}, {0,-1}, {-1,0}  
    };
    final Map<Integer, Integer> log = new HashMap<>();
    Set<Integer> set;
    Integer[][] board;
    
   
    int sid=0;
    int h,w;
    
    class Point{
        int r,c;
        public Point(int r, int c){
            this.r=r; this.c=c;
        }
    }
    
    public int solution(int[][] land) {
        h = land.length;
        w = land[0].length;
        board = new Integer[h][w];
        
        int max = Integer.MIN_VALUE;
        for(int c=0; c<w; ++c){
            set = new HashSet<>();
            int sum=0;
            for(int r=0; r<h; ++r){
                sum += dfs(r, c, land);
            }
            // print(c, sum);
            max = Math.max(max, sum);
        }
        
        return max;
    }
    
    public int dfs(int sr, int sc, int[][] land){
        if(land[sr][sc] == 0){
            return 0;
        }
        
        if(board[sr][sc] == null){
            Queue<Point> q = new ArrayDeque<>();
            q.add(new Point(sr, sc));
            board[sr][sc] = sid;

            int count=0;

            while(!q.isEmpty()){
                Point cur = q.poll();
                ++count;

                for(int i=0; i<4; ++i){
                    int nr = cur.r + dir[i][0];
                    int nc = cur.c + dir[i][1];

                    if(nr < 0 || nc < 0 || nr >= h || nc >= w) continue; // 범위 검사
                    if(land[nr][nc] == 0) continue; // 이어진 석유 검사
                    if(board[nr][nc] != null) continue; // 방문 검사
                    board[nr][nc] = sid;
                    q.add(new Point(nr, nc));
                }
            }
            log.put(sid++, count);
        }else if(set.contains(board[sr][sc])){
            return 0;
        }
        set.add(board[sr][sc]);
        
        return log.get(board[sr][sc]);
    }
    
    public void print(int sc, int sum){
        System.out.printf("[%d = %d]\n", sc + 1, sum);
        for(int r=0; r<h; ++r){
            for(int c=0; c<w; ++c){
                if(board[r][c] == null){
                    System.out.printf("%1c ", '.');
                }else{
                    System.out.printf("%1d ", board[r][c]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}