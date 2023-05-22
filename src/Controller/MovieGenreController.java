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
 * @author kjbg4
 */
public class MovieGenreController {

    DefaultTableModel dft;
    JTable table;
    List<MovieGenreDto> companys;

    public void showMembers() {
        companys = new MovieGenreDao().getList();
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
            //���� ������ ������ 0 �� ����� 

            //�ٽ� ����ϱ�
        } else {
            JOptionPane.showMessageDialog(null, "���� ����! �ߺ��� ���̵� �ֽ��ϴ�.");
        }

    }
    
 
}
