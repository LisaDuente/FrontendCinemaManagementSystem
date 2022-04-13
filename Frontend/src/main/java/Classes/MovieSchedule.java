package Classes;

import com.google.gson.Gson;

public class MovieSchedule {

    private String salonId;
    private String movieTime;
    private String movieDate;
    private int movieId;
    private int [][]seatOfArrayForMovie;
    private boolean isAvailable;


    public MovieSchedule() { this.isAvailable = true; }

    public MovieSchedule(String salonId, String movieTime, String movieDate, int movieId, String array, boolean isAvailable) {
        Gson gson = new Gson();
        this.salonId = salonId;
        this.movieTime = movieTime;
        this.movieDate = movieDate;
        this.movieId = movieId;
        this.seatOfArrayForMovie = gson.fromJson(array, int[][].class);
        this.isAvailable = isAvailable;
    }

    public MovieSchedule(String salonId, String movieTime, String movieDate, int movieId, int[][] array, boolean isAvailable) {
        Gson gson = new Gson();
        this.salonId = salonId;
        this.movieTime = movieTime;
        this.movieDate = movieDate;
        this.movieId = movieId;
        this.seatOfArrayForMovie = array;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Classes.MovieSchedule{" +
                "salonId='" + salonId + '\'' +
                ", movieTime='" + movieTime + '\'' +
                ", movieDate='" + movieDate + '\'' +
                ", movieId=" + movieId + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public String getSalonId() {
        return salonId;
    }

    public void setSalonId(String salonId) {
        this.salonId = salonId;
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

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable() {
        this.isAvailable = isAvailable;
    }

    public void setSeatOfArrayForMovie(int[][] seatOfArrayForMovie) {
        this.seatOfArrayForMovie = seatOfArrayForMovie;
    }

    public int[][] getSeatOfArrayForMovie() {
        return seatOfArrayForMovie;
    }


}

