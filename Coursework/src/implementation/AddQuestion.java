package implementation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connections.IConnectionString;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddQuestion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField questionField;
	private JTextField optionOne;
	private JTextField optionTwo;
	private JTextField optionThree;
	private JTextField optionFour;
	private JTextField correctAnswr;
	private DatabaseOperations dbOperations;

	/**
	 * Create the frame.
	 */
	public AddQuestion() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 998, 745);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add New Question");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel.setBounds(77, 23, 233, 96);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 128, 1536, 2);
		contentPane.add(separator);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(77, 168, 79, 40);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(173, 181, 49, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_2 = new JLabel("Question");
		lblNewLabel_2.setBounds(77, 242, 66, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Option 1");
		lblNewLabel_2_1.setBounds(77, 289, 49, 14);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Option 2");
		lblNewLabel_2_2.setBounds(77, 345, 49, 14);
		contentPane.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Option 3");
		lblNewLabel_2_3.setBounds(77, 391, 49, 14);
		contentPane.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_4 = new JLabel("Option 4");
		lblNewLabel_2_4.setBounds(77, 442, 49, 14);
		contentPane.add(lblNewLabel_2_4);

		JLabel lblNewLabel_2_5 = new JLabel("Answer");
		lblNewLabel_2_5.setBounds(77, 497, 49, 14);
		contentPane.add(lblNewLabel_2_5);

		questionField = new JTextField();
		questionField.setColumns(10);
		questionField.setBounds(172, 239, 802, 20);
		contentPane.add(questionField);

		optionOne = new JTextField();
		optionOne.setColumns(10);
		optionOne.setBounds(172, 286, 802, 20);
		contentPane.add(optionOne);

		optionTwo = new JTextField();
		optionTwo.setColumns(10);
		optionTwo.setBounds(172, 342, 802, 20);
		contentPane.add(optionTwo);

		optionThree = new JTextField();
		optionThree.setColumns(10);
		optionThree.setBounds(172, 388, 802, 20);
		contentPane.add(optionThree);

		optionFour = new JTextField();
		optionFour.setColumns(10);
		optionFour.setBounds(172, 439, 802, 20);
		contentPane.add(optionFour);

		correctAnswr = new JTextField();
		correctAnswr.setColumns(10);
		correctAnswr.setBounds(172, 494, 802, 20);
		contentPane.add(correctAnswr);

		try {
			Connection conn = IConnectionString.getConnection();
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("Select count(id) from questions");
			if (rs.first()) {
				int id = rs.getInt(1);
				id++;
				String str = String.valueOf(id);
				lblNewLabel_4.setText(str);
			} else {
				lblNewLabel_4.setText("1");
			}
		} catch (Exception e) {
			JFrame jf = new JFrame();
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, e);
		}

		JComboBox<String> level = new JComboBox<String>();
		level.setModel(new DefaultComboBoxModel<String>(new String[] { "Beginner", "Intermediate", "Advanced" }));
		level.setBounds(172, 540, 138, 22);
		contentPane.add(level);

		JLabel lblNewLabel_3 = new JLabel("Level");
		lblNewLabel_3.setBounds(77, 544, 49, 14);
		contentPane.add(lblNewLabel_3);

		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!validateForm()) return;
				int id = Integer.parseInt(lblNewLabel_4.getText());
				String question = questionField.getText();
				String firstOption = optionOne.getText();
				String secondOption = optionTwo.getText();
				String thirdOption = optionThree.getText();
				String fourthOption = optionFour.getText();
				String answer = correctAnswr.getText();
				String difficulty = level.getSelectedItem().toString();
				int rowsAffected = dbOperations.AddQuestion(id,question,firstOption,secondOption,thirdOption,fourthOption,answer,difficulty);
				if (rowsAffected>0) {
					 JOptionPane.showMessageDialog(null,"Successfully Updated","Success",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					 JOptionPane.showMessageDialog(null,"Could not insert Questions","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		addBtn.setBounds(179, 597, 89, 23);
		contentPane.add(addBtn);

		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				questionField.setText("");
				optionOne.setText("");
				optionTwo.setText("");
				optionThree.setText("");
				optionFour.setText("");
				correctAnswr.setText("");
			}
		});
		clearBtn.setBounds(325, 597, 89, 23);
		contentPane.add(clearBtn);

		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminHome().setVisible(true);
			}
		});
		closeBtn.setBounds(864, 23, 98, 34);
		contentPane.add(closeBtn);
		
		

	}
	public boolean validateForm(){
		 String question = questionField.getText().trim();
	        String firstOption = optionOne.getText().trim();
	        String secondOption = optionTwo.getText().trim();
	        String thirdOption = optionThree.getText().trim();
	        String fourthOption = optionFour.getText().trim();
	        String answer = correctAnswr.getText().trim();
 
	        if (question.isEmpty() || firstOption.isEmpty() || secondOption.isEmpty() ||
	                thirdOption.isEmpty() || fourthOption.isEmpty() || answer.isEmpty()) {
	            JOptionPane.showMessageDialog(null,"All fields must be filled out!","Error",JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	        if (firstOption.equalsIgnoreCase(secondOption) || firstOption.equalsIgnoreCase(thirdOption) ||
	                firstOption.equalsIgnoreCase(fourthOption) || secondOption.equalsIgnoreCase(thirdOption) ||
	                secondOption.equalsIgnoreCase(fourthOption) || thirdOption.equalsIgnoreCase(fourthOption)) {
	        	JOptionPane.showMessageDialog(null,"Options must be unique","Error",JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	        if (!(answer.equalsIgnoreCase(firstOption) || answer.equalsIgnoreCase(secondOption) ||
	                answer.equalsIgnoreCase(thirdOption) || answer.equalsIgnoreCase(fourthOption))) {
	        	JOptionPane.showMessageDialog(null,"Correct answers do not match the options","Error",JOptionPane.ERROR_MESSAGE);
	        	return false;
	        }
	        return true;

	    }

}
