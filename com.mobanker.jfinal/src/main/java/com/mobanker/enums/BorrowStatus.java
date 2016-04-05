package com.mobanker.enums;

/**
 * <b>Package Name:</b> com.mobanker.enums <br/>
 * <b>Description:</b>〈借款状态〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public enum BorrowStatus
{
    PENDING("待放款"), PROCESSING("放款处理中"), SUCCESS("放款成功"), 
    FAILURE("放款失败"), REFUSE("拒绝放款"), TIMEOUT("放款超时"), 
    LATE_REPAYING("逾期未还清"), SOLVED_END("坏账已处理"), LATE_END("逾期已还款"), 
    FINISHED("还款完成");
    
    private String type;

    private BorrowStatus(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return type;
    }
}
