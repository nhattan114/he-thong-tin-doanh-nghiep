/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cd.cnpm.main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Đây là class dùng để lấy connection để kết nối với database */
public class Connect {
    private Connection connection;
    
    public Connect() throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String username = "sa";
        String password = "nhattan114";
        connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1456;databaseName=DATSANBONG;", username, password);
    }
    
    public Connection getConnection(){
        return this.connection;
    }
}
