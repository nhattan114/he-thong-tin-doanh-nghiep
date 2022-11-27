package cd.cnpm.main.entity;

import java.sql.Time;
import java.util.Map;
import java.util.Objects;

public class SanBong {
	
	private int idSB;
	private int idUser;
	private int slot;
	private String address;
	private Time openTime;
	private Time closeTime; 
	private boolean state;
	private String map;
	private String description;
	private String image;
	private String name;
	private int cost;
        
	public SanBong() {
		super();
	}

	public SanBong(int idSB, int idUser, int slot, String address, Time openTime, Time closeTime, boolean state,
			String map, String discribtion, String image, String name) {
		super();
		this.idSB = idSB;
		this.idUser = idUser;
		this.slot = slot;
		this.address = address;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.state = state;
		this.map = map;
		this.description = description;
		this.image = image;
		this.name = name;
	}

	public int getIdSB() {
		return idSB;
	}

	public void setIdSB(int idSB) {
		this.idSB = idSB;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Time getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Time openTime) {
		this.openTime = openTime;
	}

	public Time getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }
        
        
	@Override
	public String toString() {
		return "SanBong [idSB=" + idSB + ", idUser=" + idUser + ", slot=" + slot + ", address=" + address
				+ ", openTime=" + openTime + ", closeTime=" + closeTime + ", state=" + state + ", map=" + map
				+ ", discribtion=" + description + ", image=" + image + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, closeTime, description, idSB, idUser, image, map, name, openTime, slot, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanBong other = (SanBong) obj;
		return Objects.equals(address, other.address) && Objects.equals(closeTime, other.closeTime)
				&& Objects.equals(description, other.description) && idSB == other.idSB && idUser == other.idUser
				&& Objects.equals(image, other.image) && Objects.equals(map, other.map)
				&& Objects.equals(name, other.name) && Objects.equals(openTime, other.openTime) && slot == other.slot
				&& state == other.state;
	}
	
}
