import Classes.Movie;
import Classes.MovieSchedule;
import Classes.Salon;
import Functionality.ConnectionManager;
import Panels.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    startPagePanel startPage;
    FilmListScrollPane movieList;
    MovieDetailsPanel movieDetails;
    BackPanel backPanel;
    ReceiptPanel receipt;
    ConnectionManager connect;
    AdminPage admin;
    AdminPageAddFilm movie;
    AdminPageAddEmployee employee;
    AdminPageMovieSchedule movieSchedule;
    AdminPageEmployeeSchedule employeeSchedule;
    MovieBookingPage movieBookning;
    Gson gson;
    Movie newestMovie;

    public MainFrame() {
        //DEFINE FRAME
        this.setSize(1000, 800);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //INITIALIZE
        this.connect = new ConnectionManager();
        this.gson = new Gson();
        this.startPage = new startPagePanel();
        this.movieDetails = new MovieDetailsPanel();
        this.backPanel = new BackPanel();
        this.movieList = new FilmListScrollPane(this.movieDetails, this.backPanel);
        this.receipt = new ReceiptPanel();
        this.admin = new AdminPage();
        this.movie = new AdminPageAddFilm();
        this.employee = new AdminPageAddEmployee();
        this.movieSchedule = new AdminPageMovieSchedule();
        this.employeeSchedule = new AdminPageEmployeeSchedule();
        this.movieBookning = new MovieBookingPage();

        //INITIALIZE MOVIES
       refresh();

        //ADD TO FRAME
        this.add(this.movieList, BorderLayout.CENTER);
        this.add(this.backPanel, BorderLayout.SOUTH);
        this.add(this.movieDetails, BorderLayout.CENTER);
        this.add(this.receipt,BorderLayout.CENTER);
        this.add(this.admin, BorderLayout.CENTER);
        this.add(this.movie,BorderLayout.CENTER);
        this.add(this.employee, BorderLayout.CENTER);
        this.add(this.movieSchedule, BorderLayout.CENTER);
        this.add(this.employeeSchedule, BorderLayout.CENTER);
        this.add(this.movieBookning, BorderLayout.CENTER);
        this.add(this.startPage, BorderLayout.CENTER);

        //SET VISIBILITY
        this.startPage.setVisible(true);
        this.movieList.setVisible(false);
        this.backPanel.setVisible(false);
        this.movieDetails.setVisible(false);
        this.receipt.setVisible(false);
        this.admin.setVisible(false);
        this.movie.setVisible(false);
        this.employee.setVisible(false);
        this.movieSchedule.setVisible(false);
        this.employeeSchedule.setVisible(false);
        this.movieBookning.setVisible(false);
        this.setVisible(true);

        //ADD FUNCTIONALITY STARTPAGE
        this.startPage.getBook().addActionListener((e)->{
            //download the whole movieSchedule and go to booking page
        });

        this.startPage.getListOfMovies().addActionListener((e) -> {
            this.startPage.setVisible(false);
            this.movieList.setVisible(true);
            this.backPanel.setVisible(true);

        });

        this.startPage.getWatch().addActionListener((e)->{
            this.startPage.setVisible(false);
            this.movieDetails.setCurrentMovie(this.newestMovie);
            this.movieDetails.updatePanel();
            this.movieDetails.setVisible(true);
        });

        this.startPage.getAdmin().addActionListener((e)->{
            this.startPage.setVisible(false);
            this.admin.setVisible(true);
            this.backPanel.setVisible(true);
        });

        //ADD FUNCTIONALITY MOVIEDETAILS

        this.movieDetails.getBack().addActionListener((e) -> {
            this.movieDetails.setVisible(false);
            this.movieList.setVisible(true);
            this.backPanel.setVisible(true);
        });

        this.movieDetails.getBook().addActionListener((e)->{
            bookButtonFunctionality();
        });

        //ADD FUNCTIONALITY MOVIEBOOKING
        this.movieBookning.getBackBtn().addActionListener((e)->{
            this.movieBookning.setVisible(false);
            this.movieDetails.setVisible(true);
        });

        this.movieBookning.getContinueBtn().addActionListener((e)->{
            continueButtonFunctionality();
        });

        //ADD FUNCTIONALITY RECEIPT
        this.receipt.getBack().addActionListener((e)-> {
            this.receipt.setVisible(false);
            this.movieBookning.setVisible(true);
        });

        this.receipt.getClose().addActionListener((e)-> {
            this.dispose();
        });

        //ADD FUNCTIONALITY ADMINPANEL

            this.admin.getMovie().addActionListener((e)-> {
            this.admin.setVisible(false);
            this.backPanel.setVisible(false);
            this.movie.setVisible(true);
            this.movie.fillList();
        });

        this.admin.getStaff().addActionListener((e )-> {
            this.admin.setVisible(false);
            this.backPanel.setVisible(false);
            this.employee.setVisible(true);
            this.employee.fillList();
        });

        this.admin.getMovieSchedule().addActionListener((e )-> {
            this.admin.setVisible(false);
            this.backPanel.setVisible(false);
            this.movieSchedule.setVisible(true);
            this.movieSchedule.fillList();
        });

        this.admin.getStaffSchedule().addActionListener((e)->{
            this.admin.setVisible(false);
            this.backPanel.setVisible(false);
            this.employeeSchedule.setVisible(true);
            this.employeeSchedule.fillList();
        });

        //ADD FUNCTIONALITY MOVIE

        this.movie.getBack().addActionListener((e)->{
            this.movie.setVisible(false);
            this.admin.setVisible(true);
            this.backPanel.setVisible(true);
        });

        //ADD FUNCTIONALITY TO EMPLOYEE

        this.employee.getBack().addActionListener((e)->{
            this.employee.setVisible(false);
            this.admin.setVisible(true);
            this.backPanel.setVisible(true);
        });

        //ADD FUNCTIONALITY TO MOVIESCHEDULE

        this.movieSchedule.getBack().addActionListener((e)->{
            this.movieSchedule.setVisible(false);
            this.admin.setVisible(true);
            this.backPanel.setVisible(true);
        });

        //ADD FUNCTIONALITY TO EMPLOYEESCHEDULE
        this.employeeSchedule.getBack().addActionListener((e)->{
            this.employeeSchedule.setVisible(false);
            this.admin.setVisible(true);
            this.backPanel.setVisible(true);
        });

        //ADD FUNCTIONALITY BACKPANEL

        this.backPanel.getBackToFront().addActionListener((e) -> {
            this.startPage.setVisible(true);
            this.movieList.setVisible(false);
            this.backPanel.setVisible(false);
            this.admin.setVisible(false);
            refresh();
            if(this.admin.getMovie().isEnabled()){
                this.admin.getMovieSchedule().setEnabled(false);
                this.admin.getStaffSchedule().setEnabled(false);
                this.admin.getMovie().setEnabled(false);
                this.admin.getStaff().setEnabled(false);
            }
        });
    }

    public void refresh(){
        //updating the movie List in FilmListScrollPanel
        this.movieList.emptyList();
        String movieListString = connect.sendUrlToDownloadAllMovies();
        this.movieList.setMovieListFromBackend(gson.fromJson(movieListString, new TypeToken<ArrayList<JsonObject>>() {
        }.getType()));
        this.movieList.addPanels();

        //updating most recent on startPage
        String mostRecentAsString = connect.sendUrlToDownloadMostRecentlyAddedMovie();
        this.newestMovie = gson.fromJson(mostRecentAsString, Movie.class);
        this.startPage.getMovieDesc().setText(this.newestMovie.getMovieDescription());
        try {
            URL pictureMostRecent = new URL(newestMovie.getPicturePath());
            Image image = ImageIO.read(pictureMostRecent);
            image = image.getScaledInstance(this.startPage.getMoviePicture().getWidth(),
                    this.startPage.getMoviePicture().getHeight(),
                    Image.SCALE_DEFAULT);
            this.startPage.getMoviePicture().setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertStrings(String seats){
        String finished = "";
        for(int i = 0; i<seats.length(); i++){
            if(Character.isDigit(seats.charAt(i))){
                finished = finished+seats.charAt(i)+",";
            }
        }
        int lenght = finished.length()-1;
        finished = finished.substring(0,lenght);
        return finished;
    }

    public void continueButtonFunctionality(){
        //transfering data to receiptPanel
        Movie movie = this.movieBookning.getMovie();
        this.receipt.setMovie(movie);
        Salon salon = this.movieBookning.getSalon();
        this.receipt.setSalon(salon);
        MovieSchedule movieSchedule = this.movieBookning.getMovieSchedule();
        System.out.println("movieSchedule:"+movieSchedule.toString());
        this.receipt.setMovieSchedule(movieSchedule);
        String seats= this.movieBookning.getSeatsCol().getText();
        this.receipt.setSeats(seats);
        String row = this.movieBookning.getSeatRow().getText();
        this.receipt.setRow(row);


        //making a booking in the database whit encoded strings
        String convertedSeats = convertStrings(seats);
        String convertedRow = convertStrings(row);
        connect.makeReservation(
                encodeToURL(convertedSeats),
                salon.getSalonId(),
                encodeToURL(convertedRow),
                movie.getId(),
                encodeToURL(movieSchedule.getMovieTime()),
                encodeToURL(movieSchedule.getMovieDate()));

        String reservationID = connect.getLatestReservationID();

        //updation the labes on the panel
        this.receipt.updatePanel(seats,"Reservation ID: "+reservationID, "Salon: "+salon.getSalonId());

        //setting visibility
        this.movieBookning.setVisible(false);
        this.receipt.setVisible(true);
    }

    public void bookButtonFunctionality(){
        this.movieDetails.setVisible(false);

        //get the info from the list in movieDetails
        String infoFromMovieSchedule = this.movieDetails.getList().getSelectedValue();
        System.out.println(infoFromMovieSchedule);
        String[] temp = infoFromMovieSchedule.split(",");

        //set the movie to the current movie from movieDetails
        this.movieBookning.setMovie(this.movieDetails.getCurrentMovie());

        //download the salon for the booking page
        String salonAsString = connect.sendUrlToGetSalonById(Integer.parseInt(temp[3]),1);
        this.movieBookning.setSalon(gson.fromJson(salonAsString, Salon.class));

        //download the right movieSchedule
        String movieScheduleAsString = connect.sendUrlToGetMovieSchedule(Integer.parseInt(temp[3]),
                this.movieDetails.getCurrentMovie().getId(), temp[1], temp[2]);
        System.out.println(movieScheduleAsString);
        this.movieBookning.setMovieSchedule(gson.fromJson(movieScheduleAsString, MovieSchedule.class));

        this.movieBookning.update();
        this.movieBookning.setVisible(true);

        JPanel footer = this.movieBookning.footerConfirmationPanel();
        footer.setLayout(new FlowLayout());
        footer.setVisible(false);
        this.movieBookning.add(footer,BorderLayout.NORTH);
    }

    public String encodeToURL(String inputString) {
        String encodedString = URLEncoder.encode(inputString, StandardCharsets.UTF_8);
        return encodedString;
    }
}
