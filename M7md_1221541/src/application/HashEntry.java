package application;

public class HashEntry {	
	private String key;
	private String value;
	private byte status; // insert: 1, delete: 2, empty: 0
	HashEntry(String key, String value, byte status)	{
		
		this.key = key;
		this.value = value;
		this.status = status;
	}
	
	public String getKey()	{
	 return key;
	}
	
	public String getValue(){
		return value;
	}
	
	public byte getStatus(){
		return status;
	}
	
	public void setDeleteStatus(){
		status = 2;
	}
}
