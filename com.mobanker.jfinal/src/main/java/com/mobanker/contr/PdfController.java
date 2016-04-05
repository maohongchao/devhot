package com.mobanker.contr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.mobanker.biz.PdfGenerateManager;
import com.mobanker.entity.dto.PDFGenerateParamDto;
import com.mobanker.util.HttpClientUtils;

/**
 * <b>Package Name:</b> com.mobanker.contr <br/>
 * <b>Description:</b>〈PDF生成控制器类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月3日
 * @version v1.0.0
 */
public class PdfController extends Controller
{
    private PdfGenerateManager generateManager = new PdfGenerateManager();

    /**
     * <b>Description:</b>〈PDF借款协议生成接口〉<br/>
     * @author hongchaoMao <br/>
     * Create date: 2016年2月19日
     */
    public void generate()
    {
        String msg = "<font color='green'>PDF借款协议生成成功!</font>";

        PDFGenerateParamDto paramDto = new PDFGenerateParamDto();
        paramDto.setTenderName(getPara("tender"));
        paramDto.setTenderID(getPara("tenderID"));
        paramDto.setTenderPhone(getPara("tenderPhone"));
        paramDto.setSavePath(getPara("path"));

        String nids = getPara("borrowNids");

        List<String> borrowNids = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new StringReader(nids)))
        {
            String nid = null;
            while ((nid = br.readLine()) != null)
            {
                borrowNids.add(nid);
            }
            for (String borrowNid : borrowNids)
            {
                paramDto.setBorrowNid(borrowNid);
                generateManager.generateAgreementPDF(paramDto);
            }
        }
        catch (Exception e)
        {
            msg = "<font color='red'>" + Arrays.toString(e.getStackTrace()) + "</font>";
        }
        renderHtml(msg);
    }

    public static void main(String[] args) throws IOException
    {
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-uid", "finance");
        Map<String, String> uParams = new HashMap<String, String>();
        uParams.put("borrowNid", "201602210002157334");
        uParams.put("type", "1");
        String reqResult = HttpClientUtils.doGet("http://10.8.0.1:8080/tkj_api/api/5.0.0/borrow/getBorrowOriginal",
                uParams);
        System.err.println(reqResult);
        String data = JSONObject.parseObject(reqResult).getString("data");
        JSONObject dataObj = JSONObject.parseObject(data);
        String verifyTime = dataObj.getString("verifyTime");
        String agreementTime = dataObj.getString("agreementTime");
        System.out.println(verifyTime + "," + agreementTime);
    }

}
