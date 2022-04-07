package Panels;

import Classes.EmployeeWorkplan;
import Classes.Movie;
import Functionality.ConnectionManager;
import Functionality.buttonMaker;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AdminPageEmployeeSchedule extends JPanel {
    private ConnectionManager connect = new ConnectionManager();
    private Gson gson = new Gson();
    private JScrollPane scrollList;
    private JList<String> list;
    private DefaultListModel<String> model;

    private JLabel info;

    private JLabel employeeIdText;
    private JLabel taskIdText;
    private JLabel workstationIdText;
    private JLabel shiftText;

    private JTextField employeeId;
    private JTextField taskId;
    private JTextField workstationId;
    private JTextField shift;

    private buttonMaker back;
    private buttonMaker enter;
    private buttonMaker delete;
    private buttonMaker update;

    private Color colorBack = new Color(81, 24, 69);
    private Color colorMiddle = new Color(199, 0, 57);

    public AdminPageEmployeeSchedule(){
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

        this.info = new JLabel("Choose a shift to delete!");
        this.info.setFont(new Font("sanserif", Font.BOLD, 15));
        this.info.setForeground(Color.WHITE);
        this.info.setBounds(50,10,500,25);
        this.add(this.info);


        //CONTROL PANEL

        JPanel controlPanel = new JPanel(new GridLayout(8,2,3,0));
        controlPanel.setBounds(50,255,900,400);
        controlPanel.setBackground(colorBack);

        this.employeeIdText = new JLabel("Employee ID:");
        this.employeeIdText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.employeeIdText.setForeground(Color.white);
        this.taskIdText = new JLabel("Task ID:");
        this.taskIdText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.taskIdText.setForeground(Color.white);
        this.workstationIdText = new JLabel("Workstation ID:");
        this.workstationIdText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.workstationIdText.setForeground(Color.white);
        this.shiftText = new JLabel("Shift:");
        this.shiftText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.shiftText.setForeground(Color.white);

        this.employeeId = new JTextField();
        this.employeeId.setFont(new Font("sanserif", Font.BOLD, 15));
        this.taskId = new JTextField();
        this.taskId.setFont(new Font("sanserif", Font.BOLD, 15));
        this.workstationId = new JTextField();
        this.workstationId.setFont(new Font("sanserif", Font.BOLD, 15));
        this.shift = new JTextField();
        this.shift.setFont(new Font("sanserif", Font.BOLD, 15));

        controlPanel.add(this.employeeIdText);
        controlPanel.add(this.employeeId);
        controlPanel.add(this.taskIdText);
        controlPanel.add(this.taskId);
        controlPanel.add(this.workstationIdText);
        controlPanel.add(this.workstationId);
        controlPanel.add(this.shiftText);
        controlPanel.add(this.shift);

        controlPanel.setVisible(true);
//BUTTON PANEL
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.setBounds(0,700,1000,100);
        buttons.setBackground(colorBack);

        this.back = new buttonMaker("Back",255,87,15, "/blalba");
        this.enter = new buttonMaker("Add",154 ,205,50, "/blalba");
        this.delete = new buttonMaker("Delete",220,20,60, "/blalba");
        this.update = new buttonMaker("Update",135,206,235,"/DontKnowYet");

        //TODO: Make these next two methods!
        this.enter.addActionListener((e -> {

            this.model.clear();
            this.connect.sendUrlToCreateEmployeeSchedule(Integer.parseInt(employeeId.getText()), Integer.parseInt(taskId.getText()), Integer.parseInt(workstationId.getText()), shift.getText());
            /*String parameterString = "get everything from the textfields," +
                    "clear Textfields if it worked out" +
                    "send a request via connection Manager";

             */
            //System.out.println(this.workstationId.getText());
            fillList();
        }));

        this.delete.addActionListener((e) -> {
            //call the method to delete a movie from our database
            //should also delete the movie in movie schedule!
        });

        this.update.addActionListener((e)->{
            //get the strings from the specific fields, feed them to the backend
        });



        buttons.add(this.enter);
        buttons.add(this.delete);
        buttons.add(this.update);
        buttons.add(this.back);
        buttons.setVisible(true);


        this.add(this.scrollList,BorderLayout.NORTH);
        this.add(buttons, BorderLayout.SOUTH);
        this.add(controlPanel,BorderLayout.CENTER);

        this.setVisible(true);

    }

    public void fillList(){
        //TODO: get in the view for employeeSchedule
        this.model.clear();
        String employeeWorkplanListAsString = connect.sendUrlToDownloadEmployeeWorkplan();
        EmployeeWorkplan[] employeeWorkplan = gson.fromJson(employeeWorkplanListAsString, EmployeeWorkplan[].class);
        for(int i = 0; i<employeeWorkplan.length;i++){
            //shows the employeeWorkplan(our view in database)
            this.model.add(i, String.valueOf(employeeWorkplan[i].getEmployeeName())+", "+employeeWorkplan[i].getWorkstation()+", "+employeeWorkplan[i].getTask()+", "+employeeWorkplan[i].getShift());
        }
    }

    public buttonMaker getBack() {
        return back;
    }

    public static String encodeToURL(String inputString) {
        String encodedString = URLEncoder.encode(inputString, StandardCharsets.UTF_8);
        return encodedString;
    }

}
