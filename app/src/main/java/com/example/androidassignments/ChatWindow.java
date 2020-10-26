package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.*;
import android.content.Context;
import android.widget.TextView;

public class ChatWindow extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ChatWindow";
    Button sendButton;
    ArrayList<String> chatRecords = new ArrayList<>();
    ListView listView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        Button sendButton = (Button)findViewById(R.id.sendButton);
        final ListView listView = (ListView)findViewById(R.id.lV);
        final EditText editText = (EditText)findViewById(R.id.editText);
        final ChatAdapter messageAdapter =new ChatAdapter( ChatWindow.this );
        listView.setAdapter (messageAdapter);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatRecords.add(editText.getText().toString());
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()
                editText.setText("");


            }
        });

    }

    private class ChatAdapter extends ArrayAdapter<String>{
        public ChatAdapter(Context ctx){
            super(ctx, 0);
            }
        public int getCount(){
            return chatRecords.size();
        }
        public String getItem(int Position){
            return chatRecords.get(Position);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;
            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            TextView message = (TextView)result.findViewById(R.id.messageText);
            message.setText(getItem(position)); // get the string at position
            return result;

        }

    }



}