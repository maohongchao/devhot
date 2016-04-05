package com.mobanker.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mobanker.conf.BaseModel;
import com.mobanker.enums.BorrowFeeType;

/**
 * <b>Package Name:</b> com.mobanker.entity <br/>
 * <b>Description:</b>〈借款费用实体类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BorrowFee extends BaseModel<BorrowFee>
{
    private static final long serialVersionUID = 1L;

    private String borrowNid;

    private BigDecimal fee;

    private BigDecimal lateApr;

    private Integer minDays;

    private Integer maxDays;

    private BorrowFeeType feeType;
    
}
