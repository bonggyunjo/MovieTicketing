package moiveticketing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

    List<LoginDto> companys;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    JButton b1, b2;
    JLabel l1, l2;
    JTextField id, pw;
    JPanel p;
    JButton back;

    public interface State {
        void execute();
    }

    public class LoginSuccessState implements State {
        @Override
        public void execute() {
            JOptionPane.showMessageDialog(null, "�α��� ���� !", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION);
        }
    }

    public class LoginFailureState implements State {
        @Override
        public void execute() {
            JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�", "�α��� ����", 1);
        }
    }

    public class LoginErrorState implements State {
        private String errorMessage;

        public LoginErrorState(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public void execute() {
            JOptionPane.showMessageDialog(null, errorMessage, "�α��� ����", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public class LoginStateMachine {
        private State loginSuccessState = new LoginSuccessState();
        private State loginFailureState = new LoginFailureState();

        private State state;
        private String uid;
        private String upw;

        public LoginStateMachine(String uid, String upw) {
            this.uid = uid;
            this.upw = upw;
        }

        public void setState(State state) {
            this.state = state;
        }

        public void login() {
            try {
                 if(uid.length()==0){
                     JOptionPane.showMessageDialog(null, "���̵� �Է��ϼž� �մϴ�.", "���̵� �Է�!", JOptionPane.DEFAULT_OPTION);
                    return;
               }  
                if(upw.length()==0){
                     JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼž� �մϴ�.", "��й�ȣ �Է�!", JOptionPane.DEFAULT_OPTION);
                    return;
               }
               if (uid.length() == 0 && upw.length() == 0) {
                    JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� �Է� �ϼž� �˴ϴ�.", "���̵� ����� �Է�!", JOptionPane.DEFAULT_OPTION);
                    return;
                }

                final String driver = "org.mariadb.jdbc.Driver";
                final String DB_IP = "localhost";
                final String DB_PORT = "3306";
                final String DB_NAME = "test";
                final String DB_URL =
                        "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
                conn = DriverManager.getConnection(DB_URL, "root", "12341234");

                String sql = String.format("select pw from profile where id ='%s' and pw = '%s'", uid, upw);
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                rs.next();

                if (upw.equals(rs.getString(1))) {
                    setState(loginSuccessState);
                } else {
                    setState(loginFailureState);
                }

            } catch (SQLException ex) {
                setState(loginFailureState);
                System.out.println("SQLException" + ex);
            }
        }
    }

    LoginFrame() {
    p = new JPanel();
    setSize(800, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // ȭ�� ��� ��ġ
    setTitle("�α��� ȭ��");
    setVisible(true);

    b1 = new JButton("�α���");
    b1.setBounds(500, 180, 100, 30);
    b1.setLayout(null);
    b1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String uid = id.getText();
            String upw = pw.getText();
            LoginStateMachine stateMachine = new LoginStateMachine(uid, upw);
            stateMachine.login();
            stateMachine.state.execute();
        }
    });

    b2 = new JButton("ȸ������");
    b2.setBounds(500, 240, 100, 30);
    b2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Membership membership = new Membership();
        }
    });

    l1 = new JLabel("���̵� : ");
    l1.setBounds(200, 180, 100, 30);

    l2 = new JLabel("��й�ȣ : ");
    l2.setBounds(200, 240, 100, 30);

    id = new JTextField();
    id.setBounds(275, 180, 185, 30);

    pw = new JTextField();
    pw.setBounds(275, 240, 185, 30);

    back = new JButton("���ư���");
    back.setBounds(685, 431, 100, 30);
    back.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Home home = new Home();
        }
    });

    add(l1);
    add(l2);
    add(id);
    add(pw);
    add(b1);
    add(b2);
    add(back);
    add(p);
}
}