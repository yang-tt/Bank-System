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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Control.Overdraft;
import Control.ReadWriteData;
import Control.WidhdrawFunds;

/**
 * this class is a interface for user to withdraw funds
 * @author YangTao
 *
 */
public class WithdrawUI extends JFrame {
	private JLabel accountNoLabel, balanceNoLabel, overdraftLabel, overdraftLimitLabel, amountLabel;
	private JLabel emptyLabel1, emptyLabel2, emptyLabel3, emptyLabel4;
	//private JLabel notice1Label, notice2Label, amountLabel; 
	private JButton confirmButton, backButton, overdraftButton;
	private JTextField amountTextField;
	private JPanel buttonPanel, panel1, noticePanel;
	private String accNo;
	private String balance, overdraft, overdraftLimit;
	
	
	public WithdrawUI(String accNo) {
		super("Withdraw Funds");
		this.accNo = accNo;
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(4,1));
		
		List<String> accountList = ReadWriteData.read(new File("src/Account.csv"));
		if(accountList!=null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ){
				String line = accountList.get(startLine);
				String[] lineSplit = line.split(",");
				if (lineSplit[0].equals(accNo)) {
					balance = lineSplit[4];
					overdraft = lineSplit[5];
					overdraftLimit = lineSplit[6];
				}
			}
		}
		
		accountNoLabel = new JLabel("                    Account No: " + accNo, JLabel.LEFT);
		balanceNoLabel = new JLabel("                    Balance: " + balance, JLabel.LEFT);
		overdraftLabel = new JLabel("                    Overdraft: " + overdraft, JLabel.LEFT);
		overdraftLimitLabel = new JLabel("                    OverdraftLimit: " + overdraftLimit, JLabel.LEFT);
		amountLabel = new JLabel("Please input the amount you want to withdraw or overdraft: ", JLabel.LEFT);
		amountTextField = new JTextField(8);
		
		emptyLabel1 = new JLabel("                  ",JLabel.LEFT);
		emptyLabel2 = new JLabel("                  ",JLabel.LEFT);
		emptyLabel3 = new JLabel("                  ",JLabel.LEFT);
		emptyLabel4 = new JLabel("                  ",JLabel.LEFT);
		
		confirmButton = new JButton("Confirm");
		overdraftButton = new JButton("Overdraft");
		backButton = new JButton("Back");
		buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		buttonPanel.add(overdraftButton);
		buttonPanel.add(backButton);
		
		panel1 = new JPanel(new FlowLayout());
		panel1.add(amountLabel);
		panel1.add(amountTextField);
		
		noticePanel = new JPanel(new GridLayout(4,2,3,3));
		noticePanel.add(accountNoLabel);
		noticePanel.add(emptyLabel1);
		noticePanel.add(balanceNoLabel);
		noticePanel.add(emptyLabel2);
		noticePanel.add(overdraftLabel);
		noticePanel.add(emptyLabel3);
		noticePanel.add(overdraftLimitLabel);
		noticePanel.add(emptyLabel4);
		
		//container.add(accountNoLabel);
		container.add(noticePanel);
		container.add(panel1);
		container.add(buttonPanel);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 300);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionWithdraw();
		actionOverdraft();
		actionBack();
	}
	
	public void actionWithdraw() {
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String withdrawInput = amountTextField.getText();
				
				if (withdrawInput == "" || withdrawInput == null) {
					
					JOptionPane.showMessageDialog(null,"Please input");
					amountTextField.setText("");
					amountTextField.requestFocus();
				}	else {
					WidhdrawFunds money = new WidhdrawFunds();
					int flag = money.getMoney(accNo, withdrawInput);
					if (flag == 1) {
						JOptionPane.showMessageDialog(null,"You don't have enough money!!");
						amountTextField.setText("");
						amountTextField.requestFocus();
					}	else if (flag == 2) {
						JOptionPane.showMessageDialog(null,"You've withdraw funds: $" + withdrawInput);
						MenuUI menuUI = new MenuUI(accNo);
						menuUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						dispose();
					}	else if (flag == 3) {
						JOptionPane.showMessageDialog(null,"You should reserve before make withdraw.");
						amountTextField.setText("");
						amountTextField.requestFocus();
					}	else if (flag == 4) {
						JOptionPane.showMessageDialog(null,"Please wait!!");
						amountTextField.setText("");
						amountTextField.requestFocus();
					}
				}
				
			}
		});
	}
	
	public void actionOverdraft() {
		overdraftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String overdraftInput = amountTextField.getText();
				Overdraft money = new Overdraft();
				int flag = money.overdraft(accNo, overdraftInput);
				if (flag == 2) {
					JOptionPane.showMessageDialog(null,"You don't have enough limit!!");
					amountTextField.setText("");
					amountTextField.requestFocus();
				}	else if (flag == 1) {
					JOptionPane.showMessageDialog(null,"You've overdraft funds: $" + overdraftInput);
					MenuUI menuUI = new MenuUI(accNo);
					menuUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}	else if (flag == 3) {
					JOptionPane.showMessageDialog(null,"You can't overdraft!!");
					amountTextField.setText("");
					amountTextField.requestFocus();
				}	
				
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
		WithdrawUI user = new WithdrawUI("111");
		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}
