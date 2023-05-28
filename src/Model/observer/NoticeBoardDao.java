

package Model.observer;

/**
 *
 * @author cherr
 */

import Controller.LoginDto;
import java.awt.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
    �Խ��� ���� �� ���� ��Ͽ� ���� DB ���� ������
*/
public class NoticeBoardDao extends JPanel {

    DefaultTableModel dft;
 
    
    private void deleteFromDatabase(String rate, String review) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mariadb://113.198.234.132:9090", "jbg", "12341234");
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM review WHERE rate = ? AND review = ?");
            stmt.setString(1, rate);
            stmt.setString(2, review);
            
            // ���� ����
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("�����ͺ��̽����� �����Ǿ����ϴ�.");
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadDataFromDatabase() {
        try {
            // �����ͺ��̽� ���� ����
            Connection conn = DriverManager.getConnection("jdbc:mariadb://113.198.234.132:9090", "jbg", "12341234");
            PreparedStatement stmt = conn.prepareStatement("SELECT rate, review FROM review");

            // ���� ���� �� ��� ��������
            ResultSet rs = stmt.executeQuery();

            // ����� JTable�� �߰�
            while (rs.next()) {
                String rate = rs.getString("rate");
                String review = rs.getString("review");

                Object[] rowData = { rate, review };
                dft.addRow(rowData);
            }

            // �ڿ� ����
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    
}


