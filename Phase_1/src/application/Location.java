package application;

public class Location {
	
	private String locationName ;
	private Martyr martyr;
	private District district;
	private NodeSS firstMar;

	public Location () {
		this.locationName = locationName;
		this.martyr = new Martyr();
	}
	
	public Location (String locationName) {
		this.locationName = locationName;
		this.martyr=martyr;
	}

	public District getDistrict() {
		return district;
	}


	public void setDistrict(District district) {
		this.district = district;
	}


	public Location(String locationName, Martyr martyr) {
		this.locationName = locationName;
		this.martyr = martyr;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Martyr getMartyr() {
		return martyr;
	}

	public void setMartyr(Martyr martyr) {
		this.martyr = martyr;
	}

	public NodeSS getFirstMar() {
		return firstMar;
	}


	public void setFirstMar(NodeSS firstMar) {
		this.firstMar = firstMar;
	}

	@Override
	public String toString() {
		return "Location [locationName=" + locationName + ", martyr=" + martyr + "]";
	}
	
}