package Panels;

import Functionality.buttonMaker;

import javax.swing.*;
import java.awt.*;

public class startPagePanel extends JPanel {
    private JPanel navPanel;
    private JPanel contentPanel;

    private JLabel logo;

    private JTextArea moto;
    private JTextArea movieDesc;

    private JTextField insertReservation;

    private buttonMaker showReservation;
    private buttonMaker book;
    private buttonMaker listOfMovies;
    private buttonMaker watch;
    private buttonMaker admin;

    private JLabel moviePicture;


    public startPagePanel(){ //MAIN PANEL
        this.setPreferredSize(new Dimension(1000,800));
        this.setLayout(new BorderLayout());
        this.add(navPanelSetup(), BorderLayout.NORTH);
        this.add(contentPanelSetup(), BorderLayout.CENTER);
        this.setVisible(true);
    }

    // TODO: 2022-03-23 ADD ACTION LISTENERS TO BUTTONS THAT SENDS A URL (HTTPCONNECTION)
    public JPanel navPanelSetup(){
        this.navPanel = new JPanel();
        this.navPanel.setLayout(null);
        this.navPanel.setPreferredSize(new Dimension(1000,100));
        this.navPanel.setBackground(new Color(144, 12, 63));
        this.showReservation = new buttonMaker("Check Reservation", 255,87,15, "/blabla");
        this.insertReservation = new JTextField();

        this.showReservation.setBounds(150,15,350,50);
        this.insertReservation.setBounds(520, 15, 200, 50);
        this.insertReservation.setFont(new Font("sanserif", Font.BOLD, 25));
        this.logo = logoSetup();
        this.navPanel.add(logo);
        this.navPanel.add(insertReservation);
        this.navPanel.add(showReservation);
        this.navPanel.setVisible(true);
        return this.navPanel;
    } //NAVIGATION PANEL
    public JPanel contentPanelSetup(){ //CONTENT PANEL
        this.contentPanel = new JPanel();
        JPanel newMoviePanel = newMoviePanel();

        this.moto = motoSetup();
        this.contentPanel.setPreferredSize(new Dimension(1000, 1000 - this.navPanel.getHeight()));
        this.contentPanel.setBackground(new Color(81, 24, 69));
        this.contentPanel.setLayout(null);

        this.book = new buttonMaker("Reserve", 255, 87, 15, "/blabla");
        this.listOfMovies = new buttonMaker("See all movies", 255, 87, 15, "/blabla");
        this.admin = new buttonMaker("Admin", 255, 87, 15, "/blabla");

        this.book.setBounds(80, 130, 250,100);
        this.listOfMovies.setBounds(80, 300, 250,100);
        this.admin.setBounds(20,600,150,50);

        this.contentPanel.add(moto);
        this.contentPanel.add(book);
        this.contentPanel.add(listOfMovies);
        this.contentPanel.add(newMoviePanel);
        this.contentPanel.add(this.admin);

        this.contentPanel.setVisible(true);
        return this.contentPanel;
    }

    private JPanel newMoviePanel() {
        JPanel newMoviePanel = new JPanel();
        newMoviePanel.setLayout(null);
        newMoviePanel.setPreferredSize(new Dimension(500, 500));
        newMoviePanel.setBackground(new Color(199, 0, 57));
        newMoviePanel.setBounds(420,120, 500,500);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(199, 0, 57));
        titlePanel.setSize(500,45);
        JLabel title = new JLabel("<HTML><U>Newly Arrived </U></HTML>");
        title.setFont(new Font("Italic", Font.ITALIC, 25));
        title.setForeground(Color.white);
        titlePanel.add(title);

        JPanel moviePanel = new JPanel();

        moviePanel.setBackground(new Color(199, 0, 57));
        moviePanel.setSize(500,350);
        moviePanel.setLayout(null);
        this.moviePicture = new JLabel(new ImageIcon("src/main/java/testbatman.png"));
        this.moviePicture.setBounds(0, 60, 196, 300);
        this.movieDesc = new JTextArea("The film sees the Batman, who has been fighting crime in Gotham City for two years, uncover corruption while pursuing the Riddler (Dano), a serial killer who targets Gotham's elite. Development began after Ben Affleck was cast as the Batman in the DC Extended Universe");
        this.movieDesc.setWrapStyleWord(true);
        this.movieDesc.setLineWrap(true);
        this.movieDesc.setEditable(false);
        this.movieDesc.setFocusable(false);
        this.movieDesc.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieDesc.setBounds(200, 60, 304, 300);
        this.movieDesc.setForeground(Color.white);
        this.movieDesc.setBackground(new Color(199, 0, 57));
        moviePanel.add(this.moviePicture);
        moviePanel.add(this.movieDesc);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(199, 0, 57));
        buttonPanel.setSize(500, 200);
        this.watch = new buttonMaker("watch", 255,87,15, "/blalba");
        this.watch.setBounds(230, 0, 150,150);
        buttonPanel.add(watch);
        buttonPanel.setBounds(0, 400, 500, 200);

        newMoviePanel.add(titlePanel);
        newMoviePanel.add(moviePanel);
        newMoviePanel.add(buttonPanel);
        return newMoviePanel;
    }

    private JTextArea motoSetup() {
        JTextArea moto = new JTextArea("'Movies are best in the cinema'");
        moto.setForeground(Color.WHITE);
        moto.setFont(new Font("Italic", Font.ITALIC, 45));
        moto.setBounds(50,15, 800,100);
        moto.setBackground(new Color(81, 24, 69));
        moto.setLineWrap(true);
        moto.setWrapStyleWord(true);
        moto.setFocusable(false);
        moto.setEditable(false);
        return moto;
    } //MOTO
    private JLabel logoSetup() {
        ImageIcon imageIcon = new ImageIcon("src/main/java/testlogo.png");
        Image image = imageIcon.getImage();
        Image finalImage = image.getScaledInstance(100,70, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(finalImage);
        JLabel logo = new JLabel(imageIcon);
        logo.setBounds(10, 15, 100,70);
        return logo;
    } //LOGO


    public buttonMaker getBook() {
        return book;
    }

    public buttonMaker getHome() {
        return showReservation;
    }

    public buttonMaker getListOfMovies() {
        return listOfMovies;
    }

    public buttonMaker getWatch() {
        return watch;
    }

    public JTextArea getMovieDesc() {
        return movieDesc;
    }

    public buttonMaker getAdmin() {
        return admin;
    }

    public JLabel getMoviePicture() {
        return moviePicture;
    }

    public JTextField getInsertReservation() {
        return insertReservation;
    }

    public buttonMaker getShowReservation() {
        return showReservation;
    }

    public void setMovieDesc(JTextArea movieDesc) {
        this.movieDesc = movieDesc;
    }

    public void setMoviePicture(JLabel moviePicture) {
        this.moviePicture = moviePicture;
    }
}

