import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class startPagePanel extends JPanel {
    JPanel navPanel;
    JPanel contentPanel;

    JLabel logo;

    JTextArea moto;

    buttonMaker home;
    buttonMaker aboutUs;
    buttonMaker contact;
    buttonMaker book;
    buttonMaker listOfMovies;
    buttonMaker watch;


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
        this.home = new buttonMaker("HOME", 255,87,15, "/blabla");
        this.aboutUs = new buttonMaker("About Us", 255, 87, 15, "/blabla");
        this.contact = new buttonMaker("Contact Us", 255, 87, 15, "/blabla");
        this.home.setBounds(150,15,150,70);
        this.aboutUs.setBounds(350, 15, 180, 70);
        this.contact.setBounds(580, 15, 200, 70);
        this.logo = logoSetup();
        this.navPanel.add(logo);
        this.navPanel.add(home);
        this.navPanel.add(aboutUs);
        this.navPanel.add(contact);
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
        this.book.setBounds(80, 130, 250,100);
        this.listOfMovies.setBounds(80, 300, 250,100);
        this.contentPanel.add(moto);
        this.contentPanel.add(book);
        this.contentPanel.add(listOfMovies);
        this.contentPanel.add(newMoviePanel);
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
        JLabel moviePicture = new JLabel(new ImageIcon("src/main/java/testbatman.png"));
        moviePicture.setBounds(0, 60, 196, 300);
        JTextArea movieDesc = new JTextArea("The film sees the Batman, who has been fighting crime in Gotham City for two years, uncover corruption while pursuing the Riddler (Dano), a serial killer who targets Gotham's elite. Development began after Ben Affleck was cast as the Batman in the DC Extended Universe");
        movieDesc.setWrapStyleWord(true);
        movieDesc.setLineWrap(true);
        movieDesc.setEditable(false);
        movieDesc.setFocusable(false);
        movieDesc.setFont(new Font("sanserif", Font.BOLD, 15));
        movieDesc.setBounds(200, 60, 304, 300);
        movieDesc.setForeground(Color.white);
        movieDesc.setBackground(new Color(199, 0, 57));
        moviePanel.add(moviePicture);
        moviePanel.add(movieDesc);


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

    /*
    public static void main(String[] args) {
        JFrame test = new JFrame("test");
        test.setSize(1000,800);
        test.setLayout(new BorderLayout());
        startPagePanel startPagePanel = new startPagePanel();
        test.add(startPagePanel, BorderLayout.CENTER);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        test.setVisible(true);
    }

     */

    public buttonMaker getBook() {
        return book;
    }

    public buttonMaker getAboutUs() {
        return aboutUs;
    }

    public buttonMaker getContact() {
        return contact;
    }

    public buttonMaker getHome() {
        return home;
    }

    public buttonMaker getListOfMovies() {
        return listOfMovies;
    }

    public buttonMaker getWatch() {
        return watch;
    }
}

