package Classes;

public class Reservation {

    private int customerID;
    private int reservationID;
    private int salonID;
    private int movieID;
    private String row;
    private String seats;
    private String movieTime;
    private String movieDate;

    public Reservation(int customerID, int reservationID, int salonID, int movieID, String row, String seats, String movieTime, String movieDate) {
        this.customerID = customerID;
        this.reservationID = reservationID;
        this.salonID = salonID;
        this.movieID = movieID;
        this.row = row;
        this.seats = seats;
        this.movieTime = movieTime;
        this.movieDate = movieDate;
    }
    public Reservation(){

    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customerID=" + customerID +
                ", reservationID=" + reservationID +
                ", salonID=" + salonID +
                ", movieID=" + movieID +
                ", row='" + row + '\'' +
                ", seats='" + seats + '\'' +
                ", movieTime='" + movieTime + '\'' +
                ", movieDate='" + movieDate + '\'' +
                '}';
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getSalonID() {
        return salonID;
    }

    public void setSalonID(int salonID) {
        this.salonID = salonID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(String movieTime) {
        this.movieTime = movieTime;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }
}
