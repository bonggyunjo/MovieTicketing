/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Model.Factory.TheaterPanel;

/**
 *
 * @author Admin
 */
public class LoginSuccess extends JFrame {
    JPanel j;
    JButton movie, noticeboard;
    
    public LoginSuccess(){
           j = new JPanel();
          j.setLayout(null);  
          j.setBackground(Color.white);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ??�? �??��?? 배�?
        setTitle("���� ȭ��");
        
        movie = new JButton("��ȭ ����");
        movie.setLayout(null);
         movie.setBackground(Color.white);
        movie.setBounds(110,150,150,50);
        
         noticeboard = new JButton("�Խ��� �̵�");
        noticeboard.setLayout(null);
         noticeboard.setBackground(Color.white);
        noticeboard.setBounds(315,150,150,50);
        
        
        j.add(movie);
        j.add(noticeboard);
        add(j);
        setVisible(true);
        
            noticeboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new NoticeBoard();
            }
        });
                     movie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame frame = new JFrame("��ȭ ���� �ý���");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
            // TheaterPanel ���� �� �߰�
            TheaterPanel theaterPanel = new TheaterPanel();
            frame.getContentPane().add(theaterPanel);
        
            // JFrame ũ�� �� ��ġ ���� �� ���̵��� ��
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            }
        });
    }
    
    
        
}
