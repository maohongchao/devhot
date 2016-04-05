package com.mobanker.ui.task;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import lombok.Setter;
import lombok.ToString;

import com.jfinal.kit.PropKit;
import com.mobanker.biz.PdfGenerateManager;
import com.mobanker.conf.AppConstants;
import com.mobanker.entity.dto.PDFGenerateParamDto;

/**
 * <b>Package Name:</b> com.mobanker.ui.task <br/>
 * <b>Description:</b>〈PDF借款协议文件生成进度任务类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月26日
 * @version v1.0.0
 */
@Setter
@ToString
public class ProgressTask extends SwingWorker<Void, Void>
{
    private JButton             generateBtn        = null;
    private List<String>        borrowNids         = null;
    private PdfGenerateManager  pdfGenerateManager = new PdfGenerateManager();
    private PDFGenerateParamDto paramDto           = null;

    public ProgressTask(JButton generateBtn, List<String> borrowNids)
    {
        this.generateBtn = generateBtn;
        this.borrowNids = borrowNids;
    }

    /*
     * Main task. Executed in background thread.
     */
    @Override
    public Void doInBackground()
    {
        int progress = 0;
        setProgress(0);
        String[] nameStrings = PropKit.get(AppConstants.CON_TENDER_NAMES).split(",");
        String[] IDStrings = PropKit.get(AppConstants.CON_TENDER_ID).split(",");
        for (String borrowNid : borrowNids)
        {
            paramDto.setBorrowNid(borrowNid);
            // 如果选择的是随机选定理财人
            if (paramDto.getKey_vk() == KeyEvent.VK_B)
            {
                int index = ((int) (Math.random() * 10)) % (nameStrings.length - 1) + 1;
                paramDto.setTenderName(nameStrings[index]);
                paramDto.setTenderID(IDStrings[index]);
            }
            pdfGenerateManager.generateAgreementPDF(paramDto);
            progress += 1;
            setProgress(Math.min(progress, borrowNids.size()));
        }
        return null;
    }

    /*
     * Executed in event dispatching thread
     */
    @Override
    public void done()
    {
        Toolkit.getDefaultToolkit().beep();
        generateBtn.setEnabled(true);
    }
}
