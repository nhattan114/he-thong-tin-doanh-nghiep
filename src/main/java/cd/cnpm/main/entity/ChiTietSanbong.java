package cd.cnpm.main.entity;

import java.util.Objects;

public class ChiTietSanbong{
	private int idSB;
	private int idSlot;
        
        public ChiTietSanbong(){
            this.idSB = 0;
            this.idSlot = 0;
        }
    
        
        public ChiTietSanbong(int idSB,int idSlot){
            this.idSB = idSB;
            this.idSlot = idSlot;
        }
        
        public int getIdSB() {
            return idSB;
        }

        public void setIdSB(int idSB) {
            this.idSB = idSB;
        }
        
	public int getIdSlot() {
		return idSlot;
	}
	
	public void setIdSlot(int idSlot) {
		this.idSlot = idSlot;
	}

        @Override
        public String toString() {
            return "ChiTietSanbong{" + "idSB=" + idSB + ", idSlot=" + idSlot + '}';
        }
}
