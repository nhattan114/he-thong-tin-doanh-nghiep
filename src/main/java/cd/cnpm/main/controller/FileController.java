package cd.cnpm.main.controller;

import cd.cnpm.main.config.Function;

import cd.cnpm.main.dao.SanBongDAO;

import cd.cnpm.main.entity.Account;
import cd.cnpm.main.entity.ApiMessage;

import cd.cnpm.main.service.FileStorageService;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class FileController {
    @Autowired
    FileStorageService storageService;
    
    @PostMapping("/sanbong" )
    public ResponseEntity<?> uploadSanBongImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws SQLException, ClassNotFoundException {        
        Account sessionAccount = (Account) request.getSession().getAttribute("account");
            
        if(sessionAccount == null){
            return ResponseEntity.ok(new ApiMessage(false, "Phải đăng nhập để sử dụng chức năng upload ảnh"));
        }
        
        if(sessionAccount.getRole() != 1){
            return ResponseEntity.ok(new ApiMessage(false, "Phải là chủ sân thì mới dùng được api này"));
        }
        
        SanBongDAO sanbongDAO = new SanBongDAO();           
        
        String message = "";
        try {
          String fileName = Function.fileHash(file);
          storageService.save(file, fileName);
          
          sanbongDAO.updateImageByIdSB(sanbongDAO.getLastestIdSB(), "images/chusan/uploads/" + fileName);
          message = "Uploaded the file successfully: " + fileName;
          return ResponseEntity.ok(new ApiMessage(true, message));
        } catch (Exception e) {
          message = "Could not upload the file: " + file.getOriginalFilename() + "!";
          return ResponseEntity.ok(new ApiMessage(false, message));
        }
    }
}
