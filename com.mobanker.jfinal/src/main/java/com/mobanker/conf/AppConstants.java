package com.mobanker.conf;

/**
 * <b>Package Name:</b> com.mobanker.conf <br/>
 * <b>Description:</b>〈系统常量类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public interface AppConstants
{
    /**
     * 借款第三方协议文件上传的bucket
     */
    String CON_BORROW_AGREEMENT_BUCKET  = "con_borrow_agreement_bucket";
    /**
     * 上传文件到阿里云的终端地址
     */
    String CON_ALIYUN_END_POINT         = "con_aliyun_end_point";
    /**
     * 访问阿里云的密钥ID
     */
    String CON_ALIYUN_ACCESS_KEY_ID     = "con_aliyun_access_key_id";
    /**
     * 访问阿里云的密钥加密值
     */
    String CON_ALIYUN_ACCESS_KEY_SECRET = "con_aliyun_access_key_secret";
    /**
     * 借款协议理财人姓名
     */
    String CON_TENDER_NAMES             = "con.agreement.tender.names";
    /**
     * 借款协议理财人身份证
     */
    String CON_TENDER_ID                = "con.agreement.tender.IDs";
    /**
     * 借款协议理财人电话
     */
    String CON_TENDER_PHONE             = "con.agreement.tender.phones";
    /**
     * 财务系统生产环境接口调用前缀
     */
    String CON_FINACIAL_PRE_URL         = "con.finacial.pre.url";
    /**
     * 业务系统生成环境接口调用前缀
     */
    String CON_TKJAPI_PRE_URL           = "con.tkj_api.pre.url";

}
