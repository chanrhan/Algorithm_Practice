class Solution {
    public int[] solution(int[] seq, int k) {
        int n = seq.length;
        int left = 0;
        long sum = 0;                 // k가 10억, 원소 합이 int 넘을 수 있으니 long
        int bestLeft = 0, bestRight = n; // 길이(bestRight - bestLeft)를 크게 초기화

        for (int right = 0; right < n; right++) {
            sum += seq[right];
            // 합이 k를 초과하면 왼쪽을 당겨 윈도우 축소
            while (sum > k) {
                sum -= seq[left++];
            }
            // 합이 정확히 k면 후보
            if (sum == k) {
                if (right - left < bestRight - bestLeft) {
                    bestLeft = left;
                    bestRight = right;
                }
            }
        }
        return new int[] { bestLeft, bestRight };
    }
}