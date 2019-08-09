package com.ankitapabbi.c0751145_mad3125_midterm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewScreen extends AppCompatActivity {

    WebView myWebView;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_screen);
        myWebView = (WebView)findViewById(R.id.myWebView);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        myWebView.loadUrl(url);
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient() {


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                myWebView.loadUrl(url);
                return true;
            }

//
        });
    }
}
