package DbConnect.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
   
/*
    ����Ʈ���̽� ������ ���� Ŭ����
    �̱��� ���� ����
*/
public class DbConnect {
    public static DbConnect instance;
    public Connection conn;

    public DbConnect() {
        try {
            // ����Ŭ ����̹� Ŭ���� �ε�
            Class.forName("org.mariadb.jdbc.Driver");

            // DB ���� ���� ����
            final String DB_IP = "113.198.234.132";
            final String DB_PORT = "9090";
            final String DB_NAME = "moviedb";
            final String DB_URL = "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

            // DB ���� ��ü�� ������ ���ͼ� �ʵ忡 ���
            conn = DriverManager.getConnection(DB_URL, "jbg", "12341234");

            // ���� ���� �޽��� ���
            System.out.println("DB ���� ����!");
        } catch (Exception e) {
            System.out.println("���� ����");
        }
    }

  public static DbConnect getInstance() {
    if (instance == null) {
        synchronized (DbConnect.class) {
            if (instance == null) {
                instance = new DbConnect();
            }
        }
    }
    return instance;
}

    public Connection getConn() {
        return conn;
    }

}