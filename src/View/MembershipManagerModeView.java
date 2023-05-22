/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ManagerModeController;
import Model.ProfileManagerModeDao;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Controller.LoginDto;
import Controller.MouseLisstenerController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class MembershipManagerModeView extends JFrame implements ActionListener, PropertyChangeListener {

    JPanel p;
    JButton back, insert, delete, update;
    JTable table;
    DefaultTableModel dft;
    JLabel mainname, l1, l2, l3, l4, l5, l6, l7;
    JTextField t1, t2, t3, t4, t5, t6, t7;
    List<LoginDto> companys;
 
    MembershipManagerModeView() {
        //������ȭ�� ������ gui 
        p = new JPanel();
        p.setLayout(null);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ȭ�� ��� ��ġ
        setTitle("ȸ�� ���� ����");
        setVisible(true);

        l1 = new JLabel("name");
        l2 = new JLabel("age");
        l3 = new JLabel("id");
        l4 = new JLabel("pw");
        l5 = new JLabel("phone");
        l6 = new JLabel("mail");
        l7 = new JLabel("address");

        t1 = new JTextField(10);
        t2 = new JTextField(10);
        t3 = new JTextField(10);
        t4 = new JTextField(10);
        t5 = new JTextField(10);
        t6 = new JTextField(10);
        t7 = new JTextField(10);

        l1.setBounds(67, 150, 60, 30);
        l2.setBounds(167, 150, 60, 30);
        l3.setBounds(271, 150, 60, 30);
        l4.setBounds(377, 150, 60, 30);
        l5.setBounds(490, 150, 60, 30);
        l6.setBounds(628, 150, 60, 30);
        l7.setBounds(755, 150, 60, 30);
        t1.setBounds(100, 150, 60, 30);
        t2.setBounds(191, 150, 60, 30);
        t3.setBounds(288, 150, 80, 30);
        t4.setBounds(398, 150, 80, 30);
        t5.setBounds(528, 150, 80, 30);
        t6.setBounds(658, 150, 80, 30);
        t7.setBounds(810, 150, 110, 30);
        mainname = new JLabel("ȸ������");
        mainname.setBounds(465, 10, 200, 50);
        mainname.setLayout(null);

        back = new JButton("�ڷΰ���");
        back.setBackground(Color.white);
        back.setFont(new Font("���� ���", Font.BOLD, 14));
        back.setBounds(885, 631, 100, 30);
        back.setLayout(null);

        insert = new JButton("�߰�");
        insert.setBackground(Color.white);
        insert.setFont(new Font("���� ���", Font.BOLD, 14));
        insert.setBounds(280, 110, 100, 30);
        insert.setLayout(null);

        update = new JButton("����");
        update.setBackground(Color.white);
        update.setFont(new Font("���� ���", Font.BOLD, 14));
        update.setBounds(430, 110, 100, 30);
        update.setLayout(null);

        delete = new JButton("����");
        delete.setBackground(Color.white);
        delete.setFont(new Font("���� ���", Font.BOLD, 14));
        delete.setBounds(580, 110, 100, 30);
        delete.setLayout(null);

        p.add(l1);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        p.add(l5);
        p.add(l6);
        p.add(l7);
        p.add(t1);
        p.add(t2);
        p.add(t3);
        p.add(t4);
        p.add(t5);
        p.add(t6);
        p.add(t7);
        p.add(mainname);
        p.add(back);
        p.add(insert);
        p.add(update);
        p.add(delete);
        add(p);

        String[] colNames = {"�̸�", "����", "���̵�", "��й�ȣ", "����ȣ", "����", "�ּ�"};
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

        pane.setBounds(65, 200, 850, 400);
        p.add(pane);
        // ���̺� ���� �����۾��� �Ͼ���� ������ ������ ���
        table.addPropertyChangeListener(this);
        //���̺� ȸ����� �߰��ϱ�
        showMembers();
           
        
        table.addMouseListener((MouseListener) new MouseLisstenerController());
       
        insert.addActionListener(this);
        delete.addActionListener(this);
        update.addActionListener(this);
        insert.setActionCommand("add");
        delete.setActionCommand("delete");
        update.setActionCommand("����");

        insert.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("add")) {
                     dft.setRowCount(0);
                       showMembers();
                       String name = t1.getText();
        String age = t2.getText();
        String id = t3.getText();
        String pw = t4.getText();
        String phone = t5.getText();
        String mail = t6.getText();
        String address = t7.getText();
        ManagerModeController mc = new ManagerModeController();
                    mc.addAction(name, age, id, pw, phone, mail, address);
                }
            }
        });

        delete.addActionListener(new ActionListener() {
      
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("delete")) {
                    deleteAction();
                }
                
            }
        });
      
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                FirstView home = new FirstView();
                home.setVisible(true);
                setVisible(false);
            }
        });
    }

    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChange()");
        System.out.println(evt.getPropertyName());
        if (evt.getPropertyName().equals("tableCellEditor")) {
            //���õ� row �� index �� ���ͼ� 
            int index = table.getSelectedRow();
            //�ε����� �ش��ϴ� model ���� �Էµ� �̸��� �ּҸ� �о�´�. 
            String name = (String) dft.getValueAt(index, 0); //2��° �ε����� �ּҸ� �о��
            String age = (String) dft.getValueAt(index, 1); //1��° �ε����� �̸��� �о��
            String id = (String) dft.getValueAt(index, 2); //0��° �ε����� ��ȣ�� �о��
            String pw = (String) dft.getValueAt(index, 3); //1��° �ε����� �̸��� �о��
            String phone = (String) dft.getValueAt(index, 4); //1��° �ε����� �̸��� �о��
            String mail = (String) dft.getValueAt(index, 5); //3��° �ε����� �Ի����� �о��
            String address = (String) dft.getValueAt(index, 6); //3��° �ε����� �Ի����� �о��
            //DB �� ���� �ݿ�

            //���� ���� 
            LoginDto dto = new LoginDto.Builder()
                    .setName(name)
                    .setAge(age)
                    .setId(id)
                    .setPw(pw)
                    .setPhone(phone)
                    .setMail(mail)
                    .setAddress(address)
                    .build();
            new ProfileManagerModeDao().update(dto);
        }

    }
    //ȸ����� ��ü ���
    public void showMembers() {
        companys = new ProfileManagerModeDao().getList();
        for (LoginDto tmp : companys) {
            Object[] row = {tmp.getName(), tmp.getAge(), tmp.getId(), tmp.getPw(), tmp.getPhone(), tmp.getMail(), tmp.getAddress()};
            dft.addRow(row);
        }
    }

    //���� �޼���
    private void deleteAction() {
        //���õ� row �� �ε����� ���ͼ� 
        int index = table.getSelectedRow();
         if( index <0 ) {
             JOptionPane.showMessageDialog(this,"������ ���� ������ �ּ���.");
        }
        if (index == -1) {
            return;
        }
       
        //DB ���� �����ϰ�
        LoginDto dto = new LoginDto();
        dto.setPw(companys.get(index).getPw());
        new ProfileManagerModeDao().delete(dto);
        //�ٽ� ���
        dft.setRowCount(0);
        showMembers();

        //�۾��� �������θ� ���� �޴´�. 
        boolean isSuccess = new ProfileManagerModeDao().delete(dto);

        if (isSuccess) {
            JOptionPane.showMessageDialog(this, "���� �߽��ϴ�.");
        } else {
            JOptionPane.showMessageDialog(this, "���� �߽��ϴ�.");
        }

    }

    public void actionPerformed(ActionEvent e) {

    }
}
