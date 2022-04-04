package Panels;

import Classes.Movie;
import Functionality.buttonMaker;
import com.google.gson.Gson;
import Functionality.ConnectionManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FilmListScrollPane extends JScrollPane {
    private MovieDetailsPanel movieDetails;
    private BackPanel back;
    private Gson gson = new Gson();
    private JPanel columnPanel;
    private ConnectionManager connector;
    private Movie currentMovie = new Movie();
    private ArrayList<Movie> movieListFromBackend;


    //TODO: Add an Image Icon and a Link from a website to this panel
    public FilmListScrollPane(MovieDetailsPanel movieDetails, BackPanel back){
        this.connector = new ConnectionManager();
        this.movieDetails = movieDetails;
        this.back = back;
        this.setBounds(0,0,1000,710);
        this.setBackground(new Color(81, 24, 69));
        this.setForeground(new Color(81, 24, 69));


        JPanel borderLayoutPanel = new JPanel();
        this.setViewportView(borderLayoutPanel);
        borderLayoutPanel.setLayout(new BorderLayout());
        borderLayoutPanel.setBackground(new Color(81, 24, 69));

        columnPanel = new JPanel();
        borderLayoutPanel.add(columnPanel,BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0,1,0,2));
        columnPanel.setBackground(new Color(81, 24, 69));

    }

    public void setMovieListFromBackend(ArrayList<Movie> movieListFromBackend) {
        this.movieListFromBackend = movieListFromBackend;
    }

    public void addPanels(){
        for(int i = 0; i<movieListFromBackend.size(); i++){
            //converts the json objects in the list to actual movie objects
            String movieString = String.valueOf(movieListFromBackend.get(i));
            this.currentMovie = gson.fromJson(movieString, Movie.class);
            if((boolean)this.currentMovie.isAvailable()) {

                JPanel rowPanel = new JPanel();
                rowPanel.setPreferredSize(new Dimension(600, 100));
                columnPanel.add(rowPanel);
                buttonMaker book = new buttonMaker("Book",255, 87, 15, "/blabla");
                book.setBounds(550, 20, 150, 75);
                rowPanel.add(book);

                //to get the ID
                JLabel id = new JLabel(String.valueOf(this.currentMovie.getId()));
                id.setVisible(false);
                book.addActionListener((e) -> {

                    this.setVisible(false);
                    //loads the right movie from the movie list
                    String temp = connector.sendUrlToDownloadMovieById(Integer.parseInt(id.getText()));
                    this.currentMovie = gson.fromJson(temp,Movie.class);
                    this.movieDetails.setCurrentMovie(this.currentMovie);
                    this.movieDetails.updatePanel();
                    this.movieDetails.setVisible(true);
                    this.back.setVisible(false);

                });

                //change this
                JLabel movieTitle = new JLabel(this.currentMovie.getName());
                movieTitle.setBounds(150, 10, 200, 30);
                movieTitle.setFont(new Font("sanserif", Font.BOLD, 25));
                movieTitle.setForeground(Color.white);
                rowPanel.add(movieTitle);

                JLabel movieGenre = new JLabel("Genre: "+this.currentMovie.getGenre());
                movieGenre.setBounds(150, 35, 200, 30);
                movieGenre.setFont(new Font("sanserif", Font.BOLD, 15));
                movieGenre.setForeground(Color.white);
                rowPanel.add(movieGenre);

                JLabel movieDuration = new JLabel("Duration: "+this.currentMovie.getDuration());
                movieDuration.setBounds(150, 60, 200, 30);
                movieDuration.setFont(new Font("sanserif", Font.BOLD, 15));
                movieDuration.setForeground(Color.white);
                rowPanel.add(movieDuration);

                JTextArea shortMovieDescription = new JTextArea(this.currentMovie.getShortDescription());
                shortMovieDescription.setBounds(350, 10, 200, 100);
                shortMovieDescription.setFont(new Font("sanserif", Font.BOLD, 15));
                shortMovieDescription.setForeground(Color.white);
                shortMovieDescription.setEditable(false);
                shortMovieDescription.setLineWrap(true);

                rowPanel.add(shortMovieDescription);

                rowPanel.setLayout(null);
                if (i % 2 == 0) {
                    rowPanel.setBackground(new Color(144, 12, 63));
                    shortMovieDescription.setBackground(new Color(144, 12, 63));
                }else{
                    shortMovieDescription.setBackground(new Color(144, 12, 63));
                    rowPanel.setBackground(new Color(144, 12, 63));
                }
                rowPanel.setVisible(true);
            }
        }
    }
}

