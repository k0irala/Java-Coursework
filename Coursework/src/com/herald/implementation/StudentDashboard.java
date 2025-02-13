package implementation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.HashMap;
import java.util.Map;
import connections.IConnectionString;
import enums.CompetitionLevel;
import models.Competitor;
import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StudentDashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JPanel tablePanel;

    /**
     * Create the frame.
     */
    public StudentDashboard(String id, String firstName, String middleName, String lastName) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 979, 614);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel shortDetailsPanel = new JPanel();
        shortDetailsPanel.setBounds(305, 169, 485, 307);
        contentPane.add(shortDetailsPanel);

        tablePanel = new JPanel();
        tablePanel.setBounds(317, 169, 485, 307);
        contentPane.add(tablePanel);
        tablePanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(35, 23, 407, 262);
        tablePanel.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);


        JPanel fullDetailsPanel = new JPanel();
        fullDetailsPanel.setBounds(303, 169, 503, 319);
        contentPane.add(fullDetailsPanel);
        fullDetailsPanel.setLayout(null);

        JTextArea fullDetailsTextArea = new JTextArea();
        fullDetailsTextArea.setBounds(31, 11, 445, 90);
        fullDetailsPanel.add(fullDetailsTextArea);
        shortDetailsPanel.setLayout(null);

        JTextArea shortDetailsTextArea = new JTextArea();
        shortDetailsTextArea.setBounds(32, 22, 407, 60);
        shortDetailsPanel.add(shortDetailsTextArea);

        JPanel statisticalSummaryPanel = new JPanel();
        statisticalSummaryPanel.setBounds(224, 180, 650, 313);
        contentPane.add(statisticalSummaryPanel);
        statisticalSummaryPanel.setLayout(null);

        JTextArea StatisticalSummaryTextArea = new JTextArea();
        StatisticalSummaryTextArea.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
        StatisticalSummaryTextArea.setBounds(27, 22, 591, 169);
        statisticalSummaryPanel.add(StatisticalSummaryTextArea);

        shortDetailsPanel.setVisible(false);
        fullDetailsPanel.setVisible(false);
        tablePanel.setVisible(false);
        statisticalSummaryPanel.setVisible(false);

        Competitor details = fetchDataFromDatabase(Integer.parseInt(id), firstName, middleName, lastName);
        JButton btnNewButton = new JButton("View Short Details");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shortDetailsTextArea.setText("Short Details for CompetitorID " + id);
                shortDetailsTextArea.append(details.getShortDetails());
                shortDetailsPanel.setVisible(true);
                fullDetailsPanel.setVisible(false);
                tablePanel.setVisible(false);
                statisticalSummaryPanel.setVisible(false);
            }
        });

        btnNewButton.setBounds(24, 180, 158, 74);
        contentPane.add(btnNewButton);

        JButton btnViewFullDetails = new JButton("View Full Details");
        btnViewFullDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fullDetailsTextArea.setText("Full Details for CompetitorID " + id + "\n");
                fullDetailsTextArea.append(details.getFullDetails());
                fullDetailsPanel.setVisible(true);
                shortDetailsPanel.setVisible(false);
                tablePanel.setVisible(false);
                statisticalSummaryPanel.setVisible(false);
            }
        });

        btnViewFullDetails.setBounds(24, 285, 158, 74);
        contentPane.add(btnViewFullDetails);

        JLabel lblNewLabel = new JLabel("Competitor ID");
        lblNewLabel.setBounds(24, 29, 130, 32);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(149, 29, 68, 32);
        contentPane.add(lblNewLabel_2);

        JButton btnNewButton_1_1 = new JButton("View Leaderboard");
        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                tablePanel.setVisible(true);
                shortDetailsPanel.setVisible(false);
                fullDetailsPanel.setVisible(false);
                statisticalSummaryPanel.setVisible(false);
                int id = Integer.parseInt(lblNewLabel_2.getText());
                int attempt = getAttemptFromDatabase(id);
                if (attempt <= 3) {
                    JOptionPane.showMessageDialog(null, "You Should play the quiz 3 times to generate the leaderboard!",
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try (Connection conn = IConnectionString.getConnection();) {
                    String sql = "Select firstName,middleName,lastName,level,score1,score2,score3 from users where competitorId !=101 order by (COALESCE(score1, 0) + COALESCE(score2, 0) + COALESCE(score3, 0)) desc";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        try (ResultSet rs = ps.executeQuery()) {
                            table.setModel(DbUtils.resultSetToTableModel(rs));
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

        });
        btnNewButton_1_1.setBounds(24, 390, 158, 86);
        contentPane.add(btnNewButton_1_1);

        lblNewLabel_2.setText(id);

        JLabel lblNewLabel_1 = new JLabel("Competitor Name:");
        lblNewLabel_1.setBounds(24, 93, 115, 22);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(149, 88, 276, 32);
        contentPane.add(lblNewLabel_3);

        lblNewLabel_3.setText(firstName + " " + lastName);

        JButton btnNewButton_1 = new JButton("Play Quiz");
        btnNewButton_1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(lblNewLabel_2.getText());
                int attempt = getAttemptFromDatabase(id);
                if (attempt > 3) {
                    JOptionPane.showMessageDialog(null, "You have exceeded your attempts!! Only 3 attempts allowed",
                            "Failure", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    new StudentRegistration().setVisible(true);
                } else {
                    setVisible(false);
                    new QuizExam(id).setVisible(true);
                }
            }
        });
        btnNewButton_1.setBounds(482, 487, 142, 47);
        contentPane.add(btnNewButton_1);

        JButton staisticSummaryBtn = new JButton("Statistical Summary");
        staisticSummaryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statisticalSummaryPanel.setVisible(true);
                shortDetailsPanel.setVisible(false);
                fullDetailsPanel.setVisible(false);
                tablePanel.setVisible(false);
                ArrayList<String> highestScoreDetails = getHighestScore();
                String firstName = highestScoreDetails.get(0);
                String lastName = highestScoreDetails.get(1);
                String highestScore = highestScoreDetails.get(2);

                StatisticalSummaryTextArea.setFont(new Font("Arial", Font.BOLD, 14));
                StatisticalSummaryTextArea.setText("Statistical Summary:\n");
                StatisticalSummaryTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
                StatisticalSummaryTextArea.append("    Competitor with highest Score:" + firstName + " " + lastName
                        + " with an overall score of:" + highestScore + "\n");
                StatisticalSummaryTextArea.append("    Frequency of Individual Scores:\n");

                Map<Integer, Integer> scoreFrequencies = getOverallScoreFrequencies();
                for (Map.Entry<Integer, Integer> entry : scoreFrequencies.entrySet()) {
                    if(entry.getKey() == 0) continue;
                    StatisticalSummaryTextArea.append("    Score: " + entry.getKey() + " occurs " + entry.getValue() + " times\n");
                }
            }
        });
        staisticSummaryBtn.setBounds(439, 93, 191, 65);
        contentPane.add(staisticSummaryBtn);
        
        JButton btnNewButton_2 = new JButton("Log Out");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                dispose();
                new IndexPage().setVisible(true);
        		
        	}
        });
        btnNewButton_2.setBounds(765, 24, 85, 21);
        contentPane.add(btnNewButton_2);

    }

    public Competitor fetchDataFromDatabase(int id, String firstName, String middleName, String lastName) {
        Competitor comp = new Competitor(id, firstName, middleName, lastName);
        double total = 0;
        try {
            ArrayList<Integer> scores = new ArrayList<Integer>();
            Connection conn = IConnectionString.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "Select country,age,level,score1,score2,score3 from users where CompetitorID='" + id + "'");
            while (rs.next()) {
                String country = rs.getString(1);
                String age = rs.getString(2);
                String level = rs.getString(3);
                // if scores are null then set them to 0
                if (rs.getString(4) == null) {
                    scores.add(0);
                } else {
                    scores.add(Integer.parseInt(rs.getString(4)));
                }
                if (rs.getString(5) == null) {
                    scores.add(0);
                } else {
                    scores.add(Integer.parseInt(rs.getString(5)));
                }
                if (rs.getString(6) == null) {
                    scores.add(0);
                } else {
                    scores.add(Integer.parseInt(rs.getString(6)));
                }
                comp.setCountry(country);
                comp.setAge(Integer.parseInt(age));

                CompetitionLevel lv = CompetitionLevel.valueOf(level);
                comp.setLevel(lv);
                comp.setScore(scores);
                for (int score : scores) {
                    total += score;
                }
                comp.setOverAllScore(total);

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return comp;
    }

    public static int getAttemptFromDatabase(int id) {
        int attempts = 0;
        try {
            Connection conn = IConnectionString.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select attempts from users where competitorId='" + id + "'");
            while (rs.next()) {
                attempts += rs.getInt(1);
            }
        } catch (Exception ex) {

        }
        return attempts;
    }

    public ArrayList<String> getHighestScore() {
        ArrayList<String> highestScoreDetails = new ArrayList<String>();
        try {
            Connection conn = IConnectionString.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "Select firstName,lastName,MAX((COALESCE(score1, 0) + COALESCE(score2, 0) + COALESCE(score3, 0)) / 3) AS highestScore from users GROUP BY firstName,lastName ORDER BY highestScore DESC LIMIT 1");
            if (rs.next()) {
                highestScoreDetails.add(rs.getString(1));
                highestScoreDetails.add(rs.getString(2));
                highestScoreDetails.add(String.valueOf(rs.getDouble(3)));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return highestScoreDetails;
    }

    public Map<Integer, Integer> getOverallScoreFrequencies() {
        String query = "SELECT score, COUNT(*) AS frequency FROM (" +
                "SELECT score1 AS score FROM users " +
                "UNION ALL " +
                "SELECT score2 FROM users " +
                "UNION ALL " +
                "SELECT score3 FROM users" +
                ") AS all_scores GROUP BY score ORDER BY frequency DESC";

        Map<Integer, Integer> scoreFrequencies = new HashMap<>();

        try (Connection conn = IConnectionString.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                scoreFrequencies.put(rs.getInt("score"), rs.getInt("frequency"));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return scoreFrequencies;
    }


}
