package com.mobanker.biz;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mobanker.entity.Borrow;
import com.mobanker.entity.dto.PDFGenerateParamDto;
import com.mobanker.enums.AgreementType;
import com.mobanker.enums.ProductType;
import com.mobanker.intf.BorrowFeeIntf;
import com.mobanker.intf.BorrowIntf;
import com.mobanker.intf.impl.BorrowFeeIntfImpl;
import com.mobanker.intf.impl.BorrowIntfImpl;
import com.mobanker.util.DateUtils;
import com.mobanker.util.MoneyConvetor;
import com.mobanker.util.PDFGenerator;

/**
 * <b>Package Name:</b> com.mobanker.main <br/>
 * <b>Description:</b>〈APP主入口类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月17日
 * @version v1.0.0
 */
public class PdfGenerateManager
{
    private final Logger   logger         = Logger.getLogger(getClass());

    private BorrowIntf     borrowIntf     = new BorrowIntfImpl();
    private BorrowFeeIntf  borrowFeeIntf  = new BorrowFeeIntfImpl();

    public void generateAgreementPDF(PDFGenerateParamDto paramDto)
    {
        try
        {
            Borrow borrow = borrowIntf.getRemoteBorrowByBorrowNid(paramDto.getBorrowNid());
            if (borrow != null)
            {
                String idNo = borrowIntf.getRemoteIDByUserId(borrow.getUserId());
                Map<String, String> params = new HashMap<String, String>();
                params.put("userName", paramDto.getTenderName()); // 甲方姓名
                params.put("userIDCard", paramDto.getTenderID()); // 甲方身份证号码
                if (paramDto.isTenderPhoneEnable())
                {
                    params.put("userPhone", paramDto.getTenderPhone()); // 甲方电话
                }else
                {
                    params.put("userPhone", "__________");
                }
                params.put("customerName", borrow.getCustomerName());// 客户姓名
                // 可选择行隐藏用户身份证号、手机号、银行卡账号
                if (paramDto.isUserDebitcardConcealed())
                {
                    String debitCardNum = borrow.getDebitcardNum();
                    params.put("customerBankNumber",
                            debitCardNum.replace(debitCardNum.substring(4, debitCardNum.length() - 4), "***********"));
                }
                else
                {
                    params.put("customerBankNumber", borrow.getDebitcardNum()); // 客户开户银行帐号
                }
                if (paramDto.isUserIDConcealed())
                {
                    params.put("customerIDCard", idNo.replace(idNo.substring(4, idNo.length() - 4), "**********"));
                }
                else
                {
                    params.put("customerIDCard", idNo);// 客户身份证号码
                }
                if (paramDto.isUserPhoneConcealed())
                {
                    String customePhone = borrow.getPhone();
                    params.put("customerPhone",
                            customePhone.replace(customePhone.substring(3, customePhone.length() - 3), "*****"));
                }
                else
                {
                    params.put("customerPhone", borrow.getPhone());// 客户电话
                }

                params.put("customerBankName", borrow.getBankName());// 客户开户银行
                // 大写借款本金
                params.put("upperAmount", MoneyConvetor.transFormMoney(borrow.getAccount().doubleValue()));
                // 还款日期
                params.put("repayLastTime", DateUtils.convert(borrow.getRepayLastTime(), DateUtils.DATE_CH_FORMAT));
                // 借款天数
                params.put("borrowDays", borrow.getPeriodDays().intValue() * borrow.getBorrowPeriod().intValue() + "");
                BigDecimal amountAll = borrow.getAccount().add(
                        borrowFeeIntf.getRemoteBorrowFeeByNid(paramDto.getBorrowNid()));
                // 客户还款金额
                params.put("repayAmountAll", amountAll.toString());
                // 当前日期
                params.put("now", DateUtils.convert(borrow.getBorrowSuccessTime(), DateUtils.DATE_CH_FORMAT));
                // 借款时间
                params.put("borrowSuccessTime",
                        DateUtils.convert(borrow.getBorrowSuccessTime(), DateUtils.DATE_CH_FORMAT));
                // 大写客户金额
                params.put("upperRepayAmountAll", MoneyConvetor.transFormMoney(amountAll.doubleValue()));
                // 生成临时PDF文件
                if (ProductType.shoujidai.equals(borrow.getProductType()))
                {
                    String pdfName = paramDto.getSavePath() + "\\借款协议-" + borrow.getCustomerName() + ".pdf";
                    PDFGenerator.generatePDF(params, AgreementType.SHOUJIDAI_BORROW, pdfName);
                }else if (ProductType.uzone.equals(borrow.getProductType()))
                {
                    String pdfName = paramDto.getSavePath() + "\\借款协议(U)-" + borrow.getCustomerName() + ".pdf";
                    PDFGenerator.generatePDF(params, AgreementType.UZONE_BORROW, pdfName);
                }
            }
            else
            {
                logger.error("------>generateAgreementPDF() Can't find borrow info by borrowNid["
                        + paramDto.getBorrowNid() + "] !");
            }
        }
        catch (Exception e)
        {
            logger.error("------>generateAgreementPDF() ERROR "+e);
            StackTraceElement[] stes = e.getStackTrace();
            for (StackTraceElement ste : stes)
            {
                logger.error(ste.toString());
            }
        }
    }
}
