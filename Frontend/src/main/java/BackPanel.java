import javax.swing.*;
import java.awt.*;

public class BackPanel extends JPanel {
    JButton backToFront;
    String state = "start";
    public BackPanel() {
        this.setSize(700, 200);
        this.setLayout(new BorderLayout());


        this.backToFront = new JButton("Back");
        this.backToFront.setSize(100, 25);
        this.add(backToFront, BorderLayout.EAST);
    }

    public void setState(String state) {
        this.state = state;
    }

    public JButton getBackToFront() {
        return backToFront;
    }
}
