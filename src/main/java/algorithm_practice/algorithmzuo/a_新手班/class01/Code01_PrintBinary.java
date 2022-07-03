package algorithm_practice.algorithmzuo.a_新手班.class01;

import common.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by nibnait on 2022/06/20
 */
@Slf4j
public class Code01_PrintBinary {

    @Test
    public void testCase() {

        int num = 4;
        print(num);
        print(0);

        print(Integer.MAX_VALUE);
        print(Integer.MIN_VALUE);
        print(Integer.MIN_VALUE + 1);
        print(Integer.MIN_VALUE + 2);
        print(Integer.MIN_VALUE + 3);
        print(Integer.MIN_VALUE + 4);
        print(Integer.MIN_VALUE + 5);

        print(-1);

        print(BigDecimal.valueOf(Math.pow(2, 32)).intValue());
        print(BigDecimal.valueOf(Math.pow(2, 31)).intValue());
        print(BigDecimal.valueOf(Math.pow(2, 30)).intValue());

        SystemUtil.printCuttingLine();

        // 带符号右移 和 不带符号右移
        num = 1024;
        print(num >> 1);
        print(num >>> 1);
        SystemUtil.printCuttingLine();

        // 为什么负数的二进制要取反加1？
        // 为了加法 正+正 与 正+负。。走一套逻辑。
        num = 5;
        print(num);
        print(-num);
        print(~num + 1);
    }

    private void print(int num) {
        System.out.print(num + "\t");
        for (int i = 31; i >= 0; i--) {
            System.out.print( (num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

}
