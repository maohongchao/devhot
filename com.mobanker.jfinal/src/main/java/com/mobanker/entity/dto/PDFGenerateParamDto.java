package com.mobanker.entity.dto;

import lombok.Data;

/**
 * <b>Package Name:</b> com.mobanker.entity.dto <br/>
 * <b>Description:</b>〈生成PDF借款协议参数定义〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月26日
 * @version v1.0.0
 */
@Data
public class PDFGenerateParamDto
{
    private String  borrowNid;

    private int     key_vk;

    private String  tenderName;

    private String  tenderID;

    private String  tenderPhone;

    private boolean tenderPhoneEnable;

    private boolean userIDConcealed;

    private boolean userPhoneConcealed;

    private boolean userDebitcardConcealed;

    private String  savePath;
}
