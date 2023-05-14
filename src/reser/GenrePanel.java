
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reser;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author USER
 */
public abstract class GenrePanel extends JPanel implements MovieFactory {
    String dbDriver = "org.mariadb.jdbc.Driver";
    String dbUrl = "jdbc:mariadb://localhost:3306/test";
    String dbUser = "root";
    String dbPassword = "12341234";
    Connection dbconn = null;
    
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "12341234";

    private static final String SELECT_GENRES = "SELECT DISTINCT genre.g_name FROM movie INNER JOIN genre ON movie.g_id = genre.g_id INNER JOIN theater ON movie.t_id = theater.t_id WHERE theater.t_name = '%s'";

    private JLabel titleLabel;
    private JList<String> genreList;
    private JComboBox<String> genreComboBox;
    private DefaultListModel<String> genreModel;

    public GenrePanel(String theaterName) {
        
        
        titleLabel = new JLabel("?�르�? ?????�주?��??:");
         ArrayList<String> genreNames = new ArrayList<>();
        //genreModel = new DefaultListModel<>();
        //genreList = new JList<>(genreModel);
        //JScrollPane scrollPane = new JScrollPane(genreList);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(String.format(SELECT_GENRES, theaterName))) {
            while (rs.next()) {
                //String genreName  = rs.getString("g_name");
                //genreModel.addElement(genreName);
                genreNames.add(rs.getString("g_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        String[] theaterArray = genreNames.toArray(new String[genreNames.size()]);
        
         // 극�?? ?????? ???? JList ????
        final JList<String> genreList = new JList<>(theaterArray);
        genreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        genreList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedGenre = (String) genreList.getSelectedValue();
                    SelectedGenrePanel genrePanel = new SelectedGenrePanel(selectedGenre);

                    // �?�? ?��???? ???? 보�?��?�? ???? ?��???? ??거�??�?, ?�르 ???? ?��???? �?�???
            Container parent = GenrePanel.this.getParent();
            Component currentPanel = parent.getComponent(0);
            parent.remove(currentPanel);
            parent.add(genrePanel);

            // �?경�?? ?��?? 구�?��?? �???
            parent.revalidate();
            parent.repaint();
                }
            }
        });

        add(titleLabel);
        add(new JScrollPane(genreList));
        
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == genreList) {
            String genreName = (String) genreList.getSelectedValue();

            // ?????? 극�?��?? ?��?��???? ?�르 ???? ?��?? ????
            SelectedMoviePanel moviePanel = new SelectedMoviePanel(genreName);

            // �?�? ?��???? ?�르 ???? ?��???? �?�???�?, 카�?? ???��?????��? ????
            this.getParent().add(moviePanel, "Movie");
            CardLayout cl = (CardLayout) this.getParent().getLayout();
            cl.show(this.getParent(), "Movie");
        }
    }

}
