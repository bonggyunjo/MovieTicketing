/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.observer;

import Model.observer.Observer;

/**
 *
 * @author cherr
 */


public interface Subject {
    public void registerObserver(Observer o); //������ ���
    public void removeObserver(Observer o);   //������ ����
    public void notifyObservers();            // ���� ��ȭ�� �˷��ִ� ������
}


