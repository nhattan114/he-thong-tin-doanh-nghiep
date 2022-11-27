package cd.cnpm.main.controller;

import cd.cnpm.main.dao.AccountDAO;
import cd.cnpm.main.dao.ChiTietSanBongDAO;
import cd.cnpm.main.dao.ChiTietUserDAO;
import cd.cnpm.main.dao.SanBongDAO;
import cd.cnpm.main.entity.Account;
import cd.cnpm.main.entity.ChiTietUser;
import cd.cnpm.main.entity.SanBong;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
        
@Controller
@RequestMapping("")
public class PageController {
        @GetMapping("/")
	public String home(Model model, HttpServletRequest request) {
            /* Render giao diện trang chủ của khách hàng */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            if(!isLogined(sessionAccount)){
                model.addAttribute("isLogin", false);
            }else{
                model.addAttribute("isLogin", true);
            }
            
            if(sessionAccount != null){
                int role = sessionAccount.getRole();
            
                if(role != 0){
                    return "redirect:/chusan";
                }
            }
            
            
            model.addAttribute("title", "Trang chủ");
            model.addAttribute("view", "home/home.jsp");
            return "layouts/home/template";
	}
        
        @GetMapping("/sanbong")
	public String chiTietSanBong(Model model, HttpServletRequest request, @RequestParam int idSB) throws SQLException, ClassNotFoundException {
            /* Render giao diện xem chi tiết sân bóng của khách hàng */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(!isLogined(sessionAccount)){
                model.addAttribute("isLogin", false);
            }else{
                model.addAttribute("isLogin", true);
            }
            
            if(sessionAccount != null){
                int role = sessionAccount.getRole();
            
                if(role != 0){
                    return "redirect:/chusan";
                }
            }
            
            model.addAttribute("title", "Trang chi tiết sân bóng");
            model.addAttribute("view", "home/san-bong-detail.jsp");
            
            // lấy thông tin sân bóng bằng query param idSB
            SanBongDAO sanbongDAO = new SanBongDAO();
            SanBong sanBong = sanbongDAO.getById(idSB);

            // lấy thông tin chủ sân bằng idSB để hiển thị
            ChiTietUserDAO chiTietUserDAO = new ChiTietUserDAO();
            ChiTietUser chuSan = chiTietUserDAO.getAccountByIdSB(idSB);            
            ChiTietSanBongDAO chiTietSanBongDAO = new ChiTietSanBongDAO();
            
            model.addAttribute("sanBong", sanBong);
            model.addAttribute("chuSan", chuSan);
            
            return "layouts/home/template";
	}
        
        @GetMapping("/don-dat-san")
	public String donDatSanView(Model model, HttpServletRequest request) throws SQLException, ClassNotFoundException {
            /* Render giao diện xem lịch sử đơn đặt sân của khách hàng */
            
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(!isLogined(sessionAccount)){
                return "redirect:/login";
            }
            
            if(sessionAccount != null){
                int role = sessionAccount.getRole();
            
                if(role != 0){
                    return "redirect:/chusan";
                }
            }
            
            model.addAttribute("isLogin", true);
            model.addAttribute("title", "Trang lịch sử đặt sân");
            model.addAttribute("view", "home/don-dat-san.jsp");
  
            return "layouts/home/template";
        }
           
            
	@GetMapping("/chusan")
	public String sanbong(Model model, HttpServletRequest request) {
            /* Render giao diện xem quản lý của chủ sân */
            
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(!isLogined(sessionAccount)){
                return "redirect:/login";
            }
            
            if(sessionAccount.getRole() == 0){
                return "redirect:/";
            }
            if(sessionAccount.getRole() == 2){
                return "redirect:/admin";
            }
            
            model.addAttribute("idUser", sessionAccount.getIdUser());
            model.addAttribute("role", sessionAccount.getRole());
            model.addAttribute("title", "Quản lý chủ sân");
            model.addAttribute("view", "chusan/home.jsp");
            return "layouts/template";
	}
        
        
        @GetMapping("/chusan/quan-ly-dat-san")
	public String quanLyDatSan(Model model, HttpServletRequest request) {
            /* Render giao diện xem quản lý đơn đặt sân của chủ sân */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(!isLogined(sessionAccount)){
                return "redirect:/login";
            }
            
            if(!isChuSan(sessionAccount)){
                return "redirect:/";
            }
            
            model.addAttribute("role", sessionAccount.getRole());
            model.addAttribute("idUser", sessionAccount.getIdUser());
            model.addAttribute("title", "Quản lý đơn đặt sân");
            model.addAttribute("view", "chusan/quan-ly-dat-san.jsp");
            return "layouts/template";
	}
        
        @GetMapping("/chusan/lich-su-don-dat-san")
	public String lichSuDonDatSanChuSan(Model model, HttpServletRequest request) throws SQLException, ClassNotFoundException {
            /* Render giao diện xem lịch sử đơn đặt sân của chủ sân */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(!isLogined(sessionAccount)){
                return "redirect:/login";
            }
            
            if(!isChuSan(sessionAccount)){
                return "redirect:/";
            }
            
            model.addAttribute("role", sessionAccount.getRole());
            model.addAttribute("title", "Lịch sử đơn đặt sân");
            model.addAttribute("view", "chusan/lich-su-don-dat-san.jsp");
            return "layouts/template";
	}
        
        @GetMapping("/chusan/sanbong")
	public String chitietSanBong(Model model, HttpServletRequest request) {
            /* Render giao diện xem chi tiết sân bóng của chủ sân */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(!isLogined(sessionAccount)){
                return "redirect:/login";
            }
            
            if(!isChuSan(sessionAccount)){
                return "redirect:/";
            }
            
            model.addAttribute("role", sessionAccount.getRole());
            model.addAttribute("title", "Trang chi tiết sân bóng");
            model.addAttribute("view", "chusan/pitch-detail.jsp");
            return "layouts/template";
	}
        
        // ADMIN
        @GetMapping("/admin")
	public String adminAccountManagement(Model model, HttpServletRequest request) {
            /* Render giao diện quản lý của admin */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(!isLogined(sessionAccount)){
                return "redirect:/login";
            }
            
            if(sessionAccount.getRole() != 2){
                return "redirect:/";
            }
            
            model.addAttribute("idUser", sessionAccount.getIdUser());
            model.addAttribute("role", sessionAccount.getRole());
            model.addAttribute("title", "Quản lý tài khoản chủ sân");
            model.addAttribute("view", "admin/index.jsp");
            return "layouts/template";
	}
        
        @GetMapping("/profile/{idUser}" )
	public String adminViewAccountsProfile(@PathVariable int idUser,HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException {
            /* Render giao diện xem thông tin chi tiết tài khoản chủ sân của admin */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(sessionAccount == null){
                return "redirect:/admin";
            }
            
            if(sessionAccount.getRole() != 2){
                return "redirect:/admin";
            }
            
            AccountDAO accountDAO = new AccountDAO();
            ChiTietUserDAO chiTietUserDAO = new ChiTietUserDAO();
            
            Account acc = accountDAO.getAccountById(idUser);
            ChiTietUser accDetail = chiTietUserDAO.getByIdUser(idUser);
            
            if(acc == null){
                return "redirect:/admin";
            }
            
            accDetail.setUserName(acc.getUserName());
            accDetail.setPassword(acc.getPassword());
            accDetail.setRole(acc.getRole());
            
            model.addAttribute("title", "Account detail");
            model.addAttribute("role", sessionAccount.getRole());
            model.addAttribute("view", "admin/profile.jsp");
            model.addAttribute("accDetail", accDetail);
            
            return "layouts/template";
	}
        
        @GetMapping("/profile")
	public String profileView(Model model, HttpServletRequest request) throws SQLException, ClassNotFoundException {
            /* Render giao diện xem thông tin cá nhân */
            Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
            if(!isLogined(sessionAccount)){
                return "redirect:/login";
            }
            
            if(sessionAccount.getRole() == 2){
                return "redirect:/admin";
            }
            
            ChiTietUserDAO chiTietUserDAO = new ChiTietUserDAO();
            ChiTietUser accDetail = chiTietUserDAO.getByIdUser(sessionAccount.getIdUser());
            
            model.addAttribute("isLogin", true); 
            model.addAttribute("accDetail", accDetail);
            model.addAttribute("role", sessionAccount.getRole());
            model.addAttribute("title", "Profile");
            model.addAttribute("view", "profile.jsp");
            
            if(sessionAccount.getRole() == 0 ){
                return "layouts/home/template";
            }
            return "layouts/template";
	}
        
        @GetMapping("/error")
	public String notFound() {
            return "notfound";
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
