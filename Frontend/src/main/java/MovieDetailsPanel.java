import javax.swing.*;
import java.awt.*;

public class MovieDetailsPanel extends JPanel {
    Movie currentMovie;
    JLabel title;
    JLabel duration;
    JLabel genre;
    JLabel image;
    JTextArea description;
    buttonMaker book;
    buttonMaker back;


    public MovieDetailsPanel(){
        //SET PANEL
        this.setSize(1000,600);
        this.setLayout(new BorderLayout());

        //INITIALIZE
        this.title = new JLabel();
        this.duration = new JLabel();
        this.genre = new JLabel();
        this.image = new JLabel("IMAGE");
        this.description = new JTextArea();
        this.book = new buttonMaker("Book",255,87,15,"dontKnowYet");
        this.back = new buttonMaker("Back",255,87,15,"dontKnowYet");

        //BOUNDS
        this.title.setSize(200,50);
        this.duration.setSize(100,25);
        this.genre.setSize(100,25);
        this.description.setSize(200,200);
        this.image.setSize(300,300);

        //ACTION LISTENER

        //PANELS FOR COMPONENTS
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.setPreferredSize(new Dimension(1000,100));
        northPanel.add(this.title);
        northPanel.add(this.genre);
        northPanel.add(this.duration);

        JPanel westPanel = new JPanel(new FlowLayout());
        westPanel.setPreferredSize(new Dimension(200,800));
        westPanel.add(this.image);

        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.setPreferredSize(new Dimension(1000,100));
        southPanel.add(this.book);
        southPanel.add(this.back);

        JPanel eastPanel = new JPanel(null);
        eastPanel.setPreferredSize(new Dimension(200,800));


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
