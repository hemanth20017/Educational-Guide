import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Interface implements ActionListener {
    JLabel title, bimg, bimg2;
    JFrame frame = new JFrame();
    JRadioButton choice1;
    JRadioButton choice2;
    JRadioButton choice3;
    JRadioButton choice4;
    ImageIcon img, img2;
    ButtonGroup bg = new ButtonGroup();
    JButton home;

    public Interface() {
        initialize();
        adder();
        frame.setSize(600, 600);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Services provided");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // Terminate the program
            }
        });
    }

    public void initialize() {
        title = new JLabel("The services provided by us are :");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setAlignmentX(JLabel.CENTER);
        title.setBounds(100, 0, 400, 100);

        img = new ImageIcon("C:\\Users\\HEMANTH KUMAR VVS\\Downloads\\backg5.png");
        bimg = new JLabel("", img, JLabel.RIGHT);
        bimg.setBounds(0, 0, 750, 600);
        bimg.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        img = new ImageIcon("C:\\Users\\HEMANTH KUMAR VVS\\Downloads\\home2.png");
        home = new JButton("Home", img);
        home.setFont(new Font("Arial", Font.BOLD, 10));
        home.setBounds(10, 10, 70, 60);

        choice1 = new JRadioButton("Estimate college based on rank");
        choice1.setBounds(50, 80, 300, 70);
        choice1.setFont(new Font("Arial", Font.BOLD, 15));

        choice2 = new JRadioButton("Get College Details");
        choice2.setBounds(350, 80, 200, 70);
        choice2.setFont(new Font("Arial", Font.BOLD, 15));

        choice3 = new JRadioButton("Get Colleges Placement Details");
        choice3.setBounds(50, 150, 300, 70);
        choice3.setFont(new Font("Arial", Font.BOLD, 15));

        choice4 = new JRadioButton("Get Colleges location");
        choice4.setBounds(350, 150, 200, 70);
        choice4.setFont(new Font("Arial", Font.BOLD, 15));

    }

    public void adder() {
        choice1.addActionListener(this);
        choice2.addActionListener(this);
        choice3.addActionListener(this);
        choice4.addActionListener(this);
        home.addActionListener(this);

        bg.add(choice1);
        bg.add(choice2);
        bg.add(choice3);
        bg.add(choice4);

        frame.add(title);
        frame.add(choice2);
        frame.add(choice1);
        frame.add(choice3);
        frame.add(choice4);
        frame.add(home);
        frame.add(bimg);
        // choice1.add(bimg2);

    }

    public void actionPerformed(ActionEvent e) {
        try {
            String url = "jdbc:mysql://localhost:3306/dbms_mini_project";
            String uname = "root";
            String pass = "Hemulpb35";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();

            if (e.getSource() == choice1) {
                JFrame pan = new JFrame();
                pan.setBounds(0, 260, 600, 340);
                pan.setLayout(null);
                JTextField rank_t = new JTextField();
                rank_t.setBounds(220, 100, 150, 30);
                rank_t.setFont(new Font("Arial", Font.BOLD, 15));

                img = new ImageIcon("C:\\Users\\HEMANTH KUMAR VVS\\Downloads\\backg5.png");
                bimg = new JLabel("", img, JLabel.CENTER);
                bimg.setBounds(0, 0, 750, 600);
                bimg.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                JLabel rank = new JLabel("Rank");
                rank.setBounds(65, 75, 100, 75);
                rank.setFont(new Font("Arial", Font.BOLD, 15));

                JLabel title2 = new JLabel("Please enter your rank here for the search :");
                title2.setBounds(120, 0, 400, 100);
                title2.setFont(new Font("Arial", Font.BOLD, 15));

                JButton submit = new JButton("Submit");
                submit.setBounds(140, 150, 75, 30);
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e1) {
                        try {
                            try {

                                int k = Integer.parseInt(rank_t.getText());
                                if (k <= 0) {
                                    throw new Exception();
                                }
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
                            } catch (Exception e4) {
                                JOptionPane.showMessageDialog(frame, " Please enter a valid rank ");
                            }
                        } catch (Exception e2) {
                            System.out.println(e2);
                        }
                    }
                });
                pan.add(rank);
                pan.add(rank_t);
                pan.add(submit);
                pan.add(title2);
                pan.setVisible(true);
                pan.setSize(300, 300);
                pan.setBounds(0, 300, 600, 300);
                pan.add(bimg);
                frame.add(pan);
                pan.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        System.exit(0); // Terminate the program
                    }
                });

            }
            if (e.getSource() == choice2) {
                JFrame fr2 = new JFrame("Get College details");
                fr2.setVisible(true);
                fr2.setLayout(null);
                fr2.setBounds(0, 300, 600, 300);

                JTextField name_t = new JTextField();
                name_t.setBounds(220, 100, 150, 30);
                name_t.setFont(new Font("Arial", Font.BOLD, 15));

                JLabel name = new JLabel("College id");
                name.setBounds(65, 92, 100, 50);
                name.setFont(new Font("Arial", Font.BOLD, 15));

                JLabel title1 = new JLabel("Please enter the College id for the required search:");
                title1.setBounds(120, 0, 400, 100);
                title1.setFont(new Font("Arial", Font.BOLD, 15));

                JButton submit1 = new JButton("Submit");
                submit1.setBounds(140, 150, 75, 30);

                img = new ImageIcon("C:\\Users\\HEMANTH KUMAR VVS\\Downloads\\backg5.png");
                bimg = new JLabel("", img, JLabel.CENTER);
                bimg.setBounds(0, 0, 750, 600);
                bimg.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                submit1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ex2) {
                        try {
                            JFrame tab_frame2 = new JFrame("Table");
                            tab_frame2.setSize(1000, 300);
                            tab_frame2.setVisible(true);
                            String q1 = "select * from college_specs where college_idl=" + "'" + name_t.getText() + "'"
                                    + ";";
                            ResultSet rs = st.executeQuery(q1);
                            String[][] data = new String[1][7];
                            String[] columns = { "College ID", "College name", "College fee", "Affiliation",
                                    "Autonomus", "Build up Area(in Acre)" };
                            int i = 0;
                            while (rs.next()) {
                                int j = 0;
                                data[i][j++] = rs.getString(1);
                                data[i][j++] = rs.getString(2);
                                data[i][j++] = rs.getString(3);
                                data[i][j++] = rs.getString(4);
                                data[i][j++] = rs.getString(5);
                                data[i][j++] = rs.getString(6);

                                i++;
                            }
                            JTable table2 = new JTable(data, columns);
                            tab_frame2.add(new JScrollPane(table2));
                        } catch (Exception e3) {
                            System.out.println(e3);
                        }
                    }
                });
                fr2.add(title1);
                fr2.add(name);
                fr2.add(name_t);
                fr2.add(submit1);
                fr2.add(bimg);
                frame.add(fr2);
                fr2.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        System.exit(0); // Terminate the program
                    }
                });

            }
            if (e.getSource() == choice3) {
                JFrame fr3 = new JFrame("Get College details");
                fr3.setVisible(true);
                fr3.setLayout(null);
                fr3.setBounds(0, 300, 600, 300);

                JTextField name_t1 = new JTextField();
                name_t1.setBounds(220, 100, 150, 30);
                name_t1.setFont(new Font("Arial", Font.BOLD, 15));

                JLabel name1 = new JLabel("College id");
                name1.setBounds(65, 92, 100, 50);
                name1.setFont(new Font("Arial", Font.BOLD, 15));

                JLabel title3 = new JLabel(
                        "Please enter the College id for the get College's placement details search:");
                title3.setBounds(20, 0, 550, 100);
                title3.setFont(new Font("Arial", Font.BOLD, 15));

                JButton submit3 = new JButton("Submit");
                submit3.setBounds(140, 150, 75, 30);

                img = new ImageIcon("C:\\Users\\HEMANTH KUMAR VVS\\Downloads\\backg5.png");
                bimg = new JLabel("", img, JLabel.CENTER);
                bimg.setBounds(0, 0, 750, 600);
                bimg.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                submit3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ex2) {
                        try {
                            JFrame tab_frame3 = new JFrame("Table");
                            tab_frame3.setSize(1000, 300);
                            tab_frame3.setVisible(true);
                            String q1 = "select * from college_Placement where college_idl=" + "'" + name_t1.getText()
                                    + "'" + ";";
                            ResultSet rs = st.executeQuery(q1);
                            String[][] data = new String[20][4];
                            String[] columns = { "College ID", "College name", "branch", "Placement_Avg" };
                            int i = 0;
                            while (rs.next()) {
                                int j = 0;
                                data[i][j++] = rs.getString(1);
                                data[i][j++] = rs.getString(2);
                                data[i][j++] = rs.getString(3);
                                data[i][j++] = rs.getString(4);

                                i++;
                            }
                            JTable table3 = new JTable(data, columns);
                            tab_frame3.add(new JScrollPane(table3));
                        } catch (Exception e4) {
                            System.out.println(e4);
                        }
                    }
                });
                fr3.add(title3);
                fr3.add(name1);
                fr3.add(name_t1);
                fr3.add(submit3);
                fr3.add(bimg);
                frame.add(fr3);
                fr3.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        System.exit(0); // Terminate the program
                    }
                });
            }
            if (e.getSource() == choice4) {
                JFrame fr4 = new JFrame("Get College details");
                fr4.setVisible(true);
                fr4.setLayout(null);
                fr4.setBounds(0, 300, 600, 300);

                JTextField name_t2 = new JTextField();
                name_t2.setBounds(220, 100, 150, 30);
                name_t2.setFont(new Font("Arial", Font.BOLD, 15));

                JLabel name2 = new JLabel("College id");
                name2.setBounds(65, 92, 100, 50);
                name2.setFont(new Font("Arial", Font.BOLD, 15));

                JLabel title4 = new JLabel("Please enter the College id for the get College's address:");
                title4.setBounds(60, 0, 550, 100);
                title4.setFont(new Font("Arial", Font.BOLD, 15));

                JButton submit4 = new JButton("Submit");
                submit4.setBounds(140, 150, 75, 30);

                img = new ImageIcon("C:\\Users\\HEMANTH KUMAR VVS\\Downloads\\backg5.png");
                bimg = new JLabel("", img, JLabel.CENTER);
                bimg.setBounds(0, 0, 750, 600);
                bimg.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                submit4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ex3) {
                        try {
                            JFrame tab_frame3 = new JFrame("Table");
                            tab_frame3.setSize(1000, 300);
                            tab_frame3.setVisible(true);
                            String q1 = "select * from college_address where college_idl=" + "'" + name_t2.getText()
                                    + "'" + ";";
                            ResultSet rs = st.executeQuery(q1);
                            String[][] data = new String[1][4];
                            String[] columns = { "College ID", "College name", "Local Area", "District" };
                            int i = 0;
                            while (rs.next()) {
                                int j = 0;
                                data[i][j++] = rs.getString(1);
                                data[i][j++] = rs.getString(2);
                                data[i][j++] = rs.getString(3);
                                data[i][j++] = rs.getString(4);

                                i++;
                            }
                            JTable table3 = new JTable(data, columns);
                            tab_frame3.add(new JScrollPane(table3));
                        } catch (Exception e4) {
                            System.out.println(e4);
                        }
                    }
                });
                fr4.add(title4);
                fr4.add(name2);
                fr4.add(name_t2);
                fr4.add(submit4);
                fr4.add(bimg);
                frame.add(fr4);
                fr4.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        System.exit(0); // Terminate the program
                    }
                });
            } else if (e.getSource() == home) {
                new Home();
            }
        } catch (Exception ex) {

        }
    }

    public static void main(String args[]) {
        new Interface();
    }
}
