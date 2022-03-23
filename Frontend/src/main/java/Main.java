import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String,Object> movie1 = new HashMap<>();
        movie1.put("name", "The Batman");
        movie1.put("genre", "Action");
        movie1.put("duration", "120 minutes");
        movie1.put("shortDescription","Batman beats up the Riddler");
        movie1.put("isAvailable", true);

        Map<String,Object> movie2 = new HashMap<>();
        movie2.put("name", "Titanic (uncensored version)");
        movie2.put("genre", "Extreme Romance/Horror");
        movie2.put("duration", "380 minutes");
        movie2.put("shortDescription", "falling in love and then drowning tragically");
        movie2.put("isAvailable", true);

        Map<String,Object> movie3 = new HashMap<>();
        movie3.put("name", "Pokemon - The Movie");
        movie3.put("genre", "Animation");
        movie3.put("duration", "187 minutes");
        movie3.put("shortDescription","Ash an Pikachu become best friends");
        movie3.put("isAvailable", false);

        ArrayList<Map<String,Object>> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        JFrame test = new JFrame();
        test.setSize(700,700);

        test.setLayout(new BorderLayout());

        FilmListScrollPane ourPanels = new FilmListScrollPane(movieList);
        test.getContentPane().add(ourPanels,BorderLayout.CENTER);
        test.setVisible(true);
    }
}
