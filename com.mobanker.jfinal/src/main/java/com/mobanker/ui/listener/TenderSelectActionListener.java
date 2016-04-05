package com.mobanker.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.jfinal.kit.PropKit;
import com.mobanker.conf.AppConstants;

/**
 * <b>Package Name:</b> com.mobanker.ui.listener <br/>
 * <b>Description:</b>〈理财人选择事件监听类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月26日
 * @version v1.0.0
 */
public class TenderSelectActionListener implements ActionListener
{
    private JComboBox<String> tenderCbx       = null;
    private JTextField        tenderIDText    = null;
    private JTextField        tenderPhoneText = null;

    public TenderSelectActionListener(JComboBox<String> tenderCbx, JTextField tenderIDText, JTextField tenderPhoneText)
    {
        this.tenderCbx = tenderCbx;
        this.tenderIDText = tenderIDText;
        this.tenderPhoneText = tenderPhoneText;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        int index = tenderCbx.getSelectedIndex();
        if (index != -1)
        {
            tenderIDText.setText(PropKit.get(AppConstants.CON_TENDER_ID).split(",")[index]);
            //tenderPhoneText.setText(PropKit.get(AppConstants.CON_TENDER_PHONE).split(",")[index]);
            tenderPhoneText.setText("");
        }
    }

}
