package com.auribises.activitydatapassing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsActivity extends AppCompatActivity {

    WebView webView;

    void initViews(){

        webView = findViewById(R.id.webView);

        // Enable JS in WebView
        webView.getSettings().setJavaScriptEnabled(true);

        // Create Client
        WebViewClient client = new WebViewClient();

        webView.setWebViewClient(client);
        webView.loadUrl("https://www.amazon.in/");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initViews();
    }
}
