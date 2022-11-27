package cd.cnpm.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cd.cnpm.main.entity.SanBongSchedule;

public class SanBongScheduleDAO {
	
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    
    public SanBongScheduleDAO() throws SQLException, ClassNotFoundException {
//      Account for sql connection
        Connect conn = new Connect();
        connection = conn.getConnection();
    }
    
     public boolean addSanBongSchedule(SanBongSchedule schedule) throws SQLException{
    	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO SanBongSchedule(idSB, idSlot, batDau, ketThuc) VALUES (?,?,?,?)");
    	
        preparedStatement.setInt(1, schedule.getIdSB());
        preparedStatement.setInt(2, schedule.getIdSlot());
        preparedStatement.setTime(3, schedule.getBatDau());
        preparedStatement.setTime(4, schedule.getKetThuc());
        
        boolean result = preparedStatement.executeUpdate() == 1;
        preparedStatement.close();
        
        return result;
    }
     
    public boolean deleteSanBongSchedule(int idSB) throws SQLException {
        String sql = "DELETE FROM SanBongSchedule where idSB = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setInt(1, idSB);
        boolean result = preparedStatement.executeUpdate() == 1;
        
        preparedStatement.close();
        return result; 
    }
    
    public List<SanBongSchedule> getSanBongSchedulesByIdSlot (int idSB, int idSlot) throws SQLException {
        // Lấy schedule của một slot của một sân bóng.
        List<SanBongSchedule> list = new ArrayList<SanBongSchedule>();
        String sql = "SELECT * FROM SanBongSchedule WHERE idSB = ? AND idSlot = ? AND available = 1 AND (DATEPART(HOUR, batDau) >= DATEPART(HOUR, GETDATE()) + 1)";
        // String sql = "SELECT * FROM SanBongSchedule WHERE idSB = ? " + 
        //        "AND idSlot = ? AND available = 1 AND (DATEPART(HOUR, batDau) >= DATEPART(HOUR, GETDATE()) + 1)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, idSB);
            preparedStatement.setInt(2, idSlot);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	SanBongSchedule sb = new SanBongSchedule();
                sb.setIdSchedule(rs.getInt(1));
                sb.setIdSB(rs.getInt(2));
            	sb.setIdSlot(rs.getInt(3));
            	sb.setBatDau(rs.getTime(4));
            	sb.setKetThuc(rs.getTime(5));
                sb.setAvailable(rs.getBoolean(6));
            	list.add(sb);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public SanBongSchedule getById(int idSchedule) throws SQLException{
       SanBongSchedule schedule = null;
       String sql = "SELECT * FROM SanBongSchedule WHERE idSchedule = ?";
       PreparedStatement preparedStatement = connection.prepareStatement(sql);

       preparedStatement.setInt(1, idSchedule);
       preparedStatement.execute();

       resultSet = preparedStatement.getResultSet();
       while (resultSet.next()) {
           schedule = new SanBongSchedule();
           schedule.setIdSchedule(resultSet.getInt(1));
           schedule.setIdSB(resultSet.getInt(2));
           schedule.setIdSlot(resultSet.getInt(3));
           schedule.setBatDau(resultSet.getTime(4));
           schedule.setKetThuc(resultSet.getTime(5));
       }

       preparedStatement.close();
       return schedule;
   }
    
    public void updateAvailable(int idSchedule, boolean avail) throws SQLException{
        // Cập nhật available = 0 vì đã được đặt sân
        String sql = "UPDATE SanBongSchedule SET available = ? WHERE idSchedule = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setBoolean(1, avail);
        preparedStatement.setInt(2, idSchedule);
        
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean isAvailable(int idSchedule) throws SQLException{
       boolean isAvail = false;
       String sql = "SELECT available FROM SanBongSchedule WHERE idSchedule = ?";
       PreparedStatement preparedStatement = connection.prepareStatement(sql);

       preparedStatement.setInt(1, idSchedule);
       preparedStatement.execute();

       resultSet = preparedStatement.getResultSet();
       while (resultSet.next()) {
           isAvail = resultSet.getBoolean(1);
       }

       preparedStatement.close();
       return isAvail;
   }
}
