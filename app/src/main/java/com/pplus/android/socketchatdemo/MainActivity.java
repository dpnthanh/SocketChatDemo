package com.pplus.android.socketchatdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvChat;
    TextView txtname;
    EditText edtMessage;
    Button btnSend;
    ArrayList<String> listMessages = new ArrayList<>();
    ArrayAdapter adapter;

    private Socket mSocket = new ChatApplication().getSocket();

    String room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        initDisplay();
        initEvents();




    }

    private void initEvents() {

        mSocket.on("new mess"+room, onNewMessage);

        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sendMessenge();
            }

        });
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    try {
                        message = data.getString("text");
                    } catch (JSONException e) {
                        return;
                    }

                    // add the message to view
                    addMessage(message);
                }
            });
        }
    };

    private void sendMessenge() {
        String mess = edtMessage.getText().toString().trim();
        mSocket.emit("new messege"+room, mess);
        edtMessage.setText("");
    }

    private void addMessage(String message) {
        listMessages.add(message);
        adapter.notifyDataSetChanged();
    }

    private void initDisplay() {

    }

    private void initControls() {
        txtname = (TextView) findViewById(R.id.txt_name);
        lvChat = (ListView) findViewById(R.id.listViewChat);
        edtMessage = (EditText) findViewById(R.id.edt_textChat);
        btnSend = (Button) findViewById(R.id.btnSend);

        mSocket.connect();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listMessages);
        lvChat.setAdapter(adapter);

        Intent intent = getIntent();
        txtname.setText(intent.getStringExtra("name"));
        room = intent.getStringExtra("room");

        //register
        mSocket.emit("register", txtname.getText().toString().trim());

    }


}
