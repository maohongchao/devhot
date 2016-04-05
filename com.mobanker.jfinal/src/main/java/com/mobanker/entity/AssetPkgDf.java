package com.mobanker.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mobanker.conf.BaseModel;
import com.mobanker.enums.ProductType;

/**
 * <b>Package Name:</b> com.mobanker.entity <br/>
 * <b>Description:</b>〈资产包代付记录实体类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AssetPkgDf extends BaseModel<AssetPkgDf>
{
    private static final long serialVersionUID = 1L;

    /**
     * 标的号
     */
    private String            bidNo;
    /**
     * 借款号
     */
    private String            borrowNid;
    /**
     * 产品类型借款产品(手机贷:shoujidai;大学贷:uzone)
     */
    private ProductType       productType;
    /**
     * 借款总期数
     */
    private Integer           borrowPeriod;
    /**
     * 借款每期天数
     */
    private Integer           periodDays;
    /**
     * 订单号
     */
    private String            billNo;
    /**
     * 代付金额
     */
    private BigDecimal        dfAmount;
    /**
     * 资产包累加金额
     */
    private BigDecimal        dfAdditive;
    /**
     * 代付成功时间
     */
    private Date              dfSuccessTime;
    /**
     * SUCCESS("放款成功");EXPIRE("资产包到期")
     */
    private String            status;
}
