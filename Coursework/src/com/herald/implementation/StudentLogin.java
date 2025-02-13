package com.herald.implementation;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.herald.connections.IConnectionString;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class StudentLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public StudentLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Student Login");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel.setBounds(167, 32, 198, 54);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 84, 515, 2);
		contentPane.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Competitor ID");
		lblNewLabel_1.setBounds(201, 125, 90, 31);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(155, 159, 165, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(211, 204, 68, 14);
		contentPane.add(lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(155, 229, 165, 20);
		contentPane.add(passwordField);
		
		passwordField.setEchoChar('*');
		
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try(Connection conn = IConnectionString.getConnection();) {
					boolean found = false;

					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("select CompetitorId,firstName,middleName,lastName,password from users");
					
					String id = textField.getText();
					int compID = Integer.parseInt(id);
					
					char[] passChars = passwordField.getPassword();
					String pass = new String(passChars).trim();
					while (rs.next()) {
						if (rs.getString(1).equals(String.valueOf(compID)) && rs.getString(5).equals(pass)) {
							String firstName = rs.getString(2);
							String middleName = rs.getString(3) != null ? rs.getString(3):"";
							String lastName = rs.getString(4);
							setVisible(false);
							new StudentDashboard(id,firstName,middleName,lastName).setVisible(true);
							found = true;
							break;
						}
					
					}
					if(!found) {
						 JOptionPane.showMessageDialog(null, "Invalid CompetitorId or Password", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNewButton.setBounds(190, 292, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new StudentRegistration().setVisible(true);
			}
		});
		registerBtn.setBounds(190, 326, 89, 23);
		contentPane.add(registerBtn);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
		
		chckbxNewCheckBox.setBounds(190, 256, 128, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new StudentRegistration().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(391, 10, 85, 21);
		contentPane.add(btnNewButton_1);

	}
}
