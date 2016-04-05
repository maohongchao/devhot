package com.mobanker.enums;

/**
 * <b>Package Name:</b> com.mobanker.enums <br/>
 * <b>Description:</b>〈协议类型〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public enum AgreementType
{
    /**
     * 手机贷借款协议
     */
    SHOUJIDAI_BORROW("手机贷借款协议"), 
    /**
     * U族借款协议
     */
    UZONE_BORROW("U族借款协议"), 
    /**
     * 手机贷扣款协议
     */
    SHOUJIDAI_WITHHOLDING("手机贷扣款协议"), 
    /**
     * U族扣款协议
     */
    UZONE_WITHHOLDING("U族扣款协议");

    private String type;

    private AgreementType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return type;
    }
}
