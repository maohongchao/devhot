package com.mobanker.intf.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.mobanker.conf.AppConstants;
import com.mobanker.entity.Borrow;
import com.mobanker.intf.BorrowIntf;
import com.mobanker.util.HttpClientUtils;

/**
 * <b>Package Name:</b> com.mobanker.intf.impl <br/>
 * <b>Description:</b>〈借款接口类实现累〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public class BorrowIntfImpl implements BorrowIntf
{
    private final Logger logger = Logger.getLogger(getClass());

    /**
     * <b>Description:</b>〈根据borrowNid远程查询借款信息〉<br/>
     * @param borrowNid 借款单号
     * @return Borrow
     * @author hongchaoMao <br/>
     * Create date: 2016年2月17日
     * @see com.mobanker.intf.BorrowIntf#getBorrowByBorrowNid(java.lang.String)
     */
    @Override
    public Borrow getRemoteBorrowByBorrowNid(String borrowNid)
    {
        if (StringUtils.isBlank(borrowNid))
        {
            return null;
        }
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-uid", "cw-job");
        Borrow borrow = null;
        try
        {
            HttpClientUtils.setHeader(header);
            String reqResult = HttpClientUtils.doGet(
                    PropKit.get(AppConstants.CON_FINACIAL_PRE_URL) + "/borrow/getByBorrowNid/" + borrowNid, null);
            String data = JSONObject.parseObject(reqResult).getString("data");
            borrow = JSONObject.parseObject(data, Borrow.class);
            return borrow;
        }
        catch (Exception e)
        {
            logger.error("------>getRemoteBorrowByBorrowNid() Request '"
                    + PropKit.get(AppConstants.CON_FINACIAL_PRE_URL) + "/borrow/getByBorrowNid/" + "' ERROR " + e);
            StackTraceElement[] stes = e.getStackTrace();
            for (StackTraceElement ste : stes)
            {
                logger.error(ste.toString());
            }
        }
        return null;
    }

    /**
     * <b>Description:</b>〈方法详细描述〉<br/>
     * @param userId
     * @return
     * @author hongchaoMao <br/>
     * Create date: 2016年2月29日
     * @see com.mobanker.intf.BorrowIntf#getRemoteIDByUserId(java.lang.String)
     */
    @Override
    public String getRemoteIDByUserId(String userId)
    {
        if (StringUtils.isBlank(userId))
        {
            return null;
        }
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-uid", "cw-job");
        Map<String, String> uParams = new HashMap<String, String>();
        uParams.put("userId", userId);

        try
        {
            String reqResult = HttpClientUtils.doGet(
                    PropKit.get(AppConstants.CON_FINACIAL_PRE_URL) + "/borrow/getCustomerInfoByUserId", uParams);
            return JSONObject.parseObject(JSONObject.parseObject(reqResult).getString("data")).getString("idNo");
        }
        catch (Exception e)
        {
            logger.error("------>getRemoteIDByUserId() Requst '" + PropKit.get(AppConstants.CON_FINACIAL_PRE_URL)
                    + "/borrow/getCustomerInfoByUserId" + "' ERROR " + e);
            StackTraceElement[] stes = e.getStackTrace();
            for (StackTraceElement ste : stes)
            {
                logger.error(ste.toString());
            }
        }
        return null;
    }

}
