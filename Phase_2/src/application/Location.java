package application;

public class Location {
    
    private String locationName;
    private District district;
    private application.Date date;
    private DateNode rootDate;
    
   LocTree locTree= new LocTree();
    
    public Location() {
        this.date =new application.Date();
    }
    
    public Location(String locationName) {
        this.locationName = locationName;
        this.rootDate = new DateNode(); // Initialize dateTree
    }

	public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public DateNode getRootDate() {
        return rootDate;
    }

    public void setRootDate(DateNode rootDate) {
        this.rootDate = rootDate;
    }

    public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public application.Date getDate() {
		return date;
	}

	public void setDate(application.Date date) {
		this.date = date;
	}

	public LocTree getLocTree() {
		return locTree;
	}

	public void setLocTree(LocTree locTree) {
		this.locTree = locTree;
	}

	@Override
    public String toString() {
        return "Location [locationName=" + locationName + ", rootDate=" + rootDate + "]";
    }
}
