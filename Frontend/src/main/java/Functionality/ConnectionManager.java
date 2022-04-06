package Functionality;

import Classes.Employee;
import Classes.Movie;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManager {
    private HttpURLConnection connection;
    private Gson gson;
    //private String responseString = "";

    //ERKAN
    public String sendUrlToCreateMovie(String name, String genre, String duration, String desc, String shortdesc, String picpath){
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/addMovie?name=" + name + "&genre=" + genre + "&duration=" + duration + "&movieDescription=" + desc + "&shortDescription=" + shortdesc + "&picPath=" + picpath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);

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
            System.out.println(status);
            if (status < 300){
                return responseString = "movie" + id + " deleted succesfully";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }

    public String sendUrlToDeleteEmployeeScheduleById(int employeeId){//Igor
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

    // for reference:
    // int employeeID, String employeeName, String employeeTel, String employeeEmail

    // Toros
    // Not working correctly yet
    // error: org.apache.http.conn.HttpHostConnectException: Connect to localhost:8080 [localhost/127.0.0.1, localhost/0:0:0:0:0:0:0:1] failed: Connection refused: connect
    // employeeID is auto-incremented so maybe no input or input null ??
    public String sendURLToInsertNewEmployee (String employeeName, String employeeTel, String employeeEmail) {
        String responseString = "";
        try {
            // employeeID is auto-incremented so maybe no input or input null ??
            URL url = new URL("http://localhost:8080/insertNewEmployee?employeeName=" + employeeName + "&employeeTel=" + employeeTel + "&employeeEmail=" + employeeEmail);
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
    // Not tested
    public String sendURLToDeleteEmployeeByID(int id) {
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/deleteEmployeeByID?employeeID=" + id);
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

    // Not tested
    public String sendURLToDownloadOneEmployeeByName(String employeeName) { // Toros
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadOneMovie?employeeName=" + employeeName);
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

    // Not tested
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
    }

    public String sendUrlToUpdateEmployee(Employee employee){ //LISA
        String responseString = "";

        try {
            URL url = new URL("http://localhost:8080/updateEmployee?id=" + employee.getEmployeeID()+"&name=" + employee.getEmployeeName()+
                    "&tel=" + employee.getEmployeeTel()+ "&mail=" + employee.getEmployeeEmail());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null){
                return responseString = "movie" + employee.getEmployeeName() + " updated succesfully";
            }
        } catch (IOException e) {
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


    public String sendUrlToCreateEmployeeSchedule(int employeeId, int taskId, int workstationId, String shift){//Igor
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
    // Not tested
    public String sendURLToDownloadOneSalonByID(int salonID) { // Toros
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/downloadOneSalonByID?salonID=" + salonID);
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
}
