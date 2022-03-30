public class Salon { // Toros
    private String salonID; // varchar(20) PK
    private int cinemaID; // int UN

    // TODO: make a smart function to compute nr of seats, salon rows, salon seats.
    private int salonRows;
    private String salonSeats;

    public Salon(){

    }

    public Salon(String salonID, int cinemaID, int salonRows, String salonSeats) {
        this.salonID = salonID;
        this.cinemaID = cinemaID;
        this.salonRows = salonRows;
        this.salonSeats = salonSeats;
    }

    @Override
    public String toString() {
        return "Salon{" +
                "salonID='" + salonID + '\'' +
                ", cinemaID=" + cinemaID +
                ", salonRows=" + salonRows +
                ", salonSeats='" + salonSeats + '\'' +
                '}';
    }

    public String getSalonID() {
        return salonID;
    }

    public void setSalonID(String salonID) {
        this.salonID = salonID;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public int getSalonRows() {
        return salonRows;
    }

    public void setSalonRows(int salonRows) {
        this.salonRows = salonRows;
    }

    public String getSalonSeats() {
        return salonSeats;
    }

    public void setSalonSeats(String salonSeats) {
        this.salonSeats = salonSeats;
    }
}
