package com.mobanker.intf.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.mobanker.dao.SystemVariableDao;
import com.mobanker.entity.SystemVariable;
import com.mobanker.intf.SystemVariableIntf;

/**
 * <b>Package Name:</b> com.mobanker.intf.impl <br/>
 * <b>Description:</b>〈类型详细描述〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public class SystemVariableIntfImpl implements SystemVariableIntf
{
    private SystemVariableDao systemVariableDao = new SystemVariableDao();
    
    /**
     * 根据nid集合标识查找系统变量
     * @param nids 系统变量Nid集合
     * @return Map<String, String>
     * @author hongchaoMao <br/>
     * Create date: 2016年2月17日
     * @see com.mobanker.intf.SystemVariableIntf#getSys(java.lang.String[])
     */
    @Override
    public Map<String, String> getSys(String... nids)
    {
        if (ArrayUtils.isEmpty(nids))
        {
            return null;
        }
        Map<String, String> ret = new HashMap<String, String>();
        List<SystemVariable> list = systemVariableDao.getSys(nids);
        for (SystemVariable sys : list)
        {
            ret.put(sys.getNid(), sys.getValue());
        }
        return ret;
    }

}
