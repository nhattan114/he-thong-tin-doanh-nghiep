/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cd.cnpm.main.dao;

import cd.cnpm.main.entity.ChiTietUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Drake
 */
public class ChiTietUserDAO {
    
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    
    public ChiTietUserDAO() throws SQLException, ClassNotFoundException {
//      Account for sql connection
        Connect conn = new Connect();
        connection = conn.getConnection();
    }
    
    public boolean deleteByIdUser(int idUser) throws SQLException {
        String sql = "DELETE FROM ChiTietUser where idUser = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setInt(1, idUser);
        boolean result = preparedStatement.executeUpdate() == 1;
        
        preparedStatement.close();
        return result; 
    }
    
    public boolean addChiTietUser(ChiTietUser accDetail) throws SQLException{
        boolean result;
        
    	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ChiTietUser(idUser," + 
                " firstName, lastName, sex, birthDay, cmnd, state, sdt) VALUES (?,?,?,?,?,?,?,?)");
    	
        preparedStatement.setInt(1, accDetail.getIdUser());
        preparedStatement.setString(2, accDetail.getFirstName());
        preparedStatement.setString(3, accDetail.getLastName());
        preparedStatement.setBoolean(4, accDetail.getSex());
        preparedStatement.setDate(5, accDetail.getBirthDay());
        preparedStatement.setString(6, accDetail.getCmnd());
        preparedStatement.setBoolean(7, accDetail.getState());
        preparedStatement.setString(8, accDetail.getSdt());
        
        result = preparedStatement.executeUpdate() == 1;
        preparedStatement.close();
        
        return result;
    }
    
    public ChiTietUser getAccountByIdSB(int idSB) throws SQLException{
        // Lấy thông tin chủ sân bằng idSB
        ChiTietUser accDetail = null;
        
        String sql = "SELECT * FROM dbo.ChiTietUser WHERE idUser = (SELECT idUser FROM dbo.SanBong WHERE idSB = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);        
        preparedStatement.setInt(1, idSB);

        preparedStatement.execute();
        
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            accDetail = new ChiTietUser();
            accDetail.setIdUser(resultSet.getInt(1));
            accDetail.setFirstName(resultSet.getString(2));
            accDetail.setLastName(resultSet.getString(3));
            accDetail.setSex(resultSet.getBoolean(4));  
            accDetail.setBirthDay(resultSet.getDate(5));
            accDetail.setCmnd(resultSet.getString(6));
            accDetail.setState(resultSet.getBoolean(7));
            accDetail.setSdt(resultSet.getString(8));   
        }
        
        preparedStatement.close();
        return accDetail;
    }
    
    public ChiTietUser getById(int idUser) throws SQLException{
        // Lấy thông tin chủ sân bằng idSB
        ChiTietUser accDetail = null;
        
        String sql = "SELECT * FROM dbo.ChiTietUser WHERE idUser = (SELECT idUser FROM dbo.SanBong WHERE idSB = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);        
        preparedStatement.setInt(1, idUser);

        preparedStatement.execute();
        
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            accDetail = new ChiTietUser();
            accDetail.setIdUser(resultSet.getInt(1));
            accDetail.setFirstName(resultSet.getString(2));
            accDetail.setLastName(resultSet.getString(3));
            accDetail.setSex(resultSet.getBoolean(4));  
            accDetail.setBirthDay(resultSet.getDate(5));
            accDetail.setCmnd(resultSet.getString(6));
            accDetail.setState(resultSet.getBoolean(7));
            accDetail.setSdt(resultSet.getString(8));   
        }
        
        preparedStatement.close();
        return accDetail;
    }
    
    public ChiTietUser getByIdUser(int idUser) throws SQLException{
        // Lấy chi tiết của một user
        ChiTietUser accDetail = null;
        
        String sql = "SELECT * FROM dbo.ChiTietUser WHERE idUser = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);        
        preparedStatement.setInt(1, idUser);

        preparedStatement.execute();
        
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            accDetail = new ChiTietUser();
            accDetail.setIdUser(resultSet.getInt(1));
            accDetail.setFirstName(resultSet.getString(2));
            accDetail.setLastName(resultSet.getString(3));
            accDetail.setSex(resultSet.getBoolean(4));  
            accDetail.setBirthDay(resultSet.getDate(5));
            accDetail.setCmnd(resultSet.getString(6));
            accDetail.setState(resultSet.getBoolean(7));
            accDetail.setSdt(resultSet.getString(8));   
        }
        
        preparedStatement.close();
        return accDetail;
    }
    
    public String getFirstNameByIdUser(int idUser) throws SQLException{
        // Lấy thông tin chủ sân bằng idSB
        ChiTietUser accDetail = null;
        
        String sql = "SELECT firstName FROM dbo.ChiTietUser WHERE idUser = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);        
        preparedStatement.setInt(1, idUser);

        preparedStatement.execute();
        
        String firstName = "";
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            firstName = resultSet.getString(1);
        }
        
        preparedStatement.close();
        return firstName;
    }
    
    public boolean isExistCMND(String cmnd) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT cmnd FROM ChiTietUser where cmnd = ?");
    	
        preparedStatement.setString(1, cmnd);
        
        ResultSet rs = preparedStatement.executeQuery();
        
        return rs.next();
    }
    
    public boolean isExistSDT(String sdt) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT sdt FROM ChiTietUser where sdt = ?");
    	
        preparedStatement.setString(1, sdt);
        
        ResultSet rs = preparedStatement.executeQuery();
        
        return rs.next();
    }
}
