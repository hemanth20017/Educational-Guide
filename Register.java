import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register implements ActionListener {
    JLabel bimg, username, password, title, name;
    JTextField username_t, name_t;
    JPasswordField password_t;
    JButton submit, reset;
    JFrame frame = new JFrame("Register page");
    JCheckBox show;
    ImageIcon img;

    public Register() {
        // TODO Auto-generated constructor stub//
        initialize();
        adder();
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // Terminate the program
            }
        });
    }

    public void initialize() {
        img = new ImageIcon("C:\\Users\\HEMANTH KUMAR VVS\\Downloads\\backg5.png");
        bimg = new JLabel("", img, JLabel.CENTER);
        bimg.setBounds(0, 0, 500, 500);

        title = new JLabel("Register");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setAlignmentX(JLabel.CENTER);
        title.setBounds(200, 0, 200, 50);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.BOLD, 15));
        name.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        name.setBounds(30, 100, 80, 50);

        username = new JLabel("User-name");
        username.setFont(new Font("Arial", Font.BOLD, 15));
        username.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        username.setBounds(30, 170, 80, 50);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.BOLD, 15));
        password.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        password.setBounds(30, 240, 80, 50);

        name_t = new JTextField();
        name_t.setBounds(170, 108, 180, 30);

        username_t = new JTextField();
        username_t.setBounds(170, 178, 180, 30);

        password_t = new JPasswordField();
        password_t.setBounds(170, 248, 180, 30);
        password_t.setEchoChar('*');

        show = new JCheckBox("Show Password");
        show.setBounds(170, 288, 125, 20);
        show.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (show.isSelected()) {
                    password_t.setEchoChar((char) 0);
                } else {
                    password_t.setEchoChar('*');
                }
            }
        });

        submit = new JButton("Submit");
        submit.setBounds(70, 320, 100, 30);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String url = "jdbc:mysql://localhost:3306/dbms_mini_project";
                    String uname = "root";
                    String pass = "Hemulpb35";
                    String password = new String(password_t.getPassword());
                    String usid = username_t.getText();
                    String name_te = name_t.getText();
                    try {
                        if (name_te.length() == 0 || password.length() == 0) {
                            throw new Exception();
                        }
                        String q1 = "insert into user_login_details values(" + "'" + name_te + "'" + "," + "'" + usid
                                + "'" + "," + "'" + password + "'" + ");";
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection(url, uname, pass);
                        Statement st = con.createStatement();
                        int i = st.executeUpdate(q1);
                        JOptionPane.showMessageDialog(frame, " Regestration succussfull....");
                        con.close();
                        new Interface();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, " Please try again with different username");
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        reset = new JButton("Reset");
        reset.setBounds(275, 320, 100, 30);
        reset.addActionListener(this);

    }

    public void adder() {
        frame.add(title);
        frame.add(name);
        frame.add(name_t);
        frame.add(username);
        frame.add(password);
        frame.add(username_t);
        frame.add(password_t);
        frame.add(show);
        frame.add(submit);
        frame.add(reset);
        frame.add(bimg);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String str = new String(password_t.getPassword());
            if (username_t.getText() == "Hemu" && str == "Hello") {
                JOptionPane.showMessageDialog(frame, " valid Details");
            } else if (str.length() < 8) {
                JOptionPane.showMessageDialog(frame, " The details entered are not valid");
            }
        } else {
            username_t.setText("");
            password_t.setText("");
            name_t.setText("");
        }
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        new Register();
    }
}
