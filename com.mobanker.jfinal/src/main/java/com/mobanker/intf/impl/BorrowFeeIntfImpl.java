package com.mobanker.intf.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.mobanker.conf.AppConstants;
import com.mobanker.dao.BorrowFeeDao;
import com.mobanker.entity.BorrowFee;
import com.mobanker.intf.BorrowFeeIntf;
import com.mobanker.util.HttpClientUtils;

/**
 * <b>Package Name:</b> com.mobanker.intf.impl <br/>
 * <b>Description:</b>〈借款费用接口实现类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public class BorrowFeeIntfImpl implements BorrowFeeIntf
{
    private final Logger logger = Logger.getLogger(getClass());
    private BorrowFeeDao dao    = new BorrowFeeDao();

    /**
     * 根据 borrowNid 获取所有交易费用(不包括交易逾期利率和逾期滞纳金)
     * @param borrowNid 为空返回null
     * @return List<BorrowFee>
     * @author hongchaoMao <br/>
     * Create date: 2016年2月17日
     * @see com.mobanker.intf.BorrowFeeIntf#getByBorrowNidExceptLateArp(java.lang.String)
     */
    @Override
    public List<BorrowFee> getByBorrowNidExceptLateArp(String borrowNid)
    {
        if (StringUtils.isBlank(borrowNid))
        {
            return null;
        }
        return dao.getByBorrowNidExceptLateArp(borrowNid);
    }

    /**
     * <b>Description:</b>〈根据borrowNid远程查询借款单所需各项费用之和〉<br/>
     * @param borrowNid 借款单号
     * @return BigDecimal 借款费用
     * @author hongchaoMao <br/>
     * Create date: 2016年2月29日
     * @see com.mobanker.intf.BorrowFeeIntf#getRemoteBorrowFeeByNid(java.lang.String)
     */
    @Override
    public BigDecimal getRemoteBorrowFeeByNid(String borrowNid)
    {
        if (StringUtils.isBlank(borrowNid))
        {
            return BigDecimal.ZERO;
        }
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-uid", "cw-job");
        try
        {
            HttpClientUtils.setHeader(header);
            String reqResult = HttpClientUtils.doGet(
                    PropKit.get(AppConstants.CON_FINACIAL_PRE_URL) + "/borrow/getBorrowFee/" + borrowNid, null);
            String data = JSONObject.parseObject(reqResult).getString("data");
            if (StringUtils.isNotBlank(data))
            {
                return new BigDecimal(data);
            }
        }
        catch (Exception e)
        {
            logger.error("------>getRemoteBorrowFeeByNid() Requst '" + PropKit.get(AppConstants.CON_FINACIAL_PRE_URL)
                    + "/borrow/getBorrowFee/" + "' ERROR " + e);
            StackTraceElement[] stes = e.getStackTrace();
            for (StackTraceElement ste : stes)
            {
                logger.error(ste.toString());
            }
        }
        return BigDecimal.ZERO;
    }

}
