package Classes;

public class MovieScheduleView {

    int salon;
    String time;
    String date;
    String movie;

    public MovieScheduleView(int salon, String time, String date, String movie){
        this.salon = salon;
        this.time = time;
        this.date = date;
        this.movie = movie;
    }

    public MovieScheduleView(){}

    public int getSalon() {
        return salon;
    }

    public void setSalon(int salon) {
        this.salon = salon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}

