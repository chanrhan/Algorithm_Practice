import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int calc(char[] cmds, String[] list) {
//        if (list == null) {
//            return -1;
//        }
        boolean isReversed = false;
        int start = 0;
        int end = list != null ? list.length - 1 : -1;
        for (char cmd : cmds) {
            if (cmd == 'R') {
                isReversed = !isReversed;
            } else if (cmd == 'D') {
                if (start > end) {
                    return -1;
                }
                if (isReversed) {
                    list[end--] = null;
                } else {
                    list[start++] = null;
                }
            }
//            if (isReversed) {
//                printReverse(cmd, list);
//            } else {
//                print(cmd, list);
//            }
        }

        return isReversed ? 1 : 0;
    }

    public static void print(char cmd, String[] list) {
        System.out.printf("(%c) [", cmd);
        for (int i = 0; i < list.length; ++i) {
            if (list[i] == null) {
                continue;
            }
            System.out.printf("%s", list[i]);
            if (i < list.length - 1 && list[i + 1] != null) {
                System.out.print(",");
            }
        }
        System.out.print("]\n");
    }

    public static void printReverse(char cmd, String[] list) {
        System.out.printf("(%c) [", cmd);
        for (int i = list.length - 1; i >= 0; --i) {
            if (list[i] == null) {
                continue;
            }
            System.out.printf("%s", list[i]);
            if (i > 0 && list[i - 1] != null) {
                System.out.print(",");
            }
        }
        System.out.print("]\n");
    }

    public static void main(String[] args) throws IOException {
        final int T = Integer.parseInt(br.readLine());

        char[] cmds;
        int n;
        String init;
        String[] list;
        for (int t = 0; t < T; ++t) {
            cmds = br.readLine().toCharArray();
            n = Integer.parseInt(br.readLine());
            init = br.readLine();
            if (n > 0) {
                list = init.substring(1, init.length() - 1).split(",");
            } else {
                list = null;
            }
            int rst = calc(cmds, list);

            if (rst >= 0) {
                if (list == null) {
                    bw.write("[]\n");
                } else {
                    bw.write("[");
                    if (rst == 0) {
                        for (int i = 0; i < list.length; ++i) {
                            if (list[i] == null) {
                                continue;
                            }
                            bw.write(list[i]);
                            if (i < list.length - 1 && list[i + 1] != null) {
                                bw.write(",");
                            }
                        }
                    } else {
                        for (int i = list.length - 1; i >= 0; --i) {
                            if (list[i] == null) {
                                continue;
                            }
                            bw.write(list[i]);
                            if (i > 0 && list[i - 1] != null) {
                                bw.write(",");
                            }
                        }
                    }
                    bw.write("]\n");
                }
            } else {
                bw.write("error\n");
            }
        }

        bw.close();
        br.close();
    }
}