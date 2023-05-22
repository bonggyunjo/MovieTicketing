/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Model.Factory.TheaterPanel;
import DbConnect.DbConnect;
import View.LoginSuccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author kjbg4
 */
public class LoginController {
    
    private Connection conn;
    PreparedStatement stmt = null;
        ResultSet rs;
    JFrame frame;
  
    public interface State {

        void execute();
    }

    public class LoginSuccessState implements State {
        
        @Override
        public void execute() {
            JOptionPane.showMessageDialog(null, "�α��� ���� !", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION);
              new LoginSuccess();
          
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

        public State state;
        private String uid;
        private String upw;

        public LoginStateMachine(String uid, String upw) {
            this.uid = uid;
            this.upw = upw;
        }

        public LoginStateMachine() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public void setState(State state) {
            this.state = state;
        }

        //�α��� ��ġ����
        public void login() {
            String str = "...";
            try {
                if (uid.replaceAll(" ", "").equals("") || upw.replaceAll(" ", "").equals("")) {
                    JOptionPane.showMessageDialog(null, "�����Դϴ�. ���̵� �Ǵ� ��й�ȣ�� �Է��ϼ���.", "�Է� ����", JOptionPane.DEFAULT_OPTION);
                    return;
                }
                if (uid.length() == 0) {
                    JOptionPane.showMessageDialog(null, "���̵� �Է��ϼž� �մϴ�.", "���̵� �Է�!", JOptionPane.DEFAULT_OPTION);
                    return;
                }
                if (upw.length() == 0) {
                    JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼž� �մϴ�.", "��й�ȣ �Է�!", JOptionPane.DEFAULT_OPTION);
                    return;
                }
                if (uid.length() == 0 && upw.length() == 0) {
                    JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� �Է� �ϼž� �˴ϴ�.", "���̵� ����� �Է�!", JOptionPane.DEFAULT_OPTION);
                    return;
                }
                //db�� �ִ� �����ͷ� db����̹� ���� �� ������ ��ġ�ϴ��� Ȯ�ο���
                final String driver = "org.mariadb.jdbc.Driver";
                final String DB_IP = "localhost";
                final String DB_PORT = "3306";
                final String DB_NAME = "test";
                final String DB_URL
                        = "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
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
}
