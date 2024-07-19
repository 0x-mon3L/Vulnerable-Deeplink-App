package com.mon3l.vulnerable_deeplink_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DeepLinkVulnerableApp";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        webView = findViewById(R.id.webView);

        // Configure WebView settings
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

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
    }
}