package zzzTest;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Long> userIds = new ArrayList<>();
        userIds.add(123456789012345l);
        userIds.add(2l);
        userIds.add(3l);
        List<Integer> userIdList = new ArrayList<>();
        userIds.forEach(item -> {userIdList.add(item.intValue());});
        System.out.println(userIdList);
    }
}

