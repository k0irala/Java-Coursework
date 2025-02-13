package com.herald.implementation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.herald.connections.IConnectionString;
import com.herald.enums.CompetitionLevel;
import com.herald.models.Competitor;

import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Color;

public class StudentRegistration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public StudentRegistration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Student Register");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel.setBounds(45, 11, 229, 56);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(45, 65, 645, 2);
		contentPane.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Competitor ID");
		lblNewLabel_1.setBounds(44, 89, 81, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setBounds(45, 124, 67, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Middle Name");
		lblNewLabel_2_1.setBounds(44, 159, 81, 14);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Last Name");
		lblNewLabel_2_1_1.setBounds(45, 194, 80, 14);
		contentPane.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_2 = new JLabel("Age");
		lblNewLabel_2_1_2.setBounds(45, 230, 35, 14);
		contentPane.add(lblNewLabel_2_1_2);

		JLabel lblNewLabel_2_1_3 = new JLabel("Gender");
		lblNewLabel_2_1_3.setBounds(45, 310, 46, 14);
		contentPane.add(lblNewLabel_2_1_3);

		JLabel lblNewLabel_2_1_4 = new JLabel("Level");
		lblNewLabel_2_1_4.setBounds(326, 310, 46, 14);
		contentPane.add(lblNewLabel_2_1_4);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setBounds(162, 89, 46, 14);
		contentPane.add(lblNewLabel_1_1);

		textField = new JTextField();
		textField.setBounds(162, 121, 280, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(162, 156, 280, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(162, 191, 280, 20);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(162, 227, 280, 20);
		contentPane.add(textField_3);

		JLabel lblNewLabel_5 = new JLabel("Country");
		lblNewLabel_5.setBounds(45, 266, 46, 14);
		contentPane.add(lblNewLabel_5);

		textField_4 = new JTextField();
		textField_4.setBounds(162, 263, 280, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		btnClear.setBounds(338, 386, 89, 23);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setBounds(45, 351, 67, 14);
		contentPane.add(lblNewLabel_6);

		passwordField = new JPasswordField();
		passwordField.setBounds(162, 348, 280, 20);
		passwordField.setEchoChar('*');
		contentPane.add(passwordField);

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
		chckbxNewCheckBox.setBounds(447, 347, 116, 23);
		contentPane.add(chckbxNewCheckBox);

		JComboBox<String> gender = new JComboBox<String>();
		gender.setModel(new DefaultComboBoxModel<String>(new String[] { "Male ", "Female" }));
		gender.setBounds(165, 306, 86, 22);
		contentPane.add(gender);

		JComboBox<String> level = new JComboBox<String>();
		level.setModel(new DefaultComboBoxModel<String>(new String[] { "Beginner", "Intermediate", "Advanced" }));
		level.setBounds(425, 306, 152, 22);
		contentPane.add(level);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validateForm())
					return;
				int competitorId = setCompetitorID();
				String firstName = textField.getText();
				String middleName = textField_1.getText() != null?textField_1.getText():"" ;
				String lastName = textField_2.getText();
				int age = Integer.parseInt(textField_3.getText());
				String country = textField_4.getText();
				String difficulty = level.getSelectedItem().toString();
				String genderName = gender.getSelectedItem().toString();
				String pass = passwordField.getText().toString();
				Competitor competitor = new Competitor(competitorId, firstName, middleName, lastName);
				competitor.setAge(age);
				competitor.setCountry(country);
				competitor.setLevel(CompetitionLevel.valueOf(difficulty.toUpperCase()));
				competitor.setGender(genderName);

				registerUser(competitor.getFirstName(), competitor.getMiddleName(), competitor.getLastName(),
						competitor.getAge(), competitor.getCountry(), competitor.getLevel(), competitor.getGender(),pass);
				setVisible(false);
				new StudentLogin().setVisible(true);
			}

		});
		btnNewButton.setBounds(162, 386, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new IndexPage().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(600, 11, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_3 = new JLabel("Date:");
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_3.setBounds(369, 34, 58, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblNewLabel_4.setBounds(425, 35, 89, 14);
		contentPane.add(lblNewLabel_4);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		lblNewLabel_4.setText(dateFormat.format(date));
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new StudentLogin().setVisible(true);
			}
		});
		btnLogin.setBounds(488, 387, 89, 23);
		contentPane.add(btnLogin);
		setCompetitorID();

	}

	public void registerUser(String firstName, String middleName, String lastName, int age, String country,
			CompetitionLevel competitionLevel, String gender,String password) {
		try {
			Connection conn = IConnectionString.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"Insert into Users(FirstName,MiddleName,LastName,age,Gender,country,Level,password) values(?,?,?,?,?,?,?,?)");
			ps.setString(1, firstName);
			ps.setString(2, middleName);
			ps.setString(3, lastName);
			ps.setLong(4, age);
			ps.setString(5, gender);
			ps.setString(6, country);
			ps.setString(7, competitionLevel.toString());
			ps.setString(8,password);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				JOptionPane.showMessageDialog(null, "Successfully Registered!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);

			} else {
				JOptionPane.showMessageDialog(null, "Could not Register User", "Error", JOptionPane.ERROR);
				return;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);

		}
	}

	public int setCompetitorID() {
		int id=0;
		try {
			Connection conn = IConnectionString.getConnection();
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("Select MAX(CompetitorID) from Users");
			JLabel lblNewLabel_1_1 = new JLabel("");
			lblNewLabel_1_1.setBounds(162, 89, 46, 14);
			contentPane.add(lblNewLabel_1_1);
			
			JLabel lblNewLabel_7 = new JLabel("Please remember your Competitor ID");
			lblNewLabel_7.setForeground(Color.RED);
			lblNewLabel_7.setBounds(326, 89, 237, 14);
			contentPane.add(lblNewLabel_7);
			if (rs.first()) {
				id += rs.getInt(1)+1;
				String str = String.valueOf(id);
				lblNewLabel_1_1.setText(str);
			} else {
				lblNewLabel_1_1.setText("1");
			}

		} catch (Exception e) {
			JFrame jf = new JFrame();
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, e);
		}
		return id;
	}

	public boolean validateForm() {
		String firstName = textField.getText().trim();
		String lastName = textField_2.getText().trim();
		String age = textField_3.getText().trim();
		String country = textField_4.getText().trim();
		char[] passChars = passwordField.getPassword();
		String pass = new String(passChars).trim();

		if (firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || country.isEmpty() || pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(firstName.matches("\\d+") || lastName.matches("\\d+") || country.matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "The name fields and country fields cannot be a number!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
		    Integer.parseInt(age); // Try to parse the age to an integer
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "Age must be a valid numeric value!", "Error", JOptionPane.ERROR_MESSAGE);
		    return false;
		}

		return true;

	}
}