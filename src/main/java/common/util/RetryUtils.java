package common.util;

import common.bo.BaseResponse;
import common.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * Created by nibnait on 2021/10/21
 */
@Slf4j
public class RetryUtils {

    /**
     * 无返回值的重试 num 次
     * @param runnable 一个匿名函数：() -> xx.xxx
     * @param num 重试次数
     * @param errorMsg 报错信息
     * @param sleepMillis 每次重试间隔的毫秒数
     */
    public static void retryRunnable(Runnable runnable, int num, String errorMsg, Long sleepMillis) {
        boolean isFail = true;
        int count = 0;
        while (isFail && count < num) {
            isFail = false;
            try {
                runnable.run();
            } catch (Exception e) {
                isFail = true;
                count ++ ;
                log.error(DataUtils.format("第 {} 次重试 {}", count, errorMsg), e);
                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException ex) {
                    log.warn(ex.getMessage(), ex);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * 带返回值的重试 num 次
     * @param supplier 一个匿名函数：() -> xx.xxx
     * @param num 重试次数
     * @param errorMsg 报错信息
     * @param sleepMillis 每次重试间隔的毫秒数
     */
    public static BaseResponse retrySupplier(Supplier<BaseResponse> supplier, int num, String errorMsg, Long sleepMillis) {
        boolean isFail = true;
        int count = 0;
        while (isFail && count < num) {
            isFail = false;
            try {
                BaseResponse baseResponse = supplier.get();
                if (ErrorCode.SUCCESS.getCode().equals(baseResponse.code)) {
                    return baseResponse;
                }
            } catch (Exception e) {
                isFail = true;
                count ++ ;
                log.error(DataUtils.format("第 {} 次重试 {}", count, errorMsg), e);
                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException ex) {
                    log.warn(ex.getMessage(), ex);
                    Thread.currentThread().interrupt();
                }
            }
        }

        return new BaseResponse(ErrorCode.SERVICE_ERROR, errorMsg);
    }

}
