/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.LoginDto;
import Controller.MyPageDto;
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
public class MyPageDao {
     public List<MyPageDto> getList() {
        //�ʿ��� ��ü�� ���� ���� ���� �̸� �����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MyPageDto> list = new ArrayList<>();

        try {
            //Connection ��ü�� ������ ������ 
            conn = new DbConnect().getConn();
            //������ sql ���� ���� �̸� �غ��ϱ�
            String sql = "SELECT s_row,s_col,t_id,g_id,m_id,cardnum"
                    + " FROM seat";
            //PreparedStatement ��ü�� ������ ������
            pstmt = conn.prepareStatement(sql);
            //? �� �ʿ��Ѱ� ���ε��ϱ� 

            //select �� �����ϰ� ����� ResultSet ���� �޾ƿ��� 
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //cursor �� ��ġ�� ���� Į�� �����͸� ������ 
                
                
                                                                                            //���� ���� ����
                MyPageDto dto = new MyPageDto.Builder()
                .setcardnum(rs.getInt("cardnum"))
                .setg_id(rs.getInt("g_id"))
                .setm_id(rs.getInt("m_id"))
                .sets_col(rs.getInt("s_col"))
                .sets_row(rs.getInt("s_row"))
                .sett_id(rs.getInt("t_id"))
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
