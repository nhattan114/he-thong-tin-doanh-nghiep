/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cd.cnpm.main.entity;

import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author Drake
 */
public class DonDatSan {
    private int idDon;
    private int idUser;
    private int idSchedule;
    private int state;
    private Timestamp createdAt;
    
    
    public DonDatSan() {
        this.idUser = 0;
        this.idSchedule = 0;
        this.createdAt = null;
    }
    
    public DonDatSan(int idUser, int idSchedule) {
        this.idDon = 0;
        this.idUser = idUser;
        this.idSchedule = idSchedule;
        this.state = 0;
        this.createdAt = null;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdDon() {
        return idDon;
    }

    public void setIdDon(int idDon) {
        this.idDon = idDon;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    
    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "DonDatSan{" + "idDon=" + idDon + ", idUser=" + idUser + ", idSchedule=" + idSchedule + ", state=" + state + ", createdAt=" + createdAt + '}';
    }
    
    
}
