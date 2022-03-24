import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class buttonMaker extends JButton {
    public int r, g, b;
    public final Font font = new Font("sanserif", Font.BOLD, 30);
    public String endpoint;
    public buttonMaker(String btnName, int r, int g, int b, String endpoint){
        this.setForeground(Color.WHITE);
        this.setFont(font); //blalba
        this.r = r;
        this.g = g;
        this.b = b;
        this.endpoint = endpoint;
        this.setText(btnName);
        this.setBackground(new Color(r, g, b));
        this.addMouseListener(mouseListener(this.r, this.g, this.b));
        this.setVisible(true);
        this.setFocusPainted(false);
    }

    public MouseListener mouseListener(int r, int g, int b){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //NOT IN USE, MAYBE?
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setContentAreaFilled(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setContentAreaFilled(true);
                setBackground(new Color(r,g,b));
                // TODO: 2022-03-23 URL HTTP CONNECTION SENDER
                //USE ENDPOINT VARIABLE TO SEND THE ENDPOINT
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //NOT IN USE
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //NOT IN USE
            }
        };
    }
}
