package View.ManagerMode;

/**
 *
 * @author cherr
 */
import Controller.LoginDto;
import Controller.NoticeBoardManagerController;
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

public class NoticeBoardManagerModeView extends JPanel {

    JButton delete = new JButton("����");

    JTextField t1 = new JTextField(10);
    JTextField t2 = new JTextField(10);
    JLabel l1 = new JLabel("rate");
    JLabel l2 = new JLabel("review");
    java.util.List<LoginDto> companys;
    JTable table;
    DefaultTableModel dft;
    private NoticeBoardManagerController controller;
    JButton back;

    public NoticeBoardManagerModeView() {
        controller = new NoticeBoardManagerController();
        JFrame frame = new JFrame("�Խ��� ����");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        delete.setBounds(460, 40, 100, 30);
        delete.setLayout(null);
        delete.setFont(new Font("���� ���", Font.BOLD, 14));
        delete.setBackground(Color.white);
        
        back = new JButton("�ڷΰ���");
        back.setFont(new Font("���� ���", Font.BOLD, 14));
        back.setBackground(Color.white);
        back.setLayout(null);
        back.setBounds(485, 331, 100, 30);

        frame.add(back);
        frame.add(delete);
        String[] colNames = {"����", "����"};
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

        pane.setBounds(40, 30, 400, 300);
        frame.add(pane);

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String rate = table.getValueAt(selectedRow, 0).toString();
                    String review = table.getValueAt(selectedRow, 1).toString();

                    // �����ͺ��̽����� ����
                    controller.deleteReview(rate, review);

                    // JTable���� ����
                    dft.removeRow(selectedRow);
                }
            }
        });
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManagerModeSelectView mmv = new ManagerModeSelectView();
                frame.setVisible(false);
            }
        });
        frame.setVisible(true);
        loadDataFromDatabase();

    }

    private void loadDataFromDatabase() {
        dft.setRowCount(0); // Clear the table data
        Object[][] data = controller.getReviewData();
        for (Object[] rowData : data) {
            dft.addRow(rowData);
        }
    }

}
