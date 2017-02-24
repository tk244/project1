package model;

public class characteristicMODEL {

	private String characteristic_id;				// “Á’¥ID
	private String characteristic_name;				// “Á’¥
	private String characteristic_checked;
	
	
	public String getCharacteristic_id() {return characteristic_id;}
	public String getCharacteristic_name() {return characteristic_name;}
	public String getCharacteristic_checked() {return characteristic_checked;}

	
	public characteristicMODEL(String characteristic_id, String characteristic_name, String characteristic_checked){
		this.characteristic_id = characteristic_id;
		this.characteristic_name = characteristic_name;
		this.characteristic_checked = characteristic_checked;
	}
}
