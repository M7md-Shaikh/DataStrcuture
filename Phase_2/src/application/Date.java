package application;


public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    private MarNode martyr;

    public Date() {
    	 this.year = year;
         this.month = month;
         this.day = day;
    	this.martyr = new MarNode();
    }
    // Constructor
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Getters and setters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    public MarNode getMartyr() {
		return martyr;
	}
	public void setMartyr(MarNode martyr) {
		this.martyr = martyr;
	}
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        } else if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        } else {
            return Integer.compare(this.day, other.day);
        }
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }
}
