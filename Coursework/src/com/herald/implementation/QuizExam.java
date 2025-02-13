package com.herald.implementation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.herald.connections.IConnectionString;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;

public class QuizExam extends JFrame {

    public String questionId = "1";
    public String answer = "";
    public int scores = 0;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JRadioButton rdbtnNewRadioButton = new JRadioButton("");
    private JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");
    private JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("");
    private JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("");
    private JButton btnNewButton = new JButton("Next");
    private JLabel lblNewLabel_3 = new JLabel("");
    private JLabel lblNewLabel_6 = new JLabel("");
    private int currentQuestionIndex = 0;  // Track the index of the current question
    private List<String[]> unusedQuestions = new ArrayList<>();  // Store all questions

    public QuizExam(int CompetitorId) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 949, 541);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Quiz Exam");
        lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        lblNewLabel.setBounds(60, 24, 171, 52);
        contentPane.add(lblNewLabel);

        JSeparator separator = new JSeparator();
        separator.setBounds(44, 87, 877, 2);
        contentPane.add(separator);

        JLabel lblNewLabel_1 = new JLabel("Date:");
        lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(575, 38, 64, 28);
        contentPane.add(lblNewLabel_1);

        JLabel dateLabel = new JLabel("");
        dateLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        dateLabel.setBounds(629, 46, 125, 14);
        contentPane.add(dateLabel);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        dateLabel.setText(dateFormat.format(date));

        JLabel lblNewLabel_2 = new JLabel("Competitor ID:");
        lblNewLabel_2.setBounds(242, 111, 102, 34);
        contentPane.add(lblNewLabel_2);

        lblNewLabel_3.setBounds(327, 121, 46, 14);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_3_1 = new JLabel("Competitor Name:");
        lblNewLabel_3_1.setBounds(455, 121, 102, 14);
        contentPane.add(lblNewLabel_3_1);

        JLabel lblNewLabel_3_2 = new JLabel("");
        lblNewLabel_3_2.setBounds(571, 121, 125, 14);
        contentPane.add(lblNewLabel_3_2);

        JLabel lblNewLabel_4 = new JLabel("Question");
        lblNewLabel_4.setBounds(166, 159, 65, 14);
        contentPane.add(lblNewLabel_4);

        textField = new JTextField();
        textField.setBounds(242, 156, 565, 17);
        contentPane.add(textField);
        textField.setColumns(10);

        rdbtnNewRadioButton.setBounds(242, 196, 564, 23);
        contentPane.add(rdbtnNewRadioButton);

        rdbtnNewRadioButton_1.setBounds(242, 242, 564, 23);
        contentPane.add(rdbtnNewRadioButton_1);

        rdbtnNewRadioButton_2.setBounds(242, 291, 564, 23);
        contentPane.add(rdbtnNewRadioButton_2);

        rdbtnNewRadioButton_3.setBounds(242, 336, 564, 23);
        contentPane.add(rdbtnNewRadioButton_3);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                nextQuestion();
            }
        });
        btnNewButton.setBounds(255, 382, 89, 23);
        contentPane.add(btnNewButton);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "Do you really want to Submit?", "Select",
                        JOptionPane.YES_NO_OPTION);
                if (a == 0) {
                    checkAnswer();
                    submit();
                    setVisible(false);
                    new StudentLogin().setVisible(true);
                }
            }
        });
        btnSubmit.setBounds(429, 382, 89, 23);
        contentPane.add(btnSubmit);

        lblNewLabel_3.setText(String.valueOf(CompetitorId));

        JLabel lblNewLabel_5 = new JLabel("Question No:");
        lblNewLabel_5.setBounds(300, 48, 80, 28);
        contentPane.add(lblNewLabel_5);

        lblNewLabel_6.setBounds(378, 51, 64, 21);
        contentPane.add(lblNewLabel_6);

        lblNewLabel_6.setText(questionId);

        JLabel lblNewLabel_7 = new JLabel("Attempt:");
        lblNewLabel_7.setBounds(776, 10, 64, 13);
        contentPane.add(lblNewLabel_7);

        JLabel attemptLabel = new JLabel("");
        attemptLabel.setBounds(851, 10, 45, 13);
        contentPane.add(attemptLabel);

        textField.setEditable(false);

        int attempt = StudentDashboard.getAttemptFromDatabase(CompetitorId);
        attemptLabel.setText(String.valueOf(attempt));

        loadQuestions(CompetitorId); // Load the questions into the unusedQuestions list

    }

    private void loadQuestions(int CompetitorId) {
        List<String[]> allQuestions = new ArrayList<>();

        try (Connection conn = IConnectionString.getConnection()) {
            String sqlUser = "SELECT level FROM users WHERE competitorId = '"+CompetitorId+"'";
            String level = "";

            try (PreparedStatement psUser = conn.prepareStatement(sqlUser)) {
                try (ResultSet rs = psUser.executeQuery()) {
                    if (rs.next()) {
                        level = rs.getString("level");
                    }
                }
            }

            String sqlQuestions = "SELECT name, opt1, opt2, opt3, opt4, answer FROM questions WHERE level = ?";
            try (PreparedStatement psQuestions = conn.prepareStatement(sqlQuestions)) {
                psQuestions.setString(1, level);

                try (ResultSet rs1 = psQuestions.executeQuery()) {
                    while (rs1.next()) {
                        allQuestions.add(new String[]{
                                rs1.getString("name"),
                                rs1.getString("opt1"),
                                rs1.getString("opt2"),
                                rs1.getString("opt3"),
                                rs1.getString("opt4"),
                                rs1.getString("answer")
                        });
                    }
                }
            }

            unusedQuestions.addAll(allQuestions);

            // Shuffle the questions to randomize
            Collections.shuffle(unusedQuestions);

            // Show the first question
            if (!unusedQuestions.isEmpty()) {
                showQuestion(unusedQuestions.get(0));
                unusedQuestions.remove(0);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching questions: " + e.getMessage());
        }
    }

    private void showQuestion(String[] questionData) {
        textField.setText(questionData[0]);
        rdbtnNewRadioButton.setText(questionData[1]);
        rdbtnNewRadioButton_1.setText(questionData[2]);
        rdbtnNewRadioButton_2.setText(questionData[3]);
        rdbtnNewRadioButton_3.setText(questionData[4]);
        answer = questionData[5];
    }

    public void checkAnswer() {
        String studentAnswer = "";
        if (rdbtnNewRadioButton.isSelected()) {
            studentAnswer = rdbtnNewRadioButton.getText();
        } else if (rdbtnNewRadioButton_1.isSelected()) {
            studentAnswer = rdbtnNewRadioButton_1.getText();
        } else if (rdbtnNewRadioButton_2.isSelected()) {
            studentAnswer = rdbtnNewRadioButton_2.getText();
        } else {
            studentAnswer = rdbtnNewRadioButton_3.getText();
        }
        if (studentAnswer.equals(answer)) {
            scores++;
        }

        int questionID1 = Integer.parseInt(questionId);
        questionID1 += 1;
        questionId = String.valueOf(questionID1);
        lblNewLabel_6.setText(questionId);

        rdbtnNewRadioButton.setSelected(false);
        rdbtnNewRadioButton_1.setSelected(false);
        rdbtnNewRadioButton_2.setSelected(false);
        rdbtnNewRadioButton_3.setSelected(false);

        if (questionId.equals("5")) {
            btnNewButton.setVisible(false);
        }
    }

    public void nextQuestion() {
        // If there are remaining questions, show the next one
        if (!unusedQuestions.isEmpty()) {
            showQuestion(unusedQuestions.get(0));
            unusedQuestions.remove(0);
        }
    }

    public void submit() {
        String CompetitorID = lblNewLabel_3.getText();
        checkAnswer();
        try {
            String sql = "";
            Connection conn = IConnectionString.getConnection();
            int attempt = StudentDashboard.getAttemptFromDatabase(Integer.parseInt(CompetitorID));

            if (attempt == 1) {
                sql += "UPDATE users SET score1 = ? where CompetitorID = ?";
            }
            if (attempt == 2) {
                sql += "UPDATE users SET score2 = ? where CompetitorID = ?";
            }
            if (attempt == 3) {
                sql += "UPDATE users SET score3 = ? where CompetitorID = ?";
            }
            attempt++;
            setAttemptToDatabase(attempt);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, scores);
            ps.setString(2, CompetitorID);

            int rowsAffected = ps.executeUpdate();

            String marks1 = String.valueOf(scores);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Your Score is:" + marks1, "Score",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("User not found or all scores are already filled.");
            }

            ps.close();
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void setAttemptToDatabase(int attempt) {
        String CompetitorID = lblNewLabel_3.getText();
        try {
            Connection conn = IConnectionString.getConnection();
            String sql = "UPDATE users SET attempts = ? where competitorId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, attempt);
            ps.setInt(2, Integer.parseInt(CompetitorID));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Your Attempts are updated", "Attempts",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
