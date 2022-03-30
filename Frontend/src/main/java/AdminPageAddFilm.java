import javax.swing.*;
import java.awt.*;

public class AdminPageAddFilm extends JPanel {
    private JScrollPane scrollList;
    private JList<String> list;
    private DefaultListModel<String> model;

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


    private Color colorBack = new Color(81, 24, 69);
    private Color colorMiddle = new Color(199, 0, 57);

    public AdminPageAddFilm(){
        this.setSize(1000,800);
        this.setLayout(new BorderLayout());
        this.setBackground(colorBack);

        model = new DefaultListModel<>();
        //add to the model everything we want to display
        list = new JList<>(model);
        this.scrollList = new JScrollPane(list);
    }
}
