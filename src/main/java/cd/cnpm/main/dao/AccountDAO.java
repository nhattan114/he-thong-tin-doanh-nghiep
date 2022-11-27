package cd.cnpm.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cd.cnpm.main.entity.Account;

public class AccountDAO {
	
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    
    public AccountDAO() throws SQLException, ClassNotFoundException {
//      Account for sql connection
        Connect conn = new Connect();
        connection = conn.getConnection();
    }
    
    public boolean deleteByIdUser(int idUser) throws SQLException {
        String sql = "DELETE FROM Account where idUser = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setInt(1, idUser);
        boolean result = preparedStatement.executeUpdate() == 1;
        
        preparedStatement.close();
        return result; 
    }
    
    public int getLastestIdUser() throws SQLException{
        int idUser = 0;
        String sql = "SELECT IDENT_CURRENT('dbo.Account') AS idUser";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.execute();
        
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            idUser = resultSet.getInt(1);
        }
        
        preparedStatement.close();
        return idUser;
    }
    
    public Account getAccountByUserName(String username) throws SQLException{
        Account acc = null;
        
        String sql = "SELECT * FROM Account where userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);        
        preparedStatement.setString(1, username);

        preparedStatement.execute();
        
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            acc = new Account();
            acc.setUserName(resultSet.getString(1));
            acc.setPassword(resultSet.getString(2));
            acc.setRole(resultSet.getInt(3));
            acc.setIdUser(resultSet.getInt(4));            
        }
        
        preparedStatement.close();
        return acc;
    }
    
    public List<Account> getAllChuSanAccount () throws SQLException {
        List<Account> list = new ArrayList<Account>();
        String sql = "SELECT * FROM Account WHERE role = 1";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	Account acc = new Account();
                acc.setUserName(rs.getString(1));
                acc.setPassword(rs.getString(2));
                acc.setRole(rs.getInt(3));
                acc.setIdUser(rs.getInt(4));
            	list.add(acc);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    public Account getAccountById(int idUser) throws SQLException{
        // Lấy thông tin account bằng id
        Account acc = null;
        
        String sql = "SELECT * FROM Account where idUser = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);        
        preparedStatement.setInt(1, idUser);

        preparedStatement.execute();
        
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            acc = new Account();
            acc.setUserName(resultSet.getString(1));
            acc.setPassword(resultSet.getString(2));
            acc.setRole(resultSet.getInt(3));
            acc.setIdUser(resultSet.getInt(4));            
        }
        
        preparedStatement.close();
        return acc;
    }
    
    
    public boolean addAccount(Account acc) throws SQLException{
    	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Account(userName, password, role) VALUES (?,?,?)");
    	
        preparedStatement.setString(1, acc.getUserName());
        preparedStatement.setString(2, acc.getPassword());
        preparedStatement.setInt(3, acc.getRole());
        
        boolean result = preparedStatement.executeUpdate() == 1;
        preparedStatement.close();
        
        return result;
    }
    
    public boolean updatePasswordByIdUser(int idUser, String password) throws SQLException {
    	try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Account SET password = ? WHERE idUser = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, idUser);

            boolean result = preparedStatement.executeUpdate() == 1;

            preparedStatement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean isExistUsername(String userName) throws SQLException{
    	PreparedStatement preparedStatement = connection.prepareStatement("SELECT userName FROM Account WHERE userName = ?");
    	
        preparedStatement.setString(1, userName);
        
        ResultSet rs = preparedStatement.executeQuery();
        
        return rs.next();
    }
}
