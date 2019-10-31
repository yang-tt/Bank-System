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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Control.IsCredit;
import Control.Register;

/**
 * this class is a interface for user to register
 * @author YangTao
 *
 */
public class RegisterUI extends JFrame{
	private JLabel noticeLabel;
	private JLabel accNoLabel, nameLabel, addressLabel, birthLabel, typeLabel;
	private JTextField birthTextField;
	private JTextField accNoField, nameField, addressField;
	private JRadioButton saverButton, juniorButton, currentButton;
	private ButtonGroup accountGroup;
	private JButton cancelButton, confirmButton;
	private JPanel buttonPanel, panel, panel1, panel2, panel3, panel4, panel5;
	private String type;
	private String accNo;
	
	
	public RegisterUI(String accNo) {
		super("Register");
		this.accNo = accNo;
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(8, 1));
		
		noticeLabel = new JLabel("Register Your Account", JLabel.CENTER);
		
		accNoLabel = new JLabel("Account No.: ");
		nameLabel = new JLabel("Name: ");
		addressLabel = new JLabel("Address: ");
		birthLabel = new JLabel("Date of Birth: ");
		typeLabel = new JLabel("Type of Account: ");
			
		accNoField = new JTextField(20);
		nameField = new JTextField(20);
		addressField = new JTextField(20);
		birthTextField = new JTextField(20);

		saverButton = new JRadioButton("Saver Account", false);
		juniorButton = new JRadioButton("Junior Account", false);
		currentButton = new JRadioButton("Current Account", false);
		
		buttonPanel = new JPanel(new GridLayout(1,3));
		buttonPanel.add(saverButton);
		buttonPanel.add(juniorButton);
		buttonPanel.add(currentButton);
		
		accountGroup = new ButtonGroup();
		accountGroup.add(saverButton);
		accountGroup.add(juniorButton);
		accountGroup.add(currentButton);
		
		cancelButton = new JButton("Cancel");
		confirmButton = new JButton("Confirm");
		
		panel = new JPanel(new FlowLayout());
		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		panel3 = new JPanel(new FlowLayout());
		panel4 = new JPanel(new FlowLayout());
		panel5 = new JPanel(new FlowLayout());
		
		panel1.add(accNoLabel);		panel1.add(accNoField);
		panel2.add(nameLabel);		panel2.add(nameField);
		panel3.add(addressLabel);	panel3.add(addressField);
		panel.add(birthLabel);		panel.add(birthTextField);
		panel4.add(typeLabel);		panel4.add(buttonPanel);
		panel5.add(cancelButton);	panel5.add(confirmButton);
		
		container.add(noticeLabel);
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);
		container.add(panel);
		container.add(panel4);
		container.add(panel5);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 500);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionSaver();
		actionJunior();
		actionCurrent();
		actionRegister();
		actionBack();
	}
	
	public void actionBack() {
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuUI menuUI = new MenuUI(accNo);
				menuUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public void actionRegister() {
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String accNoInput = accNoField.getText();
				String nameInput = nameField.getText();
				String addressInput = addressField.getText();
				String birthInput = birthTextField.getText();
				String typeInput = type;
				
				IsCredit checkcredit = new IsCredit();
				int flag1 = checkcredit.credit(nameInput);
				if (flag1 == 1) {
					Register user = new Register();
					String back = user.addAccount(accNoInput, typeInput, nameInput, addressInput, birthInput);
					//int flag = user.addAccount(accNoInput, typeInput, nameInput, addressInput, birthInput);
					String[] backSplit = back.split(";");
					if (backSplit[0].equals("1")) {
						String pin = backSplit[1];
						System.out.println(pin);
						JOptionPane.showMessageDialog(null,"success! You PIN code is " + pin);
						MenuUI menuUI = new MenuUI(accNo);
						menuUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						dispose();
					} else if(backSplit[0].equals("0")){
						JOptionPane.showMessageDialog(null,"Same account number!");
						accNoField.setText("");
						accNoField.requestFocus();
					} else if (backSplit[0].equals("-1")) {
						JOptionPane.showMessageDialog(null,"Age!");
					}
				}	else if (flag1 == 2) {
					JOptionPane.showMessageDialog(null,"Credit is too low!");
				}	else if (flag1 == 3) {
					JOptionPane.showMessageDialog(null,"You should join our bank first!");
					AddUserUI user = new AddUserUI();
					user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				
				
				
			}
		});
	}
	
	public void actionSaver(){
		saverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				type = "Saver";
			}
		});
	}
	public void actionJunior(){
		juniorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				type = "Junior";
			}
		});
	}
	public void actionCurrent(){
		currentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				type = "Current";
			}
		});
	}
	
	public static void main(String[] args) {
		RegisterUI user = new RegisterUI("1234");
		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}
