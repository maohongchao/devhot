package com.mobanker.intf;

import java.math.BigDecimal;
import java.util.List;

import com.mobanker.entity.BorrowFee;

/**
 * <b>Package Name:</b> com.mobanker.intf <br/>
 * <b>Description:</b>〈借款费用接口类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public interface BorrowFeeIntf
{
    /**
     * 根据 borrowNid 获取所有交易费用(不包括交易逾期利率和逾期滞纳金)
     * @param borrowNid
     * @return List<BorrowFee>
     * @author hongchaoMao <br/>
     * Create date: 2016年2月17日
     */
    List<BorrowFee> getByBorrowNidExceptLateArp(String borrowNid);
    
    /**
     * <b>Description:</b>〈根据borrowNid远程查询借款单所需各项费用之和〉<br/>
     * @param borrowNid 借款单号
     * @return BigDecimal 借款费用
     * @author hongchaoMao <br/>
     * Create date: 2016年2月29日
     */
    BigDecimal getRemoteBorrowFeeByNid(String borrowNid);
}
