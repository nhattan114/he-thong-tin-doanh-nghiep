/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cd.cnpm.main.entity;

import java.sql.Date;

/**
 *
 * @author Drake
 */
public class ChiTietUser extends Account{
    private int idUser;
    private String firstName;
    private String lastName;
    private boolean sex; // 0 - male, 1 - female
    private Date birthDay;
    private String cmnd;
    private boolean state; // 0 - false, 1 - true
    private String sdt;

    public ChiTietUser(int idUser, String firstName, String lastName, boolean sex, Date birthDay, String cmnd, boolean state, String sdt) {
        super();
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDay = birthDay;
        this.cmnd = cmnd;
        this.state = state;
        this.sdt = sdt;
    }
    
    public ChiTietUser(int idUser, String sdt, String firstName, boolean state) {
        super();
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = null;
        this.sex = false;
        this.birthDay = null;
        this.cmnd = null;
        this.state = state;
        this.sdt = sdt;
    }
    
    public ChiTietUser() {
        this.idUser = 0;
        this.firstName = null;
        this.lastName = null;
        this.sex = false;
        this.birthDay = null;
        this.cmnd = null;
        this.state = false;
        this.sdt = null;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "ChiTietUser{" + "idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", sex=" + sex + ", birthDay=" + birthDay + ", cmnd=" + cmnd + ", state=" + state + ", sdt=" + sdt + '}';
    }
}
