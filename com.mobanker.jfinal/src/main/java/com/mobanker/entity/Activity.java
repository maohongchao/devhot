package com.mobanker.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mobanker.conf.BaseModel;

/**
 * <b>Package Name:</b> com.mobanker.entity <br/>
 * <b>Description:</b>〈活动实体类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月3日
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Activity extends BaseModel<Activity>
{
    private static final long serialVersionUID = 1L;

    /**
     * 活动客户名称
     */
    private String            customerName;
    /**
     * 活动客户联系电话
     */
    private String            phone;
    /**
     * 活动客户ID
     */
    private String            userId;
    /**
     * 活动名称
     */
    private String            activityName;
    /**
     * 活动状态
     */
    private String            status;
    /**
     * 活动金额
     */
    private BigDecimal        amount;
    /**
     * 活动号
     */
    private String            activityId;
    /**
     * 活动业务流水号
     */
    private String            activityNid;
    /**
     * 产品类型
     */
    private String            productType;
    /**
     * 产品渠道
     */
    private String            productChannel;
    /**
     * 活动来源渠道
     */
    private String            sysChannel;
    /**
     * 放款渠道
     */
    private String            loanChannel;
    /**
     * 放款费率
     */
    private BigDecimal        loanFee;
    /**
     * 银行账号
     */
    private String            debitcardNum;
    /**
     * 银行名称
     */
    private String            bankName;
    /**
     * 活动时间
     */
    private Date              activityTime;
    /**
     * 活动放款成功时间
     */
    private Date              activitySuccessTime;
    /**
     * 失败原因
     */
    private String            reasonFail;
    /**
     * 备注
     */
    private String            remark;

}
