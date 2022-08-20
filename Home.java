
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Home implements ActionListener {
    JFrame frame = new JFrame("Home Page");
    JButton login, register, search;
    ImageIcon img;
    JPanel pan = new JPanel();
    JPanel pan1 = new JPanel();
    JLabel bImg, tag, Heading, rank, exam;
    JCheckBox eamcet, jeemain;
    JTextField rank_t;

    public Home() throws Exception {
        // TODO Auto-generated constructor stub

        initialize();
        add();
        frame.setSize(900, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // Terminate the program
            }
        });

    }

    public void initialize() {
        img = new ImageIcon("C:\\Users\\HEMANTH KUMAR VVS\\Downloads\\7.jpg");
        bImg = new JLabel("", img, JLabel.NORTH_EAST);
        bImg.setBounds(0, 0, 900, 600);

        tag = new JLabel("Want to estimate College with your rank??");
        tag.setFont(new Font("Arial", Font.BOLD, 18));
        tag.setAlignmentX(JLabel.CENTER);
        tag.setForeground(Color.WHITE);
        tag.setBounds(180, 100, 400, 100);

        rank = new JLabel("Rank : ");
        rank.setFont(new Font("Arial", Font.BOLD, 15));
        rank.setAlignmentX(JLabel.CENTER);
        rank.setForeground(Color.WHITE);
        rank.setBounds(180, 200, 100, 100);

        rank_t = new JTextField();
        rank_t.setBounds(320, 233, 100, 30);

        exam = new JLabel("Exam :");
        exam.setFont(new Font("Arial", Font.BOLD, 15));
        exam.setAlignmentX(JLabel.CENTER);
        exam.setForeground(Color.WHITE);
        exam.setBounds(180, 270, 100, 100);

        eamcet = new JCheckBox("EAMCET");
        eamcet.setBackground(Color.DARK_GRAY);
        eamcet.setForeground(Color.white);
        eamcet.setBounds(320, 300, 75, 40);

        jeemain = new JCheckBox("JEE-Main");
        jeemain.setBackground(Color.DARK_GRAY);
        jeemain.setForeground(Color.white);
        jeemain.setBounds(400, 300, 100, 40);

        search = new JButton("Search");
        search.setForeground(Color.WHITE);
        search.setBackground(Color.BLUE);
        search.setBounds(270, 350, 100, 30);
        search.addActionListener(this);

        login = new JButton("Login");
        login.setForeground(Color.WHITE);
        login.setBackground(Color.BLUE);
        login.setBounds(450, 75, 100, 30);
        login.addActionListener(this);

        register = new JButton("Register");
        register.setForeground(Color.WHITE);
        register.setBackground(Color.BLUE);
        register.setBounds(570, 75, 100, 30);
        register.addActionListener(this);

        Heading = new JLabel("Education College Guider");
        // Heading.setForeground(Color.WHITE);
        // Heading.setBackground(Color.ORANGE);
        Heading.setFont(new Font("Arial", Font.BOLD, 20));
        Heading.setBounds(270, 5, 400, 50);
        Heading.setAlignmentX(JLabel.CENTER);

        pan1.setAlignmentY(JPanel.TOP_ALIGNMENT);
        pan1.setBounds(5, 5, 780, 50);
        pan1.setBackground(Color.ORANGE);
        pan1.setLayout(null);

        pan.setAlignmentY(JPanel.TOP_ALIGNMENT);
        pan.setBounds(50, 50, 780, 450);
        pan.setBackground(Color.DARK_GRAY);
        pan.setLayout(null);

    }

    public void add() {
        pan1.add(Heading);
        pan.add(pan1);
        pan.add(tag);
        pan.add(rank);
        pan.add(exam);
        pan.add(eamcet);
        pan.add(jeemain);
        pan.add(rank_t);
        pan.add(register);
        pan.add(login);
        pan.add(search);
        frame.add(pan);
        frame.add(bImg);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            new Login();

        } else if (e.getSource() == register) {
            new Register();

        } else if (e.getSource() == search) {
            try {
                try {

                    int k = Integer.parseInt(rank_t.getText());
                    if (k <= 0) {
                        throw new Exception();
                    }
                    String url = "jdbc:mysql://localhost:3306/dbms_mini_project";
                    String uname = "root";
                    String pass = "Hemulpb35";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, pass);
                    Statement st = con.createStatement();
                    JFrame tab_frame = new JFrame("Table");
                    tab_frame.setSize(1000, 300);
                    tab_frame.setVisible(true);
                    String q1 = "select * from rank_table where end_rank>" + rank_t.getText() + ";";
                    ResultSet rs = st.executeQuery(q1);
                    String[] columns = { "College-ID", "College name", "Branch", "Caste", "End_rank" };
                    String[][] data = new String[20][5];
                    int i = 0;
                    while (rs.next()) {
                        int j = 0;
                        data[i][j++] = rs.getString(1);
                        data[i][j++] = rs.getString(2);
                        data[i][j++] = rs.getString(3);
                        data[i][j++] = rs.getString(4);
                        data[i][j++] = rs.getString(5);
                        i++;
                    }
                    JTable table = new JTable(data, columns);
                    tab_frame.add(new JScrollPane(table));
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(frame, " Please enter a valid rank ");
                }
            } catch (Exception e2) {
                System.out.println(e2);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        new Home();

    }

}
