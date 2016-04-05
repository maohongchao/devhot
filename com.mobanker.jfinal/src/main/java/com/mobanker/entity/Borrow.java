package com.mobanker.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mobanker.conf.BaseModel;
import com.mobanker.enums.BorrowStatus;
import com.mobanker.enums.BorrowType;
import com.mobanker.enums.FrontEndType;
import com.mobanker.enums.ProductType;

/**
 * <b>Package Name:</b> com.mobanker.entity <br/>
 * <b>Description:</b>〈借款信息实体类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Borrow extends BaseModel<Borrow>
{
    private static final long serialVersionUID = 1L;
    
    /**
     * 借款原始记录id
     */
    private String            borrowOriginalId;
    /**
     * 借款客户姓名
     */
    private String            customerName;
    /**
     * 借款客户id
     */
    private String            userId;
    /**
     * 标题
     */
    private String            name;
    /**
     * 借款状态 :
     * PENDING("待放款"), PROCESSING("放款处理中"), FAILURE("放款失败"),
     * REFUSE("拒绝放款"), REPAYING("待还款"), LATE_REPAYING("逾期未还清"),
     * LATE_END("逾期已还款"), FINISHED("还款完成")
     */
    private BorrowStatus      status;
    /**
     * 借款客户联系电话
     */
    private String            phone;
    /**
     * 借款总金额
     */
    private BigDecimal        account;
    /**
     * 借款号
     */
    private String            borrowNid;
    /**
     * 借款总期数
     */
    private Integer           borrowPeriod;
    /**
     * 每期天数
     */
    private Integer           periodDays;
    /**
     * 借款利率(年华利率)
     */
    private BigDecimal        borrowApr;
    /**
     * 年计息天数
     */
    private BigDecimal        aprDays;
    /**
     * 借款时间
     */
    private Date              borrowTime;
    /**
     * 借款成功时间
     */
    private Date              borrowSuccessTime;
    /**
     * 应还款总额
     */
    private BigDecimal        repayAccountAll;
    /**
     * 总还款利息
     */
    private BigDecimal        repayAccountInterest;
    /**
     * 总还款本金
     */
    private BigDecimal        repayAccountCapital;
    /**
     * 已还款总额
     */
    private BigDecimal        repayAccountYes;
    /**
     * 已还款利息
     */
    private BigDecimal        repayAccountInterestYes;
    /**
     * 已还款本金
     */
    private BigDecimal        repayAccountCapitalYes;
    /**
     * 未还款总额
     */
    private BigDecimal        repayAccountWait;
    /**
     * 未还款利息
     */
    private BigDecimal        repayAccountInterestWait;
    /**
     * 未还款本金
     */
    private BigDecimal        repayAccountCapitalWait;
    /**
     * 减免金额
     */
    private BigDecimal        exemptionAmount;
    /**
     * 最后还款时间
     */
    private Date              repayLastTime;
    /**
     * 下一笔还款时间
     */
    private Date              repayNextTime;
    /**
     * 下一笔还款金额
     */
    private BigDecimal        repayNextAccount;
    /**
     * 已还款次数
     */
    private Integer           repayTimes;
    /**
     * 逾期利息
     */
    private BigDecimal        lateInterest;
    /**
     * 逾期罚金
     */
    private BigDecimal        lateForfeit;
    /**
     * 逾期崔收费
     */
    private BigDecimal        lateReminder;
    /**
     * 复审人id(财务放款人)
     */
    private String            reverifyUserId;
    /**
     * (财务放款时间)
     */
    private Date              reverifyTime;
    /**
     * 复审备注(财务放款备注)
     */
    private String            reverifyRemark;
    /**
     * 协议号
     */
    private String            sn;
    /**
     * 银行账号
     */
    private String            debitcardNum;
    /**
     * 银行名称
     */
    private String            bankName;
    /**
     * 分配理财人状态:0没分配理财人  3 已被分配给理财人
     */
    private Integer           tenderStatus;
    /**
     * 是否有效借款 0无效 1有效 ;是否有效借款 0无效 1有效
     */
    private Integer           effective;
    /**
     * 失败原因
     */
    private String            reasonFall;
    /**
     * 借款产品:手机贷:shoujidai;大学贷:uzone
     */
    private ProductType       productType;
    /**
     * 产品渠道
     */
    private String            productChannel;
    /**
     * 还款渠道
     */
    private String            repayChannel;
    /**
     * 放款渠道
     */
    private String            loanChannel;
    /**
     * 放款费率
     */
    private BigDecimal        loanFee;
    /**
     * 逾期扣款开始时间:逾期多少天之后执行扣款
     */
    private Integer           fddays;
    /**
     * 前后端收费标识 FRONT:前端收费;END:后端收费
     */
    private FrontEndType      frontEndFeeFlag;
    /**
     * 交易费率
     */
    private BigDecimal        tradingFeeApr;
    /**
     * 固定交易费
     */
    private BigDecimal        tradingFeeFixed;
    /**
     * '借款类型  普通借款:NORMAL  小额贷:MICROLOANS' 现金分期:PERIODLOAN
     */
    private BorrowType        borrowType;
    /**
     * 是否测试数据 1是测试 0非测试数据
     */
    private Integer           isTest;
    /**
     * 白名单标志1是白名单
     */
    private String            flowflag;
    /**
     * 逾期天数
     */
    private Long              lateDays;
    /**
     * app版本
     */
    private String            appVersion;
    
}
