package com.mobanker.intf;

import com.mobanker.entity.Borrow;

/**
 * <b>Package Name:</b> com.mobanker.intf <br/>
 * <b>Description:</b>〈借款接口类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public interface BorrowIntf
{
    /**
     * <b>Description:</b>〈根据borrowNid远程查询借款信息〉<br/>
     * @param borrowNid 借款单号
     * @return Borrow
     * @author hongchaoMao <br/>
     * Create date: 2016年2月17日
     */
    Borrow getRemoteBorrowByBorrowNid(String borrowNid);
    
    /**
     * <b>Description:</b>〈根据userId远程查询用户身份证号〉<br/>
     * @param userId 用户ID
     * @return String 身份证号码
     * @author hongchaoMao <br/>
     * Create date: 2016年2月29日
     */
    String getRemoteIDByUserId(String userId);
}
