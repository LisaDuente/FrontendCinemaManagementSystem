import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AdminPage extends JPanel {
    private buttonMaker movie;
    private buttonMaker staff;
    private buttonMaker movieSchedule;
    private buttonMaker staffSchedule;
    private JLabel instructions;
    private JPasswordField passwordText;
    private buttonMaker enter;

    private Color colorBack = new Color(81, 24, 69);
    private Color colorMiddle = new Color(199, 0, 57);

    private char[] password = new char[]{'1','2','3','c','i','n','e','m','a'};

    public AdminPage(){
    //INITIALIZE PANEL
        this.setSize(1000,800);
        this.setLayout(new BorderLayout());
        this.setBackground(colorBack);

    //INITIALIZE BUTTONS
        this.movie = new buttonMaker("Movie", 255,87,15, "/blalba");
        this.movie.setBounds(100,100,200,100);
        this.movie.setEnabled(false);

        this.staff = new buttonMaker("Staff", 255,87,15, "/blalba");
        this.staff.setBounds(400,100,200,100);
        this.staff.setEnabled(false);

        this.movieSchedule = new buttonMaker("Movie Schedule", 255,87,15, "/blalba");
        this.movieSchedule.setBounds(100,200,200,100);
        this.movieSchedule.setEnabled(false);

        this.staffSchedule = new buttonMaker("Staff Schedule", 255,87,15, "/blalba");
        this.staffSchedule.setBounds(400,200,200,100);
        this.staffSchedule.setEnabled(false);

        this.enter = new buttonMaker("Enter", 255,87,15, "/blalba");
        this.enter.setBounds(525,190,200,50);

    //ACTION LISTENERS
        this.enter.addActionListener((e)->{
            if(Arrays.equals(passwordText.getPassword(), this.password)){
                this.movie.setEnabled(true);
                this.staff.setEnabled(true);
                this.movieSchedule.setEnabled(true);
                this.staffSchedule.setEnabled(true);
            }
        });

    //INITIALIZE PASSWORD
        this.instructions = new JLabel("Please enter your password:");
        this.instructions.setBounds(300,100,300,100);
        this.instructions.setFont(new Font("sanserif", Font.BOLD, 15));
        this.instructions.setForeground(Color.white);
        this.passwordText = new JPasswordField();
        this.passwordText.setBounds(300,200,200,25);

    //INNER PANEL
        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(1000,200));
        buttons.setBackground(colorMiddle);
        buttons.add(this.movie);
        buttons.add(this.movieSchedule);
        buttons.add(this.staff);
        buttons.add(this.staffSchedule);
        buttons.setVisible(true);

        JPanel password = new JPanel(null);
        password.setPreferredSize(new Dimension(1000,200));
        password.setBackground(colorBack);
        password.add(this.enter);
        password.add(this.instructions);
        password.add(this.passwordText);
        password.setVisible(true);


    //ADD
        this.add(buttons,BorderLayout.NORTH);
        this.add(password, BorderLayout.CENTER);

        this.setVisible(true);


    }
}
