/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.ManagerMode;

import Controller.LoginDto;
import Controller.ManagerModeController;
import Controller.MovieGenreController;
import Controller.MovieGenreDto;
import Model.MovieGenreDao;
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
 *  ��ȭ ���� ȭ��
 * @author kjbg4
 */
public class MovieGenreManagerModeView extends JFrame implements ActionListener, PropertyChangeListener {
    //���� �ʵ� 
    JPanel p;
    JButton insert, delete, back;
    List<MovieGenreDto> companys;
    DefaultTableModel dft;
    JTable table;
    JLabel label1, label2;
    JTextField genre, movie;

    MovieGenreManagerModeView() {
     
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
        insert.setFont(new Font("���� ���", Font.BOLD, 14));
        insert.setBackground(Color.white);

        delete = new JButton("�����ϱ�");
        delete.setLayout(null);
        delete.setBounds(410, 20, 150, 40);
        delete.setFont(new Font("���� ���", Font.BOLD, 14));
        delete.setBackground(Color.white);

        back = new JButton("�ڷΰ���");
        back.setLayout(null);
        back.setBounds(686, 431, 100, 30);
        back.setFont(new Font("���� ���", Font.BOLD, 14));
        back.setBackground(Color.white);

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
                if (column == 0 || column == 1) {
                    return false;
                } else {
                    //�������� ��� ���� �����ϵ��� true�� �����Ѵ�.
                    return true;
                }

            }
        };
        table = new JTable(dft);
        MovieGenreController controller = new MovieGenreController(dft, table);
        // ��ũ�� ����
        JScrollPane pane = new JScrollPane(table);

        pane.setBounds(185, 140, 400, 300);
        p.add(pane);
        table.addPropertyChangeListener(this);
        //���̺� ȸ����� �߰��ϱ�
        showMembers();

        insert.addActionListener(this);
        delete.addActionListener(this);

        insert.setActionCommand("add");
        delete.setActionCommand("delete");

        // ���̺� ���� �����۾��� �Ͼ���� ������ ������ ���
        setVisible(true);

        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ManagerModeSelectView mmv = new ManagerModeSelectView();
                setVisible(false);
            }
        });
        insert.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("add")) {

                    String g_name = genre.getText();
                    String m_name = movie.getText();
                    
                   
                    controller.addAction(m_name, g_name);
                    dft.setRowCount(0);
                    showMembers();
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
    }

    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChange()");
        System.out.println(evt.getPropertyName());
        if (evt.getPropertyName().equals("tableCellEditor")) {
            //���õ� row �� index �� ���ͼ� 
            int index = table.getSelectedRow();
            //�ε����� �ش��ϴ� model ���� �Էµ� �̸��� �ּҸ� �о�´�. 
            String movie = (String) dft.getValueAt(index, 0); //2��° �ε����� �ּҸ� �о��
            String genre = (String) dft.getValueAt(index, 1); //1��° �ε����� �̸��� �о��

            //���� ���� 
            MovieGenreDto dto = new MovieGenreDto.Builder()
                    .setMovie(movie)
                    .setGenre(genre)
                    .build();

        }
    }

    //ȸ����� ��ü ���
    public void showMembers() {
        MovieGenreDao dao = new MovieGenreDao();
        companys = dao.getList();
        dft.setRowCount(0);
        for (MovieGenreDto tmp : companys) {
            Object[] row = {tmp.getGenre(), tmp.getMovie()};
            dft.addRow(row);
        }
    }

    //���� �޼���
    private void deleteAction() {
        //���õ� row �� �ε����� ���ͼ� 
        int index = table.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "������ ���� ������ �ּ���.");
        }
        if (index == -1) {
            return;
        }

        //DB ���� �����ϰ�
        MovieGenreDto dto = new MovieGenreDto();
        dto.setGenre(companys.get(index).getGenre());
        dto.setMovie(companys.get(index).getMovie());
        new MovieGenreDao().delete(dto);
        //�ٽ� ���
        dft.setRowCount(0);
        showMembers();

        //�۾��� �������θ� ���� �޴´�. 
        boolean isSuccess = new MovieGenreDao().delete(dto);

        if (isSuccess) {
            JOptionPane.showMessageDialog(this, "���� �߽��ϴ�.");
        } else {
            JOptionPane.showMessageDialog(this, "���� �߽��ϴ�.");
        }

    }

    public void actionPerformed(ActionEvent e) {

    }

}
