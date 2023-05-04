package top.zhexian.common.tools;

import java.util.Random;

public class InventCode {
    public static String getCode() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int cur = r.nextInt(36);
            if (cur >= 10) {
                sb.append((char) ('A' + cur - 10));
            } else {
                sb.append(cur);
            }
        }
        return sb.toString();
    }
}
