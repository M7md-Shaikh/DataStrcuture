package application;

public class District {

    private String districtName;
    private Location location;
    private LocNode rootLoc;

    public District() {
        this.location = new Location();
    }

    public District(String dis) {
        this.districtName = dis;
        this.location = location; // Initialize location field
    }

    public District(String dis, Location location) {
        this.districtName = dis;
        this.location = location;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocNode getRootLoc() {
        return rootLoc;
    }

    public void setRootLoc(LocNode rootLoc) {
        this.rootLoc = rootLoc;
    }

    @Override
    public String toString() {
        return "District [districtName=" + districtName + ", location=" + location + "]";
    }
}
