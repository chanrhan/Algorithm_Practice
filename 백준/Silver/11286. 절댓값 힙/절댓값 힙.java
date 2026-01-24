import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static class Heap {
        private int[] arr;
        private int capacity = 4;
        private int size = 0;

        public Heap() {
            arr = new int[capacity];
        }

        private void increaseCapacity() {
            capacity = capacity * 2;
            arr = Arrays.copyOf(arr, capacity);
        }

        public void add(int n) {
            if (size == 0) {
                arr[size++] = n;
                return;
            }
            if (size == capacity) {
                increaseCapacity();
            }
            arr[size] = n;
            up();
            ++size;
        }

        public int top() {
            if (size == 0) {
                return 0;
            }
            int top = arr[0];
            arr[0] = arr[size - 1];
            arr[size - 1] = 0;
            --size;
            down();
//            System.out.println("top: " + top);
            return top;
        }

        private void down() {
            int curr = 0, next, left, right;
            while (curr < size) {
                next = curr;
                left = curr * 2 + 1;
                right = curr * 2 + 2;
                if (left < size && compare(next, left)) {
                    next = left;
                }
                if (right < size && compare(next, right)) {
                    next = right;
                }
                if (next == curr) {
                    break;
                }
                swap(curr, next);
                curr = next;
            }
        }

        private void up() {
            int curr = size, next, parent;
            while (curr > 0) {
                next = curr;
                parent = (curr - 1) / 2;
                if (compare(parent, next)) {
                    next = parent;
                }
                if (next == curr) {
                    break;
                }
                swap(curr, next);
                curr = next;
            }
        }

        private boolean compare(int a, int b) {
            int diff = Math.abs(arr[a]) - Math.abs(arr[b]);
//            System.out.printf("diff [%d, %d] : %d\n", arr[a], arr[b], diff);
            if (diff == 0) {
                return arr[a] > arr[b];
            }
            return diff > 0;
        }

        private void swap(int a, int b) {
//            System.out.printf("swap ([%d]%d, [%d]%d)\n", a, arr[a], b, arr[b]);
            int tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }

        @Override
        public String toString() {
            return Arrays.toString(arr);
        }
    }

    public static void main(String[] _s) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        Heap heap = new Heap();
        int n;
        for (int i = 0; i < N; ++i) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                bw.write(Integer.toString(heap.top()) + '\n');
            } else {
                heap.add(n);
            }
//            System.out.println(heap);
        }

        bw.close();
        br.close();
    }
}
