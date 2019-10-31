package Boundary;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Control.DepositFunds;
import Control.PayOff;
import Control.ReadWriteData;

/**
 * this class is a interface for users to deposit funds.
 * @author YangTao
 *
 */
public class DepositUI extends JFrame {
	private JLabel notice1Label, notice2Label, amountLabel, typeLabel; 
	private JButton confirmButton, backButton, payoffButton;
	private JRadioButton clearedButton, unclearedButton;
	private ButtonGroup fundsGroup;
	private JTextField amountTextField;
	private JPanel buttonPanel, panel, panel1, panel2;
	private String accNo;
	private String balance, overdraft;
	private boolean isClear;
	
	public DepositUI(String accNo) {
		super("Deposit Funds");
		this.accNo = accNo;
		Container container = getContentPane();
		container.setLayout(new GridLayout(6,1));
		
		List<String> accountList = ReadWriteData.read(new File("src/Account.csv"));
		if(accountList!=null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ){
				String line = accountList.get(startLine);
				String[] lineSplit = line.split(",");
				if (lineSplit[0].equals(accNo)) {
					balance = lineSplit[4];
					overdraft = lineSplit[5];
				}
			}
		}
		
		notice1Label = new JLabel("< Account  "+ accNo + " >" + "  Balance:" + balance, JLabel.CENTER);
		notice2Label = new JLabel("            Overdraft: " + overdraft, JLabel.CENTER);
		amountLabel = new JLabel("Please input the amount you want to deposit: ", JLabel.LEFT);
		amountTextField = new JTextField(8);
		typeLabel = new JLabel("Type of Funds:");
		
		clearedButton = new JRadioButton("Cleared Funds", false);
		unclearedButton = new JRadioButton("Uncleared Funds", false);
		
		
		panel = new JPanel(new FlowLayout());
		panel.add(clearedButton);
		panel.add(unclearedButton);
		
		fundsGroup = new ButtonGroup();
		fundsGroup.add(clearedButton);
		fundsGroup.add(unclearedButton);

		confirmButton = new JButton("Confirm");
		backButton = new JButton("Back");
		payoffButton = new JButton("Pay Off Overdraft");
		buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		buttonPanel.add(payoffButton);
		buttonPanel.add(backButton);
		
		panel1 = new JPanel(new FlowLayout());
		panel1.add(amountLabel);
		panel1.add(amountTextField);
		
		panel2 = new JPanel(new FlowLayout());
		panel2.add(typeLabel);
		
		container.add(notice1Label);
		container.add(notice2Label);
		container.add(panel1);
		container.add(panel2);
		container.add(panel);
		container.add(buttonPanel);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 300);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionDeposit();
		actionPay();
		actionClear();
		actionUnclear();
		actionBack();
	}
	
	public void actionDeposit() {
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String depositInput = amountTextField.getText();
				boolean clearInput = isClear;
				DepositFunds money = new DepositFunds();
				int flag = money.addMoney(accNo, depositInput, clearInput);
				if (flag == 1) {
					JOptionPane.showMessageDialog(null,"You've save clear funds: $" + depositInput);
					MenuUI menuUI = new MenuUI(accNo);
					menuUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}	else if (flag == 2) {
					JOptionPane.showMessageDialog(null,"You've save unclear funds: $" + depositInput);
					MenuUI menuUI = new MenuUI(accNo);
					menuUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
			}
		});
	}
	
	public void actionPay() {
		payoffButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PayOff money = new PayOff();
				int flag = money.pay(accNo);
				if (flag == 0) {
					JOptionPane.showMessageDialog(null,"Your overdraft is 0!");
				}	else if (flag == 1) {
					JOptionPane.showMessageDialog(null,"You don't have enough balance to pay off overdraft!");
				}	else if (flag == 2) {
					JOptionPane.showMessageDialog(null,"Pay Off Overdraft Successfully!");
					DepositUI user = new DepositUI(accNo);
					user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}	else if (flag == 3) {
					JOptionPane.showMessageDialog(null,"You don't have this option!");
				}
			}
		});
	}
	
	public void actionClear(){
		clearedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				isClear = true;
			}
		});
	}
	public void actionUnclear(){
		clearedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				isClear = false;
			}
		});
	}
	
	public void actionBack() {
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuUI menuUI = new MenuUI(accNo);
				menuUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public static void main(String[] args) {
		DepositUI user = new DepositUI("1");
		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}
