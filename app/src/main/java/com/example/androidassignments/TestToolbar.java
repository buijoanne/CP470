package com.example.androidassignments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {
    Menu m;
    MenuItem item;
    private String newMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello there", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        int duration = Toast.LENGTH_SHORT;
        switch(id) {
            case R.id.action_one:
                if ( newMessage != null){
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), newMessage, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "You selected item 1", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                break;

            case R.id.action_two:
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "You selected item 2", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.goBack);
// Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
// Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.action_three:
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "You selected item 3", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                LayoutInflater inflater = TestToolbar.this.getLayoutInflater();
                builder1.setView(inflater.inflate(R.layout.activity_test_toolbar, null));
                builder1.setTitle(R.string.sendMessage);

                final EditText message = new EditText(this);
                builder1.setView(message);
                builder1.setIcon(R.drawable.add_box);

                builder1.setPositiveButton(R.string.send, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        newMessage = message.getText().toString();

                    }
                });
                builder1.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                AlertDialog d = builder1.create();
                d.show();

                break;

            case R.id.about:
                CharSequence text4 = "Version 1.0, by Joanne Bui";
                Toast toast4 = Toast.makeText(this, text4, duration);
                toast4.show();
            break;
        }
        return true;
    }

}