/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author kjbg4
 */
public class ManagerMode extends JFrame {
    JPanel p;
    JButton b1,b2,b3;
    
    ManagerMode(){
    p = new JPanel();
        p.setLayout(null);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ȭ�� ��� ��ġ
        setTitle("������ ȭ��");
        setVisible(true);
        
        b1 = new JButton("ȸ�� ���� ����");
        b1.setLayout(null);
        b1.setBounds(200,20,150,50);
        
        b2 = new JButton("���� ���� ����");
        b2.setLayout(null);
        b2.setBounds(400,20,150,50);
        
        b3 = new JButton("�Խ��� ����");
        b3.setLayout(null);
        b3.setBounds(600,20,150,50);
        
        p.add(b1);
        p.add(b2);
        p.add(b3);
        add(p);
         setVisible(true);
         
           b1.addActionListener(new ActionListener() {
      
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                 new MembershipManagerMode();                        
            }
        });
           
             b2.addActionListener(new ActionListener() {
      
            public void actionPerformed(ActionEvent e) { 
                 setVisible(false);
               new MovieManagerMode();
            }
        });
             
               b3.addActionListener(new ActionListener() {
      
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                
            }
        });
    }
   
    
    public static void main(String[] args) {
        new ManagerMode();
    }
}
