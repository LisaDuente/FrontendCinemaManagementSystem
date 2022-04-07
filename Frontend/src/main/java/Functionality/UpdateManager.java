package Functionality;

import Classes.Employee;
import Classes.Movie;
import com.google.gson.Gson;

import java.util.Locale;

public class UpdateManager {
    private Movie movie;
    private Employee employee;
    private ConnectionManager connection;
    private Gson gson;

    public UpdateManager(){
        this.connection = new ConnectionManager();
        this.gson = new Gson();
        this.movie = new Movie();
    }

    public Movie updateMovie(String parameterString){
        this.movie = new Movie();
        String[] parameters = parameterString.split(",");
        String[] checkID = parameters[0].split(":");

        //check if we have an ID to update the movie
        if(checkID[0].equals("ID")) {
            if(Character.isDigit(checkID[1].charAt(0))){

                //load the movie that needs an update
                String movieToUpdate = connection.sendUrlToDownloadMovieById(Integer.parseInt(checkID[1]));
                this.movie = gson.fromJson(movieToUpdate, Movie.class);

                //check all the parameters to make a flexible update
                for (int i = 1; i < parameters.length; i++) {
                    String[] temp = parameters[i].split(":");
                    switch (temp[0]) {
                        case "Name":
                            this.movie.setName(temp[1]);
                            break;
                        case "Genre":
                            this.movie.setGenre(temp[1]);
                            break;
                        case "Duration":
                            this.movie.setDuration(temp[1]);
                            break;
                        case "Short Description":
                            this.movie.setShortDescription(temp[1]);
                            break;
                        case "Description":
                            this.movie.setMovieDescription(temp[1]);
                            break;
                        case "Picture Path":
                            this.movie.setPicturePath(temp[1]);
                            break;
                        case "Available":
                            this.movie.setAvailable(Boolean.parseBoolean(temp[1].toLowerCase(Locale.ROOT)));
                            break;
                    }
                }
            }else{
                this.movie = null;
            }
        }else{
            this.movie = null;
        }
        return this.movie;
    }

    public Employee updateEmployee(String parameterString){
        Employee employee = new Employee();
        String[] parameters = parameterString.split(",");
        String[] checkID = parameters[0].split(":");

        if(checkID[0].equals("ID")) {
            if(Character.isDigit(checkID[1].charAt(0))){

                String employeeToUpdate = connection.sendURLToDownloadOneEmployeeByID(Integer.parseInt(checkID[1]));
                employee = gson.fromJson(employeeToUpdate, Employee.class);

                for (int i = 1; i < parameters.length; i++) {
                    String[] temp = parameters[i].split(":");
                    switch (temp[0]) {
                        case "Name":
                            employee.setEmployeeName(temp[1]);
                            break;
                        case "Mobil":
                            employee.setEmployeeTel(temp[1]);
                            break;
                        case "E-Mail":
                            employee.setEmployeeEmail(temp[1]);
                            break;
                    }
                }
            }else{
                employee = null;
            }
        }else{
            employee = null;
        }
        return employee;
    }
}
