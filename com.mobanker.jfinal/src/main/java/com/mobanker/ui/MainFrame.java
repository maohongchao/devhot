package com.mobanker.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jfinal.kit.PropKit;
import com.mobanker.conf.AppConstants;
import com.mobanker.ui.listener.CheckBoxActionListener;
import com.mobanker.ui.listener.ChooseExcelActionListener;
import com.mobanker.ui.listener.ChooseSavePathActionListener;
import com.mobanker.ui.listener.GeneratePdfActionListener;
import com.mobanker.ui.listener.RadioChooseActionListener;
import com.mobanker.ui.listener.TenderSelectActionListener;

/**
 * <b>Package Name:</b> com.mobanker.ui <br/>
 * <b>Description:</b>〈应用主入口类〉<br/>
 * @author hongchaoMao <br/>
 * Create date: 2016年2月26日
 * @version v1.0.0
 */
public class MainFrame
{

    public static void main(String[] args)
    {
        PropKit.use("conf/appconstans.properties");

        JFrame mainFrame = new JFrame("生成第三方借款协议工具");
        Container mainPanel = mainFrame.getContentPane();
        
        // Head
        JLabel title = new JLabel("《生成第三方借款协议》");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        mainPanel.add(title, BorderLayout.PAGE_START);
        // Body
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));

        // Body - Up  Borrow parameter info
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1, 2));
        // Body - Left
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("指定批量借款单"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        JTextArea borrowNidTextArea = new JTextArea(19, 30);
        JScrollPane scrollPane = new JScrollPane(borrowNidTextArea);
        JPanel excelPanel = new JPanel();
        excelPanel.setLayout(new BoxLayout(excelPanel, BoxLayout.X_AXIS));
        excelPanel.add(new JLabel("导入Excel:"));
        JTextField excelPath = new JTextField();
        excelPath.setColumns(14);
        excelPanel.add(excelPath);
        JButton changeTheLabel = new JButton("选择Excel(xls/xlsx)文件");
        changeTheLabel.setMargin(new Insets(0, 0, 0, 0));
        excelPanel.add(changeTheLabel);
        leftPanel.add(excelPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(scrollPane);
        tablePanel.add(leftPanel);
        
        // Body - right
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("理财人/用户信息"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        JPanel tenderPanel = new JPanel();
        tenderPanel.setPreferredSize(new Dimension(360, 170));
        tenderPanel.setLayout(new BoxLayout(tenderPanel, BoxLayout.Y_AXIS));
        tenderPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("理财人属性"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        JPanel radioPanel = new JPanel();
        JRadioButton manualBtn = new JRadioButton("指定");
        manualBtn.setMnemonic(KeyEvent.VK_A);
        manualBtn.setActionCommand("manualAction");
        manualBtn.setSelected(true);
        JRadioButton randomBtn = new JRadioButton("随机");
        randomBtn.setMnemonic(KeyEvent.VK_B);
        randomBtn.setActionCommand("randomAction");
        randomBtn.setSelected(false);
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(manualBtn);
        radioGroup.add(randomBtn);
        radioPanel.add(manualBtn);
        radioPanel.add(randomBtn);
        tenderPanel.add(radioPanel);
        
        String[] nameStrings = PropKit.get(AppConstants.CON_TENDER_NAMES).split(",");
        JComboBox<String> tenderCbx = new JComboBox<String>(nameStrings);
        tenderCbx.setEditable(true);
        tenderCbx.setPreferredSize(new Dimension(245, 20));
        JTextField tenderPhoneText = new JTextField();
        tenderPhoneText.setColumns(20);
        tenderPhoneText.setEditable(false);
        JTextField tenderIDText = new JTextField();
        tenderIDText.setColumns(22);
        JPanel tenderNamePanel = new JPanel();
        tenderNamePanel.add(new JLabel("理财人姓名:"));
        tenderNamePanel.add(tenderCbx);
        JPanel tenderIDPanel = new JPanel();
        tenderIDPanel.add(new JLabel("身份证号码:"));
        tenderIDPanel.add(tenderIDText);
        JPanel tenderPhonePanel = new JPanel();
        tenderPhonePanel.add(new JLabel("理财人电话:"));
        tenderPhonePanel.add(tenderPhoneText);
        JCheckBox tenderPhoneCbox = new JCheckBox("");
        tenderPhoneCbox.setSelected(false);
        tenderPhoneCbox.setActionCommand("enablePhoneText");
        tenderPhonePanel.add(tenderPhoneCbox);
        tenderPanel.add(tenderNamePanel);
        tenderPanel.add(tenderIDPanel);
        tenderPanel.add(tenderPhonePanel);
        rightPanel.add(tenderPanel);
        
        JPanel concealPanel = new JPanel();
        concealPanel.setPreferredSize(new Dimension(360, 150));
        concealPanel.setLayout(new BoxLayout(concealPanel, BoxLayout.Y_AXIS));
        concealPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("客户隐私属性"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        JCheckBox userIDCbox = new JCheckBox("客户身份证");
        JCheckBox userPhoneCbox = new JCheckBox("客户手机号");
        JCheckBox userDebitcardCbox = new JCheckBox("客户银行卡");
        concealPanel.add(userIDCbox);
        concealPanel.add(userPhoneCbox);
        concealPanel.add(userDebitcardCbox);
        rightPanel.add(concealPanel);
        tablePanel.add(rightPanel);
        boxPanel.add(tablePanel);

        // Body - Up  Save path panel
        JPanel pathPanel = new JPanel();
        pathPanel.setPreferredSize(new Dimension(250, 10));
        pathPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        pathPanel.add(new JLabel("选择生成路径:"));
        JTextField savePathText = new JTextField();
        savePathText.setColumns(27);
        savePathText.setEditable(false);
        pathPanel.add(savePathText);
        JButton saveButton = new JButton("选择要保存的路径");
        saveButton.setMargin(new Insets(0, 0, 0, 0));
        pathPanel.add(saveButton);
        JProgressBar progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(260, 20));
        progressBar.setOpaque(true);
        progressBar.setStringPainted(true);
        pathPanel.add(progressBar);
        boxPanel.add(pathPanel);
        mainPanel.add(boxPanel);
        
        // Footer - Save path panel
        JButton generateBtn = new JButton("生成第三方借款协议");
        mainPanel.add(generateBtn, BorderLayout.PAGE_END);

        // Action
        changeTheLabel.addActionListener(new ChooseExcelActionListener(mainFrame, excelPath, borrowNidTextArea));
        tenderCbx.addActionListener(new TenderSelectActionListener(tenderCbx, tenderIDText, tenderPhoneText));
        RadioChooseActionListener radioChooseAction = new RadioChooseActionListener();
        radioChooseAction.setTenderCbx(tenderCbx);
        radioChooseAction.setTenderIDText(tenderIDText);
        radioChooseAction.setTenderPhoneCbox(tenderPhoneCbox);
        manualBtn.addActionListener(radioChooseAction);
        randomBtn.addActionListener(radioChooseAction);
        CheckBoxActionListener cbxActionListener = new CheckBoxActionListener();
        cbxActionListener.setTenderPhoneText(tenderPhoneText);
        tenderPhoneCbox.addActionListener(cbxActionListener);
        saveButton.addActionListener(new ChooseSavePathActionListener(mainFrame, savePathText));
        GeneratePdfActionListener generateListener = new GeneratePdfActionListener();
        generateListener.setParentContainer(mainFrame);
        generateListener.setBorrowNidTextArea(borrowNidTextArea);
        generateListener.setRadioGroup(radioGroup);
        generateListener.setTenderCbx(tenderCbx);
        generateListener.setTenderIDText(tenderIDText);
        generateListener.setTenderPhoneText(tenderPhoneText);
        generateListener.setTenderPhoneCbox(tenderPhoneCbox);
        generateListener.setUserIDCbox(userIDCbox);
        generateListener.setUserPhoneCbox(userPhoneCbox);
        generateListener.setUserDebitcardCbox(userDebitcardCbox);
        generateListener.setSavePathText(savePathText);
        generateListener.setSaveBtn(saveButton);
        generateListener.setGenerateBtn(generateBtn);
        generateListener.setProgressBar(progressBar);
        generateBtn.addActionListener(generateListener);
        
        // Display the window.
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setBounds(400, 200, 800, 550);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
