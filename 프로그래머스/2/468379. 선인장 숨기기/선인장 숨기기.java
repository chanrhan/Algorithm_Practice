import java.util.*;

class Solution {
    
    public void print(int[][] map, int row, int col){
        for(int r=0;r<row;++r){
            for(int c=0;c<col;++c){
                System.out.printf("%2d ", map[r][c] == Integer.MAX_VALUE ? 0 : map[r][c]);
            }
            System.out.println();
        }
        System.out.println("ㅡㅡㅡㅡ");
    }
    
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[] answer = new int[2];
        answer[0] = Integer.MAX_VALUE;
        answer[1] = Integer.MAX_VALUE;
        
        int[][] board = new int[m][n];
        for(int r=0; r < m; ++r){
            Arrays.fill(board[r], Integer.MAX_VALUE);
        }
        for(int i=0; i < drops.length; ++i){
            board[drops[i][0]][drops[i][1]] = i + 1;
        }
        // print(board, m, n);
        
        int[][] rows = new int[m][n - w + 1];
        int[][] cols = new int[m - h + 1][n - w + 1]; 
        
        ArrayDeque<Integer> dq = null;
        
        // 가로 최솟값 슬라이딩
        int left, right;
        for(int r = 0; r < m; ++r){
            dq = new ArrayDeque<>();
            for(int c = 0; c < n; ++c){
                while(!dq.isEmpty() && dq.peekFirst() <= c - w){
                    dq.pollFirst();
                }
                
                while(!dq.isEmpty() && board[r][dq.peekLast()] >= board[r][c]){
                    dq.pollLast();
                }
                
                dq.offerLast(c);
                
                if(c >= w - 1){
                    rows[r][c - w + 1] = board[r][dq.peekFirst()];
                }
            }
        }
        // print(rows, m, n - w + 1);
        
        
        // 세로 최솟값 슬라이딩
        int result = Integer.MIN_VALUE;
        for(int c = 0; c < n - w + 1; ++c){
            dq = new ArrayDeque<>();
            for(int r = 0; r < m; ++r){
                while(!dq.isEmpty() && dq.peekFirst() <= r - h){
                    dq.pollFirst();
                }
                
                while(!dq.isEmpty() && rows[dq.peekLast()][c] >= rows[r][c]){
                    dq.pollLast();
                }
                
                dq.offerLast(r);
                
                if(r >= h - 1){
                    int a = cols[r - h + 1][c] = rows[dq.peekFirst()][c];
                    if(a >= result){
                        if(result == a && r - h + 1 >= answer[0] && c >= answer[1]){
                            continue;
                        }
                        result = a;
                        answer[0] = r - h + 1;
                        answer[1] = c;
                    }
                }
            }
        }
        // print(cols, m - h + 1, n - w + 1);
        
        
        return answer;
    }
}