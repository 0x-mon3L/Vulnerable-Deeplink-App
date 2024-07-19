package com.mon3l.vulnerable_deeplink_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DeepLinkVulnerableApp";
    private WebView webView;
    private Button showMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        webView = findViewById(R.id.webView);
        showMessageButton = findViewById(R.id.buttonShowMessage);

        // Configure WebView settings
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            String uriString = data.toString();
            Log.d(TAG, "Deep link data: " + uriString);
            textView.setText("Received deep link:\n" + uriString);

            String url = data.getQueryParameter("url");
            if (url != null) {
                webView.loadUrl(url);
            } else {
                textView.setText("Invalid deep link: no URL parameter found");
            }
        }

        // Set up button click listener
        showMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a Toast message
                Toast.makeText(MainActivity.this, "Challenge: Identify Deep-Link Vulnerability!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}