package Panels;

import Classes.Movie;
import Classes.MovieSchedule;
import Classes.Salon;
import Functionality.ConnectionManager;
import Functionality.buttonMaker;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

//ERKAN
public class MovieBookingPage extends JPanel {
    ConnectionManager connectionManager = new ConnectionManager();
    Gson gson = new Gson();
    JPanel mainpanel;
    JPanel nav;
    JPanel nav2;
    JPanel booking;
    JPanel info;
    JPanel confirmationPanel;
    JPanel footer;

    JLabel title;
    JLabel seatsLabel;
    JLabel seatRow;
    JLabel seatsCol;
    String seatsString = "";
    int tempFinalI;
    int tempFinalJ;
    int seatsSelectedCount = 2;
    boolean isBookable = false;
    int[][]tempArray;
    int[]seatsCache = new int[seatsSelectedCount];
    int grid_size = 50;
    JTextField[][] seats;
    MovieSchedule movieSchedule;
    Salon salon;
    Movie movie;
    private buttonMaker backBtn;

    public MovieBookingPage(){
        this.setSize(1000,1000);
        this.setLayout(new BorderLayout());

        this.add(mainpanel(), BorderLayout.CENTER);

        this.setVisible(true);
    }

    public void initializeBooking() {
        movieSchedule = gson.fromJson(connectionManager.sendUrlToGetMovieSchedule(1,2,"19:50","2022-04-04"), MovieSchedule.class);
        salon = gson.fromJson(connectionManager.sendUrlToGetSalonById(1,1), Salon.class);
        movie = gson.fromJson(connectionManager.sendUrlToDownloadMovieById(2), Movie.class);

        tempArray = movieSchedule.getSeatOfArrayForMovie();
        seats = new JTextField[salon.getSalonRow()][salon.getSalonCol()];

        for (int i = 0; i < salon.getSalonRow(); i++){
            for (int j = 0; j < salon.getSalonCol(); j++){
                JTextField temp = new JTextField();
                temp.setText(Integer.toString(tempArray[i][j]));
                if (!Objects.equals(temp.getText(), "0")){
                    temp.setEditable(false);
                    temp.setFocusable(false);
                    temp.setBackground(Color.orange);
                    temp.setHorizontalAlignment(SwingConstants.CENTER);
                }
                temp.setFont(new Font("sanserif", Font.BOLD, 50));
                temp.setEditable(false);
                temp.setPreferredSize(new Dimension(30,30));
                temp.setForeground(Color.red);
                temp.setHorizontalAlignment(SwingConstants.CENTER);
                seats[i][j] = temp;
            }
        }
    }

    public JPanel  mainpanel(){
        mainpanel = new JPanel();
        mainpanel.setPreferredSize(new Dimension(1000,1000));
        mainpanel.setLayout(new BorderLayout());
        mainpanel.setBackground(Color.black);
        booking();
        nav(movieSchedule);
        info(movie);
        confirmationPanel();
        nav.setBounds(0,0,1000,100);
        booking.setBounds(0,100,1000,900);
        mainpanel.add(nav, BorderLayout.PAGE_START);
        mainpanel.add(booking, BorderLayout.CENTER);
        mainpanel.add(confirmationPanel, BorderLayout.LINE_START);
        mainpanel.add(info, BorderLayout.LINE_END);
        mainpanel.setVisible(true);
        return mainpanel;
    }
    public JPanel nav(MovieSchedule movieSchedule){
        nav = new JPanel();
        nav.setLayout(null);
        JLabel time = new JLabel();
        time.setText("time:  "+ movieSchedule.getMovieTime() + " | " + "date:  " + movieSchedule.getMovieDate());
        time.setForeground(Color.white);
        time.setFont(new Font("sanserif", Font.BOLD, 25));
        title = new JLabel("BOOKING:  " + movie.getName() );
        title.setFont(new Font("sanserif", Font.BOLD, 25));
        title.setForeground(Color.white);
        nav.setPreferredSize(new Dimension(1000,100));
        title.setBounds(50, 0, 500, 100);
        time.setBounds(600, 0, 500, 100);
        nav.setBackground(Color.red);
        nav.add(title);
        nav.add(time);
        nav.setVisible(true);
        return nav;
    }
    public JPanel booking(){
        initializeBooking();
        booking = new JPanel();
        booking.setBackground(Color.gray);

        for (int i = 0; i < seats.length; i++) {
            JPanel temp = new JPanel();
            temp.setPreferredSize(new Dimension(700, salon.getSalonRow() * grid_size));
            temp.setBackground(Color.gray);
            temp.setLayout(new GridLayout(1, salon.getSalonCol(), 10, 12));
            for (int j = 0; j < salon.getSalonCol(); j++) {
                JTextField textField;
                textField = seats[i][j];
                if (textField.getText().equals("1")){
                    textField.setText("X");
                }else{
                    textField.setText("✓");
                }
                int finalI = i;
                int finalJ = j;
                textField.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //NOT IN USE
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (isBookable){
                            nav.setVisible(false);
                            booking.setVisible(false);
                            info.setVisible(false);
                            tempFinalI = finalI;
                            tempFinalJ = finalJ;
                            seatsCache = new int[seatsSelectedCount];
                            if (seatsSelectedCount == 1){
                                seatRow.setText("row:  " + (tempFinalI + 1));
                                seatsCol.setText("seat:  " + (tempFinalJ + 1));
                            }
                            else{
                                for (int i = 0; i < seatsCache.length; i++){
                                    seatsCache[i] = tempFinalJ + i;
                                    int tempInt = seatsCache[i] + 1;
                                    if (i == 0){
                                        seatRow.setText("row:  " + (tempFinalI + 1));
                                        seatsString = seatsString + tempInt;
                                    }else {
                                        seatsString = seatsString + " | " + tempInt;
                                    }
                                }
                                seatsCol.setText("Seats:  " + seatsString);
                            }
                            confirmationPanel.setVisible(true);
                            footer.setVisible(true);
                            nav2.setVisible(true);

                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //NOT IN USE
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        try{
                            if (textField.getText().equals("X")){
                                textField.setBackground(Color.orange);
                            }

                            if (!textField.getText().equals("X") && isValid(finalI, finalJ, seats)){
                                for (int i = 0; i < seatsSelectedCount; i++){
                                    seats[finalI][finalJ+i].setBackground(Color.black);
                                    isBookable = true;
                                }
                            }else{
                                for (int j = 0; j < seatsSelectedCount; j++) {
                                    if (finalJ == salon.getSalonCol()-1){
                                        seats[finalI][finalJ].setBackground(Color.lightGray);
                                        isBookable = false;
                                    }else{
                                        seats[finalI][finalJ+j].setBackground(Color.lightGray);
                                    }
                                }
                            }
                        }catch (IndexOutOfBoundsException ignore){}
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        isBookable = false;
                        try{
                            if (textField.getText().equals("X")){
                                textField.setBackground(Color.orange);
                            }

                            if (!textField.getText().equals("X") && isValid(finalI, finalJ, seats)){
                                for (int i = 0; i < seatsSelectedCount; i++){
                                    seats[finalI][finalJ+i].setBackground(Color.white);
                                }
                            }else{
                                for (int j = 0; j < seatsSelectedCount; j++) {
                                    if (finalJ == salon.getSalonCol()-1){
                                        if (seats[finalI][finalJ].getText().equals("X")){
                                            seats[finalI][finalJ].setBackground(Color.orange);
                                            isBookable = false;
                                        }else{
                                            seats[finalI][finalJ].setBackground(Color.white);
                                            isBookable = false;
                                        }
                                    }else{
                                        if (seats[finalI][finalJ+j].getText().equals("X")){
                                            seats[finalI][finalJ + j].setBackground(Color.orange);
                                            isBookable = false;
                                        }else{
                                            seats[finalI][finalJ + j].setBackground(Color.white);
                                            isBookable = false;
                                        }
                                    }
                                }
                            }
                        }catch (IndexOutOfBoundsException ignore){}

                    }
                });
                temp.add(textField);
            }
            temp.setVisible(true);
            booking.add(temp);
        }
        booking.setVisible(true);
        return booking;
    }
    public JPanel info(Movie movie){
        JLabel empty = new JLabel("______________");
        empty.setFont(new Font("sanserif", Font.BOLD, 25));
        empty.setHorizontalAlignment(0);
        JPanel buttonPanel = new JPanel();
        JLabel amountOfSeatsToBook = new JLabel();
        JLabel infoSeats = new JLabel("<HTML><u>Seats To Book </u> </HTML>");
        JLabel title = new JLabel("<HTML><u> MOVIE </u> </HTML>");
        title.setFont(new Font("sanserif", Font.BOLD, 40));
        title.setHorizontalAlignment(0);
        JLabel movieName = new JLabel();
        movieName.setText(movie.getName());
        movieName.setFont(new Font("sanserif", Font.BOLD, 25));
        movieName.setHorizontalAlignment(SwingConstants.CENTER);

        this.backBtn = new buttonMaker("Back",255,87,15,"dontKnowYet");

        infoSeatsConfirmationPanel(infoSeats, 30);
        buttonConfirmationPanel(buttonPanel, amountOfSeatsToBook);
        amountOfSeatsToBookConfirmationPanel(amountOfSeatsToBook);

        info = new JPanel();
        info.setPreferredSize(new Dimension(200, 1000));
        info.setLayout(new GridLayout(10,1));
        info.setBackground(Color.green);
        info.add(infoSeats);
        info.add(amountOfSeatsToBook);
        info.add(buttonPanel);
        info.add(empty);
        info.add(title);
        info.add(movieName);
        info.add(empty);
        info.add(this.backBtn);

        info.setVisible(true);
        return info;
    }

    private void infoSeatsConfirmationPanel(JLabel infoSeats, int size) {
        infoSeats.setFont(new Font("sanserif", Font.BOLD, size));
        infoSeats.setHorizontalAlignment(SwingConstants.CENTER);
    }
    private void amountOfSeatsToBookConfirmationPanel(JLabel amountOfSeatsToBook) {
        amountOfSeatsToBook.setText(Integer.toString(seatsSelectedCount));
        infoSeatsConfirmationPanel(amountOfSeatsToBook, 50);
    }

    private void buttonConfirmationPanel(JPanel buttonPanel, JLabel amountOfSeatsToBook) {
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.setPreferredSize(new Dimension(200,200));
        JButton up = new JButton(">");
        JButton down = new JButton("<");
        Font buttonFont = new Font("sanserif", Font.BOLD, 50);
        up.setFont(buttonFont);
        down.setFont(buttonFont);
        buttonPanel.add(down);
        buttonPanel.add(up);
        up.addActionListener(e -> {
            if (up.isEnabled() && seatsSelectedCount <= salon.getSalonCol() -1){
                seatsSelectedCount++;
                amountOfSeatsToBook.setText(Integer.toString(seatsSelectedCount));
                seatsCache = new int[seatsSelectedCount];
            }
        });
        down.addActionListener(e -> {
            if (down.isEnabled() && seatsSelectedCount > 1 ){
                seatsSelectedCount--;
                amountOfSeatsToBook.setText(Integer.toString(seatsSelectedCount));
                seatsCache = new int[seatsSelectedCount];
            }
        });
    }

    public JPanel confirmationPanel(){
        confirmationPanel = new JPanel();
        confirmationPanel.setLayout(new BorderLayout());
        confirmationPanel.setPreferredSize(new Dimension(1000,900));
        confirmationPanel.setLayout(new BorderLayout());
        nav2ConfirmationPanel();
        infoDisplayConfirmationPanel();
        footerConfirmationPanel();
        confirmationPanel.setVisible(false);
        return confirmationPanel;
    }

    private void nav2ConfirmationPanel() {
        nav2 = new JPanel();
        nav2.setPreferredSize(new Dimension(1000,100));
        nav2.setLayout(null);
        JLabel bookingTITLE = new JLabel("Booking:  " + movie.getName());
        bookingTITLE.setFont(new Font("sanserif", Font.BOLD, 35));
        JLabel dateNtime = new JLabel("date: " + movieSchedule.getMovieDate() + " | time: " + movieSchedule.getMovieTime());
        dateNtime.setFont(new Font("sanserif", Font.BOLD, 25));
        bookingTITLE.setBounds(50, 0, 500, 100);
        dateNtime.setBounds(600, 0, 500, 100);
        nav2.add(bookingTITLE);
        nav2.add(dateNtime);
        confirmationPanel.add(nav2, BorderLayout.PAGE_START);
    }

    private void infoDisplayConfirmationPanel() {
        JPanel infoDisplayPanel = new JPanel();
        infoDisplayPanel.setPreferredSize(new Dimension(1000, 900));
        infoDisplayPanel.setBackground(Color.green);
        infoDisplayPanel.setLayout(null);
        seatsLabel = new JLabel("Seats: ");
        seatsLabel.setForeground(Color.white);
        seatsLabel.setFont(new Font("sanserif", Font.BOLD, 35));
        seatsLabel.setBounds(100,50,500,50);

        seatRow = new JLabel();
        seatRow.setFont(new Font("sanserif", Font.BOLD, 35));
        seatRow.setForeground(Color.white);
        seatRow.setBounds(250,50,500,50);

        seatsCol = new JLabel();
        seatsCol.setFont(new Font("sanserif", Font.BOLD, 35));
        seatsCol.setForeground(Color.white);
        seatsCol.setBounds(250, 110, 500, 50);

        infoDisplayPanel.add(seatsLabel);
        infoDisplayPanel.add(seatRow);
        infoDisplayPanel.add(seatsCol);
        confirmationPanel.add(infoDisplayPanel, BorderLayout.CENTER);
    }

    public JPanel footerConfirmationPanel() {
        footer = new JPanel();
        footer.setPreferredSize(new Dimension(1000,200));
        footer.setLayout(new GridLayout(3,6));
        JLabel e1 = new JLabel();
        JLabel e2 = new JLabel();
        JLabel e3 = new JLabel();
        JLabel e4 = new JLabel();
        JLabel e5 = new JLabel();
        JLabel e6 = new JLabel();
        JLabel e7 = new JLabel();
        JButton continueBtn = new JButton("Do you want to proceed?");
        JButton changeSeatBtn = new JButton("Change seats");
        changeSeatBtn.addActionListener(e -> {
            footer.setVisible(false);
            nav2.setVisible(false);
            nav.setVisible(true);
            confirmationPanel.setVisible(false);
            seatsString = "";
            info.setVisible(true);
            booking.setVisible(true);
        });
        footer.add(e1);
        footer.add(changeSeatBtn);
        footer.add(e2);
        footer.add(e3);
        footer.add(continueBtn);
        footer.add(e4);
        footer.add(e5);
        footer.add(e6);
        footer.add(e7);
        confirmationPanel.add(footer, BorderLayout.SOUTH);
        return footer;
    }


    public boolean isValid(int row, int col, JTextField[][]array){
        if (col + seatsSelectedCount-1 > salon.getSalonCol()-1 && seatsSelectedCount != 1){
            return false;
        }
        for (int i = 1; i < seatsSelectedCount; i++){
            if (array[row][col+i].getText().equals("X")){
                return false;
            }
        }
        return true;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setMovieSchedule(MovieSchedule movieSchedule) {
        this.movieSchedule = movieSchedule;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public buttonMaker getBackBtn() {
        return backBtn;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        frame.add( new MovieBookingPage());
        frame.setVisible(true);
    }
}