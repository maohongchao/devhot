package com.mobanker.dao;

import java.util.List;

import com.mobanker.entity.BorrowFee;

/**
 * <b>Package Name:</b> com.mobanker.dao <br/>
 * <b>Description:</b>〈借款费用原子操作类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public class BorrowFeeDao
{
    private final BorrowFee dao = new BorrowFee();

    /**
     * <b>Description:</b>〈根据借款单号获取所有交易费用(不包括交易逾期利率和逾期滞纳金)〉<br/>
     * @param borrowNid 借款单号
     * @return List<BorrowFee>
     * @author hongchaoMao <br/>
     * Create date: 2016年2月18日
     */
    public List<BorrowFee> getByBorrowNidExceptLateArp(String borrowNid)
    {
        String sql = "SELECT T.ID,T.BORROW_NID,T.FEE,T.LATE_APR,T.MIN_DAYS,T.MAX_DAYS,"
                + "T.FEE_TYPE,T.CREATE_TIME,T.CREATE_USER,T.UPDATE_TIME,T.UPDATE_USER "
                + "FROM T_BORROW_FEE T WHERE T.BORROW_NID = ? "
                + "AND T.FEE_TYPE != 'LATE_APR' AND T.FEE_TYPE != 'LATE_REMINDER'";
        return dao.find(sql, borrowNid);
    }

}
