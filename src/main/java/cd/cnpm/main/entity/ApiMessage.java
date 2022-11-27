/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cd.cnpm.main.entity;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Drake
 */
public class ApiMessage <E>{
    private boolean state;
    private String message;

    public ApiMessage(boolean state, String message) {
        this.state = state;
        this.message = message;
    }
    
    public ApiMessage() {
        this.state = false;
        this.message = null;
    }
   
   
    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
