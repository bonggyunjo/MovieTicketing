/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.MovieGenreDao;
import Model.ProfileManagerModeDao;
import java.beans.PropertyChangeEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * �������� ��ȭ ���� ��Ʈ�ѷ�
 */
public class MovieGenreController {

    DefaultTableModel dft;
    JTable table;
    List<MovieGenreDto> companys;
    
    public MovieGenreController(DefaultTableModel dft, JTable table){
        this.dft=dft;
        this.table = table;
        table.setModel(dft);  // ���̺�� DefaultTableModel ����
    }
    
    public void showMembers() {
        MovieGenreDao dao = new MovieGenreDao();
        companys = dao.getList();
        dft.setRowCount(0);
        for (MovieGenreDto tmp : companys) {
            Object[] row = {tmp.getGenre(), tmp.getMovie()};
            dft.addRow(row);
        }
        }
    

    public void addAction(String m_name, String g_name) {
        //1. �Է��� �̸��� �ּҸ� �о�´�.

        //2. DB �� �����Ѵ�.
        MovieGenreDto dto = new MovieGenreDto.Builder()
                .setMovie(m_name)
                .setGenre(g_name)
                .build();
                
        //�۾��� �������θ� ���� �޴´�. 
        boolean isSuccess = new MovieGenreDao().insert(dto);

        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "���� �߽��ϴ�.");
            dft.setRowCount(0);
            showMembers();
               dft.fireTableDataChanged();
            //���� ������ ������ 0 �� ����� 

            //�ٽ� ����ϱ�
        } else {
            JOptionPane.showMessageDialog(null, "���� ����! �ߺ��� ���̵� �ֽ��ϴ�.");
        }

    }
    
 
}
