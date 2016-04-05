package com.mobanker.intf.impl;

import com.mobanker.dao.ActivityDao;
import com.mobanker.entity.Activity;
import com.mobanker.intf.ActivityIntf;

public class ActivityIntfImpl implements ActivityIntf
{
    private ActivityDao dao;
    
    public ActivityIntfImpl()
    {
        dao = new ActivityDao();
    }
    
    @Override
    public Activity getById(String id)
    {
        return dao.getById(id);
    }

}
