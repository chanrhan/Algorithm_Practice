import java.util.*;

class Solution {
    public class Node{
        int stage;
        int cost;
        int[] hints = new int[16];
        
        public Node(int stage, int cost){
            this.stage = stage;
            this.cost = cost;
            this.hints = new int[16];
        }
        
        public Node(int stage, int cost, int[] h){
            this.stage = stage;
            this.cost = cost;
            this.hints = new int[16];
            for(int i = 0; i < 16; ++i){
                this.hints[i] = h[i];
            }
        }
        
        public Node buyAndClone(int[] h){
            Node newNode = new Node(this.stage, this.cost + h[0], this.hints);
            
            for(int i=1; i < h.length; ++i){
                newNode.hints[h[i] - 1] += 1;
            }
            return newNode;
        }
        
        public int get(int stage){
            return hints[stage];
        }
    }
    
    public int solution(int[][] cost, int[][] hint) {
        int answer = Integer.MAX_VALUE;
        int maxStage = cost.length;
        ArrayDeque<Node> dq = new ArrayDeque<>();
        dq.addLast(new Node(0, 0));
        
        Node curr;
        while(!dq.isEmpty()){
            curr = dq.pollLast();
            
            if(curr.stage == maxStage){
                answer = Math.min(answer, curr.cost);
                continue;
            }
            
            int hintCount = curr.get(curr.stage);
            if(hintCount >= cost[curr.stage].length){
                hintCount = cost[curr.stage].length - 1;
            }
            
            int pay = cost[curr.stage][hintCount]; // 보유한 힌트권 수에 대한 해결 비용
            // System.out.printf("[%d] hint count: %d, pay: %d\n", curr.stage+1, hintCount, pay);
            Node noHintNode = new Node(curr.stage + 1, curr.cost + pay, curr.hints);
            dq.addLast(noHintNode);
            
            if(curr.stage < maxStage - 1){ // 마지막 스테이지는 힌트권이 없으므로 제외
                Node next = noHintNode.buyAndClone(hint[curr.stage]);
                dq.addLast(next); // 현재 스테이지에 대한 힌트권을 구매한 Node 생성
            }
        }
        
        return answer;
    }
}