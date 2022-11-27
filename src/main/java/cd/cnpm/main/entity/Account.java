/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cd.cnpm.main.entity;

/**
 *
 * @author Drake
 */
public class Account {
    private int idUser;
    private String userName;
    private String password;
    private int role;

    public Account(String userName, String password, int role) {
        this.idUser = 0;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
    
    public Account() {
        this.idUser = 0;
        this.userName = null;
        this.password = null;
        this.role = 0;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" + "idUser=" + idUser + ", userName=" + userName + ", password=" + password + ", role=" + role + '}';
    }
   
    
}
