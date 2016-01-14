package com.example.nbaumgartner.yodatalk;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nbaumgartner on 1/14/2016.
 */
public class Translator extends AsyncTask<Void,Void,String> {
    private String textToTranslate;
    private String translated;
    private Listener<String> callback;

    public Translator(Listener<String> cb,String text){
        this.callback = cb;
        textToTranslate = text.replace(" ","+");
    }

    @Override
    protected String doInBackground(Void... params){
        try{
            URL url = new URL("https://yoda.p.mashape.com/yoda?sentence="+textToTranslate);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("X-Mashape-Key","lCFrpbzZatmshnfTpt8YE6srX59yp1LJ2dIjsnUScGOl8I8MNR");
            urlConnection.setRequestProperty("Accept","text/plain ");
            urlConnection.setRequestProperty("Host","yoda.p.mashape.com");
            InputStream in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            String translatedText = new String();
            int data = isw.read();
            while (data != -1) {
                translatedText += (char) data;
                data = isw.read();
            }
            translated = translatedText;
        }catch(Exception e){
            e.printStackTrace();
        }

        return translated;
    }

    @Override
    protected void onPostExecute(String translated){
        if(callback != null){
            callback.onTaskComplete(translated,1);
        }
    }
}
