package implementation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class LoginPageAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private final String adminUser = "gaurav";
	private final String adminPass = "admin";


	/**
	 * Create the frame.
	 */
	public LoginPageAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 427);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 235, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(150, 114, 68, 13);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(150, 144, 165, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(150, 173, 82, 13);
		contentPane.add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(150, 196, 165, 19);
		passwordField.setEchoChar('*');
		contentPane.add(passwordField);

		JButton loginBtn = new JButton("Login");
		loginBtn.setBounds(147, 259, 85, 21);
		
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals(adminUser)
						&& String.valueOf(passwordField.getPassword()).equals(adminPass)) {
					JOptionPane pane = new JOptionPane("Login Successful!", JOptionPane.INFORMATION_MESSAGE);
					JDialog dialog = pane.createDialog(null,"Success");
					Timer timer = new Timer(500, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							dialog.dispose();
							setVisible(false);
							new AdminHome().setVisible(true);
						}
					});
					timer.setRepeats(false);
					timer.start();
					
					dialog.setModal(false);
					dialog.setVisible(true);
				} else {
					JOptionPane pane = new JOptionPane("Incorrect Username or Password!!",JOptionPane.ERROR_MESSAGE);
					JDialog dialog = pane.createDialog(null,"Error");
					Timer timer = new Timer(500, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							dialog.dispose();
							setVisible(false);
							new LoginPageAdmin().setVisible(true);
						}
					});
					timer.setRepeats(false);
					timer.start();
					
					dialog.setModal(false);
					dialog.setVisible(true);
				}
			}
		});
		contentPane.add(loginBtn);

		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new IndexPage().setVisible(true);
			}
		});
		backBtn.setBounds(259, 259, 85, 21);
		contentPane.add(backBtn);

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
		chckbxNewCheckBox.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox.setBounds(179, 221, 118, 21);
		contentPane.add(chckbxNewCheckBox);
	}
}
