package Model;


import Controller.LoginDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DbConnect.DbConnect;
public class ProfileManagerModeDao {

    //������ ���� �޼���
    public boolean insert(LoginDto dto) {
        Connection conn = null;
        PreparedStatement st = null;
        int flag = 0;
        try {
            conn = new DbConnect().getConn();
            String sql = "INSERT INTO profile" + "(name,age,id,pw,phone,mail,address)" + " values(?,?,?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            st.setString(1, dto.getName());       //1~5�� �ǹ̴� values�� ? ��
            st.setString(2, dto.getAge());
            st.setString(3, dto.getId());
            st.setString(4, dto.getPw());
            st.setString(5, dto.getPhone());
            st.setString(6, dto.getMail());
            st.setString(7, dto.getAddress());
            flag = st.executeUpdate(); //���� �޼���� ���� ���� �ϰ� ���ִ� �� // ��ü�� ���� ��ȯ��Ű�� �� // ���� ���� SQL�� ��������ִ� �� 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
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

    // ���� �޼���
    
    public boolean update(LoginDto dto) {
        //�ʿ��� ��ü�� ���� ���� ���� �̸� �����
        Connection conn = null;
        PreparedStatement pstmt = null;
        int flag = 0;
        try {
            //Connection ��ü�� ������ ������ 
            conn = new DbConnect().getConn();
            //������ sql ���� ���� �̸� �غ��ϱ�
            String sql = "UPDATE profile"
                    + " SET name=?, age=?, pw=? ,phone=?,mail=?,address=?"
                    + " WHERE id=?";          //���࿡ pw, name ,id�� �߰��� ��� address �� mail�� ������ �ȵ� ���� x ���� ���� ���� address, mail�� �߰����� ����
            //PreparedStatement ��ü�� ������ ������
            pstmt = conn.prepareStatement(sql);
            //? �� �ʿ��Ѱ� ���ε��ϱ� 
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getAge());
            pstmt.setString(3, dto.getPw());
            pstmt.setString(4, dto.getPhone());
            pstmt.setString(5, dto.getMail());
            pstmt.setString(6, dto.getAddress());
            pstmt.setString(7, dto.getId());
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

    public LoginDto getData(String id) {
        //�ʿ��� ��ü�� ���� ���� ���� �̸� �����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LoginDto dto = null;
        try {
            //Connection ��ü�� ������ ������ 
            conn = new DbConnect().getConn();
            //������ sql ���� ���� �̸� �غ��ϱ�
            String sql = "SELECT pw,name "
                    + " FROM profile"
                    + " WHERE id = ?";
            //PreparedStatement ��ü�� ������ ������
            pstmt = conn.prepareStatement(sql);
            //? �� �ʿ��Ѱ� ���ε��ϱ� 
            pstmt.setString(1, id);
            //select �� �����ϰ� ����� ResultSet ���� �޾ƿ��� 
            rs = pstmt.executeQuery();
            if (rs.next()) {
                //cursor �� ��ġ�� ���� Į�� �����͸� ������
                
                                                                                        //���� ���� ����
                dto = new LoginDto.Builder()
                .setPw(rs.getString("pw"))
                .setName(rs.getString("name"))
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
    public boolean delete(LoginDto dto) {
        //�ʿ��� ��ü�� ���� ���� ���� �̸� �����
        Connection conn = null;
        PreparedStatement pstmt = null;
        int flag = 0;
        try {
            //Connection ��ü�� ������ ������ 
            conn = new DbConnect().getConn();
            //������ sql ���� ���� �̸� �غ��ϱ�
            String sql = "DELETE FROM profile"
                    + " WHERE pw=?";
            //PreparedStatement ��ü�� ������ ������
            pstmt = conn.prepareStatement(sql);
            //? �� �ʿ��Ѱ� ���ε��ϱ� 
            pstmt.setString(1, dto.getPw());
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

    public List<LoginDto> getList() {
        //�ʿ��� ��ü�� ���� ���� ���� �̸� �����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<LoginDto> list = new ArrayList<>();

        try {
            //Connection ��ü�� ������ ������ 
            conn = new DbConnect().getConn();
            //������ sql ���� ���� �̸� �غ��ϱ�
            String sql = "SELECT name,age,id, pw,phone,mail,address"
                    + " FROM profile";
            //PreparedStatement ��ü�� ������ ������
            pstmt = conn.prepareStatement(sql);
            //? �� �ʿ��Ѱ� ���ε��ϱ� 

            //select �� �����ϰ� ����� ResultSet ���� �޾ƿ��� 
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //cursor �� ��ġ�� ���� Į�� �����͸� ������ 
                
                
                                                                                            //���� ���� ����
                LoginDto dto = new LoginDto.Builder()
                .setName(rs.getString("name"))
                .setAge(rs.getString("age"))
                .setId(rs.getString("id"))
                .setPw(rs.getString("pw"))
                .setPhone(rs.getString("phone"))
                .setMail(rs.getString("mail"))
                .setAddress(rs.getString("address"))
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
