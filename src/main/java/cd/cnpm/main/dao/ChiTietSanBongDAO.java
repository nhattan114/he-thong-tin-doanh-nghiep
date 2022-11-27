package cd.cnpm.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cd.cnpm.main.entity.ChiTietSanbong;

public class ChiTietSanBongDAO {
	
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    
    public ChiTietSanBongDAO() throws SQLException, ClassNotFoundException {
//      Account for sql connection
        Connect conn = new Connect();
        connection = conn.getConnection();
    }

    public void addChiTietSanbong(ChiTietSanbong ctsb) throws SQLException {
    	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ChiTietSanBong VALUES (?,?)");
    	
        preparedStatement.setInt(1, ctsb.getIdSB());
        preparedStatement.setInt(2, ctsb.getIdSlot());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public int deleteChiTietSanBong(int idSB) throws SQLException {
        String sql = "DELETE FROM ChiTietSanBong where idSB = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setInt(1, idSB);
        
        return preparedStatement.executeUpdate(); 
    }
    
    
    public List<ChiTietSanbong> getChiTietSanBongsByIdSB(int idSB) throws SQLException {
    	List<ChiTietSanbong> list = new ArrayList<ChiTietSanbong>();
        String sql = "SELECT * FROM ChiTietSanBong WHERE idSB = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idSB);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	ChiTietSanbong ctsb = new ChiTietSanbong();
            	ctsb.setIdSB(rs.getInt(1));
            	ctsb.setIdSlot(rs.getInt(2));
            	list.add(ctsb);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }    
}
