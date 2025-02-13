package com.herald.implementation;

import java.sql.*;

import javax.swing.JOptionPane;

import com.herald.connections.IConnectionString;

public class DatabaseOperations {
	public int AddQuestion(int id, String question, String firstOption, String secondOption, String thirdOption,
			String fourthOption, String answer, String difficulty) {
		int rowsAffected = 0;
		try {
			Connection conn = IConnectionString.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO questions (id, name, opt1, opt2, opt3, opt4, answer, level) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, id);
			ps.setString(2, question);
			ps.setString(3, firstOption);
			ps.setString(4, secondOption);
			ps.setString(5, thirdOption);
			ps.setString(6, fourthOption);
			ps.setString(7, answer);
			ps.setString(8, difficulty);
			rowsAffected += ps.executeUpdate();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);

		}
		return rowsAffected;
	}

}
