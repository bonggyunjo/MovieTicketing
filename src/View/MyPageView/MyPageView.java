/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.MyPageView;

import Controller.LoginDto;
import Controller.MyPageDto;
import Model.MyPageDao;
import Model.ProfileManagerModeDao;
import View.Login.LoginSuccessView;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kjbg4
 */
public class MyPageView extends JFrame implements PropertyChangeListener {

    JPanel p;
    JButton back;
    JLabel titleLabel, reseverok;
    DefaultTableModel dft;
    JTable table;
    List<MyPageDto> mypages;
    boolean isSuccess;

    public MyPageView() {
        p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.white);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ȭ�� ��� ��ġ
        setTitle("ȸ������ ȭ��");

        back = new JButton("�ڷΰ���");
        back.setLayout(null);
        back.setBounds(686, 431, 100, 30);
        back.setFont(new Font("���� ���", Font.BOLD, 14));
        back.setBackground(Color.white);

        titleLabel = new JLabel("���� ������");
        titleLabel.setLayout(null);
        titleLabel.setBounds(317, 5, 200, 40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 25));

        reseverok = new JLabel("���� ����");
        reseverok.setLayout(null);
        reseverok.setBounds(333, 85, 200, 40);
        reseverok.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20));

        String[] colNames = {"�¼� ��", "�¼� ��", "����� ȣ", "�帣 ��ȣ", "��ȭ ��ȣ", "���� ��ȣ"};
        // ���̺� ����� �����͸� �������ִ� ����Ʈ�����̺�
        dft = new DefaultTableModel(colNames, 0) {
            //���� ���� ���θ� �����ϴ� �޼ҵ�
            public boolean isCellEditable(int row, int column) {
                //0�� Į���� ���� �Ұ����ϵ��� false�� �������ְ�
                if (column == 0 || column == 3) {
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

        pane.setBounds(135, 125, 500, 300);
        p.add(pane);
        p.add(reseverok);
        p.add(titleLabel);
        p.add(back);
        add(p);
        table.addPropertyChangeListener(this);
        //���̺� ȸ����� �߰��ϱ�
        showMembers();
        setVisible(true);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LoginSuccessView();

            }
        });
    }

    public static void main(String[] args) {
        new MyPageView();
    }

    //ȸ����� ��ü ���
    public void showMembers() {
        mypages = new MyPageDao().getList();
        for (MyPageDto tmp : mypages) {
            Object[] row = { tmp.getS_row(),tmp.getS_col(),tmp.getT_id(),tmp.getG_id(), tmp.getM_id(),tmp.getCardnum()};
            dft.addRow(row);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChange()");
        System.out.println(evt.getPropertyName());
        if (evt.getPropertyName().equals("tableCellEditor")) {
            //���õ� row �� index �� ���ͼ� 
            int index = table.getSelectedRow();
            //�ε����� �ش��ϴ� model ���� �Էµ� �̸��� �ּҸ� �о�´�. 
            int cardnum = (int) dft.getValueAt(index, 0); //2��° �ε����� �ּҸ� �о��
            int g_id = (int) dft.getValueAt(index, 1); //1��° �ε����� �̸��� �о��
            int m_id = (int) dft.getValueAt(index, 2); //0��° �ε����� ��ȣ�� �о��
            int s_col = (int) dft.getValueAt(index, 3); //1��° �ε����� �̸��� �о��
            int s_row = (int) dft.getValueAt(index, 4); //1��° �ε����� �̸��� �о��
            int t_id = (int) dft.getValueAt(index, 5); //3��° �ε����� �Ի����� �о��
          

            //���� ���� 
            MyPageDto dto = new MyPageDto.Builder()     
                    .sets_row(s_row)
                     .sets_col(s_col)
                     .sett_id(t_id)
                    .setg_id(g_id)
                    .setm_id(m_id)        
                    .setcardnum(cardnum)
                    .build();
        }

    }
  
}
