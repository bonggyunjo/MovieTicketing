/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DbConnect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class DbConnect {
      private Connection conn;
    
 
    public DbConnect(){
         try {
	    	 //����Ŭ ����̹� Ŭ���� �ε�(OracleDriver Ŭ������ ����� �غ� �Ѵ�)
	         Class.forName("org.mariadb.jdbc.Driver");
	         //������ DB �� ����
	       final String driver = "org.mariadb.jdbc.Driver";
		final String DB_IP = "localhost";
		final String DB_PORT = "3306";
		final String DB_NAME = "test";
		final String DB_URL = 
				"jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
	         //DB ���� ��ü�� ������ ���ͼ� �ʵ忡 ���
	         conn=DriverManager.getConnection(DB_URL, "root", "12341234");
	         //���ܰ� �߻����� �ʰ� ������� ��������� �����̵Ǹ� ���� �����̴�.
	         System.out.println("Oracle DB ���� ����!");
	         
	      }catch(Exception e){
                  System.out.println("���� ����");
	      }
	}
    public Connection getConn() {
		return conn;
	}
  
          public static void main(String[] args) {
        DbConnect dbconnect = new DbConnect();
    }
}
