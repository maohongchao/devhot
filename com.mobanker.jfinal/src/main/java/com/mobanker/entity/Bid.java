package com.mobanker.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mobanker.conf.BaseModel;

/**
 * <b>Package Name:</b> com.mobanker.entity <br/>
 * <b>Description:</b>〈理财标实体类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Bid extends BaseModel<Bid>
{
    private static final long serialVersionUID = 1L;

    /**
     * 标的号
     */
    private String            bidNo;
    /**
     * 标的名称
     */
    private String            bidName;
    /**
     * 标的金额
     */
    private BigDecimal        bidAmount;
    /**
     * 标的可用余额
     */
    private BigDecimal        bidBalance;
    /**
     * 标的冻结金额
     */
    private BigDecimal        bidFrost;
    /**
     * 标的已放款金额
     */
    private BigDecimal        bidLoanAmount;
    /**
     * 招标开始时间
     */
    private Date              inviteStartTime;
    /**
     * 招标结束时间
     */
    private Date              inviteStopTime;
    /**
     * 标的开始时间
     */
    private Date              bidBeginTime;
    /**
     * 标的结束时间
     */
    private Date              bidEndTime;
    /**
     * 实际理财天数
     */
    private Integer           actualDays;
    /**
     * 理财人
     */
    private String            userId;
    /**
     * 标的周期
     */
    private Integer           bidPeriod;
    /**
     * 标的利率
     */
    private BigDecimal        bidApr;
    /**
     * 计息天数
     */
    private Integer           aprDays;
    /**
     * 计息年化天数
     */
    private Integer           aprAnnualizedDays;
    /**
     * 标的利息收益
     */
    private BigDecimal        bidInterest;
    /**
     * 标的本金和利息
     */
    private BigDecimal        bidCapitalInterest;
    /**
     * 标的状态
     */
    private String            status;
    /**
     * 是否虚拟标的
     */
    private Integer           bidFlag;
    /**
     * 标的理财类型
     */
    private String            bidTenderType;
    /**
     * 资产包标的限定金额
     */
    private BigDecimal        pkgLimitAmount;
    /**
     * 充值订单号
     */
    private String            orderNo;

}
