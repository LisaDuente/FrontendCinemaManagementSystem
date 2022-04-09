package Panels;

import Classes.Employee;
import Classes.Movie;

import Classes.Salon;

import Classes.MovieSchedule;
import Classes.MovieScheduleView;

import Functionality.ConnectionManager;
import Functionality.UpdateManager;
import Functionality.buttonMaker;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AdminPageMovieSchedule extends JPanel {
    private UpdateManager updater;
    private ConnectionManager connect = new ConnectionManager();
    private Gson gson = new Gson();
    private JScrollPane scrollList;
    private JList<String> list;
    private DefaultListModel<String> model;

    private JLabel info;

    private JLabel movieIdText;
    private JLabel movieNameText;
    private JLabel movieTimeText;
    private JLabel movieDateText;
    private JLabel movieSalonIDText;
    private JLabel cinemaIDText;

    private JTextField movieID;
    private JTextField movieName;
    private JTextField movieTime;
    private JTextField movieDate;
    private JTextField movieSalonID;
    private JTextField cinemaID;

    private buttonMaker back;
    private buttonMaker enter;
    private buttonMaker delete;
    private buttonMaker update;

    private Color colorBack = new Color(81, 24, 69);
    private Color colorMiddle = new Color(199, 0, 57);

    public AdminPageMovieSchedule(){
        this.setSize(1000,800);
        this.setLayout(null);
        this.setBackground(colorBack);

        this.model = new DefaultListModel<>();
        this.list = new JList<>(model);
        this.list.setFont(new Font("sanserif", Font.BOLD, 15));
        this.list.setForeground(Color.white);
        this.list.setBackground(colorMiddle);
        this.scrollList = new JScrollPane(list);

        this.scrollList.createHorizontalScrollBar();
        this.scrollList.setBounds(50,50,900,200);

        this.info = new JLabel("Choose an entry from the list to delete!");
        this.info.setFont(new Font("sanserif", Font.BOLD, 15));
        this.info.setForeground(Color.WHITE);
        this.info.setBounds(50,10,500,25);
        this.add(this.info);


        //CONTROL PANEL

        JPanel controlPanel = new JPanel(new GridLayout(8,2,3,0));
        controlPanel.setBounds(50,255,900,400);
        controlPanel.setBackground(colorBack);

        this.movieIdText = new JLabel("movieID:");
        this.movieIdText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieIdText.setForeground(Color.white);
        this.movieNameText = new JLabel("Name:");
        this.movieNameText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieNameText.setForeground(Color.white);
        this.movieTimeText = new JLabel("Time:");
        this.movieTimeText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieTimeText.setForeground(Color.white);
        this.movieDateText = new JLabel("Date:");
        this.movieDateText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieDateText.setForeground(Color.white);
        this.movieSalonIDText = new JLabel("SalonID:");
        this.movieSalonIDText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieSalonIDText.setForeground(Color.white);
        this.cinemaIDText = new JLabel("CinemaID:");
        this.cinemaIDText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.cinemaIDText.setForeground(Color.white);

        this.movieID = new JTextField();
        this.movieID.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieName = new JTextField();
        this.movieName.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieTime = new JTextField();
        this.movieTime.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieDate = new JTextField();
        this.movieDate.setFont(new Font("sanserif", Font.BOLD, 15));
        this.movieSalonID = new JTextField();
        this.movieSalonID.setFont(new Font("sanserif", Font.BOLD, 15));
        this.cinemaID = new JTextField();
        this.cinemaID.setFont(new Font("sanserif", Font.BOLD, 15));

        //TODO: take away name?
        controlPanel.add(this.movieIdText);
        controlPanel.add(this.movieID);
        controlPanel.add(this.movieNameText);
        controlPanel.add(this.movieName);
        controlPanel.add(this.movieTimeText);
        controlPanel.add(this.movieTime);
        controlPanel.add(this.movieDateText);
        controlPanel.add(this.movieDate);
        controlPanel.add(this.movieSalonIDText);
        controlPanel.add(this.movieSalonID);
        controlPanel.add(this.cinemaIDText);
        controlPanel.add(this.cinemaID);

        controlPanel.setVisible(true);
//BUTTON PANEL
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.setBounds(0,700,1000,100);
        buttons.setBackground(colorBack);

        this.back = new buttonMaker("Back",255,87,15, "/blalba");
        this.enter = new buttonMaker("Add",154 ,205,50, "/blalba");
        this.delete = new buttonMaker("Delete",220,20,60, "/blalba");
        this.update = new buttonMaker("Update",135,206,235,"/DontKnowYet");

        //TODO: salon is null, 500 error, cant add movie_schedule yet

        this.enter.addActionListener((e -> {
            // Toros
                String movieID = encodeToURL(this.movieID.getText());
                String movieName = encodeToURL(this.movieName.getText());
                String movieTime = encodeToURL(this.movieTime.getText());
                String movieDate = encodeToURL(this.movieDate.getText());
                String movieSalonID = encodeToURL(this.movieSalonID.getText());
                String cinemaID = encodeToURL(this.cinemaID.getText());

                String salonString = connect.sendURLToDownloadOneSalonByID(Integer.parseInt(movieSalonID), Integer.parseInt(cinemaID));
                System.out.println(salonString);
                Salon salon = gson.fromJson(salonString, Salon.class);

                //Below row is NOT finished. We need to solve the problem with seatsData Array.
                connect.sendURLToCreateMovieSchedule(Integer.parseInt(movieSalonID), movieTime, movieDate, movieID, Arrays.toString(salon.getDefultSalonSeats()));
                fillList();
                clearAllText();
        }));

        this.delete.addActionListener((e) -> {
            String movieScheduleSTring = this.list.getSelectedValue();
            String[] temp = movieScheduleSTring.split(",");
            //we can erase things with salon, date and time
            String temp2 = encodeToURL(temp[2]);
            String temp3 = encodeToURL(temp[3]);
            connect.sendUrlToDeleteMovieScheduleWithAllParameters(Integer.parseInt(temp[1]),temp2,temp3);
            fillList();
        });

        this.update.addActionListener((e)->{
            //get the strings from the specific fields, feed them to the backend
        });

        buttons.add(this.enter);
        buttons.add(this.delete);
        //buttons.add(this.update);
        buttons.add(this.back);
        buttons.setVisible(true);


        this.add(this.scrollList,BorderLayout.NORTH);
        this.add(buttons, BorderLayout.SOUTH);
        this.add(controlPanel,BorderLayout.CENTER);

        this.setVisible(true);

    }

    public void fillList(){
        this.model.clear();
        String movieScheduleString = connect.sendUrlToDownloadWholeMovieScheduleView();
        MovieScheduleView[] schedule = gson.fromJson(movieScheduleString, MovieScheduleView[].class);
        for(int i = 0; i<schedule.length;i++){
            //only shows the name of the movie
            this.model.add(i, String.valueOf(schedule[i].getMovie())+","+schedule[i].getSalon()+","
                    +schedule[i].getTime()+","+schedule[i].getDate());
        }

    }

    /*public void updateFunctionality(){
        String input = "";
        if (!this.movieID.getText().equals("")) {
            input = this.movieIdText.getText()+this.movieID.getText()+",";
        }
        if (!this.movieName.getText().equals("")) {
            input = input + this.movieNameText.getText()+this.movieName.getText()+",";
        }
        if (!this.movieTime.getText().equals("")) {
            input = input + this.movieTimeText.getText()+this.movieTime.getText()+",";
        }
        if (!this.movieDate.getText().equals("")) {
            input = input + this.movieDateText.getText()+this.movieDate.getText()+",";
        }
        if (!this.movieSalonID.getText().equals("")) {
            input = input + this.movieSalonIDText.getText()+this.movieSalonID.getText()+",";
        }


        input =  input.replace(" ","+");
        input = input.replace("@","%40");
        input = input.replace(".","%2E");
        MovieSchedule movieSchedule = this.updater.updateMovieSchedule(input);
        //this.connect.sendUrlToUpdateMovieSchedule(employee);
        fillList();
    }

     */

    public buttonMaker getBack() {
        return back;
    }


    public void clearAllText(){
        this.movieID.setText("");
        this.movieName.setText("");
        this.movieTime.setText("");
        this.movieDate.setText("");
        this.movieSalonID.setText("");
    }

    public String encodeToURL(String inputString) {
        String encodedString = URLEncoder.encode(inputString, StandardCharsets.UTF_8);
        return encodedString;
    }


}
