import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MainFrame extends JFrame {
    startPagePanel startPage;
    FilmListScrollPane movieList;
    MovieDetailsPanel movieDetails;
    BackPanel backPanel;
    ReceiptPanel receipt;
    ConnectionManager connect;
    AdminPage admin;
    Gson gson;
    Movie newestMovie;
    //ArrayList<Movie> movieArrayList;

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


        //INITIALIZE MOVIES
        String movieListString = connect.sendUrlToDownloadAllMovies();
        this.movieList.setMovieListFromBackend(gson.fromJson(movieListString, new TypeToken<ArrayList<JsonObject>>() {
        }.getType()));
        String mostRecentAsString = connect.sendUrlToDownloadMostRecentlyAddedMovie();
        this.newestMovie = gson.fromJson(mostRecentAsString,Movie.class);
        this.startPage.getMovieDesc().setText(this.newestMovie.getMovieDescription());
        this.movieList.addPanels();


        //ADD TO FRAME
        this.add(this.movieList, BorderLayout.CENTER);
        this.add(this.backPanel, BorderLayout.SOUTH);
        this.add(this.movieDetails, BorderLayout.CENTER);
        this.add(this.receipt,BorderLayout.CENTER);
        this.add(this.admin, BorderLayout.CENTER);
        this.add(this.startPage, BorderLayout.CENTER);

        //SET VISIBILITY
        this.startPage.setVisible(true);
        this.movieList.setVisible(false);
        this.backPanel.setVisible(false);
        this.movieDetails.setVisible(false);
        this.receipt.setVisible(false);
        this.admin.setVisible(false);
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
        //TODO: this should lead to the Bookingpage not to the receipt
        this.movieDetails.getBook().addActionListener((e)->{
            this.movieDetails.setVisible(false);
            this.receipt.setVisible(true);
        });

        //ADD FUNCTIONALITY RECEIPT
        //TODO: this should lead back to booking and not to movie details
        this.receipt.getBack().addActionListener((e)-> {
            this.receipt.setVisible(false);
            this.movieDetails.setVisible(true);
        });

        this.receipt.getClose().addActionListener((e)-> {
            this.dispose();
        });

        //ADD FUNCTIONALITY BACKPANEL

        this.backPanel.getBackToFront().addActionListener((e) -> {
            this.startPage.setVisible(true);
            this.movieList.setVisible(false);
            this.backPanel.setVisible(false);
            this.admin.setVisible(false);
        });
    }
}
