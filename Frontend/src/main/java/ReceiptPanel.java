import javax.swing.*;
import java.awt.*;

public class ReceiptPanel extends JPanel{
    //TODO: something that holds all info from the booking page?

    private JLabel headMessage;
    private JLabel reservationID;
    private JLabel seatNumber;
    private JLabel salonID;

    private buttonMaker back;
    private buttonMaker close;

    private Color colorBack = new Color(81, 24, 69);
    private Color colorMiddle = new Color(199, 0, 57);

    public ReceiptPanel(){
    //INITIALIZE PANEL
        this.setSize(1000,800);
        this.setLayout(new BorderLayout());
        this.setBackground(colorBack);

    //INITIALIZE COMPONENTS
        this.headMessage = new JLabel("Thank you for booking!");
        this.headMessage.setFont(new Font("sanserif", Font.BOLD, 40));
        this.headMessage.setForeground(Color.white);
        this.headMessage.setBounds(300,50,500,100);

        this.reservationID = new JLabel("RESERVATIONID");
        this.reservationID.setFont(new Font("sanserif", Font.BOLD, 25));
        this.reservationID.setForeground(colorMiddle);
        this.reservationID.setBounds(300,50,500,100);

        this.seatNumber = new JLabel("SEATS");
        this.seatNumber.setFont(new Font("sanserif", Font.BOLD, 25));
        this.seatNumber.setForeground(Color.white);
        this.seatNumber.setBounds(300,100,500,100);

        this.salonID = new JLabel("SALONID");
        this.salonID.setFont(new Font("sanserif", Font.BOLD, 25));
        this.salonID.setForeground(Color.white);
        this.salonID.setBounds(300,150,500,100);

        this.back = new buttonMaker("Back",255, 87, 15, "/blabla");
        this.close =  new buttonMaker("Close",255, 87, 15, "/blabla");


    //SET INNER PANELS
        JPanel north = new JPanel(null);
        north.setPreferredSize(new Dimension(1000,200));
        north.setBackground(colorBack);
        north.add(this.headMessage);
        north.setVisible(true);

        JPanel central = new JPanel(null);
        central.setBackground(colorBack);
        central.add(this.reservationID);
        central.add(this.seatNumber);
        central.add(this.salonID);
        central.setVisible(true);

        JPanel south = new JPanel(new FlowLayout());
        south.setPreferredSize(new Dimension(1000,200));
        south.setBackground(colorBack);
        south.add(this.back);
        south.add(this.close);
        south.setVisible(true);

        //ADD TO PANEL
        this.add(north,BorderLayout.NORTH);
        this.add(central,BorderLayout.CENTER);
        this.add(south,BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public buttonMaker getBack() {
        return back;
    }

    public buttonMaker getClose() {
        return close;
    }

    public JLabel getReservationID() {
        return reservationID;
    }

    public JLabel getSalonID() {
        return salonID;
    }

    public JLabel getSeatNumber() {
        return seatNumber;
    }

    //TODO: update method to set the Labes to the right reservation number, seat and so on

    public void updatePanel(String seats, String reservationID, String salonID){
        this.seatNumber.setText(seats);
        this.reservationID.setText(reservationID);
        this.salonID.setText(salonID);
    }
}
