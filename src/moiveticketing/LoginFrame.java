/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author Admin
 */
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
        b2 = new JButton("ȸ������");
        b2.setBounds(500, 240, 100, 30);
        b2.setLayout(null);

        back = new JButton("�ڷΰ���");
        back.setBounds(685, 431, 100, 30);
        back.setLayout(null);

        l1 = new JLabel("���̵� : ");
        l1.setBounds(200, 180, 100, 30);
        l1.setLayout(null);
        l2 = new JLabel("��й�ȣ : ");
        l2.setBounds(200, 240, 100, 30);
        l2.setLayout(null);

        id = new JTextField();
        id.setBounds(275, 180, 185, 30);
        id.setLayout(null);

        pw = new JTextField();
        pw.setBounds(275, 240, 185, 30);
        pw.setLayout(null);

        add(b1);
        add(b2);
        add(back);
        add(l1);
        add(l2);
        add(id);
        add(pw);
        add(p);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                
                String uid = id.getText();
                String upw = pw.getText();
                 try {
                     String sql = String.format("select pw from profile where id ='%s' and pw = '%s'",uid,upw);	
                    final String driver = "org.mariadb.jdbc.Driver";
		final String DB_IP = "localhost";
		final String DB_PORT = "3306";
		final String DB_NAME = "test";
		final String DB_URL = 
				"jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
                    conn= DriverManager.getConnection(DB_URL,"root", "12341234");                   
                        stmt = conn.prepareStatement(sql);
                       rs=stmt.executeQuery();     
                       rs.next();
                       
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
               
                 
                    if(pw.equals(rs.getString(1)))
                         System.out.println("���� !");
                        JOptionPane.showMessageDialog(null, "�α��� ���� !","�α��� Ȯ��!",JOptionPane.DEFAULT_OPTION);
                      
                                    
                } 
                catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�", "�α��� ����", 1);
				System.out.println("SQLException" + ex);
                }
                 if(uid.length()==0 && upw.length()==0){
                    JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� �Է��ϼ���.", "�α��� ����", JOptionPane.PLAIN_MESSAGE);
                }
                if(uid.length()==0 &&upw.length()!=0){
                    JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���", "�α��� ����", JOptionPane.PLAIN_MESSAGE);
                }
                  if(uid.length()!=0 &&upw.length()==0){
                    JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���", "�α��� ����", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Membership membership = new Membership();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
            }
        });
    }
}
