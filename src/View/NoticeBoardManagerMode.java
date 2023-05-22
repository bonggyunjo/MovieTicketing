

package View;

/**
 *
 * @author cherr
 */

import Controller.LoginDto;
import DbConnect.DbConnect;
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

public class NoticeBoardManagerMode extends JPanel {
    
    JButton delete=new JButton("����");
    JTextField t1=new JTextField(10);
    JTextField t2=new JTextField(10);
    JLabel l1 = new JLabel("rate");
    JLabel l2 = new JLabel("review");
    java.util.List<LoginDto> companys;
    JTable table;
    DefaultTableModel dft;
    
    public NoticeBoardManagerMode() {
        JFrame frame = new JFrame("������ ���");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 
        frame.getContentPane().setLayout(null);            
        
        frame.add(delete);
        delete.setBounds(460,40,100,30);
        
        String[] colNames = {"rate","review"};
        // ���̺� ����� �����͸� �������ִ� ����Ʈ�����̺�
        dft = new DefaultTableModel(colNames, 0) {
            //���� ���� ���θ� �����ϴ� �޼ҵ�
            public boolean isCellEditable(int row, int column) {
                //0�� Į���� ���� �Ұ����ϵ��� false�� �������ְ�
                if (column == 0) {
                    return false;
                } else {
                    //�������� ��� ���� �����ϵ��� true�� �����Ѵ�.
                    return true;
                }

            }
        };
        
        table = new JTable(dft);
        // ��ũ�� ����
        JScrollPane pane = new JScrollPane(table);
        
        pane.setBounds(40, 30, 400, 350);
        frame.add(pane);  
        
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String rate = table.getValueAt(selectedRow, 0).toString();
                    String review = table.getValueAt(selectedRow, 1).toString();
                    
                    // �����ͺ��̽����� ����
                    deleteFromDatabase(rate, review);

                    // JTable���� ����
                    dft.removeRow(selectedRow);
                }
            }
        });
        
        frame.setVisible(true);
        loadDataFromDatabase();
        
    }
    
    private void deleteFromDatabase(String rate, String review) {
        try {
            Connection conn = conn = new DbConnect().getConn();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM review WHERE rate = ? AND review = ?");
            stmt.setString(1, rate);
            stmt.setString(2, review);
                  int index = table.getSelectedRow();
            // ���� ����
            int rowsAffected = stmt.executeUpdate();
      
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "���� �Ǿ����ϴ�");
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
            Connection conn =conn = new DbConnect().getConn();
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


