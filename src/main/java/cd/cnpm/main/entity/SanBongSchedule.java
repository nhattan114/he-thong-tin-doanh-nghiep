/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cd.cnpm.main.entity;

import java.sql.Time;

/**
 *
 * @author Drake
 */
public class SanBongSchedule {
    private int idSchedule;
    private int idSB;
    private int idSlot;
    private Time batDau;
    private Time ketThuc;
    private boolean available;
    
    public SanBongSchedule() {
        this.idSB = 0;
        this.idSlot = 0;
        this.batDau = null;
        this.ketThuc = null;
    }
    
    public SanBongSchedule(int idSB, int idSlot, Time batDau, Time ketThuc) {
        this.idSB = idSB;
        this.idSlot = idSlot;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public int getIdSB() {
        return idSB;
    }

    public void setIdSB(int idSB) {
        this.idSB = idSB;
    }

    public int getIdSlot() {
        return idSlot;
    }

    public void setIdSlot(int idSlot) {
        this.idSlot = idSlot;
    }

    public Time getBatDau() {
        return batDau;
    }

    public void setBatDau(Time batDau) {
        this.batDau = batDau;
    }

    public Time getKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(Time ketThuc) {
        this.ketThuc = ketThuc;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    } 

    @Override
    public String toString() {
        return "SanBongSchedule{" + "idSchedule=" + idSchedule + ", idSB=" + idSB + ", idSlot=" + idSlot + ", batDau=" + batDau + ", ketThuc=" + ketThuc + ", available=" + available + '}';
    }
    
    
}
