package implementation;

import connections.IConnectionString;
import net.proteanit.sql.DbUtils;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class AdminHome extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable allQuestionsTable;
    private JTable allStudentsTable;


    /**
     * Create the frame.
     */
    public AdminHome() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(90, 30, 1254, 837);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setResizable(false);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Operations");
        lblNewLabel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 41));
        lblNewLabel.setBounds(47, 47, 293, 38);
        contentPane.add(lblNewLabel);

        JPanel allQuestionPanel = new JPanel();
        allQuestionPanel.setBounds(501, 290, 685, 411);
        contentPane.add(allQuestionPanel);
        allQuestionPanel.setLayout(null);

        JScrollPane allQuestionsScrollPane = new JScrollPane();
        allQuestionsScrollPane.setBounds(47, 29, 615, 325);
        allQuestionPanel.add(allQuestionsScrollPane);
        allQuestionsTable = new JTable();
        allQuestionsScrollPane.setViewportView(allQuestionsTable);

        JPanel allStudentsPanel = new JPanel();
        allStudentsPanel.setBounds(501, 277, 685, 411);
        contentPane.add(allStudentsPanel);
        allStudentsPanel.setLayout(null);

        JScrollPane allStudentsScrollPane = new JScrollPane();
        allStudentsScrollPane.setBounds(35, 32, 618, 337);
        allStudentsPanel.add(allStudentsScrollPane);

        allStudentsTable = new JTable();
        allStudentsScrollPane.setViewportView(allStudentsTable);

        allStudentsPanel.setVisible(false);
        allQuestionPanel.setVisible(false);

        JButton allStudentRecord = new JButton("All Student Records");
        allStudentRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                allStudentRecord();
                allStudentsPanel.setVisible(true);
                allQuestionPanel.setVisible(false);

            }
        });
        allStudentRecord.setBounds(562, 143, 158, 82);
        contentPane.add(allStudentRecord);

        JButton addQuestionBtn = new JButton("Add Question");
        addQuestionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddQuestion().setVisible(true);
            }
        });
        addQuestionBtn.setBounds(100, 113, 130, 216);
        contentPane.add(addQuestionBtn);

        JButton updateQuestion = new JButton("Update Question");
        updateQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    dispose();
                    new UpdateQuestion().setVisible(true);

            }
        });
        updateQuestion.setBounds(100, 346, 130, 216);
        contentPane.add(updateQuestion);

        JButton delQuestion = new JButton("Delete Question");
        delQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new deleteQuestion().setVisible(true);
            }
        });
        delQuestion.setBounds(100, 572, 130, 216);
        contentPane.add(delQuestion);

        JButton allQuestionsBtn = new JButton("Display All Questions");
        allQuestionsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                allQuestions();
                allStudentsPanel.setVisible(false);
                allQuestionPanel.setVisible(true);

            }
        });
        allQuestionsBtn.setBounds(983, 146, 158, 77);
        contentPane.add(allQuestionsBtn);

        JButton logOutBtn = new JButton("Log Out");
        logOutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame();
                jf.setAlwaysOnTop(true);

                int a = JOptionPane.showConfirmDialog(jf, "Are you Sure!", "Select", JOptionPane.YES_NO_OPTION);
                if (a == 0) {
                    setVisible(false);
                    new LoginPageAdmin().setVisible(true);
                }

            }
        });
        logOutBtn.setBounds(983, 47, 89, 23);
        contentPane.add(logOutBtn);

        JButton exitAppBtn = new JButton("Exit");
        exitAppBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame();
                jf.setAlwaysOnTop(true);

                int a = JOptionPane.showConfirmDialog(jf, "Are you Sure!", "Select", JOptionPane.YES_NO_OPTION);
                if (a == 0) {
                    System.exit(0);
                }
            }
        });
        exitAppBtn.setBounds(1082, 47, 89, 23);
        contentPane.add(exitAppBtn);



    }

    public void allStudentRecord() {
        try {
            String query = "select * from users";
            Connection conn = IConnectionString.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            allStudentsTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void allQuestions() {
        try {
            String query = "select * from questions";
            Connection conn = IConnectionString.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            allQuestionsTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
