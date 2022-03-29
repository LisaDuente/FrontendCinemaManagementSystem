import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class FilmListScrollPane extends JScrollPane {
    MovieDetailsPanel movieDetails;
    BackPanel back;
    private Movie currentMovie = new Movie();
    ArrayList<Movie> movieListFromBackend;
    //buttonMaker backButton;
    Gson gson = new Gson();
    JPanel columnPanel;

    //TODO: Add an Image Icon and a Link from a website to this panel
    public FilmListScrollPane(MovieDetailsPanel movieDetails, BackPanel back){
        this.movieDetails = movieDetails;
        this.back = back;
        this.setBounds(50,20,900,700);

        JPanel borderLayoutPanel = new JPanel();
        this.setViewportView(borderLayoutPanel);
        borderLayoutPanel.setLayout(new BorderLayout(0,0));

        columnPanel = new JPanel();
        borderLayoutPanel.add(columnPanel,BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0,1,0,1));
        columnPanel.setBackground(Color.DARK_GRAY);

        //this.backButton = new buttonMaker("Back",255,87,15,"test");
        //borderLayoutPanel.add(backButton,BorderLayout.SOUTH);


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
                JButton book = new JButton("Book");
                book.setBounds(550, 35, 100, 30);
                rowPanel.add(book);

                //to get the ID
                JLabel id = new JLabel(String.valueOf(this.currentMovie.getId()));
                id.setVisible(false);
                book.addActionListener((e) -> {

                    this.setVisible(false);
                    //loads the right movie from the movie list
                    this.currentMovie = gson.fromJson(String.valueOf(movieListFromBackend.get(Integer.parseInt(id.getText())-1)),Movie.class);
                    this.movieDetails.setCurrentMovie(this.currentMovie);
                    this.movieDetails.updatePanel();
                    this.movieDetails.setVisible(true);
                    this.back.setVisible(false);

                });

                //change this
                JLabel movieTitle = new JLabel(this.currentMovie.getName());
                movieTitle.setBounds(150, 10, 200, 30);
                rowPanel.add(movieTitle);

                JLabel movieGenre = new JLabel(this.currentMovie.getGenre());
                movieGenre.setBounds(150, 35, 200, 30);
                rowPanel.add(movieGenre);

                JLabel movieDuration = new JLabel(this.currentMovie.getDuration());
                movieDuration.setBounds(150, 50, 200, 30);
                rowPanel.add(movieDuration);

                JTextArea shortMovieDescription = new JTextArea(this.currentMovie.getShortDescription());
                shortMovieDescription.setBounds(350, 10, 200, 100);
                shortMovieDescription.setEditable(false);
                shortMovieDescription.setLineWrap(true);

                rowPanel.add(shortMovieDescription);

                rowPanel.setLayout(null);
                if (i % 2 == 0) {
                    rowPanel.setBackground(SystemColor.pink);
                    shortMovieDescription.setBackground(SystemColor.pink);
                }else{
                    shortMovieDescription.setBackground(SystemColor.WHITE);
                    rowPanel.setBackground(SystemColor.WHITE);
                }
                rowPanel.setVisible(true);
            }
        }
    }
}

