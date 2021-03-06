package Panels;

import Functionality.UpdateManager;
import Functionality.buttonMaker;
import com.google.gson.Gson;
import Functionality.ConnectionManager;
import Classes.Employee;

import javax.swing.*;
import java.awt.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class AdminPageAddEmployee extends JPanel {
    private UpdateManager updater = new UpdateManager();
    private ConnectionManager connect = new ConnectionManager();
    private Gson gson = new Gson();
    private JScrollPane scrollList;
    private JList<String> list;
    private DefaultListModel<String> model;

    private JLabel info;

    private JLabel employeeIdText;
    private JLabel employeeNameText;
    private JLabel employeeTelText;
    private JLabel employeeMailText;

    private JTextField employeeID;
    private JTextField employeeName;
    private JTextField employeeTel;
    private JTextField employeeMail;

    private buttonMaker back;
    private buttonMaker enter;
    private buttonMaker delete;
    private buttonMaker update;

    private Color colorBack = new Color(81, 24, 69);
    private Color colorMiddle = new Color(199, 0, 57);

    public AdminPageAddEmployee(){
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

        this.info = new JLabel("Choose an employee from list to delete!");
        this.info.setFont(new Font("sanserif", Font.BOLD, 15));
        this.info.setForeground(Color.WHITE);
        this.info.setBounds(50,10,500,25);
        this.add(this.info);


        //CONTROL PANEL

        JPanel controlPanel = new JPanel(new GridLayout(8,2,3,0));
        controlPanel.setBounds(50,255,900,400);
        controlPanel.setBackground(colorBack);

        this.employeeIdText = new JLabel("ID:");
        this.employeeIdText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.employeeIdText.setForeground(Color.white);
        this.employeeNameText = new JLabel("Name:");
        this.employeeNameText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.employeeNameText.setForeground(Color.white);
        this.employeeTelText = new JLabel("Mobil:");
        this.employeeTelText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.employeeTelText.setForeground(Color.white);
        this.employeeMailText = new JLabel("E-Mail:");
        this.employeeMailText.setFont(new Font("sanserif", Font.BOLD, 15));
        this.employeeMailText.setForeground(Color.white);


        this.employeeID = new JTextField();
        this.employeeID.setFont(new Font("sanserif", Font.BOLD, 15));
        this.employeeName = new JTextField();
        this.employeeName.setFont(new Font("sanserif", Font.BOLD, 15));
        this.employeeTel = new JTextField();
        this.employeeTel.setFont(new Font("sanserif", Font.BOLD, 15));
        this.employeeMail = new JTextField();
        this.employeeMail.setFont(new Font("sanserif", Font.BOLD, 15));


        controlPanel.add(this.employeeIdText);
        controlPanel.add(this.employeeID);
        controlPanel.add(this.employeeNameText);
        controlPanel.add(this.employeeName);
        controlPanel.add(this.employeeTelText);
        controlPanel.add(this.employeeTel);
        controlPanel.add(this.employeeMailText);
        controlPanel.add(this.employeeMail);


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

            // Toros
            //String employeeID = encodeToURL(this.employeeID.getText());
            errorMessageAddButton();
        }));

        this.delete.addActionListener((e) -> {
            String employeeToDelete = this.list.getSelectedValue();
            if(this.list.getSelectedValue() == null){
                JOptionPane.showMessageDialog(null, "Please, delete something");
            } else {
                String[] temp = employeeToDelete.split(",");
                connect.sendURLToDeleteEmployeeByID(Integer.parseInt(temp[0]));
                fillList();
            }
        });

        this.update.addActionListener((e)->{
            errorMessageUpdateButton();
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
        this.model.clear();
        String employeesListAsSTring = connect.sendUrlToDownloadAllEmployees();
        Employee[] employees = gson.fromJson(employeesListAsSTring, Employee[].class);
        for(int i = 0; i<employees.length;i++){
            //only shows the name and id of the employee
            this.model.add(i, String.valueOf(employees[i].getEmployeeID())+","+employees[i].getEmployeeName()+
                    ","+employees[i].getEmployeeTel()+","+employees[i].getEmployeeEmail());
        }

    }

    public void errorMessageAddButton(){
        if(employeeID.getText().equals("") && employeeName.getText().equals("") && employeeTel.getText().equals("") && employeeMail.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please, don't leave textfields empty");
        } else {
            String employeeName = encodeToURL(this.employeeName.getText());
            String employeeTel = encodeToURL(this.employeeTel.getText());
            String employeeMail = encodeToURL(this.employeeMail.getText());

            connect.sendURLToInsertNewEmployee(employeeName, employeeTel, employeeMail);
            fillList();
            clearAllText();

            JOptionPane.showMessageDialog(null, "Sucess!");
        }
    }

    public void errorMessageUpdateButton(){
        if(employeeID.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please select what employee you want to update(SELECT ID!!!)");
        } else{
            updateFunctionality();
            clearAllText();
        }
    }

    public buttonMaker getBack() {
        return back;
    }

    public void updateFunctionality(){
        String input = "";
        if (!this.employeeID.getText().equals("")) {
            input = this.employeeIdText.getText()+this.employeeID.getText()+",";
        }
        if (!this.employeeName.getText().equals("")) {
            input = input + this.employeeNameText.getText()+this.employeeName.getText()+",";
        }
        if (!this.employeeTel.getText().equals("")) {
            input = input + this.employeeTelText.getText()+this.employeeTel.getText()+",";
        }
        if (!this.employeeMail.getText().equals("")) {
            input = input + this.employeeMailText.getText()+this.employeeMail.getText()+",";
        }

        input =  input.replace(" ","+");
        input = input.replace("@","%40");
        input = input.replace(".","%2E");
        Employee employee = updater.updateEmployee(input);
        employee.changeSpecialCharacters();
        connect.sendUrlToUpdateEmployee(employee);
        fillList();
    }

    public void clearAllText(){
        this.employeeTel.setText("");
        this.employeeID.setText("");
        this.employeeName.setText("");
        this.employeeMail.setText("");
    }

    public String encodeToURL(String inputString) {
        String encodedString = URLEncoder.encode(inputString, StandardCharsets.UTF_8);
        return encodedString;
    }
}

