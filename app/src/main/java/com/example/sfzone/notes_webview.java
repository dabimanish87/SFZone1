package com.example.sfzone;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class notes_webview extends AppCompatActivity {

    WebView webview1;
    ProgressBar progressbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_webview);


        webview1 = findViewById(R.id.webview1);
        progressbar1= findViewById(R.id.progressbar1);
        progressbar1.setVisibility(View.VISIBLE);

        String url=getIntent().getStringExtra("WEB_URL");
        String finalURL="http://drive.google.com/viewerng/viewer?embedded=true&url="+url;

        webview1.setWebViewClient(new WebViewClient());

        webview1.getSettings().setJavaScriptEnabled(true);
        webview1.getSettings().setDisplayZoomControls(true);
//
//        webview1.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onProgressChanged(WebView view, int newProgress){
//                super.onProgressChanged(view, newProgress);
//
//                getSupportActionBar().setTitle("Loading...");
//                if(newProgress == 100){
//                    progressbar1.setVisibility(View.GONE);
//                    getSupportActionBar().setTitle(R.string.app_name);
//                }
//            }
//        });

        webview1.loadUrl(finalURL);
    }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressbar1.setVisibility(View.GONE);
        }
    }
}
