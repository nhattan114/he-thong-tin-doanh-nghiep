/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cd.cnpm.main.config;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Drake
 */
public class Function {
    
    public Function (){
        
    }
    
    public static String fileHash(MultipartFile file){
        String fileName = BCrypt.hashpw(file.getOriginalFilename(), BCrypt.gensalt(10)) + "_" + file.getOriginalFilename();
        return fileName.replace("/", "");
    }
    
    public static String formatOutputCost(String cost){
        String[] costs = cost.split(" -");
        costs[0] += ".000";
        costs[1] += ".000";
        
        return costs[0] + " -" + costs[1];
    }
}
