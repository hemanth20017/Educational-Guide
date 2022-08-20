
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login {
    JLabel bimg, username, password, title;
    JTextField username_t;
    JPasswordField password_t;
    JButton submit, reset;
    JFrame frame = new JFrame("Login page");
    ImageIcon img;

    public Login() {
        // TODO Auto-generated constructor stub
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

        title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setAlignmentX(JLabel.CENTER);
        title.setBounds(200, 0, 200, 50);

        username = new JLabel("User-name");
        username.setFont(new Font("Arial", Font.BOLD, 15));
        username.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        username.setBounds(30, 100, 80, 50);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.BOLD, 15));
        password.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        password.setBounds(30, 170, 80, 50);

        username_t = new JTextField();
        username_t.setBounds(170, 108, 180, 30);

        password_t = new JPasswordField();
        password_t.setBounds(170, 178, 180, 30);

        submit = new JButton("Submit");
        submit.setBounds(70, 250, 100, 30);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String url = "jdbc:mysql://localhost:3306/dbms_mini_project";
                    String uname = "root";
                    String pass = "Hemulpb35";
                    String password = new String(password_t.getPassword());
                    String usid = username_t.getText();
                    String q1 = "select user_name from user_login_details where user_id=" + "'" + usid + "'"
                            + "and user_password=" + "'" + password + "'";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, uname, pass);
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(q1);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(frame, " Login succussfull....");
                        new Interface();
                    } else {
                        JOptionPane.showMessageDialog(frame, " Incorrect username or password");
                    }
                    con.close();

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        reset = new JButton("Reset");
        reset.setBounds(275, 250, 100, 30);
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e2) {
                username_t.setText("");
                password_t.setText("");

            }

        });

    }

    public void adder() {
        frame.add(title);
        frame.add(username);
        frame.add(password);
        frame.add(username_t);
        frame.add(password_t);
        frame.add(submit);
        frame.add(reset);
        frame.add(bimg);
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        new Login();

    }

}
