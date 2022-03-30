import javax.swing.*;
import java.awt.*;

public class MovieDetailsPanel extends JPanel {
    private Movie currentMovie;

    private JLabel title;
    private JLabel duration;
    private JLabel genre;
    private JLabel image;

    private JTextArea description;

    private buttonMaker book;
    private buttonMaker back;

    private ImageIcon imageIcon;

    private Color colorBack = new Color(81, 24, 69);
    private Color colorMiddle = new Color(199, 0, 57);


    public MovieDetailsPanel(){
        //SET PANEL
        this.setSize(1000,800);
        this.setLayout(new BorderLayout());
        this.setBackground(colorBack);

        //INITIALIZE
        this.imageIcon = new ImageIcon("src/main/java/testbatman.png");
        this.title = new JLabel();
        this.duration = new JLabel();
        this.genre = new JLabel();
        this.image = new JLabel(this.imageIcon);
        this.description = new JTextArea();
        this.book = new buttonMaker("Book",255,87,15,"dontKnowYet");
        this.back = new buttonMaker("Back",255,87,15,"dontKnowYet");

        //BOUNDS
        this.title.setSize(200,50);
        this.duration.setSize(100,25);
        this.genre.setSize(100,25);
        this.description.setSize(200,200);
        this.image.setSize(300,300);

        //COLORS AND FONTS
        this.title.setFont(new Font("Italic", Font.ITALIC, 30));
        this.title.setForeground(Color.white);
        this.duration.setFont(new Font("Italic", Font.ITALIC, 20));
        this.duration.setForeground(Color.white);
        this.genre.setFont(new Font("Italic", Font.ITALIC, 20));
        this.genre.setForeground(Color.white);
        this.description.setFont(new Font("Italic", Font.ITALIC, 25));
        this.description.setForeground(Color.white);
        this.description.setBackground(colorMiddle);

        //DEFINE DESCRIPTION
        this.description.setWrapStyleWord(true);
        this.description.setLineWrap(true);
        this.description.setEditable(false);
        this.description.setFocusable(false);


        //ACTION LISTENER

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
        this.add(this.description,BorderLayout.CENTER);
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
        this.title.setText(this.currentMovie.getName());
        this.genre.setText("Genre: "+this.currentMovie.getGenre());
        this.duration.setText("Duration: "+this.currentMovie.getDuration());
        this.description.setText(this.currentMovie.getMovieDescription());
    }

    public buttonMaker getBook() {
        return book;
    }

    public buttonMaker getBack() {
        return back;
    }
}
