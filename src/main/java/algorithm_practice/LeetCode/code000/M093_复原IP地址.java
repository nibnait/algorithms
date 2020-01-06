package algorithm_practice.LeetCode.code000;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

示例:

输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]

https://leetcode-cn.com/explore/interview/card/bytedance/242/string/1044/
https://leetcode-cn.com/problems/restore-ip-addresses/
Created by nibnait on 2020-01-06
*/
public class M093_复原IP地址 extends TestCase {

    @Test
    public void testCase() {
        String s1 = "25525511135";
        System.out.println(restoreIpAddresses(s1));

        String s2 = "25525511135";
        System.out.println(restoreIpAddresses(s2));

    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }

        restoreIpAddresses(s, 0, "", result);

        return result;
    }

    private void restoreIpAddresses(String s, int n, String ipAddress, List<String> result) {
        if (n == 4) {
            if (s.isEmpty()) {
                result.add(ipAddress);
            }
            return;
        }

        for (int segLength = 1; segLength <= 3; segLength++) {
            if (s.length() < segLength) {
                break;
            }
            String currentSeg = s.substring(0, segLength);
            int value = Integer.valueOf(currentSeg);
            if (!isIpSegmentNum(value, segLength)) {
                continue;
            }
            restoreIpAddresses(s.substring(segLength), n + 1, ipAddress + currentSeg + (n == 3 ? "" : "."), result);
        }
    }

    private boolean isIpSegmentNum(int value, int length) {
        return String.valueOf(value).length() == length
                && value <= 255;
    }

}