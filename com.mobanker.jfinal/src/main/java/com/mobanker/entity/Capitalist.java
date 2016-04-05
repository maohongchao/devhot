package com.mobanker.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mobanker.conf.BaseModel;

/**
 * <b>Package Name:</b> com.mobanker.entity <br/>
 * <b>Description:</b>〈理财人实体类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月19日
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Capitalist extends BaseModel<Capitalist>
{
    private static final long serialVersionUID = 1L;

    /**
     * 理财人姓名
     */
    private String            uname;
    /**
     * 理财人ID
     */
    private String            userId;
    /**
     * 理财人类型
     */
    private String            userType;
    /**
     * 手机号
     */
    private String            phone;
    /**
     * 用户状态
     */
    private Integer           ustatus;
    /**
     * 身份证号
     */
    private String            cardid;
    /**
     * 理财优先级
     */
    private Integer           sort;
    /**
     * 开户名
     */
    private String            accountName;
    /**
     * 银行卡号
     */
    private String            bankNumber;
    /**
     * 开户行
     */
    private String            bankName;
    
}
