package Boundary;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Control.AddUser;

/**
 * this class is a interface for manager to add users
 * @author YangTao
 *
 */
public class AddUserUI extends JFrame {
	private JLabel noticeLabel;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JButton cancelButton, confirmButton;
	private JPanel buttonPanel, panel1;
	private String type;
	private String accNo;
	
	public AddUserUI() {
		super("Add User");
		Container container = getContentPane();
		container.setLayout(new GridLayout(3,1));
		
		noticeLabel = new JLabel("Welcome to Join Our Bank!", JLabel.CENTER);	//welcome title
		nameLabel = new JLabel("Name: ", JLabel.LEFT);
		nameTextField = new JTextField(15);
		
		confirmButton = new JButton("Confirm");
		buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		
		panel1 = new JPanel(new FlowLayout());
		panel1.add(nameLabel);
		panel1.add(nameTextField);
		
		
		container.add(noticeLabel);
		container.add(panel1);
		container.add(buttonPanel);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 300);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionAdd();
	}
	
	public void actionAdd() {
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameInput = nameTextField.getText();
				AddUser user = new AddUser();
				String back = user.adduser(nameInput);
				String[] backSplit = back.split(";");
				if (backSplit[0].equals("1")) {
					JOptionPane.showMessageDialog(null,"You've already in our bank!");
					LoginUI use = new LoginUI();
					use.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}	else if(backSplit[0].equals("2")){
					JOptionPane.showMessageDialog(null,"Success! Your credit value is: " + backSplit[1]);
					LoginUI use = new LoginUI();
					use.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}	else if (backSplit[0].equals("3")) {
					JOptionPane.showMessageDialog(null,"You are not in the credit system so we can't check your identity!");
					nameTextField.setText("");
					nameTextField.requestFocus();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		AddUserUI user = new AddUserUI();
		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
