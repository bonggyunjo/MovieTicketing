/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

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
public class ManagerModeController {

    DefaultTableModel dft;
    JTable table;
    // �߰��ϱ� �޼���
    List<LoginDto> companys;

    public void showMembers() {
        companys = new ProfileManagerModeDao().getList();
        for (LoginDto tmp : companys) {
            Object[] row = {tmp.getName(), tmp.getAge(), tmp.getId(), tmp.getPw(), tmp.getPhone(), tmp.getMail(), tmp.getAddress()};
            dft.addRow(row);
        }
    }

    public void addAction(String name, String age, String id, String pw, String phone, String mail, String address) {
        //1. �Է��� �̸��� �ּҸ� �о�´�.

        //2. DB �� �����Ѵ�.
        LoginDto dto = new LoginDto.Builder()
                .setName(name)
                .setAge(age)
                .setId(id)
                .setPw(pw)
                .setPhone(phone)
                .setMail(mail)
                .setAddress(address)
                .build();
        //�۾��� �������θ� ���� �޴´�. 
        boolean isSuccess = new ProfileManagerModeDao().insert(dto);

        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "���� �߽��ϴ�.");
            //���� ������ ������ 0 �� ����� 

            //�ٽ� ����ϱ�
        } else {
            JOptionPane.showMessageDialog(null, "���� ����! �ߺ��� ���̵� �ֽ��ϴ�.");
        }

    }

    //���� �޼���
    public void deleteAction() {
        //���õ� row �� �ε����� ���ͼ�

        int index = table.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "������ ���� ������ �ּ���.");
        }
        if (index == -1) {
            return;
        }

        //DB ���� �����ϰ�
        LoginDto dto = new LoginDto();
        dto.setPw(companys.get(index).getPw());
        new ProfileManagerModeDao().delete(dto);
        //�ٽ� ���
       

        //�۾��� �������θ� ���� �޴´�. 
        boolean isSuccess = new ProfileManagerModeDao().delete(dto);

        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "���� �߽��ϴ�.");
        } else {
            JOptionPane.showMessageDialog(null, "���� �߽��ϴ�.");
        }

    }

    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChange()");
        System.out.println(evt.getPropertyName());
        if (evt.getPropertyName().equals("tableCellEditor")) {
            //���õ� row �� index �� ���ͼ� 
            int index = table.getSelectedRow();
            //�ε����� �ش��ϴ� model ���� �Էµ� �̸��� �ּҸ� �о�´�. 
            String name = (String) dft.getValueAt(index, 0); //2��° �ε����� �ּҸ� �о��
            String age = (String) dft.getValueAt(index, 1); //1��° �ε����� �̸��� �о��
            String id = (String) dft.getValueAt(index, 2); //0��° �ε����� ��ȣ�� �о��
            String pw = (String) dft.getValueAt(index, 3); //1��° �ε����� �̸��� �о��
            String phone = (String) dft.getValueAt(index, 4); //1��° �ε����� �̸��� �о��
            String mail = (String) dft.getValueAt(index, 5); //3��° �ε����� �Ի����� �о��
            String address = (String) dft.getValueAt(index, 6); //3��° �ε����� �Ի����� �о��
            //DB �� ���� �ݿ�

            //���� ���� 
            LoginDto dto = new LoginDto.Builder()
                    .setName(name)
                    .setAge(age)
                    .setId(id)
                    .setPw(pw)
                    .setPhone(phone)
                    .setMail(mail)
                    .setAddress(address)
                    .build();
            new ProfileManagerModeDao().update(dto);
        }

    }
    //ȸ����� ��ü ���

}
