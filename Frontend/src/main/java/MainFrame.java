import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    startPagePanel startPage;
    FilmListScrollPane movieList;
    MovieDetailsPanel movieDetails;
    BackPanel backPanel;
    ConnectionManager connect;
    Gson gson;
    //Movie currentMovie;
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

        //ADD TO FRAME
        this.add(this.movieList, BorderLayout.CENTER);
        this.add(this.backPanel, BorderLayout.SOUTH);
        this.add(this.movieDetails, BorderLayout.CENTER);
        this.add(this.startPage, BorderLayout.CENTER);

        //SET VISIBILITY
        this.startPage.setVisible(true);
        this.movieList.setVisible(false);
        this.backPanel.setVisible(false);
        this.movieDetails.setVisible(false);
        this.setVisible(true);

        //ADD FUNCTIONALITY
        this.startPage.getBook().addActionListener((e) -> {
            this.startPage.setVisible(false);
            this.movieList.setVisible(true);
            this.backPanel.setVisible(true);

            String movieListString = connect.sendUrlToDownloadAllMovies();
            this.movieList.setMovieListFromBackend(gson.fromJson(movieListString, new TypeToken<ArrayList<JsonObject>>() {
            }.getType()));
            this.movieList.addPanels();
        });

        this.movieDetails.getBack().addActionListener((e) -> {
            this.movieDetails.setVisible(false);
            this.movieList.setVisible(true);
            this.backPanel.setVisible(true);
        });

        this.backPanel.getBackToFront().addActionListener((e) -> {
            this.startPage.setVisible(true);
            this.movieList.setVisible(false);
            this.backPanel.setVisible(false);
        });
    }
}
