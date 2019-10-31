package Boundary;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Control.Login;

/**
 * this class is a interface for users to login
 * @author YangTao
 *
 */
public class LoginUI extends JFrame{
	private JLabel noticeLabel, accNoLabel, pinLabel; 
	private JButton confirmButton;
	private JTextField accNoTextField;
	private JPasswordField pin;
	private JPanel buttonPanel, panel1, panel2;

	public LoginUI() {
		super("User Login");	//set title
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(4,1));
		
		noticeLabel = new JLabel("Welcome to Bank System!", JLabel.CENTER);	//welcome title
		accNoLabel = new JLabel("Account No.: ", JLabel.LEFT);
		accNoTextField = new JTextField(15);
		pinLabel = new JLabel("PIN:     ", JLabel.LEFT);
		pin = new JPasswordField(15);
		
		confirmButton = new JButton("Confirm");
		buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		
		panel1 = new JPanel(new FlowLayout());
		panel1.add(accNoLabel);
		panel1.add(accNoTextField);
		
		panel2 = new JPanel(new FlowLayout());
		panel2.add(pinLabel);
		panel2.add(pin);
		
		container.add(noticeLabel);
		container.add(panel1);
		container.add(panel2);
		container.add(buttonPanel);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 300);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionlogin();
	}
	
	public void actionlogin(){
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String accNoInput = accNoTextField.getText(); 
				String PINInput = pin.getText();
				Login account = new Login();
				int flag = account.ckeckLogin(accNoInput, PINInput);
				if (flag == 1) {
					MenuUI menuUI = new MenuUI(accNoInput);
					menuUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				} else if(flag == 0){
					JOptionPane.showMessageDialog(null,"Wrong PIN");
					pin.setText("");
					pin.requestFocus();
				} else if (flag == -1) {
					JOptionPane.showMessageDialog(null,"Wrong Account Number");
					accNoTextField.setText("");
					pin.setText("");
					accNoTextField.requestFocus();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		LoginUI user = new LoginUI();
		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
