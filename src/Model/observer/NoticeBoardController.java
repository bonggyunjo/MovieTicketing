/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.observer;

import Model.observer.Reviewdata;
import Model.observer.Observer;
import Model.observer.NoticeBoardView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *   �Խ��� ���� �� ���� ��Ʈ�ѷ�
 * 
 * @author kjbg4
 */
public class NoticeBoardController {

    final String DB_IP = "113.198.234.132";
    final String DB_PORT = "9090";
    final String DB_NAME = "moviedb";
    final String DB_URL
            = "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
    public ReviewData reviewData;
    private final NoticeBoardView noticeBoard;

    public NoticeBoardController(NoticeBoardView noticeBoard) {
        this.noticeBoard = noticeBoard;
        reviewData = new ReviewData();
    }

    public void registerObserver(Observer observer) {
        reviewData.registerObserver(observer);
    }

    public void saveReview(String rate, String review) {
        try (Connection conn = DriverManager.getConnection(DB_URL, "jbg", "12341234"); Statement stmt = conn.createStatement()) {
            // INSERT 쿼리 ?��??
            String sql = "INSERT INTO review (rate, review) VALUES ('" + rate + "', '" + review + "')";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "���䰡 ����Ǿ����ϴ�.");
            System.out.println("save reivew observer");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "���� ���忡 �����Ͽ����ϴ� .");
        }
    }

    public void showAllReviews() {

        JTextArea txtlog = noticeBoard.getTxtlog();
        txtlog.setText("");

        try (Connection conn = DriverManager.getConnection(DB_URL, "jbg", "12341234"); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT rate, review FROM review")) {
            while (rs.next()) {
                String rating = rs.getString("rate");
                String review = rs.getString("review");
                String reviewString = "���� : " + rating + " / ���� : " + review + "\n";
                txtlog.append(reviewString);
                System.out.println("show all reviews observer");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "���� ��ȸ�� �����Ͽ����ϴ�..");
        }
    }

    private static class ReviewData {

        public ReviewData() {
        }

        private void registerObserver(Observer observer) {

        }
    }
}
