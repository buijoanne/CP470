package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ChatWindow extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ChatWindow";
    Button sendButton;
    ArrayList<String> chatRecords = new ArrayList<>();
    ListView listView;
    EditText editText;
    static SQLiteDatabase db;
    ChatDatabaseHelper c;
    private Cursor cursor;
    private String[] columns;

    ArrayList<String> messages = new ArrayList<>();
    ContentValues contentValues = new ContentValues();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        Button sendButton = (Button)findViewById(R.id.sendButton);
        final ListView listView = (ListView)findViewById(R.id.lV);
        final EditText editText = (EditText)findViewById(R.id.editText);
        final ChatAdapter messageAdapter =new ChatAdapter( ChatWindow.this );

        c = new ChatDatabaseHelper(this);
        db = c.getReadableDatabase();

        String[] columns = {ChatDatabaseHelper.KEY_MESSAGE};
        final Cursor cursor = db.query(ChatDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Log.i(ACTIVITY_NAME, "SELECT KEY_MESSAGE FROM RECORDS " + cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            messages.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            cursor.moveToNext();
        }
        Log.i(ACTIVITY_NAME, "column count: " + cursor.getColumnCount());
        int columnIndex;
        for (columnIndex = 0; columnIndex < cursor.getColumnCount();columnIndex++){
            Log.i(ACTIVITY_NAME,"column name: " + cursor.getColumnName(columnIndex));


        }

        listView.setAdapter (messageAdapter);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatRecords.add(editText.getText().toString());
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()
                contentValues.put(ChatDatabaseHelper.KEY_MESSAGE, editText.getText().toString());
                db.insert(ChatDatabaseHelper.TABLE_NAME, "null", contentValues);

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
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
        db.close();
    }



}