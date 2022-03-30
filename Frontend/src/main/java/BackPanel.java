import javax.swing.*;
import java.awt.*;

public class BackPanel extends JPanel {
    private buttonMaker backToFront;
    private String state = "start";

    public BackPanel() {
        this.setSize(1000, 200);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(81, 24, 69));


        this.backToFront = new buttonMaker("Back",255,87,15,"dontKnowYet");
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
