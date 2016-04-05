package com.mobanker.enums;

/**
 * <b>Package Name:</b> com.mobanker.enums <br/>
 * <b>Description:</b>〈借款费用类型〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public enum BorrowFeeType
{
    LATE_APR("逾期利率"),
    LATE_REMINDER("逾期崔收费"),
    APPROVE_FEE("审核费用"),
    TRADING_FEE("交易手续费率"),
    TRADING_FEE_FIXED("固定交易手续费");

    private String type;

    private BorrowFeeType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
