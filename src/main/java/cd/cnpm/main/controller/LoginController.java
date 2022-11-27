package cd.cnpm.main.controller;

import cd.cnpm.main.dao.AccountDAO;
import cd.cnpm.main.dao.ChiTietUserDAO;
import cd.cnpm.main.entity.Account;
import cd.cnpm.main.entity.ApiMessage;
import cd.cnpm.main.entity.ChiTietUser;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.crypto.bcrypt.BCrypt;
        
@Controller
@RequestMapping("")
public class LoginController {        
        @GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
            // Render login
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            // Nếu đã đăng nhập thì redirect về trang chủ
            if(isLogined(sessionAccount)){
                return "redirect:/chusan";
            }
             
            model.addAttribute("title", "Trang đăng nhập");
            model.addAttribute("view", "login/login.jsp");
            return "layouts/loginTemplate";
	}    
        
        @PostMapping("/login")
        @ResponseBody
	public ApiMessage login(@RequestBody Account acc, HttpServletRequest request) throws SQLException, ClassNotFoundException {
                @SuppressWarnings("unchecked")
		Account sessionAccount = (Account) request.getSession().getAttribute("account");
                
                if(sessionAccount != null){
                    return new ApiMessage(false, "Đã đăng nhập trước đó");
                }
                
                // Access to account DAO
                AccountDAO accountDAO = new AccountDAO();
                
                // empty validation
                if(acc.getUserName() == null || acc.getPassword() == null){
                    return new ApiMessage(false, "Thiếu thông tin đầu vào");
                }
                
                // check if account exist
                Account account = accountDAO.getAccountByUserName(acc.getUserName());
                if(account == null){
                    return new ApiMessage(false, "Sai thông tin đăng nhập!");
                }
                
                if(!BCrypt.checkpw(acc.getPassword(), account.getPassword())){
                    return new ApiMessage(false, "Sai thông tin đăng nhập!");
                }
                
                request.getSession().setAttribute("account", account);

                return new ApiMessage(true, "Đã đăng nhập thành công!");

	}
        
        @GetMapping("/logout")
	public String logout(HttpServletRequest request) throws SQLException {
             // Chức năng log out
            @SuppressWarnings("unchecked")
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount != null){
                // Hủy session
                request.getSession().invalidate();                
            }
            return "redirect:/";
	}
        
        @GetMapping("/register")
	public String register(Model model) {
            // Render giao diện register
            model.addAttribute("title", "Trang đăng ký tài khoản");
            model.addAttribute("view", "login/register.jsp");
            return "layouts/loginTemplate";
	}
        
        @PostMapping("/register")
        @ResponseBody
	public ApiMessage registerAccount(@RequestParam String userName, 
                                    @RequestParam String password,
                                    @RequestParam String confirmPassword,
                                    @RequestParam String phone,
                                    @RequestParam String firstName) throws SQLException, ClassNotFoundException {  
            /* Xử lý chức năng đăng ký tài khoản */
            
            // empty validation
            if(userName.isEmpty() || password.isEmpty() || 
                confirmPassword.isEmpty() || phone.isEmpty() || firstName.isEmpty()){
                
                return new ApiMessage(false, "Thiếu thông tin đầu vào!");
            }
            
            // password validation
            if(!password.equals(confirmPassword)){
                return new ApiMessage(false, "Mật khẩu xác nhận không trùng khớp!");
            }
            
            AccountDAO accountDAO = new AccountDAO();           
            ChiTietUserDAO chiTietUserDAO = new ChiTietUserDAO();
            
            // Kiểm tra xem có user trên hệ thống hay chưa
            if(accountDAO.isExistUsername(userName)){
                return new ApiMessage(false, "Đã có user này trong hệ thống!");
            }
            
            // Thêm tài khoản vào bảng Account, role = 0 mặc định là khách hàng
            password = BCrypt.hashpw(password, BCrypt.gensalt(10));
            
            accountDAO.addAccount(new Account(userName, password, 0));
            Account acc = accountDAO.getAccountByUserName(userName);
            
            // Thêm tài khoản vào bảng ChiTietUser, state = true mặc định là khách hàng
            chiTietUserDAO.addChiTietUser(new ChiTietUser(acc.getIdUser(), phone, firstName, true));
            return new ApiMessage(true, "Tạo tài khoản thành công!");
	}
        
        @GetMapping("/change-password")
	public String changePassword(Model model, HttpServletRequest request, HttpServletResponse response) {
            /* Render giao diện đổi mật khẩu */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
                
            if(sessionAccount == null){
                return "redirect:/login";
            }
            
            model.addAttribute("title", "Trang đổi mật khẩu");
            model.addAttribute("view", "login/change-password.jsp");
          
            return "layouts/loginTemplate";
	}  
        
        @PostMapping("/change-password")
        @ResponseBody
	public ResponseEntity<?> changePassword(@RequestParam String currentPassword, 
                                    @RequestParam String password,
                                    @RequestParam String confirmPassword,
                                    HttpServletRequest request) throws SQLException, ClassNotFoundException {  
            /* Xử lý chức năng đổi mật khẩu */
            
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
                
            if(sessionAccount == null){
                return ResponseEntity.ok(new ApiMessage(false, "Vui lòng đăng nhập để đổi mật khẩu"));
            }
                
            // empty validation
            if(currentPassword.isEmpty() || password.isEmpty() || 
                confirmPassword.isEmpty()){
                return ResponseEntity.ok(new ApiMessage(false, "Thiếu thông tin đầu vào!"));
            }
            
            // password validation
            if(!password.equals(confirmPassword)){
                return ResponseEntity.ok(new ApiMessage(false, "Mật khẩu mới và mật khẩu xác nhận không trùng khớp!"));
            }
            
            // password validation
            if(password.equals(currentPassword)){
                return ResponseEntity.ok(new ApiMessage(false, "Mật khẩu mới không được trùng mật khẩu cũ"));
            }
            
            
            AccountDAO accountDAO = new AccountDAO();           

            if(!BCrypt.checkpw(currentPassword, sessionAccount.getPassword())){
                return ResponseEntity.ok(new ApiMessage(false, "Mật khẩu hiện tại không đúng"));
            }      
            
            password = BCrypt.hashpw(password, BCrypt.gensalt(10));
            accountDAO.updatePasswordByIdUser(sessionAccount.getIdUser(), password);
            sessionAccount.setPassword(password);
            
            return ResponseEntity.ok(new ApiMessage(true, "Đã đổi mật khẩu thành công"));
	}
        
        public boolean isLogined(Account sessionAccount){
            // Kiểm tra xem có đăng nhập hay chưa
            return sessionAccount != null;      
        }
        
        public boolean isChuSan(Account sessionAccount){
           // Kiểm tra xem tài khoản có phải là chủ sân hay không
           return sessionAccount.getRole() == 1;
        }
        
        public boolean isKhachHang(Account sessionAccount){
            // Kiểm tra xem tài khoản có phải là khác hàng hay không
            return sessionAccount.getRole() == 0;
        }
}
