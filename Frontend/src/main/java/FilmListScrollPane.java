import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class FilmListScrollPane extends JScrollPane {
    private ArrayList<Map<String,Object>> movieList;
    private Map<String, Object> currentMovie;

    public FilmListScrollPane(ArrayList<Map<String,Object>> list){
        this.movieList = list;
        this.setBounds(100,100,500,500);

        JPanel borderLayoutPanel = new JPanel();
        this.setViewportView(borderLayoutPanel);
        borderLayoutPanel.setLayout(new BorderLayout(0,0));

        JPanel columnPanel = new JPanel();
        borderLayoutPanel.add(columnPanel,BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0,1,0,1));
        columnPanel.setBackground(Color.DARK_GRAY);


        for(int i = 0; i<movieList.size(); i++){
            this.currentMovie = movieList.get(i);
            if((boolean)this.currentMovie.get("isAvailable")) {

                JPanel rowPanel = new JPanel();
                rowPanel.setPreferredSize(new Dimension(400, 100));
                columnPanel.add(rowPanel);

                JButton book = new JButton("Book");
                book.setBounds(550, 35, 100, 30);
                rowPanel.add(book);

                //change this
                JLabel movieTitle = new JLabel(String.valueOf(this.currentMovie.get("name")));
                movieTitle.setBounds(150, 10, 200, 30);
                rowPanel.add(movieTitle);

                JLabel movieGenre = new JLabel(String.valueOf(this.currentMovie.get("genre")));
                movieGenre.setBounds(150, 35, 200, 30);
                rowPanel.add(movieGenre);

                JLabel movieDuration = new JLabel(String.valueOf(this.currentMovie.get("duration")));
                movieDuration.setBounds(150, 50, 200, 30);
                rowPanel.add(movieDuration);

                JTextArea shortMovieDescription = new JTextArea((String.valueOf(this.currentMovie.get("shortDescription"))));
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
            }
        }
    }



}
