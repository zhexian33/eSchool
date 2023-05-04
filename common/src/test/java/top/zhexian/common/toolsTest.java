package top.zhexian.common;

import org.junit.Test;
import top.zhexian.common.tools.InventCode;

public class toolsTest {

    @Test
    public void inventCodeTest() {
        for (int i = 0; i < 30; i++) {
            System.out.println(InventCode.getCode());
        }
    }
}
