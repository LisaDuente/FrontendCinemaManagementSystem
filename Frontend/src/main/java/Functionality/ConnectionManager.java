package Functionality;

import Classes.Employee;
import Classes.Movie;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;

public class ConnectionManager {
    private HttpURLConnection connection;

    // -------------------- MOVIE ------------------------
    //ERKAN
    public String sendUrlToCreateMovie(String name, String genre, String duration, String shortdesc, String desc, String picpath){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/insertMovie?name=" + name + "&genre=" + genre + "&duration=" + duration + "&shortDescription=" + shortdesc + "&movieDescription=" + desc +  "&path=" + picpath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public String sendUrlToDownloadAllMovies(){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadAllMovies");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public String sendUrlToDownloadMovieById(int id){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadOneMovie?id=" + id);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("response:"+responseString);
        return responseString;
    }

    public String sendUrlToDeleteMovieById(int id){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/deleteMovieById?movieId=" + id);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                return responseString = "movie" + id + " deleted succesfully";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }

    //Lisa
    public String sendUrlToDeleteMovieScheduleWithAllParameters(int salonID, String movieTime, String movieDate){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/deleteMovieAllParameters?salonID="+salonID+
                    "&movieTime="+movieTime+"&movieDate="+movieDate);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("DELETE");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                return responseString = "movieSchedule sucessfully deleted";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }

    //Lisa
    public String sendUrlToUpdateMovie(Movie movie){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/updateMovie?id=" + movie.getId()+"&name=" + movie.getName()+
                    "&genre=" + movie.getGenre()+ "&duration=" + movie.getDuration() + "&movieDescription=" + movie.getMovieDescription()
                    + "&shortDescription=" + movie.getShortDescription() + "&picPath=" + movie.getPicturePath());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status < 300){
                return responseString = "movie" + movie.getName() + " dupdated succesfully";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }

    //Erkan
    public String sendUrlToDownloadMostRecentlyAddedMovie(){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadMostRecentMovie");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null){
                    responseString += line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public String sendUrlToGetMovieByName(String name){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadMovieByName?name=" + name);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null){
                    responseString += line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    // ---------------------------- MOVIE ----------------------------------

    // ---------------------------------- Movie Schedule ----------------------------------------
    // Toros // erkan
    public String sendURLToCreateMovieSchedule(int salonId, String movieTime, String movieDate, int movieId, String seatsData){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/insertMovieSchedule?salonId=" + salonId + "&movieTime=" + movieTime + "&movieDate=" + movieDate +"&movieId=" + movieId + "&seatsData=" + seatsData);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    //Erkan
    public String sendUrlToGetMovieSchedule(int salonID, int movieID, String time, String date){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadOneMovieSchedule?salonID=" + salonID + "&movieID=" + movieID + "&time=" + time + "&date=" + date);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("response:"+responseString);
        return responseString;
    }

    // ---------------------------------- Movie Schedule ----------------------------------------

    // ---------------------------- EMPLOYEE SCHEDULE ----------------------------------

    //Igor
    public String sendUrlToDeleteEmployeeScheduleById(int employeeId){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/deleteEmployeeScheduleById?employeeId=" + employeeId);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status < 300){
              return responseString = "employeeSchedule" + employeeId + " deleted succesfully";
              }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }

    //Igor
    public String sendUrlToCreateEmployeeSchedule(int employeeId, int taskId, int workstationId, String shift){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/insertEmployeeSchedule?employeeId=" + employeeId + "&taskId=" + taskId + "&workstationId=" + workstationId + "&shift=" + shift);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);

            int status = connection.getResponseCode();
            System.out.println(status);

            if (status < 300) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    // ----------------------------------- GET REQUEST -------------------------------------------
    //LISA
    public String sendGetRequest(String request){
        String line = "";
        String response = "";

        try{
            URL url = new URL("http://localhost:8080/"+request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(3000);

            int status = connection.getResponseCode();

            if(status <300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    response += line;
                }
                reader.close();
            }
            connection.disconnect();

        }catch(IOException e){
            e.printStackTrace();
        }
        return response;
    }


    // ------------------------------------------- Employee --------------------------------------------------------


    // Toros
    public String sendURLToInsertNewEmployee (String employeeName, String employeeTel, String employeeEmail) {
        String responseString = "";
        try {
            // employeeID is auto-incremented so maybe no input or input null ??
            URL url = new URL("http://localhost:8080/insertNewEmployee?employee_name=" + employeeName + "&employee_tel=" + employeeTel + "&employee_email=" + employeeEmail);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST"); // Or connection.setRequestMethod("GET"); ?? If so change to @PostMapping in employeeController class
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);

            int status = connection.getResponseCode();
            if (status < 300) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    // Toros
    public String sendURLToDeleteEmployeeByID(int id) {
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/deleteEmployeeByID?employee_ID=" + id);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("DELETE");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status < 300) {
                return responseString = "employee" + id + " deleted successfully";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public String sendURLToDownloadOneEmployeeByName(String employeeName) { // Toros
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadOneEmployeeByName?employeeName=" + employeeName);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public String sendURLToDownloadOneEmployeeByID(int employeeID) { // Toros
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadOneEmployeeByID?employee_ID=" + employeeID);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public String sendUrlToDownloadAllEmployees(){ // Lisa
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadAllEmployees");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    // ---------------------------------- Employee WorkPlan ----------------------------------------


    public String sendUrlToDownloadEmployeeWorkplan(){ //Igor
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadEmployeeWorkplan");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
           connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

           int status = connection.getResponseCode();
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;

        // ---------------------------------- Employee WorkPlan ----------------------------------------
    }

    //Lisa
    public String sendUrlToUpdateEmployee(Employee employee) { //LISA
        String responseString = "";

        try {
            URL url = new URL("http://localhost:8080/updateEmployee?id=" + employee.getEmployeeID() + "&name=" + employee.getEmployeeName() +
                    "&tel=" + employee.getEmployeeTel() + "&mail=" + employee.getEmployeeEmail());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    return responseString = "movie" + employee.getEmployeeName() + " updated succesfully";
                }
            }

        }catch(IOException e){
                e.printStackTrace();
            }
            return responseString;
    }

    // ---------------------------------- Salon ----------------------------------------

    // Toros
    // Not tested
    public String sendURLToInsertNewSalon (int salonID, int cinemaID, int salonRows, String salonSeats) {
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/insertNewSalon?salonID=" + salonID + "&cinemaID=" + cinemaID + "&salonRows=" + salonRows + "&salonSeats=" + salonSeats);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);

            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);

            int status = connection.getResponseCode();
            if (status < 300) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    // Toros
    // Not tested
    public String sendURLToDeleteSalonByID(int SalonID) {
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/deleteSalonByID?salonID=" + SalonID);
            connection.setRequestMethod("DELETE");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status < 300) {
                return responseString = "employee" + SalonID + " deleted successfully";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    // Toros
    public String sendURLToDownloadOneSalonByID(int salonID, int cinemaID) { // Toros
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadOneSalon?salonID=" + salonID + "&cinemaID=" + cinemaID);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    //Erkan
    public String sendUrlToGetSalonById(int salonID, int cinemaID){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadOneSalon?salonID=" + salonID +"&cinemaID=" + cinemaID);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("response:"+responseString);
        return responseString;
    }

    //Lisa
    public String sendUrlToDownloadWholeMovieSchedule(){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadWholeMovieSchedule");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    //Lisa
    public String sendUrlToDownloadWholeMovieScheduleView(){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadWholeMovieScheduleView");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    //Lisa
    public String downloadMovieScheduleViewOneMovie(String movieName){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/getAllInfoOneMovie?movieName="+movieName);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    //LISA
    public String makeReservation(String seats, int salonID, String row, int movieID, String time, String date){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/makeReservation?seats="+seats+"&salonID="+salonID+"&row="+row+
                    "&movieID="+movieID+"&time="+time+"&date="+date);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    //LISA
    public String getReservation(int ID){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/getReservation?ID="+ID);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public String getLatestReservationID(){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/getLatestReservationID");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                    responseString = responseString + line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

}
