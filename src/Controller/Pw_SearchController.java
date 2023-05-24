/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.PwSearchDao;
import View.Pw_Search.Pw_SearchView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author kjbg4
 */
public class Pw_SearchController {

    private Pw_SearchView psv;

    public Pw_SearchController() {
        psv = new Pw_SearchView();

    }

    public void addAction(String uid, String uname) {
        LoginDto dto = new LoginDto.Builder()
                .setName(uname)
                .setId(uid)
                .build();
        PwSearchDao psd = new PwSearchDao();
        String value = psd.findpw(uid, uname);

        if (value.equals("errorinformation")) {
            JOptionPane.showMessageDialog(null, "��ġ�ϴ� ������ �����ϴ�. �ٽ� �õ����ּ���");
            psv.dispose(); // â�� ����

        } else {
            JOptionPane.showMessageDialog(null, "ȸ������ ��й�ȣ��" + value + "�Դϴ�.");
            psv.dispose(); // â�� ����
        }
    }

}
