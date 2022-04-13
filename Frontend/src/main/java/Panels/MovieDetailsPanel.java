package Panels;

import Classes.Movie;
import Classes.MovieSchedule;
import Classes.MovieScheduleView;
import Functionality.ConnectionManager;
import Functionality.buttonMaker;
import com.google.gson.Gson;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MovieDetailsPanel extends JPanel {
    private Movie currentMovie;

    private JLabel title;
    private JLabel duration;
    private JLabel genre;
    private JLabel image;

    private JTextArea description;

    private JScrollPane scrollList;
    private JList<String> list;
    private DefaultListModel<String> model;

    private buttonMaker book;
    private buttonMaker back;

    private ImageIcon imageIcon;

    private Color colorBack = new Color(81, 24, 69);
    private Color colorMiddle = new Color(199, 0, 57);

    private URL url;


    public MovieDetailsPanel(){
        //SET PANEL
        this.setSize(1000,800);
        this.setLayout(new BorderLayout());
        this.setBackground(colorBack);

        //INITIALIZE
        this.model = new DefaultListModel<>();
        this.list = new JList<>(model);
        this.list.setFont(new Font("sanserif", Font.BOLD, 15));
        this.list.setForeground(Color.white);
        this.list.setBackground(colorMiddle);
        this.scrollList = new JScrollPane(list);

        this.scrollList.createHorizontalScrollBar();

        this.imageIcon = new ImageIcon("src/main/java/testbatman.png");
        this.title = new JLabel();
        this.duration = new JLabel();
        this.genre = new JLabel();
        this.image = new JLabel(this.imageIcon);
        this.description = new JTextArea();
        this.book = new buttonMaker("Book",255,87,15,"dontKnowYet");
        this.book.setEnabled(false);
        this.back = new buttonMaker("Back",255,87,15,"dontKnowYet");

        //BOUNDS
        this.title.setSize(200,50);
        this.duration.setSize(100,25);
        this.genre.setSize(100,25);
        this.description.setSize(200,200);
        this.image.setSize(300,300);
        this.scrollList.setSize(200,400);

        //COLORS AND FONTS
        this.title.setFont(new Font("sanserif", Font.BOLD, 30));
        this.title.setForeground(Color.white);
        this.duration.setFont(new Font("sanserif", Font.BOLD, 20));
        this.duration.setForeground(Color.white);
        this.genre.setFont(new Font("sanserif", Font.BOLD, 20));
        this.genre.setForeground(Color.white);
        this.description.setFont(new Font("sanserif", Font.BOLD, 25));
        this.description.setForeground(Color.white);
        this.description.setBackground(colorMiddle);

        //DEFINE DESCRIPTION
        this.description.setWrapStyleWord(true);
        this.description.setLineWrap(true);
        this.description.setEditable(false);
        this.description.setFocusable(false);

        //ACTION LISTENER
        this.list.addListSelectionListener((e -> this.book.setEnabled(true)));

        //PANELS FOR COMPONENTS
        JPanel northPanel = new JPanel(new GridLayout(0,3,0,2));
        northPanel.setPreferredSize(new Dimension(1000,100));
        northPanel.setBackground(colorBack);
        northPanel.add(this.title);
        northPanel.add(this.genre);
        northPanel.add(this.duration);

        JPanel westPanel = new JPanel(new FlowLayout());
        westPanel.setPreferredSize(new Dimension(200,800));
        westPanel.setBackground(colorBack);
        westPanel.add(this.image);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(this.description, BorderLayout.CENTER);
        centerPanel.add(this.scrollList, BorderLayout.SOUTH);

        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.setPreferredSize(new Dimension(1000,100));
        southPanel.setBackground(colorBack);
        southPanel.add(this.book);
        southPanel.add(this.back);

        JPanel eastPanel = new JPanel(null);
        eastPanel.setPreferredSize(new Dimension(200,800));
        eastPanel.setBackground(colorBack);


        //ADD ON PANEL
        this.add(northPanel,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(westPanel,BorderLayout.WEST);
        this.add(southPanel,BorderLayout.SOUTH);
        this.add(eastPanel,BorderLayout.EAST);

        this.setVisible(true);
    }

    public void setCurrentMovie(Movie currentMovie) {
        this.currentMovie = currentMovie;
    }

    //TODO: set the imageIcon to a new source
    public void updatePanel(){
        //set current movie information
        this.title.setText(this.currentMovie.getName());
        this.genre.setText("Genre: "+this.currentMovie.getGenre());
        this.duration.setText("Duration: "+this.currentMovie.getDuration());
        this.description.setText(this.currentMovie.getMovieDescription());
        //set the right picture
        try {
            this.url = new URL(this.currentMovie.getPicturePath());
            Image image = ImageIO.read(this.url);
            image = image.getScaledInstance(this.image.getWidth(),this.image.getHeight(),Image.SCALE_DEFAULT);
            this.imageIcon.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //fill in the right lists
        ConnectionManager connect = new ConnectionManager();
        Gson gson = new Gson();
        String schedulesAsString = connect.downloadMovieScheduleViewOneMovie(encodeToURL(this.currentMovie.getName()));
        MovieScheduleView[] movieSchedules = gson.fromJson(schedulesAsString,MovieScheduleView[].class);
        this.model.clear();
        for(int i = 0; i<movieSchedules.length;i++){
            //only shows the name and id of the employee
            this.model.add(i, movieSchedules[i].getMovie()+","+movieSchedules[i].getTime()+
                    ","+movieSchedules[i].getDate()+","+movieSchedules[i].getSalon());
        }

    }

    public String encodeToURL(String inputString) {
        String encodedString = URLEncoder.encode(inputString, StandardCharsets.UTF_8);
        return encodedString;
    }

    public buttonMaker getBook() {
        return book;
    }

    public buttonMaker getBack() {
        return back;
    }

    public JList<String> getList() {
        return list;
    }

    public Movie getCurrentMovie() {
        return currentMovie;
    }
}
