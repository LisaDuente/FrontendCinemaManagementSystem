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
    JPanel footerPanel;

    public startPagePanel(){
        this.setPreferredSize(new Dimension(1000,1000));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(81, 24, 69));
        this.add(navPanelSetup(), BorderLayout.NORTH);
        this.setVisible(true);
    }

    // TODO: 2022-03-23 ADD ACTION LISTENERS TO BUTTONS THAT SENDS A URL (HTTPCONNECTION)
    public JPanel navPanelSetup(){
        this.navPanel = new JPanel();
        this.navPanel.setLayout(null);
        this.navPanel.setPreferredSize(new Dimension(1000,100));
        this.navPanel.setBackground(new Color(144, 12, 63));
        JButton home = new JButton("home");
        home.setBounds(150, 15, 100,70);
        JButton aboutUs = new JButton("about us");
        aboutUs.setBounds(300, 15, 100,70);
        JButton contact = new JButton("contact");
        contact.setBounds(450,15,100,70);
        ImageIcon imageIcon = new ImageIcon("src/main/java/testlogo.png");
        Image image = imageIcon.getImage();
        Image finalImage = image.getScaledInstance(100,70, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(finalImage);
        JLabel logo = new JLabel(imageIcon);
        logo.setBounds(10, 15, 100,70);
        this.navPanel.add(logo);
        this.navPanel.add(home);
        this.navPanel.add(aboutUs);
        this.navPanel.add(contact);
        this.navPanel.setVisible(true);
        return this.navPanel;
    } //NAVIGATION BAR

    public static void main(String[] args) {
        JFrame test = new JFrame("test");
        test.setSize(1000,1000);
        test.setLayout(new BorderLayout());
        startPagePanel startPagePanel = new startPagePanel();
        test.add(startPagePanel, BorderLayout.CENTER);
        test.setVisible(true);
    }
}
