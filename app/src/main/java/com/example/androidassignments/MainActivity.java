package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "MainActivity";
    static final int requestCode = 10;
    int resposeCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivity(intent);
            }
        });

        Button chatButton = (Button)findViewById(R.id.button2);
        chatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(ACTIVITY_NAME, "User clicked Start Chat");
                Intent i = new Intent(MainActivity.this, ChatWindow.class);
                startActivity(i);

            }
        });

        Button toolBarButton = (Button)findViewById(R.id.testToolbar);
        toolBarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(MainActivity.this, TestToolbar.class);
                startActivity(in);
            }
        });

    }



    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
    public void button(){
        Button buttonText = (Button)findViewById(R.id.button);
        Intent intent = new Intent(this, ListItemsActivity.class);
        startActivityForResult(intent, requestCode);

        }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data){
        if ((requestCode == 10)){
            Log.i(ACTIVITY_NAME, "returned to MainActivity.onActivityResult");
            if (responseCode == Activity.RESULT_OK) {
                String messagePassed = data.getStringExtra("Response");
                Toast.makeText(getApplicationContext(), messagePassed, Toast.LENGTH_LONG).show();
            }
        }
    }




}