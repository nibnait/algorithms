package org.tianbin.excel;

import lombok.Data;

import java.util.Date;

/**
 * Created by nibnait on 2022/01/18
 */
@Data
public class StockDTO {

    private static final long serialVersionUID = -503150452346799617L;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * 可售库存
     */
    private Integer stock;

    /**
     * 冻结库存
     */
    private Integer frozenStock;

    /**
     * 出库库存
     */
    private Integer outStock;

    /**
     * 隔离库存
     */
    private Integer isolateStock;

    /**
     * 采购库存
     */
    private Integer purchaseStock;

    /**
     * 仓库id
     */
    private String warehouseIds;

    /**
     * 预警库存
     */
    private Integer warnStock;

    /**
     * 待下发冻结
     */
    private Integer frozenUndeliveryStock;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 修改时间
     */
    private Date mtime;

    /**
     * 一番赏箱子数量
     */
    private Integer boxNum;


    /**
     * 一番赏箱子数量
     */
    private Integer onsaleBoxNum;
}
