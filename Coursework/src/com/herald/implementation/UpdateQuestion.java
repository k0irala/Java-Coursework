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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class UpdateQuestion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField questionField;
	private JTextField optionOneField;
	private JTextField optionTwoField;
	private JTextField optionThreeField;
	private JTextField optionFourField;
	private JTextField answerField;
	private JTextField idField;
	/**
	 * Create the frame.
	 */
	public UpdateQuestion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1055, 751);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Question");
		lblNewLabel.setBounds(68, 31, 233, 96);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 119, 1534, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(68, 158, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Question");
		lblNewLabel_3.setBounds(65, 215, 59, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Option1");
		lblNewLabel_4.setBounds(68, 277, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Option2");
		lblNewLabel_4_1.setBounds(68, 337, 46, 14);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Option3");
		lblNewLabel_4_2.setBounds(68, 398, 46, 14);
		contentPane.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("Option4");
		lblNewLabel_4_3.setBounds(65, 462, 46, 14);
		contentPane.add(lblNewLabel_4_3);
		
		JLabel lblNewLabel_4_4 = new JLabel("Answer");
		lblNewLabel_4_4.setBounds(65, 518, 46, 14);
		contentPane.add(lblNewLabel_4_4);
		
		questionField = new JTextField();
		questionField.setBounds(188, 212, 817, 20);
		contentPane.add(questionField);
		questionField.setColumns(10);
		
		optionOneField = new JTextField();
		optionOneField.setBounds(191, 274, 814, 20);
		optionOneField.setColumns(10);
		contentPane.add(optionOneField);
		
		optionTwoField = new JTextField();
		optionTwoField.setBounds(191, 334, 814, 20);
		optionTwoField.setColumns(10);
		contentPane.add(optionTwoField);
		
		optionThreeField = new JTextField();
		optionThreeField.setBounds(191, 395, 814, 20);
		optionThreeField.setColumns(10);
		contentPane.add(optionThreeField);
		
		optionFourField = new JTextField();
		optionFourField.setBounds(188, 459, 817, 20);
		optionFourField.setColumns(10);
		contentPane.add(optionFourField);
		
		answerField = new JTextField();
		answerField.setBounds(188, 515, 817, 20);
		answerField.setColumns(10);
		contentPane.add(answerField);
		
		JLabel lblNewLabel_5 = new JLabel("Level");
		lblNewLabel_5.setBounds(68, 578, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(191, 574, 152, 22);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Beginner", "Intermediate", "Advanced"}));
		contentPane.add(comboBox);
		
		
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questionField.setText("");
				optionOneField.setText("");
				optionTwoField.setText("");
				optionThreeField.setText("");
				optionFourField.setText("");
				answerField.setText("");
				idField.setEditable(true);
			}
		});
		btnClear.setBounds(364, 638, 89, 23);
		contentPane.add(btnClear);
		
		idField = new JTextField();
		idField.setBounds(188, 155, 86, 20);
		contentPane.add(idField);
		idField.setColumns(10);
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idField.getText());
				String question = questionField.getText();
				String optionOne = optionOneField.getText();
				String optionTwo = optionTwoField.getText();
				String optionThree = optionThreeField.getText();
				String optionFour  = optionFourField.getText();
				String correctAnswer = answerField.getText();
				String difficuilty = comboBox.getSelectedItem().toString();
				try {
					Connection conn = IConnectionString.getConnection();
					PreparedStatement ps = conn.prepareStatement("Update questions set name=?,opt1=?,opt2=?,opt3=?,opt4=?,answer=?,level=? where id=?");
					ps.setString(1, question);
					ps.setString(2, optionOne);
					ps.setString(3, optionTwo);
					ps.setString(4, optionThree);
					ps.setString(5, optionFour);
					ps.setString(6,correctAnswer);
					ps.setString(7, difficuilty);
					ps.setLong(8, id);
					
					ps.executeUpdate();
					JFrame jf = new JFrame();
					jf.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(jf,"Successfully Updated");
					setVisible(false);
					new UpdateQuestion().setVisible(true);
					
				}
				catch(Exception ex) {
					JFrame jf = new JFrame();
					jf.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(jf, ex);
				}
				
			}
		});
		btnNewButton.setBounds(188, 638, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idText = (idField.getText());
				if(idText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter an ID!", "Error", JOptionPane.ERROR_MESSAGE);
				    return; 
				}
				
				try {
					int id = Integer.parseInt(idText);
					Connection conn = IConnectionString.getConnection();
					Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ResultSet rs = st.executeQuery("Select * from questions where id ='"+id+"'");
					if(rs.first()) {
						questionField.setText(rs.getString(2));
						optionOneField.setText(rs.getString(3));
						optionTwoField.setText(rs.getString(4));
						optionThreeField.setText(rs.getString(5));
						optionFourField.setText(rs.getString(6));
						answerField.setText(rs.getString(7));
						idField.setEditable(false);
						comboBox.setSelectedItem(rs.getString(8));
					}
					else {
						JFrame jf = new JFrame();
						jf.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(jf, "Id not found");
					}
				}
				catch(SQLException ex) {
					JFrame jf = new JFrame();
					jf.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(jf, ex);
				}
			}
		});
		btnNewButton_1.setBounds(310, 154, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Close");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminHome().setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(916, 54, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
	}
}
