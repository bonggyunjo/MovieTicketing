/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManagerMode;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MyMouseListener extends MouseAdapter {

    public void mouseClicked(MouseEvent e) {
      
        if(e.getButton()==3){
             JOptionPane.showMessageDialog(null, "���� �߽��ϴ�.");
        }
    }
    
}
