import javax.swing.*;
import java.awt.*;

public class BackPanel extends JPanel {
    JButton backToFront;
    public BackPanel(FilmListScrollPane scrollPane){
        this.setSize(700,200);
        this.setLayout(new BorderLayout());

        this.backToFront = new JButton("Back");
        this.backToFront.setSize(100,25);
        this.add(backToFront,BorderLayout.EAST);

        this.backToFront.addActionListener((e) -> {this.setVisible(false); scrollPane.setVisible(false);});
        this.setVisible(true);


    }
}
