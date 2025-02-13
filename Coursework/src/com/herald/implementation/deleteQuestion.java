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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class deleteQuestion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public deleteQuestion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Delete Question");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel.setBounds(54, 42, 268, 83);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 136, 1057, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(125, 205, 88, 100);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(256, 238, 141, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idText = (textField.getText());
				if (idText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter an ID!", "Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}
				try {
					int id = Integer.parseInt(idText);
					Connection conn = IConnectionString.getConnection();
					PreparedStatement ps = conn.prepareStatement("delete from questions where id=?");
					ps.setLong(1,id);
					int rows = ps.executeUpdate();
					
					if(rows > 0) {
						JOptionPane.showMessageDialog(null, "Deleted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
						new deleteQuestion().setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Id not found", "Error", JOptionPane.ERROR_MESSAGE);
					}
					ps.close();
				}
				catch(Exception ex) {
					JFrame jf = new JFrame();
					jf.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(jf, ex);
					
				}
			}
		});
		btnNewButton_1.setBounds(192, 347, 112, 40);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField.setEditable(true);
			}
		});
		btnNewButton_1_1.setBounds(384, 347, 104, 40);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminHome().setVisible(true);
			}
		});
		btnNewButton.setBounds(493, 60, 104, 33);
		contentPane.add(btnNewButton);
	}

}
