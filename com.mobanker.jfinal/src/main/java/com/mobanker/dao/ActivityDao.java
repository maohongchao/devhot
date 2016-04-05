package com.mobanker.dao;

import com.mobanker.entity.Activity;

public class ActivityDao
{
    public final Activity dao = new Activity();
    
    public Activity getById(String id)
    {
        return dao.findById(id);
    }
}
