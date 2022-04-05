package Panels;

import Classes.Movie;
import Functionality.ConnectionManager;
import Functionality.buttonMaker;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;

public class AdminPageAddFilm extends JPanel {
    private ConnectionManager connect = new ConnectionManager();
    private Gson gson = new Gson();
    private JScrollPane scrollList;
    private JList<String> list;
    private DefaultListModel<String> model;

    private JLabel info;

    private JLabel movieIdText;
    private JLabel movieNameText;
    private JLabel movieGenreText;
    private JLabel movieDurationText;
    private JLabel movieShortText;
    private JLabel movieDescrText;
    private JLabel moviePictureText;
    private JLabel movieAvailableText;

    private JTextField movieID;
    private JTextField movieName;
    private JTextField genre;
    private JTextField duration;
    private JTextField shortDesc;
    private JTextField movieDesc;
    private JTextField picturePath;
    private JTextField available;

    private buttonMaker back;
    private buttonMaker enter;
    private buttonMaker delete;
    private buttonMaker update;

    private Color colorBack = new Color(81, 24, 69);
    private Color colorMiddle = new Color(199, 0, 57);

    public AdminPageAddFilm(){
        this.setSize(1000,800);
        this.setLayout(null);
        this.setBackground(colorBack);

        this.model = new DefaultListModel<>();
        this.list = new JList<>(model);
        this.list.setFont(new Font("sanserif", Font.BOLD, 15));
        this.list.setForeground(Color.white);
        this.list.setBackground(colorMiddle);
        this.scrollList = new JScrollPane(list);

        this.scrollList.createHorizontalScrollBar();
        this.scrollList.setBounds(50,50,900,200);

        this.info = new JLabel("Choose a movie from the list to delete!");
        this.info.setFont(new Font("sanserif", Font.BOLD, 15));
        this.info.setForeground(Color.WHITE);
        this.info.setBounds(50,10,500,25);
        this.add(this.info);


    //CONTROL PANEL

        JPanel controlPanel = new JPanel(new GridLayout(8,2,3,0));
        controlPanel.setBounds(50,255,900,400);
        controlPanel.setBackground(colorBack);

        this.movieIdText = new JLabel("ID:");
        this.movieIdText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieIdText.setForeground(Color.white);
        this.movieNameText = new JLabel("Name:");
        this.movieNameText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieNameText.setForeground(Color.white);
        this.movieGenreText = new JLabel("Genre:");
        this.movieGenreText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieGenreText.setForeground(Color.white);
        this.movieDurationText = new JLabel("Duration");
        this.movieDurationText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieDurationText.setForeground(Color.white);
        this.movieShortText = new JLabel("Short Description:");
        this.movieShortText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieShortText.setForeground(Color.white);
        this.movieDescrText = new JLabel("Description:");
        this.movieDescrText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieDescrText.setForeground(Color.white);
        this.moviePictureText = new JLabel("Picture Path:");
        this.moviePictureText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.moviePictureText.setForeground(Color.white);
        this.movieAvailableText = new JLabel("Available:");
        this.movieAvailableText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieAvailableText.setForeground(Color.white);

        this.movieID = new JTextField();
        this.movieID.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieName = new JTextField();
        this.movieName.setFont(new Font("sanserif", Font.BOLD, 15));
        this.genre = new JTextField();
        this.genre.setFont(new Font("sanserif", Font.BOLD, 15));
        this.duration = new JTextField();
        this.duration.setFont(new Font("sanserif", Font.BOLD, 15));
        this.shortDesc = new JTextField();
        this.shortDesc.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieDesc = new JTextField();
        this.movieDesc.setFont(new Font("sanserif", Font.BOLD, 15));
        this.picturePath = new JTextField();
        this.picturePath.setFont(new Font("sanserif", Font.BOLD, 15));
        this.available = new JTextField();
        this.available.setFont(new Font("sanserif", Font.BOLD, 15));

        controlPanel.add(this.movieIdText);
        controlPanel.add(this.movieID);
        controlPanel.add(this.movieNameText);
        controlPanel.add(this.movieName);
        controlPanel.add(this.movieGenreText);
        controlPanel.add(this.genre);
        controlPanel.add(this.movieDurationText);
        controlPanel.add(this.duration);
        controlPanel.add(this.movieShortText);
        controlPanel.add(this.shortDesc);
        controlPanel.add(this.movieDescrText);
        controlPanel.add(this.movieDesc);
        controlPanel.add(this.moviePictureText);
        controlPanel.add(this.picturePath);
        controlPanel.add(this.movieAvailableText);
        controlPanel.add(this.available);

        controlPanel.setVisible(true);
//BUTTON PANEL
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.setBounds(0,700,1000,100);
        buttons.setBackground(colorBack);

        this.back = new buttonMaker("Back",255,87,15, "/blalba");
        this.enter = new buttonMaker("Add",154 ,205,50, "/blalba");
        this.delete = new buttonMaker("Delete",220,20,60, "/blalba");
        this.update = new buttonMaker("Update",135,206,235,"/DontKnowYet");

        //TODO: Make these next two methods!
        this.enter.addActionListener((e -> {
            String parameterString = "get everything from the textfields," +
                    "clear Textfields if it worked out" +
                    "send a request via connection Manager";
        }));

        this.delete.addActionListener((e) -> {
                String movieString = this.list.getSelectedValue();
                String[] temp = movieString.split(",");
                int id = Integer.parseInt(temp[0]);
                this.connect.sendUrlToDeleteMovieById(id);
                fillList();

        });

        this.update.addActionListener((e)->{
            //get the strings from the specific fields, feed them to the backend
        });

        buttons.add(this.enter);
        buttons.add(this.delete);
        buttons.add(this.update);
        buttons.add(this.back);
        buttons.setVisible(true);


        this.add(this.scrollList,BorderLayout.NORTH);
        this.add(buttons, BorderLayout.SOUTH);
        this.add(controlPanel,BorderLayout.CENTER);

        this.setVisible(true);

    }

    public void fillList(){
        this.model.clear();
        String movieListAsSTring = connect.sendUrlToDownloadAllMovies();
        Movie[] movies = gson.fromJson(movieListAsSTring, Movie[].class);
        for(int i = 0; i<movies.length;i++){
            //only shows the name of the movie
           this.model.add(i, String.valueOf(movies[i].getId())+","+movies[i].getName());
        }
    }

    public buttonMaker getBack() {
        return back;
    }
}
