package cd.cnpm.main.dao;

import cd.cnpm.main.entity.SanBong;
import cd.cnpm.main.entity.ChiTietSanbong;
import cd.cnpm.main.entity.SanBongSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SanBongDAO {
	
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    
    public SanBongDAO() throws SQLException, ClassNotFoundException {
        Connect conn = new Connect();
        connection = conn.getConnection();
    }
    
    public Time getPlusOneHour(Time time){
        LocalTime localTime = time.toLocalTime();
        localTime = localTime.plusHours(1);
        
        return Time.valueOf(localTime);
    }
    public boolean addSanBong(SanBong sb) throws SQLException, ClassNotFoundException {
        boolean result;
        
    	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO SanBong(idUser, "
    			+ "slot, address, openTime, closeTime, state, map, description, image, name, cost) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
    	
        preparedStatement.setInt(1, sb.getIdUser());
        preparedStatement.setInt(2, sb.getSlot());
        preparedStatement.setString(3, sb.getAddress());
        preparedStatement.setTime(4, sb.getOpenTime());
        preparedStatement.setTime(5, sb.getCloseTime());
        preparedStatement.setBoolean(6, sb.isState());
        preparedStatement.setString(7, sb.getMap());
        preparedStatement.setString(8, sb.getDescription());
        preparedStatement.setString(9, sb.getImage());
        preparedStatement.setString(10, sb.getName());
        preparedStatement.setInt(11, sb.getCost());
        
        result = preparedStatement.executeUpdate() == 1;
       
        if(!result){
            return false;
        }
        int idSB = getLastestIdSB();
        // Thêm sân mới thì cũng phải thêm chi tiết của sân
        ChiTietSanBongDAO chiTietSanBongDAO = new ChiTietSanBongDAO();
        // Generate idSlot tương ứng với số slot
        for(int i = 1; i <= sb.getSlot(); i ++){
            chiTietSanBongDAO.addChiTietSanbong(new ChiTietSanbong(idSB, i));
        }
        
        // Thêm chi tiết sân thì cũng phải thêm sân bóng schedule
        SanBongScheduleDAO sanBongScheduleDAO = new SanBongScheduleDAO();
        Time openTime = sb.getOpenTime();
        Time closeTime = sb.getCloseTime();
        
        // Generate idSchedule tương ứng với thời gian mở cửa và óng cửa
        for(int idSlot = 1; idSlot <= sb.getSlot(); idSlot ++){
            Time loopTime = openTime;
            while(loopTime.compareTo(closeTime) == -1){
                // Lặp đến khi giờ vượt qua giờ đóng cửa
                sanBongScheduleDAO.addSanBongSchedule(new SanBongSchedule(idSB, idSlot, loopTime, getPlusOneHour(loopTime)));
                loopTime = getPlusOneHour(loopTime);
            }
        }
            
        preparedStatement.close();
        return result;
    }
    
    
    public int getLastestIdSB() throws SQLException{
        int idSB = 0;
        String sql = "SELECT IDENT_CURRENT('dbo.SanBong') AS idSB";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.execute();
        
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            idSB = resultSet.getInt(1);
        }
        
        preparedStatement.close();
        return idSB;
    }
    
    public int getIdUserByIdSB(int idSB) throws SQLException{
        int idUser = 0;
        String sql = "SELECT idUser FROM SanBong WHERE idSB = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idSB);
        preparedStatement.execute();
        
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            idUser = resultSet.getInt(1);
        }
        
        preparedStatement.close();
        return idUser;
    }
    
    public List<Integer> getAllIdSBByIdUser(int idUser) throws SQLException{
        // Lấy hết id sân bóng của một chủ sân
        List<Integer> list = new ArrayList<Integer>();
        
        String sql = "SELECT idSB FROM SanBong WHERE idUser = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idUser);
        
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            list.add(rs.getInt(1));
        }
        
        return list;
    }
    public boolean deleteSanBong(int idSB) throws SQLException, ClassNotFoundException { 
        String sql = "DELETE FROM SanBong WHERE idSB = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setInt(1, idSB);
        
        DonDatSanDAO donDatSanDAO = new DonDatSanDAO();
        ChiTietSanBongDAO chiTietSanBongDAO = new ChiTietSanBongDAO();
        SanBongScheduleDAO scheduleDAO = new SanBongScheduleDAO();
        
        donDatSanDAO.deleteAllByIdSB(idSB);
        scheduleDAO.deleteSanBongSchedule(idSB);
        chiTietSanBongDAO.deleteChiTietSanBong(idSB);
        
        int result = preparedStatement.executeUpdate();
            
        preparedStatement.close();
        return result == 1; 
    }
    
    public boolean updateSanBong(SanBong sb, int idSB) throws SQLException {
    	try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE SanBong SET "
            		+ "address = ?, state = ?, map = ?, description = ?, "
            		+ "name = ? WHERE idSB = ?");
            preparedStatement.setString(1, sb.getAddress());
            preparedStatement.setBoolean(2, sb.isState());
            preparedStatement.setString(3, sb.getMap());
            preparedStatement.setString(4, sb.getDescription());
            preparedStatement.setString(5, sb.getName());
            preparedStatement.setInt(6, idSB);

            
            boolean result = preparedStatement.executeUpdate() == 1;

            preparedStatement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateImageByIdSB(int idSB, String imagePath) throws SQLException {
    	try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE SanBong SET image = ? WHERE idSB = ?");
            preparedStatement.setString(1, imagePath);
            preparedStatement.setInt(2, idSB);

            boolean result = preparedStatement.executeUpdate() == 1;

            preparedStatement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<SanBong> getSanBongs () throws SQLException {
        List<SanBong> list = new ArrayList<SanBong>();
        String sql = "select * from SanBong";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	SanBong sb = new SanBong();
                sb.setIdSB(rs.getInt(1));
            	sb.setIdUser(rs.getInt(2));
            	sb.setSlot(rs.getInt(3));
            	sb.setAddress(rs.getString(4));
            	sb.setOpenTime(rs.getTime(5));
            	sb.setCloseTime(rs.getTime(6));
            	sb.setState(rs.getBoolean(7));
            	sb.setMap(rs.getString(8));
            	sb.setDescription(rs.getString(9));
            	sb.setImage(rs.getString(10));
            	sb.setName(rs.getString(11));
                sb.setCost(rs.getInt(12));
            	list.add(sb);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<SanBong> getSanBongListByKeyword (String keyword) throws SQLException {
        List<SanBong> list = new ArrayList<SanBong>();
        String sql = "SELECT * FROM dbo.SanBong WHERE name LIKE ? ESCAPE '!'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	SanBong sb = new SanBong();
                sb.setIdSB(rs.getInt(1));
            	sb.setIdUser(rs.getInt(2));
            	sb.setSlot(rs.getInt(3));
            	sb.setAddress(rs.getString(4));
            	sb.setOpenTime(rs.getTime(5));
            	sb.setCloseTime(rs.getTime(6));
            	sb.setState(rs.getBoolean(7));
            	sb.setMap(rs.getString(8));
            	sb.setDescription(rs.getString(9));
            	sb.setImage(rs.getString(10));
            	sb.setName(rs.getString(11));
                sb.setCost(rs.getInt(12));
            	list.add(sb);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<SanBong> getSanBongListByIdUser (int idUser) throws SQLException {
        // lay list san bong cua chu san
        List<SanBong> list = new ArrayList<SanBong>();
        String sql = "select * from SanBong where idUser = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	SanBong sb = new SanBong();
                sb.setIdSB(rs.getInt(1));
            	sb.setIdUser(rs.getInt(2));
            	sb.setSlot(rs.getInt(3));
            	sb.setAddress(rs.getString(4));
            	sb.setOpenTime(rs.getTime(5));
            	sb.setCloseTime(rs.getTime(6));
            	sb.setState(rs.getBoolean(7));
            	sb.setMap(rs.getString(8));
            	sb.setDescription(rs.getString(9));
            	sb.setImage(rs.getString(10));
            	sb.setName(rs.getString(11));
                sb.setCost(rs.getInt(12));
            	list.add(sb);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public SanBong getById(int idSB) throws SQLException{
        SanBong sb = null;
        String sql = "SELECT * FROM SanBong WHERE idSB = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        preparedStatement.setInt(1, idSB);
        preparedStatement.execute();
        
        resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            sb = new SanBong();
            sb.setIdSB(resultSet.getInt(1));
            sb.setIdUser(resultSet.getInt(2));
            sb.setSlot(resultSet.getInt(3));
            sb.setAddress(resultSet.getString(4));
            sb.setOpenTime(resultSet.getTime(5));
            sb.setCloseTime(resultSet.getTime(6));
            sb.setState(resultSet.getBoolean(7));
            sb.setMap(resultSet.getString(8));
            sb.setDescription(resultSet.getString(9));
            sb.setImage(resultSet.getString(10));
            sb.setName(resultSet.getString(11));
            sb.setCost(resultSet.getInt(12));
        }
        
        preparedStatement.close();
        return sb;
    }
    
    public List<SanBong> getSanBongsByIdUser (int idUser) throws SQLException {
        List<SanBong> list = new ArrayList<SanBong>();
        String sql = "select * from SanBong where idUser = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.execute();
            
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
            	SanBong sb = new SanBong();
            	sb.setIdSB(rs.getInt(1));
                sb.setIdUser(rs.getInt(2));
            	sb.setSlot(rs.getInt(3));
            	sb.setAddress(rs.getString(4));
            	sb.setOpenTime(rs.getTime(5));
            	sb.setCloseTime(rs.getTime(6));
            	sb.setState(rs.getBoolean(7));
            	sb.setMap(rs.getString(8));
            	sb.setDescription(rs.getString(9));
            	sb.setImage(rs.getString(10));
            	sb.setName(rs.getString(11));
                sb.setCost(rs.getInt(12));
                
            	list.add(sb);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
