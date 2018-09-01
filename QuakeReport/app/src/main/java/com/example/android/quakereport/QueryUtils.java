package com.example.android.quakereport;


import android.util.Log;
import android.text.TextUtils;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Sample JSON response for a USGS query */

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    public static final String LOG_TAG=QueryUtils.class.getSimpleName();
    private QueryUtils() {
    }

    public static URL createurl(String s){
        URL url=null;
        try{
            url =new URL(s);
          }catch (MalformedURLException e){
            Log.e(LOG_TAG,"Cannot Create URL",e);
        }
        return url;

        }

        public static String makeHTTPRequest(URL url) throws IOException {

            String json="";
            HttpURLConnection urlConnection=null;
            InputStream inputStream=null;
            try {
                if(url==null){
                    return json;
                }
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.connect();


                if(urlConnection.getResponseCode()==200) {
                    inputStream=urlConnection.getInputStream();
                    json = ConvertIntoString(inputStream);
                }
                else{
                    Log.e(LOG_TAG,"Error Response Code"+urlConnection.getResponseCode());
                }

            }catch (IOException i){
                Log.e(LOG_TAG,"Cannot Open URL",i);
            }finally {
                if(urlConnection!=null){
                    urlConnection.disconnect();
                }

                if(inputStream!=null){
                    inputStream.close();
                }
            }
            return json;
        }

    private static String ConvertIntoString(InputStream inputStream)throws IOException {
        StringBuilder out=new StringBuilder();
        if(inputStream!=null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = br.readLine();
            while (line != null) {
                out.append(line);
                line = br.readLine();
            }
        }
        return out.toString();
    }


    /**
     * Return a list of {@link Quake} objects that has been built up from
     * parsing a JSON response.
     * @param string
     */
    public static List<Quake> extractEarthquakes(String string) {

        if(TextUtils.isEmpty(string)){
            return null;
        }
        // Create an empty ArrayList that we can start adding earthquakes to
        List<Quake> earthquakes = new ArrayList<>();



        try {
            JSONObject Root=new JSONObject(string);
            JSONArray features=Root.optJSONArray("features");
            for(int i=0;i<10;i++){
                String[] s=new String[2];
                JSONObject count=features.optJSONObject(i);
                JSONObject properties=count.optJSONObject("properties");
                double mag=Double.parseDouble(properties.optString("mag").toString());
                String place=properties.getString("place").toString();
                String time=properties.getString("time").toString();
                long timeinmill=Long.parseLong(time);
                boolean contains= place.contains("of");
                if(contains){
                    s=place.split("of");
                    s[0]=s[0]+"of";
                }
                else{
                    s[0]="Near the ";
                    s[1]="Pacific-Antarctic Ridge";
                }
                Date dateobject=new Date(timeinmill);
                SimpleDateFormat dateFormat=new SimpleDateFormat("MMM dd,yyyy");
                String dateo=dateFormat.format(dateobject);
                SimpleDateFormat timeFormat=new SimpleDateFormat("h:mm a");
                String timeo=timeFormat.format(timeinmill);
                String url=properties.getString("url").toString();
                earthquakes.add(new Quake(mag,s[0],s[1],dateo,timeo,url));
            }




            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException h) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", h);
        }


        // Return the list of earthquakes
        return earthquakes;
    }
    public static List<Quake> fetchEarthquakeData(String requestUrl)  {
        // Create URL object
        URL url = createurl(requestUrl);
        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            Thread.sleep(2000);
            jsonResponse = makeHTTPRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Quake> earthquakes = extractEarthquakes(jsonResponse);
        // Return the list of {@link Earthquake}s
        return earthquakes;
    }


}