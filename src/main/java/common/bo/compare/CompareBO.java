package common.bo.compare;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by nibnait on 2021/06/23
 */
@Data
public class CompareBO {

    /**
     * 当前对比的字段
     */
    private String currentField;

    /**
     * 排除字段
     */
    private Set<String> excludeFields = new HashSet<>();

    /**
     * 聚焦字段
     */
    private Set<String> focusFields = new HashSet<>();

    /**
     * 系统默认优化项: pageInfo
     */
    private Set<OptimizationType> optimizations = new LinkedHashSet<>(Collections.singletonList(OptimizationType.IGNORE_NEW_FIELDS));

    @AllArgsConstructor
    public enum OptimizationType {
        /**
         * 优化类型
         */
        PAGE_HELPER("pageHelper 分页插件"),
        IGNORE_NEW_FIELDS("比对时忽略新增字段"),
        IGNORE_MISSING_FIELDS("比对时忽略丢失字段"),
        ;

        String desc;
    }

    public static CompareBO newNoOptimizeCompareBO() {
        CompareBO compareBO = new CompareBO();
        compareBO.setOptimizations(new LinkedHashSet<>());
        return compareBO;
    }

}
