package com.tanat.volleytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;
    private LinearLayout container;
    private Button nextButton, closeButton;
    private EditText findBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView)findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://devemat-androidprogramming.blogspot.com/");
        mWebView.setWebViewClient(new MyWebViewClient());
    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private static final int SEARCH_MENU_ID = Menu.FIRST;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        menu.add(0, SEARCH_MENU_ID, 0, "Search");

        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu){
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case SEARCH_MENU_ID:
                search();
                return true;
        }
        return true;
    }

    public void search(){
        container = (LinearLayout)findViewById(R.id.layoutId);

        nextButton = new Button(this);
        nextButton.setText("Next");
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mWebView.findNext(true);
            }
        });
        container.addView(nextButton);

        closeButton = new Button(this);
        closeButton.setText("Close");
        closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                container.removeAllViews();

            }
        });
        container.addView(closeButton);

        findBox = new EditText(this);
        findBox.setMinEms(30);
        findBox.setSingleLine(true);
        findBox.setHint("Search");

        findBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mWebView.findAllAsync(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        container.addView(findBox);
    }
}