package com.mobanker.enums;

/**
 * <b>Package Name:</b> com.mobanker.enums <br/>
 * <b>Description:</b>〈产品类型〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public enum ProductType
{
    shoujidai("手机贷"), shoujidai_smallLoan("手机贷-小额贷"), uzone("大学贷"), uzone_smallLoan("大学贷-小额贷");

    private String type;

    private ProductType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return type;
    }
}
