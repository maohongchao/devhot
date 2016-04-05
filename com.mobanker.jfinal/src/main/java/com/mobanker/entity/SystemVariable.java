package com.mobanker.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mobanker.conf.BaseModel;

/**
 * <b>Package Name:</b> com.mobanker.entity <br/>
 * <b>Description:</b>〈系统变量实体类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemVariable extends BaseModel<SystemVariable>
{
    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private String type;

    private String status;

    private String value;

    private String nid;
}
