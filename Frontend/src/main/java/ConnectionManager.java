import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManager {
    private HttpURLConnection connection;
    private Gson gson;
    private String responseString = "";

    //ERKAN
    public String sendUrlToCreateMovie(String name, String genre, String duration, String desc, String shortdesc, String picpath){
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
        try {
            URL url = new URL("http://localhost:8080/deleteMovieById?movieId=" + id);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
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
    public String sendUrlToDownloadMostRecentlyAddedMovie(){
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
}
