package com.mobanker.enums;


/**
 * <b>Package Name:</b> com.mobanker.enums <br/>
 * <b>Description:</b>〈借款单类型〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public enum BorrowType
{

    NORMAL("普通借款", "6"), MICROLOANS("小额贷", "7"), PERIODLOAN("现金分期", "8");
    
    public String getBusinessType()
    {
        return businessType;
    }

    public void setBusinessType(String businessType)
    {
        this.businessType = businessType;
    }

    private String type;

    private String businessType;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    private BorrowType(String type, String typeInt)
    {
        this.type = type;
        this.businessType = typeInt;
    }

    private BorrowType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return type;
    }

    public String getTypeInt(String name)
    {
        BorrowType[] bts = BorrowType.values();
        for (BorrowType bt : bts)
        {
            if (bt.name().equals(name))
            {
                return bt.businessType;
            }
        }
        return "";

    }

    public String getTypeInt()
    {
        return businessType;
    }

}
