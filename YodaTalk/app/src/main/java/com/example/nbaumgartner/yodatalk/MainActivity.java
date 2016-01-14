package com.example.nbaumgartner.yodatalk;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity implements Listener<String> {

    private EditText textToTranslate;
    private TextView translatedText;
    private Button translateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToTranslate = (EditText) findViewById(R.id.toBeTranslated);
        translatedText = (TextView) findViewById(R.id.translatedText);
        translateButton = (Button) findViewById(R.id.button);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textToTranslate.getText().toString();
                Translator translator = new Translator(MainActivity.this,text);
                translatedText.setText("Wait!");
                translateButton.setClickable(false);
                translateButton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                translator.execute();
            }
        });
    }

    @Override
    public void onTaskComplete(String result, int Number){
        translatedText.setText(result);
        translateButton.setClickable(true);
        translateButton.setBackgroundColor(Color.parseColor("#e0e0e0"));
    }
}
