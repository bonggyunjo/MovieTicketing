/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.LoginDto;
import Controller.ManagerModeController;
import Model.ProfileManagerModeDao;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
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

/**
 *
 * @author kjbg4
 */
public class MovieManagerMode extends JFrame {

    JPanel p;
    JButton insert, delete, back;
    List<LoginDto> companys;
    DefaultTableModel dft;
    JTable table;
    JLabel label1, label2;
    JTextField genre, movie;

    MovieManagerMode() {
        //������ȭ�� ������ gui 
        p = new JPanel();
        p.setLayout(null);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ȭ�� ��� ��ġ
        setTitle("��ȭ ����");
        setVisible(true);

        insert = new JButton("�߰��ϱ�");
        insert.setLayout(null);
        insert.setBounds(210, 20, 150, 40);

        delete = new JButton("�����ϱ�");
        delete.setLayout(null);
        delete.setBounds(410, 20, 150, 40);

        back = new JButton("�ڷΰ���");
        back.setLayout(null);
        back.setBounds(686, 431, 100, 30);

        label1 = new JLabel("�帣 : ");
        label1.setLayout(null);
        label1.setBounds(200, 80, 100, 30);

        label2 = new JLabel("��ȭ : ");
        label2.setLayout(null);
        label2.setBounds(380, 80, 100, 30);

        genre = new JTextField();
        genre.setLayout(null);
        genre.setBounds(245, 80, 125, 30);

        movie = new JTextField();
        movie.setLayout(null);
        movie.setBounds(425, 80, 125, 30);

        p.add(label1);
        p.add(genre);
        p.add(movie);
        p.add(label2);
        p.add(back);
        p.add(insert);
        p.add(delete);
        add(p);

       
        String[] colNames = {"�帣", "��ȭ �̸�"};
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

        pane.setBounds(185, 140, 400, 300);
        p.add(pane);
        // ���̺� ���� �����۾��� �Ͼ���� ������ ������ ���
        setVisible(true);

        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new Home();
                setVisible(false);
            }
        });
    }

}
