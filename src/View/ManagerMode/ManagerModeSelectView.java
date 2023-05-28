/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.ManagerMode;

import View.ManagerMode.NoticeBoardManagerModeView;
import View.ManagerMode.MovieGenreManagerModeView;
import Controller.Main;
import View.FirstView;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * ������ �α��ο� ���� ���� �� ������ ��带 �� �� �ְ� ���ִ� ���� ȭ��
 */
public class ManagerModeSelectView extends JFrame {

    JPanel p;
    JButton b1, b2, b3, back;

    public ManagerModeSelectView() {
        p = new JPanel();
        p.setLayout(null);
        setSize(600, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ȭ�� ��� ��ġ
        setTitle("������ ȭ��");
        setVisible(true);

        b1 = new JButton("ȸ�� ���� ����");
        b1.setLayout(null);
        b1.setBounds(20, 20, 150, 50);
        b1.setFont(new Font("���� ���", Font.BOLD, 14));
        b1.setBackground(Color.white);

        b2 = new JButton("��ȭ ����");
        b2.setLayout(null);
        b2.setBounds(215, 20, 150, 50);
        b2.setFont(new Font("���� ���", Font.BOLD, 14));
        b2.setBackground(Color.white);

        b3 = new JButton("�Խ��� ����");
        b3.setLayout(null);
        b3.setBounds(415, 20, 150, 50);
        b3.setFont(new Font("���� ���", Font.BOLD, 14));
        b3.setBackground(Color.white);

        back = new JButton("�ڷΰ���");
        back.setLayout(null);
        back.setBounds(494, 81, 100, 30);
        back.setFont(new Font("���� ���", Font.BOLD, 14));
        back.setBackground(Color.white);
        p.add(back);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        add(p);
        setVisible(true);

        b1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MembershipManagerModeView();
            }
        });

        b2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MovieGenreManagerModeView();
            }
        });

        b3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new NoticeBoardManagerModeView();
            }
        });
    
      back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
              setVisible(false);
              new FirstView();
            }
        });
    }

    public static void main(String[] args) {
        new ManagerModeSelectView();
    }
}
