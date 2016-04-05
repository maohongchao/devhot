package com.mobanker.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.mobanker.entity.Borrow;

/**
 * <b>Package Name:</b> com.mobanker.dao <br/>
 * <b>Description:</b>〈借款数据访问类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public class BorrowDao
{
    private final Borrow dao = new Borrow();

    /**
     * <b>Description:</b>〈根据借款单号查询借款对象信息〉<br/>
     * @param borrowNid 借款单号
     * @return Borrow
     * @author hongchaoMao <br/>
     * Create date: 2016年2月18日
     */
    public Borrow getBorrowByNid(String borrowNid)
    {
        String sql = "SELECT * FROM T_BORROW where BORROW_NID = ?";
        List<Borrow> list = dao.find(sql, borrowNid);
        if (CollectionUtils.isNotEmpty(list))
        {
            return list.get(0);
        }
        return null;
    }
}
