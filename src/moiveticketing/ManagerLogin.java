/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moiveticketing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.io.FileNotFoundException;

/**
 *
 * @author Admin
 */
public class ManagerLogin extends JFrame {
    
    JButton login, back;
    JPanel j;
    JLabel word;
    JPasswordField pwIn;
    
    ManagerLogin() {
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ȭ�� ��� ��ġ
        setTitle("������ �α���");
        setVisible(true);
        
        j = new JPanel();
        j.setBackground(Color.white);
        j.setLayout(null);
        
        login = new JButton("�α���");
        login.setBounds(200, 180, 100, 30);
        login.setBackground(Color.white);
        login.setFont(new Font("���� ���", Font.BOLD, 14));
        login.setLayout(null);
        
        back = new JButton("�ڷΰ���");
        back.setBounds(385, 332, 100, 30);
        back.setBackground(Color.white);
        back.setFont(new Font("���� ���", Font.BOLD, 14));
        
        word = new JLabel("��й�ȣ : ");
        word.setFont(new Font("���� ���", Font.BOLD, 14));
        word.setBounds(100, 120, 70, 25);
        
        pwIn = new JPasswordField();
        pwIn.setBounds(180, 120, 200, 30);
        
        add(word);
        add(pwIn);
        add(login);
        add(back);
        add(j);
        
        login.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
             
                String PW = "010203";
                String input = new String(pwIn.getPassword());
                
                if (input.equals(PW)) {
                    
                    JOptionPane.showMessageDialog(null, "�α��� Ȯ��", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION);
                    setVisible(false);
                   
                    ManagerMode Managermode = new ManagerMode();
                    
                } else {
                    
                    String s1 = "<html><div width='115px' align='center'>";
                    String s2 = "</div></html>";
                    // �߾� ����

                    String errorms = s1 + "��й�ȣ�� Ʋ�Ƚ��ϴ�." + s2;
                    setVisible(true);
                    JLabel ms = new JLabel(errorms);
                    JOptionPane.showMessageDialog(null, ms, "��й�ȣ ����", JOptionPane.WARNING_MESSAGE);
                    
                }
            }
        });
        
        back.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                setVisible(false);
            }
        });
    }
}
