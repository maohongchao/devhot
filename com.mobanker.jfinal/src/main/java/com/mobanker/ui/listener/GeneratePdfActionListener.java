package com.mobanker.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Setter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.mobanker.entity.dto.PDFGenerateParamDto;
import com.mobanker.ui.task.ProgressTask;

/**
 * <b>Package Name:</b> com.mobanker.ui.listener <br/>
 * <b>Description:</b>〈生成PDf借款协议按钮事件监听类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月26日
 * @version v1.0.0
 */
@Setter
public class GeneratePdfActionListener implements ActionListener
{
    private final Logger      logger            = Logger.getLogger(getClass());
    private ProgressTask      progressTask      = null;

    private JFrame            parentContainer   = null;

    private JTextArea         borrowNidTextArea = null;
    private ButtonGroup       radioGroup        = null;
    private JComboBox<String> tenderCbx         = null;
    private JTextField        tenderIDText      = null;
    private JTextField        tenderPhoneText   = null;
    private JCheckBox         tenderPhoneCbox   = null;
    private JCheckBox         userIDCbox        = null;
    private JCheckBox         userPhoneCbox     = null;
    private JCheckBox         userDebitcardCbox = null;

    private JTextField        savePathText      = null;
    private JProgressBar      progressBar       = null;
    private JButton           saveBtn           = null;
    private JButton           generateBtn       = null;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!verifyTenderInfo())
            return;
        List<String> borrowNids = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new StringReader(borrowNidTextArea.getText())))
        {
            String nid = null;
            while ((nid = br.readLine()) != null)
            {
                if (StringUtils.isNotBlank(nid))
                {
                    borrowNids.add(nid);
                }
            }
        }
        catch (IOException e2)
        {
            StackTraceElement[] stes = e2.getStackTrace();
            for (StackTraceElement ste : stes)
            {
                logger.error(ste.toString());
            }
        }
        if (CollectionUtils.isEmpty(borrowNids))
        {
            logger.info("------>actionPerformed() INFO : No borrowNids !");
            return;
        }
        progressBar.setMinimum(0);
        progressBar.setMaximum(borrowNids.size());
        progressBar.setValue(0);
        generateBtn.setEnabled(false);
        progressTask = new ProgressTask(this.generateBtn, borrowNids);
        PDFGenerateParamDto paramDto = new PDFGenerateParamDto();
        paramDto.setKey_vk(radioGroup.getSelection().getMnemonic());
        // 如果选择的是手动指定理财人
        if (radioGroup.getSelection().getMnemonic() == KeyEvent.VK_A)
        {
            paramDto.setTenderName(tenderCbx.getSelectedItem().toString());
            paramDto.setTenderID(tenderIDText.getText());
        }
        paramDto.setTenderPhone(tenderPhoneText.getText());
        paramDto.setTenderPhoneEnable(tenderPhoneCbox.isSelected());
        paramDto.setUserIDConcealed(userIDCbox.isSelected());
        paramDto.setUserPhoneConcealed(userPhoneCbox.isSelected());
        paramDto.setUserDebitcardConcealed(userDebitcardCbox.isSelected());
        paramDto.setSavePath(savePathText.getText());
        progressTask.setParamDto(paramDto);

        progressTask.addPropertyChangeListener(new ProgressProChangeListener(progressBar));
        progressTask.execute();
    }

    /**
     * <b>Description:</b>〈理财人信息及保存地址输入校验〉<br/>
     * @return boolean 校验是否通过
     * @author hongchaoMao <br/>
     * Create date: 2016年2月29日
     */
    private boolean verifyTenderInfo()
    {
        String msg = "";
        boolean b = true;
        if (radioGroup.getSelection().getMnemonic() == KeyEvent.VK_A)
        {
            if (StringUtils.isBlank(tenderCbx.getSelectedItem().toString()))
            {
                msg = "请输入正确的理财人名称!";
                b = false;
            }
            else if (StringUtils.isBlank(tenderIDText.getText()))
            {
                msg = "请输入正确的理财人身份证!";
                b = false;
            }
            else if (StringUtils.isBlank(tenderPhoneText.getText()))
            {
                if (tenderPhoneCbox.isSelected())
                {
                    msg = "请输入正确的理财人联系电话!";
                    b = false;
                }
            }
        }
        if (!b)
        {
            JOptionPane.showMessageDialog(parentContainer, msg, "警告", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (StringUtils.isBlank(savePathText.getText()))
        {
            saveBtn.doClick();
            return false;
        }
        return true;
    }
}
