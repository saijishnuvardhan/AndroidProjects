package com.example.praneeth.booklist;

import android.util.Log;

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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    public static final String LOG_TAG=QueryUtils.class.getSimpleName();

    public static URL createURL(String s){
        URL url=null;
        try{
             url=new URL(s);
        }catch (MalformedURLException m){
            Log.e(LOG_TAG,"unable to create url",m);
        }
        return url;
    }

    public static String makeHTTPRequest(URL url) throws IOException {
        String json="";
        InputStream inputStream=null;
        HttpURLConnection urlConnection=null;
        try{
        if(url==null){
            return json;
        }
        urlConnection=(HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.connect();

        inputStream = urlConnection.getInputStream();
        json=converttostring(inputStream);
        }catch (IOException i){
                Log.e(LOG_TAG, "Unable to connect",i);
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

    private static String converttostring(InputStream inputStream) throws IOException {

        StringBuilder sb=new StringBuilder();
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader br=new BufferedReader(inputStreamReader);
        String line=br.readLine();
        while(line!=null) {
            sb.append(line);
            line = br.readLine();
        }
        return sb.toString();

    }

    public static List<Book> fetchdata(String s){
         URL url=createURL(s);
        String jsonresponse=null;
        try{

            Thread.sleep(2000);
            jsonresponse=makeHTTPRequest(url);
            }catch(IOException i){
            Log.e(LOG_TAG,"catch IOException");
        }catch (InterruptedException ie){
          ie.printStackTrace();
        }
        List<Book> list=extractfromBook(jsonresponse);
        return list;
    }

    public static List<Book> extractfromBook(String s){
        List<Book> list=new ArrayList<>();
        try {
            JSONObject root=new JSONObject(s);
            JSONArray jsonArray=root.getJSONArray("items");
            for(int i=0;i<20;i++){
                JSONObject obj=jsonArray.getJSONObject(i);
                JSONObject volume=obj.getJSONObject("volumeInfo");
                String title=volume.getString("title");
                JSONArray ar=volume.getJSONArray("authors");
                String url="";
                String author=ar.get(0).toString();
                JSONObject image=volume.getJSONObject("imageLinks");
                String thumbnail=image.getString("thumbnail");
                    url=volume.getString("infoLink").toString();


                    list.add(new Book(title,author,url,thumbnail));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;

    }

}
