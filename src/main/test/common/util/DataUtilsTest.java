package common.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 * Created by nibnait on 2022/03/29
 */
@Slf4j
public class DataUtilsTest {

    @Test
    public void difference() {
        List<Integer> list1 = Lists.newArrayList(1);
        List<Integer> list2 = Lists.newArrayList(0);

        System.out.println(DataUtils.difference(list1, list2));
    }

}
