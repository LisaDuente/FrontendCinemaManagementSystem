package Panels;

import Classes.Movie;
import Classes.MovieSchedule;
import Classes.Salon;
import Functionality.buttonMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import static java.awt.Color.black;
import static java.awt.Color.white;

//ERKAN
public class MovieBookingPage extends JPanel {
    JPanel mainpanel;
    JPanel nav;
    JPanel nav2;
    JPanel booking;
    JPanel info;
    JPanel confirmationPanel;
    JPanel footer;

    JLabel movieName;
    JLabel dateNtime;
    JLabel bookingTITLE;
    JLabel time;
    JLabel title;
    JLabel seatsLabel;
    JLabel seatRow;
    JLabel seatsCol;
    JLabel salonNumLabel;

    JButton continueBtn;
    JButton changeSeatBtn;
    String seatsString = "";
    int tempFinalI;
    int tempFinalJ;
    int seatsSelectedCount = 2;
    boolean isBookable = false;
    int[][]tempArray;
    int[]seatsCache = new int[seatsSelectedCount];
    int grid_size = 50;
    public JTextField[][] seats;
    JTextField temp;
    MovieSchedule movieSchedule;
    Salon salon;
    Movie movie;
    private buttonMaker backBtn;


    public MovieBookingPage(){
        this.setSize(1000,1000);
        this.setLayout(new BorderLayout());
        this.movieSchedule = new MovieSchedule("1", "1", "1", 1, "[[1,1],[1,1]]", true);
        this.salon = new Salon(1, 1, 2, 2);
        this.movie = new Movie(1, "dull", "dull", "dull", "dull", "dull", "dull",true);
        this.tempArray = movieSchedule.getSeatOfArrayForMovie();
        this.seats = new JTextField[salon.getSalonRow()][salon.getSalonCol()];

        for (int i = 0; i < salon.getSalonRow(); i++){
            for (int j = 0; j < salon.getSalonCol(); j++){
                temp = new JTextField();
                temp.setText(Integer.toString(tempArray[i][j]));
                if (!Objects.equals(temp.getText(), "0")){
                    temp.setEditable(false);
                    temp.setFocusable(false);
                    temp.setBackground(black);
                    temp.setForeground(black);
                    temp.setHorizontalAlignment(SwingConstants.CENTER);
                }
                temp.setFont(new Font("sanserif", Font.BOLD, 50));
                temp.setEditable(false);
                temp.setPreferredSize(new Dimension(30,30));
                temp.setForeground(Color.white);
                temp.setBackground(new Color(255, 87, 51));
                temp.setHorizontalAlignment(SwingConstants.CENTER);
                seats[i][j] = temp;
            }
        }
        this.add(mainpanel(), BorderLayout.CENTER);
        this.setVisible(true);
    }



    public JPanel  mainpanel(){
        mainpanel = new JPanel();
        mainpanel.setPreferredSize(new Dimension(1000,1000));
        mainpanel.setLayout(new BorderLayout());
        mainpanel.setBackground(new Color(81, 24, 69));
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
        time = new JLabel();
        time.setText("time:  "+ movieSchedule.getMovieTime() + " | " + "date:  " + movieSchedule.getMovieDate());
        time.setForeground(Color.white);
        time.setFont(new Font("sanserif", Font.BOLD, 25));
        title = new JLabel("BOOKING:  " + movie.getName() );
        title.setFont(new Font("sanserif", Font.BOLD, 25));
        title.setForeground(Color.white);
        nav.setPreferredSize(new Dimension(1000,100));
        title.setBounds(50, 0, 500, 100);
        time.setBounds(600, 0, 500, 100);
        nav.setBackground(new Color(144, 12, 63));
        nav.add(title);
        nav.add(time);
        nav.setVisible(true);
        return nav;
    }
    public JPanel booking(){
        booking = new JPanel();
        booking.setBackground(new Color(81, 24, 69));

        for (int i = 0; i < seats.length; i++) {
            JPanel temp = new JPanel();
            temp.setPreferredSize(new Dimension(700, salon.getSalonRow() * grid_size));
            temp.setBackground(new Color(81, 24, 69));
            temp.setLayout(new GridLayout(1, salon.getSalonCol(), 10, 12));
            for (int j = 0; j < salon.getSalonCol(); j++) {
                JTextField textField = new JTextField();
                textField.setEditable(false);
                textField = seats[i][j];
                if (textField.getText().equals("1")){
                    textField.setText("X");
                    textField.setBackground(black);
                    textField.setForeground(black);
                }else{
                    textField.setBackground(new Color(255, 87, 51));
                    textField.setForeground(Color.white);
                    textField.setText("✓");
                }
                int finalI = i;
                int finalJ = j;
                JTextField finalTextField = textField;
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
                            if (finalTextField.getText().equals("X")){
                                finalTextField.setBackground(black);
                                finalTextField.setForeground(black);
                            }

                            if (!finalTextField.getText().equals("X") && isValid(finalI, finalJ, seats)){
                                for (int i = 0; i < seatsSelectedCount; i++){
                                    seats[finalI][finalJ+i].setBackground(new Color(0,100,0));
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
                            if (finalTextField.getText().equals("X")){
                                finalTextField.setBackground(black);
                                finalTextField.setForeground(black);
                            }

                            if (!finalTextField.getText().equals("X") && isValid(finalI, finalJ, seats)){
                                for (int i = 0; i < seatsSelectedCount; i++){
                                    seats[finalI][finalJ+i].setBackground(white);
                                }
                            }else{
                                for (int j = 0; j < seatsSelectedCount; j++) {
                                    if (finalJ == salon.getSalonCol()-1){
                                        if (seats[finalI][finalJ].getText().equals("X")){
                                            seats[finalI][finalJ].setBackground(black);
                                            isBookable = false;
                                        }else{
                                            seats[finalI][finalJ].setBackground(white);
                                            isBookable = false;
                                        }
                                    }else{
                                        if (seats[finalI][finalJ+j].getText().equals("X")){
                                            seats[finalI][finalJ + j].setBackground(black);
                                            isBookable = false;
                                        }else{
                                            seats[finalI][finalJ + j].setBackground(white);
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
        empty.setForeground(white);
        empty.setFont(new Font("sanserif", Font.BOLD, 25));
        empty.setHorizontalAlignment(0);
        JPanel buttonPanel = new JPanel();
        JLabel amountOfSeatsToBook = new JLabel();
        amountOfSeatsToBook.setForeground(white);
        JLabel infoSeats = new JLabel("<HTML><u>Seats To Book </u> </HTML>");
        infoSeats.setForeground(white);
        JLabel title = new JLabel("<HTML><u> MOVIE </u> </HTML>");
        title.setForeground(white);
        title.setFont(new Font("sanserif", Font.BOLD, 40));
        title.setHorizontalAlignment(0);
        movieName = new JLabel();
        movieName.setText(movie.getName());
        movieName.setForeground(white);
        movieName.setFont(new Font("sanserif", Font.BOLD, 25));
        movieName.setHorizontalAlignment(SwingConstants.CENTER);

        this.backBtn = new buttonMaker("Back",255,87,15,"dontKnowYet");

        infoSeatsConfirmationPanel(infoSeats, 30);
        buttonConfirmationPanel(buttonPanel, amountOfSeatsToBook);
        amountOfSeatsToBookConfirmationPanel(amountOfSeatsToBook);

        info = new JPanel();
        info.setPreferredSize(new Dimension(200, 1000));
        info.setLayout(new GridLayout(10,1));
        info.setBackground(new Color(144, 12, 63));
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
        up.setBackground(new Color(255, 87, 51));
        up.setForeground(white);
        JButton down = new JButton("<");
        down.setForeground(white);
        down.setBackground(new Color(255, 87, 51));
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
        nav2.setBackground(new Color(144, 12, 63));
        bookingTITLE = new JLabel("Booking:  " + movie.getName());
        bookingTITLE.setFont(new Font("sanserif", Font.BOLD, 35));
        bookingTITLE.setForeground(white);
        dateNtime = new JLabel("date: " + movieSchedule.getMovieDate() + " | time: " + movieSchedule.getMovieTime());
        dateNtime.setFont(new Font("sanserif", Font.BOLD, 25));
        dateNtime.setForeground(white);
        bookingTITLE.setBounds(50, 0, 500, 100);
        dateNtime.setBounds(600, 0, 500, 100);
        nav2.add(bookingTITLE);
        nav2.add(dateNtime);
        confirmationPanel.add(nav2, BorderLayout.PAGE_START);
    }

    private void infoDisplayConfirmationPanel() {
        JPanel infoDisplayPanel = new JPanel();
        infoDisplayPanel.setPreferredSize(new Dimension(1000, 900));
        infoDisplayPanel.setBackground(new Color(81, 24, 69));
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

        salonNumLabel = new JLabel();
        salonNumLabel.setForeground(white);
        salonNumLabel.setFont(new Font("sanserif", Font.BOLD, 35));
        salonNumLabel.setText("salon: " + salon.getSalonId());
        salonNumLabel.setBounds(250, 170, 500, 50);

        infoDisplayPanel.add(seatsLabel);
        infoDisplayPanel.add(seatRow);
        infoDisplayPanel.add(seatsCol);
        infoDisplayPanel.add(salonNumLabel);
        confirmationPanel.add(infoDisplayPanel, BorderLayout.CENTER);
    }

    public JPanel footerConfirmationPanel() {
        footer = new JPanel();
        footer.setPreferredSize(new Dimension(1000,200));
        footer.setLayout(new GridLayout(3,6));
        footer.setBackground(new Color(144, 12, 63));
        JLabel e1 = new JLabel();
        JLabel e2 = new JLabel();
        JLabel e3 = new JLabel();
        JLabel e4 = new JLabel();
        JLabel e5 = new JLabel();
        JLabel e6 = new JLabel();
        JLabel e7 = new JLabel();
        continueBtn = new JButton("Do you want to proceed?");
        changeSeatBtn = new JButton("Change seats");
        changeSeatBtn.setForeground(white);
        changeSeatBtn.setBackground(new Color(255, 87, 51));
        changeSeatBtn.setFont(new Font("sanserif", Font.BOLD, 30));
        continueBtn.setForeground(white);
        continueBtn.setBackground(new Color(255, 87, 51));
        continueBtn.setFont(new Font("sanserif", Font.BOLD, 30));
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

    public void update(){
        this.booking.removeAll();
        this.booking.repaint();

        movieName.setText(this.movie.getName());
        time.setText(this.movieSchedule.getMovieTime());
        bookingTITLE.setText("Booking:  " + this.movie.getName());
        dateNtime.setText("date: " + this.movieSchedule.getMovieDate() + " | time: " + this.movieSchedule.getMovieTime());
        time.setText("time:  "+ this.movieSchedule.getMovieTime() + " | " + "date:  " + this.movieSchedule.getMovieDate());
        title.setText("BOOKING:  " + this.movie.getName());

        this.tempArray = movieSchedule.getSeatOfArrayForMovie();
        this.seats = new JTextField[salon.getSalonRow()][salon.getSalonCol()];

        for (int i = 0; i < salon.getSalonRow(); i++){
            for (int j = 0; j < salon.getSalonCol(); j++){
                temp = new JTextField();
                temp.setText(Integer.toString(tempArray[i][j]));
                if (!Objects.equals(temp.getText(), "0")){
                    temp.setEditable(false);
                    temp.setFocusable(false);
                    temp.setBackground(Color.BLACK);
                    temp.setHorizontalAlignment(SwingConstants.CENTER);
                }
                temp.setFont(new Font("sanserif", Font.BOLD, 50));
                temp.setSize(150,100);
                temp.setEditable(false);
                temp.setForeground(Color.red);
                temp.setHorizontalAlignment(SwingConstants.CENTER);
                seats[i][j] = temp;
            }
        }


        for (int i = 0; i < seats.length; i++) {
            JPanel temp = new JPanel();
            temp.setBackground(new Color(81, 24, 69));
            temp.setPreferredSize(new Dimension(700, salon.getSalonRow() * grid_size));
            temp.setLayout(new GridLayout(1, salon.getSalonCol(), 10, 10));
            for (int j = 0; j < salon.getSalonCol(); j++) {
                JTextField textField;
                textField = seats[i][j];
                if (textField.getText().equals("1")){
                    textField.setText("X");
                    textField.setBackground(black);
                    textField.setForeground(black);
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
                                textField.setBackground(black);
                            }

                            if (!textField.getText().equals("X") && isValid(finalI, finalJ, seats)){
                                for (int i = 0; i < seatsSelectedCount; i++){
                                    seats[finalI][finalJ+i].setBackground(new Color(0,100,0));
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
                                textField.setBackground(black);
                            }

                            if (!textField.getText().equals("X") && isValid(finalI, finalJ, seats)){
                                for (int i = 0; i < seatsSelectedCount; i++){
                                    seats[finalI][finalJ+i].setBackground(white);
                                }
                            }else{
                                for (int j = 0; j < seatsSelectedCount; j++) {
                                    if (finalJ == salon.getSalonCol()-1){
                                        if (seats[finalI][finalJ].getText().equals("X")){
                                            seats[finalI][finalJ].setBackground(black);
                                            isBookable = false;
                                        }else{
                                            seats[finalI][finalJ].setBackground(white);
                                            isBookable = false;
                                        }
                                    }else{
                                        if (seats[finalI][finalJ+j].getText().equals("X")){
                                            seats[finalI][finalJ + j].setBackground(black);
                                            isBookable = false;
                                        }else{
                                            seats[finalI][finalJ + j].setBackground(white);
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

    }
}