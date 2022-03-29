public class Movie {

    private int id;
    private String name;
    private String genre;
    private String duration;
    private String shortDescription;
    private String movieDescription;
    private String picturePath;
    private boolean isAvailable;

    public Movie(){
        this.isAvailable = true;
    }

    public Movie(int id, String name, String genre, String duration,String shortDescription, String movieDescription, String picturePath, boolean available){
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.shortDescription = shortDescription;
        this.movieDescription = movieDescription;
        this.picturePath = picturePath;
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", duration='" + duration + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getShortDescription() {return this.shortDescription;}

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
