/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moiveticketing;

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

public class Membership extends JFrame implements ActionListener  {

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
    Membership() {
        p = new JPanel();
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ȭ�� ��� ��ġ
        setTitle("ȸ������ ȭ��");

        join = new JButton("�����ϱ�");
        join.setLayout(null);
        join.setBounds(20, 700, 250, 50);

        cancel = new JButton("���");
        cancel.setLayout(null);
        cancel.setBounds(310, 700, 250, 50);


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

        add(p);
        add(join);
        add(cancel);
        add(name);
        add(age);
        add(id);
        add(pw);
        add(pwreconfirm);
        add(phone);
        add(mail);
        add(address);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(l8);
        add(l9);
        setVisible(true);
        join.addActionListener(this);
        join.setActionCommand("add");
        cancel.addActionListener(this);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LoginFrame();
            }
        });
    }
    
    public void actionPerformed(ActionEvent e) { // ��ư Ŭ���ÿ� �ߵ��Ǵ� �޼ҵ�
        if (e.getActionCommand().equals("add")) {
            addAction();
        }
    }
    //������ �Է��� ȸ������ Ȯ�ο���
    private void addAction() {
        String uname = name.getText();
        String uage = age.getText();
        String uid = id.getText();
        String upw = pw.getText();
        String upwreconfirm = pwreconfirm.getText();
        String uphone = phone.getText();
        String umail = mail.getText();
        String uaddress = address.getText();
        //���� ���� ����
    
        if (uname.length() == 0 || uage.length() == 0 || uid.length() == 0 || upw.length() == 0 || uphone.length() == 0 || umail.length() == 0 || uaddress.length() == 0) {
            JOptionPane.showMessageDialog(this, "��ĭ�� �Է����ּ���.");
        } else {
            String password2 = new String(upw);
            String passwordCheck2 = new String(upwreconfirm);
            if (!password2.equals(passwordCheck2)) {
                JOptionPane.showMessageDialog(this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
              
            }
            else {
               
                
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
