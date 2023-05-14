/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reser;

/**
 *
 * @author USER
 */
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TheaterPanel extends JPanel implements MovieFactory, ActionListener {

    // ?��?��?��??��?? ?�결 ??�?
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "12341234";

    // 콤보�??��???? ???? �??��?? 극�?? ??보�?? �??��?�기 ???? 쿼리
    private static final String SELECT_THEATERS = "SELECT * FROM theater";

    // 극�?? ?????? ???? ???��?�? 콤보�???
    private JLabel titleLabel;
    private JComboBox<String> theaterComboBox;

    public TheaterPanel() {

        
        // 극�?? ?????? ???? ???��? ????
        titleLabel = new JLabel("????�??? ?????�주?��??:");

        // ?��?��?��??��?��???? 극�?? ??보�?? �??��???? 리�?��?��?? �?�?
        ArrayList<String> theaterNames = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM theater")) {
            while (rs.next()) {
                theaterNames.add(rs.getString("t_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String[] theaterArray = theaterNames.toArray(new String[theaterNames.size()]);

        // 극�?? ?????? ???? JList ????
        final JList<String> theaterList = new JList<>(theaterArray);
        theaterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 극�?? ???? ???��?�? JList�? ?��???? �?�?
        add(titleLabel);
        add(new JScrollPane(theaterList));

        // 극�?? ???? JList?? ?��?? 리�?��?? ?��?
        theaterList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
            String selectedTheater = theaterList.getSelectedValue();

            // ?????? 극�?��?? ?��?��???? ?�르 ???? ?��?? ????
            SelectedGenrePanel genrePanel = new SelectedGenrePanel(selectedTheater);

            // �?�? ?��???? ???? 보�?��?�? ???? ?��???? ??거�??�?, ?�르 ???? ?��???? �?�???
            Container parent = TheaterPanel.this.getParent();
            Component currentPanel = parent.getComponent(0);
            parent.remove(currentPanel);
            parent.add(genrePanel);

            // �?경�?? ?��?? 구�?��?? �???
            parent.revalidate();
            parent.repaint();
        }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == theaterComboBox) {
            String selectedTheater = (String) theaterComboBox.getSelectedItem();

            // ?????? 극�?��?? ?��?��???? ?�르 ???? ?��?? ????
            SelectedGenrePanel genrePanel = new SelectedGenrePanel(selectedTheater);

            // �?�? ?��???? ?�르 ???? ?��???? �?�???�?, 카�?? ???��?????��? ????
            this.getParent().add(genrePanel, "Genre");
            CardLayout cl = (CardLayout) this.getParent().getLayout();
            cl.show(this.getParent(), "Genre");
        }
    }

    @Override
    public TheaterPanel createTheaterPanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GenrePanel createGenrePanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MoviePanel createMoviePanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}