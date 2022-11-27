package cd.cnpm.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cd.cnpm.main.entity.DonDatSan;

public class DonDatSanDAO {
	
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    
    public DonDatSanDAO() throws SQLException, ClassNotFoundException {
//      Account for sql connection
        Connect conn = new Connect();
        connection = conn.getConnection();
    }

      public void addDonDatSan(DonDatSan donDatSan) throws SQLException, ClassNotFoundException {
    	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DonDatSan VALUES (?,?,DEFAULT, DEFAULT)");
    	
        preparedStatement.setInt(1, donDatSan.getIdUser());
        preparedStatement.setInt(2, donDatSan.getIdSchedule());
        
        preparedStatement.executeUpdate();
        preparedStatement.close();
        
        // Khi đã đặt sân thì phải cập nhật cho giờ đó để không được đặt nữa
        SanBongScheduleDAO sanBongScheduleDAO = new SanBongScheduleDAO();
        sanBongScheduleDAO.updateAvailable(donDatSan.getIdSchedule(), false);
    }
     
    
    public boolean deleteDonDatSanByIdDon(int idDon) throws SQLException {
        String sql = "DELETE FROM DonDatSan where idDon = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setInt(1, idDon);
        boolean result = preparedStatement.executeUpdate() == 1;
        
        preparedStatement.close();
        return result; 
    }
    
     public boolean deleteAllByIdSB(int idSB) throws SQLException {
        String sql = "DELETE FROM DonDatSan where idSchedule IN (SELECT idSchedule FROM dbo.SanBongSchedule WHERE idSB = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setInt(1, idSB);
        boolean result = preparedStatement.executeUpdate() == 1;
        
        preparedStatement.close();
        return result; 
    }
     
     public List<DonDatSan> getAllByIdSB (int idSB) throws SQLException {
        // Lấy đơn đã đặt của một user
        List<DonDatSan> list = new ArrayList<DonDatSan>();
        String sql = "SELECT * FROM DonDatSan WHERE idSchedule IN (SELECT idSchedule FROM dbo.SanBongSchedule WHERE idSB = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, idSB);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	DonDatSan donDatSan = new DonDatSan();
                donDatSan.setIdDon(rs.getInt(1));
                donDatSan.setIdUser(rs.getInt(2));
            	donDatSan.setIdSchedule(rs.getInt(3));
                donDatSan.setState(rs.getInt(4));
                donDatSan.setCreatedAt(rs.getTimestamp(5));
            	list.add(donDatSan);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
     
    public boolean disapproveDonDatSanByIdDon(int idDon) throws SQLException {
        // Từ chối đơn đặt sân bằng cách set state = -1;
        String sql = "UPDATE DonDatSan SET state = -1 where idDon = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setInt(1, idDon);
        boolean result = preparedStatement.executeUpdate() == 1;
        
        preparedStatement.close();
        return result; 
    }
    
    public boolean approveDonDatSanByIdDon(int idDon) throws SQLException {
       // Từ chối đơn đặt sân bằng cách set state = -1;
       String sql = "UPDATE DonDatSan SET state = 1 where idDon = ?";
       PreparedStatement preparedStatement = connection.prepareStatement(sql);

       preparedStatement.setInt(1, idDon);
       boolean result = preparedStatement.executeUpdate() == 1;

       preparedStatement.close();
       return result; 
   }
    
    public List<DonDatSan> getDonDatSansByIdUser (int idUser) throws SQLException {
        // Lấy đơn đã đặt của một user
        List<DonDatSan> list = new ArrayList<DonDatSan>();
        String sql = "SELECT * FROM DonDatSan WHERE idUser = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, idUser);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	DonDatSan donDatSan = new DonDatSan();
                donDatSan.setIdDon(rs.getInt(1));
                donDatSan.setIdUser(rs.getInt(2));
            	donDatSan.setIdSchedule(rs.getInt(3));
                donDatSan.setState(rs.getInt(4));
                donDatSan.setCreatedAt(rs.getTimestamp(5));
            	list.add(donDatSan);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int getIdScheduleByIdDon(int idDon) throws SQLException {
        // Lấy tất cả các đơn chưa duyệt của chủ sân
        int idSchedule = 0;
        String sql = "SELECT idSchedule FROM dbo.DonDatSan WHERE idDon = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, idDon);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	idSchedule = rs.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idSchedule;
    }
    
    public List<DonDatSan> getDonDatSansByIdChuSan (int idChuSan) throws SQLException {
        // Lấy tất cả các đơn chưa duyệt của chủ sân
        List<DonDatSan> list = new ArrayList<DonDatSan>();
        String sql = "SELECT * FROM dbo.DonDatSan WHERE (idSchedule IN (SELECT idSchedule FROM dbo.SanBongSchedule WHERE idSB IN ( SELECT idSB FROM dbo.SanBong WHERE idUser = ? ) AND available = 0)) AND state = 0";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, idChuSan);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	DonDatSan donDatSan = new DonDatSan();
                donDatSan.setIdDon(rs.getInt(1));
                donDatSan.setIdUser(rs.getInt(2));
            	donDatSan.setIdSchedule(rs.getInt(3));
                donDatSan.setState(rs.getInt(4));
                donDatSan.setCreatedAt(rs.getTimestamp(5));
            	list.add(donDatSan);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
