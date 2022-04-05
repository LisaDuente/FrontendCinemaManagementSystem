package Functionality;

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
            connection.setRequestMethod("GET");
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
        System.out.println(responseString);
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

    /*
        // for reference:
    // int employeeID, String employeeName, String employeeTel, String employeeEmail
    public void deleteEmployeeByID(int employeeID) {
        String query = "DELETE FROM employees WHERE employee_ID = ?;";
        int result = jdbcTemplate.update(query, employeeID);

        if (result > 0) {
            System.out.println(result + "employee deleted from database");
            this.error = "employee deleted from database";
        }
    }
     */

    // Toros
    public String sendURLToDeleteEmployeeByID(int id) {
        String responseString = "";
        try {
            URL url = new URL("http://localhost:8080/deleteEmployeeByID?employeeID=" + id);
            connection.setRequestMethod("DELETE");
            connection.setReadTimeout(5000);
            connection.setConnectionTimeout(5000);

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
            connection.setReadTimeOut(5000);
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

    public String sendUrlToDownloadAllEmployees(){
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
}
