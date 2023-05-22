/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.LoginDto;
import Controller.MovieGenreDto;
import DbConnect.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kjbg4
 */
public class MovieGenreDao {

    //������ ���� �޼���
  public boolean insert(MovieGenreDto dto) {
    Connection conn = null;
    PreparedStatement st1 = null;
    PreparedStatement st2 = null;
    int flag = 0;
    try {
        
        conn = new DbConnect().getConn();
        conn.setAutoCommit(false);
       String sql = "INSERT INTO genre (g_name) VALUES (?);";
st1 = conn.prepareStatement(sql);
st1.setString(1, dto.getGenre());
flag = st1.executeUpdate();

sql = "INSERT INTO movie (m_name) VALUES (?);";
st2 = conn.prepareStatement(sql);
st2.setString(1, dto.getMovie());
flag = st2.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // ... (close resources)
    }
    return flag > 0;
}

    public MovieGenreDto getData(String genre, String movie) {
        //�ʿ��� ��ü�� ���� ���� ���� �̸� �����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MovieGenreDto dto = null;

        try {
            //Connection ��ü�� ������ ������ 
            conn = new DbConnect().getConn();
            //������ sql ���� ���� �̸� �غ��ϱ�
            String sql = "SELECT movie.m_name, genre.g_name " + "FROM movie " + "JOIN genre ON movie.t_id = genre.t_id WHERE genre.g_name = ? AND movie.m_name = ?";
            //PreparedStatement ��ü�� ������ ������
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, movie);
            pstmt.setString(2, genre);
            //? �� �ʿ��Ѱ� ���ε��ϱ� 
            //select �� �����ϰ� ����� ResultSet ���� �޾ƿ��� 
            rs = pstmt.executeQuery();
            if (rs.next()) {
                //cursor �� ��ġ�� ���� Į�� �����͸� ������

                //���� ���� ����
                dto = new MovieGenreDto.Builder()
                        .setGenre(rs.getString("g_name"))
                        .setMovie(rs.getString("m_name"))
                        .build();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return dto;
    }

    public boolean delete(MovieGenreDto dto) {
       //�ʿ��� ��ü�� ���� ���� ���� �̸� �����
        Connection conn = null;
        PreparedStatement pstmt = null;
        int flag = 0;
        try {
            //Connection ��ü�� ������ ������ 
            conn = new DbConnect().getConn();
            //������ sql ���� ���� �̸� �غ��ϱ�
            String sql = "DELETE FROM movie"
                    + " WHERE m_name = ?";
            //PreparedStatement ��ü�� ������ ������
            pstmt = conn.prepareStatement(sql);
            //? �� �ʿ��Ѱ� ���ε��ϱ� 
            pstmt.setString(1, dto.getMovie());
            //sql �� �����ϱ� (INSERT, UPDATE, DELETE)
            flag = pstmt.executeUpdate();
          
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        if (flag > 0) {
            //����
            return true;
        } else {
            //����
            return false;
        }
    }

    public List<MovieGenreDto> getList() {
        //�ʿ��� ��ü�� ���� ���� ���� �̸� �����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MovieGenreDto> list = new ArrayList<>();

        try {
            //Connection ��ü�� ������ ������ 
            conn = new DbConnect().getConn();
            //������ sql ���� ���� �̸� �غ��ϱ�
            String sql = "SELECT movie.m_name, genre.g_name FROM movie JOIN genre ON movie.t_id = genre.t_id";

            //PreparedStatement ��ü�� ������ ������
            pstmt = conn.prepareStatement(sql);
            //? �� �ʿ��Ѱ� ���ε��ϱ� 

            //select �� �����ϰ� ����� ResultSet ���� �޾ƿ��� 
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //cursor �� ��ġ�� ���� Į�� �����͸� ������ 

                //���� ���� ����
                MovieGenreDto dto = new MovieGenreDto.Builder()
                        .setMovie(rs.getString("m_name"))
                        .setGenre(rs.getString("g_name"))
                        .build();

                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return list;
    }
}
