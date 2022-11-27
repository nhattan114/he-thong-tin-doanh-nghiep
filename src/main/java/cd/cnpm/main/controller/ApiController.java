package cd.cnpm.main.controller;

import cd.cnpm.main.dao.AccountDAO;
import cd.cnpm.main.dao.ChiTietSanBongDAO;
import cd.cnpm.main.dao.ChiTietUserDAO;
import cd.cnpm.main.dao.DonDatSanDAO;
import cd.cnpm.main.dao.SanBongDAO;
import cd.cnpm.main.dao.SanBongScheduleDAO;
import cd.cnpm.main.entity.Account;
import cd.cnpm.main.entity.ApiMessage;
import cd.cnpm.main.entity.ChiTietUser;
import cd.cnpm.main.entity.DonDatSan;
import cd.cnpm.main.entity.SanBong;
import cd.cnpm.main.entity.SanBongSchedule;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
        @GetMapping("/sanbong")
	public ResponseEntity<?> getSanBongList() throws SQLException, ClassNotFoundException {    
            /* API GET /sanbong trả về list tất cả sân bóng */
            SanBongDAO sanbongDAO = new SanBongDAO();
            List<SanBong> listS = sanbongDAO.getSanBongs();       
            return ResponseEntity.ok(listS);
	}
        @PostMapping("/sanbong")
	public ResponseEntity<?> addSanBong(@RequestBody SanBong sb, HttpServletRequest request) throws SQLException, ClassNotFoundException { 
            /* API POST /sanbong cho chủ sân thêm sân bóng mới vào Database */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để lấy thông tin đơn đặt sân"));
            }
            
            if(sessionAccount.getRole() != 1){
                return ResponseEntity.ok(new ApiMessage(false, "Không phải chủ sân nên không thể sử dụng api này"));
            }
            
            sb.setIdUser(sessionAccount.getIdUser());
            SanBongDAO sanbongDAO = new SanBongDAO();           
            
            boolean result = sanbongDAO.addSanBong(sb);
            String message = result ? "Add san bong successfully!": "Add san bong unsuccessfully!";
            
            ApiMessage apiMessage = new ApiMessage(result, message);

            return ResponseEntity.ok(apiMessage);
	}
        
        @PostMapping("/sanbong/keyword")
        /* API POST /sanbong/keyword cho khách hàng tìm kiếm sân bóng từ Database */
	public ResponseEntity<?> getSanBongListByKeyword(@RequestParam(value="keyword") String keyword) throws SQLException, ClassNotFoundException {        
            SanBongDAO sanbongDAO = new SanBongDAO();
            List<SanBong> listS = sanbongDAO.getSanBongListByKeyword(keyword);       
            return ResponseEntity.ok(listS);
	}
        
       @GetMapping("/sanbong/chusan")
        public ResponseEntity<?> getSanBongListByIdChuSan(HttpServletRequest request) throws SQLException, ClassNotFoundException {   
            /* API GET /sanbong/chusan trả về một list sân bóng sở hữu bởi chủ sân */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để lấy thông tin đơn đặt sân"));
            }
            
            if(sessionAccount.getRole() != 1){
                return ResponseEntity.ok(new ApiMessage(false, "Không phải chủ sân nên không thể sử dụng api này"));
            }
            int idUser = sessionAccount.getIdUser();
            
            SanBongDAO sanbongDAO = new SanBongDAO();
            List<SanBong> listS = sanbongDAO.getSanBongListByIdUser(idUser);       
            return ResponseEntity.ok(listS);
        }
        
        @GetMapping("/sanbong/{id}")
	public ResponseEntity<?> getSanBongById(@PathVariable("id") int id) throws SQLException, ClassNotFoundException {        
            /* API GET /sanbong/{id} trả về một SanBong tìm kiếm bởi id của sân bóng */
            SanBongDAO sanbongDAO = new SanBongDAO();
            
            SanBong sanbong = sanbongDAO.getById(id);       
            return ResponseEntity.ok(sanbong);
	}
        
        @PutMapping("/sanbong/{id}")
	public ResponseEntity<?> editSanBongById(@PathVariable("id") int id, @RequestBody SanBong sb, HttpServletRequest request) throws SQLException, ClassNotFoundException {
            /* API PUT /sanbong/{id} cho phép chủ sân chỉnh sửa thông tin chi tiết của sân */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để lấy thông tin đơn đặt sân"));
            }
            
            if(sessionAccount.getRole() != 1){
                return ResponseEntity.ok(new ApiMessage(false, "Không phải chủ sân nên không thể sử dụng api này"));
            }
            
            int currentIdUser = sessionAccount.getIdUser();
            
            sb.setIdUser(sessionAccount.getIdUser());
            SanBongDAO sanbongDAO = new SanBongDAO();
            
            if(sanbongDAO.getIdUserByIdSB(sb.getIdSB()) != currentIdUser){
                return ResponseEntity.ok(new ApiMessage(false, "Không thể sửa thông tin sân mà mình không sỡ hữu"));
            }
            
            boolean result = sanbongDAO.updateSanBong(sb, id);
            String message = result ? "Updated successfully!": "Updated unsuccessfully!";
            
            ApiMessage apiMessage = new ApiMessage(result, message);

            return ResponseEntity.ok(apiMessage);
	}
        
        @DeleteMapping("/sanbong/{id}")
	public ResponseEntity<?> deleteSanBongById(@PathVariable("id") int id, HttpServletRequest request) throws SQLException, ClassNotFoundException { 
            /* API DELETE /sanbong/{id} cho phép chủ sân xóa một sân bóng của sân */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để lấy thông tin đơn đặt sân"));
            }
            
            if(sessionAccount.getRole() != 1){
                return ResponseEntity.ok(new ApiMessage(false, "Không phải chủ sân nên không thể sử dụng api này"));
            }
            
            int currentIdUser = sessionAccount.getIdUser();
            SanBongDAO sanbongDAO = new SanBongDAO();
            
            if(sanbongDAO.getIdUserByIdSB(id) != currentIdUser){
                return ResponseEntity.ok(new ApiMessage(false, "Không thể xóa sân mà mình không sỡ hữu"));
            }
            
            boolean result = sanbongDAO.deleteSanBong(id);
            String message = result ? "Deleted successfully!": "Deleted unsuccessfully!";
            
            ApiMessage apiMessage = new ApiMessage(result, message);
            return ResponseEntity.ok(apiMessage);
	}     
        
        @GetMapping("/sanbong/schedule/{idSB}/{idSlot}")
	public ResponseEntity<?> getSanBongSchedulesByIdSlot(@PathVariable("idSB") int idSB, @PathVariable("idSlot") int idSlot) throws SQLException, ClassNotFoundException {        
            /* API GET /sanbong/schedule/{idSB}/{idSlot} trả về list các khung giờ của một sân bóng nhỏ */
            SanBongScheduleDAO scheduleDAO = new SanBongScheduleDAO();
            
            List<SanBongSchedule> schedules = scheduleDAO.getSanBongSchedulesByIdSlot(idSB, idSlot);       
            return ResponseEntity.ok(schedules);
	}
        
        @PostMapping({ "/sanbong/schedule" })
	public ResponseEntity<?> datSanBongByIdSlot(
                @RequestBody ArrayList<Integer> idSchedules, HttpServletRequest request) 
                throws SQLException, ClassNotFoundException {  
            
             /* API POST /sanbong/schedule xử lý logic đơn đặt sân của khách hàng */
            // Nhận vào một list idSchedules rồi lưu vào don dat san
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để sử dụng dịch vụ đặt sân!"));
            }
            
            if(idSchedules.isEmpty())
                return ResponseEntity.ok(new ApiMessage(false, "Vui lòng chọn sân và khung giờ"));
            // Thêm idSchedules đơn đặt sân
            DonDatSanDAO donDatSanDAO = new DonDatSanDAO();
            SanBongScheduleDAO sanBongScheduleDAO = new SanBongScheduleDAO();
            
            for(int i = 0; i < idSchedules.size(); i++){
                // Kiểm tra có khung giờ nào đã dc đặt trc đó hay không
                int idSchedule = idSchedules.get(i);
                if(!sanBongScheduleDAO.isAvailable(idSchedule)){
                    return ResponseEntity.ok(new ApiMessage(false, "Một hoặc nhiều khung giờ đã được đặt!"));
                }
            }
            
            for(int i = 0; i < idSchedules.size(); i++){
                // Không có khung giờ nào đc đặt thì cho user đặt
                int idSchedule = idSchedules.get(i);
                DonDatSan donDatSan = new DonDatSan(sessionAccount.getIdUser(), idSchedule);
                donDatSanDAO.addDonDatSan(donDatSan); 
            }
            
            return ResponseEntity.ok(new ApiMessage(true, "Đã đặt sân thành công!"));
	}
        
        @GetMapping("/don-dat-san")
	public ResponseEntity getLichSuDatSanByIdUser(HttpServletRequest request) throws SQLException, ClassNotFoundException {  
             /* API GET /don-dat-san lấy lịch sử đặt sân của khách hàng rồi trả về một list
            bao gồm thông tin cần thiết dể show lên giao diện ngời dùng*/
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để lấy thông tin đơn đặt sân"));
            }
            
            ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<>();
            
            
            SanBongDAO sanBongDAO = new SanBongDAO();
            DonDatSanDAO donDatSanDAO = new DonDatSanDAO();
            SanBongScheduleDAO sanBongScheduleDAO = new SanBongScheduleDAO();
            
            List<DonDatSan> donDatSanList = donDatSanDAO.getDonDatSansByIdUser(sessionAccount.getIdUser());
            
            for(int i = 0; i < donDatSanList.size(); i++){
                map = new HashMap<>();
          
                DonDatSan donDatSan = donDatSanList.get(i);
                int idSchedule = donDatSan.getIdSchedule();
                
                SanBongSchedule schedule = sanBongScheduleDAO.getById(idSchedule);
                int idSB = schedule.getIdSB();
                SanBong sb = sanBongDAO.getById(idSB);
                
                map.put("idSB", idSB);
                map.put("idSchedule", donDatSanList.get(i).getIdSchedule());
                map.put("name", sb.getName());
                map.put("state", donDatSan.getState());
                map.put("address", sb.getAddress());
                map.put("batDau", schedule.getBatDau());
                map.put("ketThuc", schedule.getKetThuc());
                map.put("createdAt", donDatSan.getCreatedAt());
                map.put("cost", sb.getCost());
                
                list.add(map);
            }
           
            return  ResponseEntity.ok(list);
        }
        
        @GetMapping("/chusan/don-dat-san" )
	public ResponseEntity<?> getDonDatSanByIdChuSan(HttpServletRequest request) throws SQLException, ClassNotFoundException {
            /* API GET /chusan/don-dat-san lấy tất cả các đơn đặt sân của tất cả khách hàng
            đặt sân của chủ sân đó rồi trả về list ó
            bao gồm thông tin cần thiết dể show lên giao diện ngời dùng*/
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để lấy thông tin đơn đặt sân"));
            }
            
            if(sessionAccount.getRole() != 1){
                return ResponseEntity.ok(new ApiMessage(false, "Không phải chủ sân nên không thể sử dụng api này"));
            }
            
            ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<>();

            ChiTietUserDAO chiTietUserDAO = new ChiTietUserDAO();
            SanBongDAO sanBongDAO = new SanBongDAO();
            DonDatSanDAO donDatSanDAO = new DonDatSanDAO();
            SanBongScheduleDAO sanBongScheduleDAO = new SanBongScheduleDAO();
            
            List<DonDatSan> donDatSanList = donDatSanDAO.getDonDatSansByIdChuSan(sessionAccount.getIdUser());
            for(int i = 0; i < donDatSanList.size(); i++){
                map = new HashMap<>();
          
                DonDatSan donDatSan = donDatSanList.get(i);
                int idSchedule = donDatSan.getIdSchedule();
                
                int idUser = donDatSan.getIdUser();
                ChiTietUser userDetail = chiTietUserDAO.getByIdUser(idUser);

                SanBongSchedule schedule = sanBongScheduleDAO.getById(idSchedule);
                
                int idSB = schedule.getIdSB();
                SanBong sb = sanBongDAO.getById(idSB);
                
                map.put("idDon", donDatSan.getIdDon());
                map.put("pitchName", sb.getName());
                map.put("idSlot", schedule.getIdSlot());
                map.put("name", userDetail.getFirstName());
                map.put("sdt", userDetail.getSdt());
                map.put("batDau", schedule.getBatDau());
                map.put("ketThuc", schedule.getKetThuc());
                map.put("createdAt", donDatSan.getCreatedAt());
                
                list.add(map);
            }
            
            return ResponseEntity.ok(list);
	}
        
        @GetMapping("/chusan/lich-su-don-dat-san" )
	public ResponseEntity<?> getLichSuDonDatSanByIdChuSan(HttpServletRequest request) throws SQLException, ClassNotFoundException {
             /* API GET /don-dat-san lấy lịch sử đặt sân của khách hàng rồi trả về một list
            bao gồm thông tin cần thiết dể show lên giao diện ngời dùng*/
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để lấy thông tin đơn đặt sân"));
            }
            
            if(sessionAccount.getRole() != 1){
                return ResponseEntity.ok(new ApiMessage(false, "Không phải chủ sân nên không thể sử dụng api này"));
            }
            
            ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<>();

            ChiTietUserDAO chiTietUserDAO = new ChiTietUserDAO();
            SanBongDAO sanBongDAO = new SanBongDAO();
            DonDatSanDAO donDatSanDAO = new DonDatSanDAO();
            SanBongScheduleDAO sanBongScheduleDAO = new SanBongScheduleDAO();
            
            int currentIdUser = sessionAccount.getIdUser();
            
            List<DonDatSan> donDatSanList = new ArrayList<DonDatSan>();
            List<Integer> idSBList = (ArrayList<Integer>)sanBongDAO.getAllIdSBByIdUser(currentIdUser);
            
            for(int i = 0; i< idSBList.size(); i ++){
                int idSB = idSBList.get(i);
                donDatSanList.addAll(donDatSanDAO.getAllByIdSB(idSB));
            }
            
            for(int i = 0; i < donDatSanList.size(); i++){
                map = new HashMap<>();
          
                DonDatSan donDatSan = donDatSanList.get(i);
                int idSchedule = donDatSan.getIdSchedule();
                
                int idUser = donDatSan.getIdUser();
                ChiTietUser userDetail = chiTietUserDAO.getByIdUser(idUser);

                SanBongSchedule schedule = sanBongScheduleDAO.getById(idSchedule);
                
                int idSB = schedule.getIdSB();
                SanBong sb = sanBongDAO.getById(idSB);
                
                map.put("idDon", donDatSan.getIdDon());
                map.put("pitchName", sb.getName());
                map.put("idSlot", schedule.getIdSlot());
                map.put("name", userDetail.getFirstName());
                map.put("sdt", userDetail.getSdt());
                map.put("batDau", schedule.getBatDau());
                map.put("ketThuc", schedule.getKetThuc());
                map.put("state", donDatSan.getState());
                map.put("createdAt", donDatSan.getCreatedAt());
                
                list.add(map);
            }
            
            return ResponseEntity.ok(list);
	}
        
        @DeleteMapping("/chusan/don-dat-san/{idDon}" )
	public ResponseEntity<?> disApproveDonDatSanByIdSon(@PathVariable int idDon, HttpServletRequest request) throws SQLException, ClassNotFoundException {
            /* API DELETE /chusan/don-dat-san/{idDon} từ chối một đơn của khách hàng*/
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để lấy thông tin đơn đặt sân"));
            }
            
            if(sessionAccount.getRole() != 1){
                return ResponseEntity.ok(new ApiMessage(false, "Không phải chủ sân nên không thể sử dụng api này"));
            }
            
            DonDatSanDAO donDatSanDAO = new DonDatSanDAO();
            boolean result = donDatSanDAO.disapproveDonDatSanByIdDon(idDon);
            
            if(!result)
                return ResponseEntity.ok(new ApiMessage(false, "Đơn đã được từ chối trước đó!"));
            
            // từ chối sân thì set schedule lại thành available
            SanBongScheduleDAO scheduleDAO = new SanBongScheduleDAO();
            int idSchedule = donDatSanDAO.getIdScheduleByIdDon(idDon);
            scheduleDAO.updateAvailable(idSchedule, true);
            
            return ResponseEntity.ok(new ApiMessage(true, "Đã từ chối đơn đặt sân thành công"));
	}
        
        @PostMapping("/chusan/don-dat-san/{idDon}" )
	public ResponseEntity<?> approveDonDatSanByIdSon(@PathVariable int idDon, HttpServletRequest request) throws SQLException, ClassNotFoundException {
            /* API POST /chusan/don-dat-san/{idDon} duyệt một đơn của khách hàng*/
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để lấy thông tin đơn đặt sân"));
            }
            
            if(sessionAccount.getRole() != 1){
                return ResponseEntity.ok(new ApiMessage(false, "Không phải chủ sân nên không thể sử dụng api này"));
            }
            
            DonDatSanDAO donDatSanDAO = new DonDatSanDAO();
            boolean result = donDatSanDAO.approveDonDatSanByIdDon(idDon);
            if(!result)
                return ResponseEntity.ok(new ApiMessage(false, "Đơn đặt sân đã được xác nhận trước đó!"));
            return ResponseEntity.ok(new ApiMessage(true, "Đã xác nhận đơn đặt sân thành công"));
	}
        
        @GetMapping("/admin/accounts" )
	public ResponseEntity<?> adminGetChuSanAccounts(HttpServletRequest request) throws SQLException, ClassNotFoundException {
            /* API GET /admin/accounts trả về tất cả tài khoản của chủ sân*/
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để sử dụng api"));
            }
            
            if(sessionAccount.getRole() != 2){
                return ResponseEntity.ok(new ApiMessage(false, "Không phải admin nên không thể sử dụng api này"));
            }
            
            AccountDAO accountDAO = new AccountDAO();
            ChiTietUserDAO chiTietUserDAO = new ChiTietUserDAO();
            
            List<Account> chuSanAccounts = (ArrayList<Account>) accountDAO.getAllChuSanAccount();
            List<ChiTietUser> chuSanAccountsDetail = new ArrayList<ChiTietUser>();
            
            for(int i = 0; i < chuSanAccounts.size(); i ++){
                // Lấy chi tiết user từ tất cả account của chủ sân
                Account currAcc = chuSanAccounts.get(i);
                ChiTietUser accDetail = chiTietUserDAO.getByIdUser(currAcc.getIdUser());
                
                accDetail.setUserName(currAcc.getUserName());
                accDetail.setPassword(currAcc.getPassword());
                accDetail.setRole(currAcc.getRole());
                
                chuSanAccountsDetail.add(accDetail);
            }
            return ResponseEntity.ok(chuSanAccountsDetail);
	}
        
        @PostMapping("/admin/accounts" )
	public ResponseEntity<?> addChuSanAccounts(@RequestBody ChiTietUser accDetail, HttpServletRequest request) throws SQLException, ClassNotFoundException {
            /* API POST /admin/accounts cho phép admin add account mới vào Database*/
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để sử dụng api"));
            }
            
            if(sessionAccount.getRole() != 2){
                return ResponseEntity.ok(new ApiMessage(false, "Không phải admin nên không thể sử dụng api này"));
            }
            
            AccountDAO accountDAO = new AccountDAO();
            ChiTietUserDAO chiTietUserDAO = new ChiTietUserDAO();
            
            
            if(accountDAO.isExistUsername(accDetail.getUserName())){
                return ResponseEntity.ok(new ApiMessage(false, "Đã có username này"));
            }
            if(chiTietUserDAO.isExistCMND(accDetail.getCmnd())){
                return ResponseEntity.ok(new ApiMessage(false, "Đã có CMND này"));
            }
            if(chiTietUserDAO.isExistSDT(accDetail.getSdt())){
                return ResponseEntity.ok(new ApiMessage(false, "Đã có SĐT này"));
            }
            
            String defaultPassword = "123456";
            Account acc = new Account();
            
            acc.setPassword(BCrypt.hashpw(defaultPassword, BCrypt.gensalt(10)));
            acc.setUserName(accDetail.getUserName());
            acc.setRole(1);
            accDetail.setState(false);
            
            boolean result = accountDAO.addAccount(acc);
            int lastestId = accountDAO.getLastestIdUser();
            accDetail.setIdUser(lastestId);
            
            result = result && chiTietUserDAO.addChiTietUser(accDetail);
            
            if(result)
                return ResponseEntity.ok(new ApiMessage(true, "Tài khoản chủ sân đã tạo thành công"));
            return ResponseEntity.ok(new ApiMessage(false, "Tài khoản chủ sân tạo thất bại"));
	}
        
        @DeleteMapping("/admin/accounts/{idUser}" )
	public ResponseEntity<?> deleteChuSanAccountById(@PathVariable int idUser, HttpServletRequest request) throws SQLException, ClassNotFoundException {
            /* API DELETE /admin/accounts/{idUser} cho phép admin delete account khỏi Database*/
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để sử dụng api này"));
            }
            
            if(sessionAccount.getRole() != 2){
                return ResponseEntity.ok(new ApiMessage(false, "Không phải admin nên không thể sử dụng api này"));
            }
            
            SanBongDAO sanBongDAO = new SanBongDAO();
            List<Integer> listIdSBs = (ArrayList<Integer>)sanBongDAO.getAllIdSBByIdUser(idUser);
            
            for(int i = 0; i < listIdSBs.size(); i++){
                int currIdSB = listIdSBs.get(i);
                sanBongDAO.deleteSanBong(currIdSB);
            }
            
            ChiTietUserDAO chiTietUserDAO = new ChiTietUserDAO();
            chiTietUserDAO.deleteByIdUser(idUser);
            
            AccountDAO accountDAO = new AccountDAO();
            accountDAO.deleteByIdUser(idUser);
           
            return ResponseEntity.ok(new ApiMessage(true, "Đã xóa tài khoản thành công"));
	}
        
}
