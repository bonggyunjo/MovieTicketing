/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Membership;

import Model.ProfileManagerModeDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import Controller.LoginDto;
import java.awt.Color;
import java.awt.Font;

public class MembershipView extends JFrame implements ActionListener {

    List<LoginDto> companys;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    JPanel p;
    //���̵�, ��й�ȣ, �̸�, ��ȭ��ȣ, �ּ�
    JButton join, cancel;
    JTextField id, pw, age, name, mail, phone, address, pwreconfirm;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    List<LoginDto> LoginDatas;
    boolean isSuccess;

    //������ ȭ�� gui
    public MembershipView() {
        p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.white);
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ȭ�� ��� ��ġ
        setTitle("ȸ������ ȭ��");

        join = new JButton("�����ϱ�");
        join.setLayout(null);
        join.setBounds(20, 700, 250, 50);
        join.setFont(new Font("���� ���", Font.BOLD, 14));
        join.setBackground(Color.white);
        
        cancel = new JButton("���");
        cancel.setLayout(null);
        cancel.setBounds(310, 700, 250, 50);
        cancel.setFont(new Font("���� ���", Font.BOLD, 14));
        cancel.setBackground(Color.white);
        
        name = new JTextField();
        name.setLayout(null);
        name.setBounds(200, 30, 200, 50);

        age = new JTextField();
        age.setLayout(null);
        age.setBounds(200, 90, 200, 50);

        id = new JTextField();
        id.setLayout(null);
        id.setBounds(200, 150, 200, 50);

        pwreconfirm = new JTextField();
        pwreconfirm.setLayout(null);
        pwreconfirm.setBounds(200, 210, 200, 50);

        pw = new JTextField();
        pw.setLayout(null);
        pw.setBounds(200, 270, 200, 50);

        phone = new JTextField();
        phone.setLayout(null);
        phone.setBounds(200, 330, 200, 50);

        mail = new JTextField();
        mail.setLayout(null);
        mail.setBounds(200, 390, 200, 50);

        address = new JTextField();
        address.setLayout(null);
        address.setBounds(200, 450, 200, 50);

        l1 = new JLabel("�̸� : ");
        l1.setLayout(null);
        l1.setBounds(100, 30, 100, 30);

        l2 = new JLabel("���� : ");
        l2.setLayout(null);
        l2.setBounds(100, 90, 100, 30);

        l3 = new JLabel("���̵� : ");
        l3.setLayout(null);
        l3.setBounds(100, 150, 100, 30);

        l4 = new JLabel("��й�ȣ : ");
        l4.setLayout(null);
        l4.setBounds(100, 210, 100, 30);

        l5 = new JLabel("��й�ȣ Ȯ�� : ");
        l5.setLayout(null);
        l5.setBounds(100, 270, 100, 30);

        l6 = new JLabel("�޴��� ��ȣ :  ");
        l6.setLayout(null);
        l6.setBounds(100, 330, 100, 30);

        l7 = new JLabel("���� :  ");
        l7.setLayout(null);
        l7.setBounds(100, 390, 100, 30);

        l8 = new JLabel("�ּ� :  ");
        l8.setLayout(null);
        l8.setBounds(100, 450, 100, 30);
        l9 = new JLabel(" ");
        l9.setLayout(null);

        p.add(join);
        p.add(cancel);
        p.add(name);
        p.add(age);
        p.add(id);
        p.add(pw);
        p.add(pwreconfirm);
        p.add(phone);
        p.add(mail);
        p.add(address);
        p.add(l1);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        p.add(l5);
        p.add(l6);
        p.add(l7);
        p.add(l8);
        p.add(l9);
        add(p);
        setVisible(true);
        join.addActionListener(this);
        join.setActionCommand("add");
        cancel.addActionListener(this);
        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addAction();

            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

            }
        });
    }

    public void actionPerformed(ActionEvent e) { // ��ư Ŭ���ÿ� �ߵ��Ǵ� �޼ҵ�

    }

    //������ �Է��� ȸ������ Ȯ�ο���
    private void addAction() {
        String uname = name.getText().trim();
        String uage = age.getText().trim();
        String uid = id.getText().trim();
        String upw = pw.getText().trim();
        String upwreconfirm = pwreconfirm.getText().trim();
        String uphone = phone.getText().trim();
        String umail = mail.getText().trim();
        String uaddress = address.getText().trim();

        if (uname.isEmpty() || uage.isEmpty() || uid.isEmpty() || upw.isEmpty()
                || upwreconfirm.isEmpty() || uphone.isEmpty() || umail.isEmpty() || uaddress.isEmpty()) {
            JOptionPane.showMessageDialog(null, "�Է� ����� ���� �ʽ��ϴ�.", "�Է� ����", JOptionPane.DEFAULT_OPTION);
            return; // �Է��� �ùٸ��� ������ �޼��� ���� �ߴ�
        }
        if (uname.contains(" ") || uage.contains(" ") || uid.contains(" ") || upw.contains(" ")
                || upwreconfirm.contains(" ") || uphone.contains(" ") || umail.contains(" ") ) {
            JOptionPane.showMessageDialog(null, "�Է� ���� ������ ���ԵǾ� �ֽ��ϴ�.", "�Է� ����", JOptionPane.DEFAULT_OPTION);
            return; // �Է¿� ������ ���ԵǾ� ������ �޼��� ���� �ߴ�
        }

        if (uname.length() == 0 || uage.length() == 0 || uid.length() == 0 || upw.length() == 0 || uphone.length() == 0 || umail.length() == 0 || uaddress.length() == 0) {
            JOptionPane.showMessageDialog(this, "��ĭ�� �Է����ּ���.");
        } else {
            String password2 = new String(upw);
            String passwordCheck2 = new String(upwreconfirm);
            if (!password2.equals(passwordCheck2)) {
                JOptionPane.showMessageDialog(this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);

            } else {

                //���� ���� ����
                LoginDto dto = new LoginDto.Builder()
                        .setName(uname)
                        .setAge(uage)
                        .setId(uid)
                        .setPw(upw)
                        .setPhone(uphone)
                        .setMail(umail)
                        .setAddress(uaddress)
                        .build();
                isSuccess = new ProfileManagerModeDao().insert(dto);

                if (isSuccess) {
                    JOptionPane.showMessageDialog(this, "���� �߽��ϴ�.");
                    setVisible(false);
                    //���� ������ ������ 0 �� �����    
                } else {
                    JOptionPane.showMessageDialog(this, "���� ����! �ߺ��� ���̵� �ֽ��ϴ�.");
                    setVisible(true);
                }

            }
        }
    }
}
