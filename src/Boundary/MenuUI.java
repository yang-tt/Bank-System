package Boundary;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Control.CloseAccount;
import Control.IsSuspend;
import Control.MakeNotice;
import Control.SuspendAccount;

/**
 * this class is a interface for user to select functions
 * @author YangTao
 *
 */
public class MenuUI extends JFrame{

	private JLabel noticeLabel;
	private JPanel functionPanel, buttonPanel;
	private JButton addButton, depositButton, withdrawButton, noticeButton, clearButton, suspendButton, unsuspendButton, closeButton, logoutButton;
	private JButton backButton;
	private String accNo;

	public MenuUI(String accNo){
		super("User Menu");
		this.accNo = accNo;
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(3,1));
		
		noticeLabel = new JLabel("Account Numer: " + accNo, JLabel.CENTER);
		
		addButton = new JButton("1.Creat New Account");
		depositButton = new JButton("2.Deposit Funds");
		withdrawButton = new JButton("3.Withdraw Funds");
		noticeButton = new JButton("4.Reservation for Withdraw");
		clearButton = new JButton("5.Clear Funds");
		suspendButton = new JButton("6.Suspend Account");
		unsuspendButton = new JButton("7.Unsuspend Account");
		closeButton = new JButton("8.Close Account");
		logoutButton = new JButton("9.Logout");
		
		functionPanel = new JPanel(new FlowLayout());
		functionPanel.add(addButton);
		functionPanel.add(depositButton);
		functionPanel.add(withdrawButton);
		functionPanel.add(noticeButton);
		functionPanel.add(clearButton);
		functionPanel.add(suspendButton);
		functionPanel.add(unsuspendButton);
		functionPanel.add(closeButton);
		functionPanel.add(logoutButton);
		
		backButton = new JButton("Back");
		buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.add(backButton, BorderLayout.SOUTH);
		
		container.add(noticeLabel, BorderLayout.NORTH);
		container.add(functionPanel, BorderLayout.CENTER);
		container.add(buttonPanel,BorderLayout.SOUTH);
		
		
		setResizable(false);	//set frame can not be resize
		setSize(600, 370);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window	
		
		
		actionCreate();
		actionDeposit();
		actionWithdraw();
		actionNotice();
		actionClear();
		actionSuspend();
		actionUnsuspend();
		actionClose();
		actionLogout();
		actionBack();
		
	}
	
	public void actionCreate() {
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IsSuspend test = new IsSuspend();
				boolean flag1 = test.isSuspend(accNo);
				if (!flag1) {
					RegisterUI user = new RegisterUI(accNo);
					user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}	else {
					JOptionPane.showMessageDialog(null,"This account has been suspended, you should unsuspend it first!");
				}
			}
		});
	}
	
	public void actionDeposit() {
		depositButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IsSuspend test = new IsSuspend();
				boolean flag1 = test.isSuspend(accNo);
				if (!flag1) {
					DepositUI user = new DepositUI(accNo);
					user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}	else {
					JOptionPane.showMessageDialog(null,"This account has been suspended, you should unsuspend it first!");
				}
				
			}
		});
	}
	
	public void actionWithdraw() {
		withdrawButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IsSuspend test = new IsSuspend();
				boolean flag1 = test.isSuspend(accNo);
				if (!flag1) {
					WithdrawUI user = new WithdrawUI(accNo);
					user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}	else {
					JOptionPane.showMessageDialog(null,"This account has been suspended, you should unsuspend it first!");
				}
				
			}
		});
	}
	
	public void actionNotice() {
		noticeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IsSuspend test = new IsSuspend();
				boolean flag1 = test.isSuspend(accNo);
				if (!flag1) {
					MakeNotice notice = new MakeNotice();
					String back = notice.reserve(accNo);
					String[] backSplit = back.split(";");
					if (backSplit[0].equals("1")) {
						JOptionPane.showMessageDialog(null,
							"You've made a 2-day-after notice successfully, and you can can withdraw in or after: " + backSplit[1]);
					}	else if (backSplit[0].equals("2")) {
						JOptionPane.showMessageDialog(null,"You've already make a notice, and you can withdraw in or after: " 
															+ backSplit[1]);
					}	else if (backSplit[0].equals("3")) {
						JOptionPane.showMessageDialog(null,"You don't need reservation.");
					}
				}	else {
					JOptionPane.showMessageDialog(null,"This account has been suspended, you should unsuspend it first!");
				}
				 
			}
		});
	}
	
	public void actionClear() {
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"The uncleared balance will be cleared in 8 am everyday!");
				
			}
		});
	}
	
	public void actionSuspend() {
		suspendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SuspendAccount acc = new SuspendAccount();
				int flag = acc.suspend(accNo);
				if (flag == 1) {
					JOptionPane.showMessageDialog(null,"Suspend Successfully!");
				}	else if (flag == 2) {
					JOptionPane.showMessageDialog(null,"You've already Suspend!");
				}
				
			}
		});
	}
	
	public void actionUnsuspend() {
		unsuspendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SuspendAccount acc = new SuspendAccount();
				int flag = acc.unsuspend(accNo);
				if (flag == 1) {
					JOptionPane.showMessageDialog(null,"Unsuspend Successfully!");
				}	else if (flag == 2) {
					JOptionPane.showMessageDialog(null,"You've already Unsuspend!");
				}
				
			}
		});
	}
	
	public void actionClose() {
		closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CloseAccount account = new CloseAccount();
				int flag = account.closeAccount(accNo);
				if (flag == 1) {
					JOptionPane.showMessageDialog(null,"Close This Account Successfully!");
				}	else if (flag == 2) {
					JOptionPane.showMessageDialog(null,"You should empty balance and overdraft.");
				}
				
			}
		});
	}
	
	public void actionLogout() {
		logoutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginUI user = new LoginUI();
				user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();			
			}
		});
	}
	
	public void actionBack() {
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginUI user = new LoginUI();
				user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
				
			}
		});
	}
	
	
	public static void main(String[] args) {
		MenuUI user = new MenuUI("111");
		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
