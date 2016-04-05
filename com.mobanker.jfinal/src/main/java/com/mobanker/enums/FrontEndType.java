package com.mobanker.enums;

/**
 * <b>Package Name:</b> com.mobanker.enums <br/>
 * <b>Description:</b>〈收费类型〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public enum FrontEndType
{
    FRONT("前端收费"), END("后端收费");

    private String type;

    private FrontEndType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return type;
    }
}
