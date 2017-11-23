package com.pplus.android.socketchatdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView edtUsername;
    Button btnroom1, btnroom2;

//    Socket socket = new ChatApplication().getSocket();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        socket.connect();



        edtUsername = (TextView) findViewById(R.id.edt_username);
        btnroom1 = (Button) findViewById(R.id.btn_room1);
        btnroom2 = (Button) findViewById(R.id.btn_room2);

        btnroom1.setOnClickListener(this);
        btnroom2.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String name = edtUsername.getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", name);

        switch (view.getId()) {
            case R.id.btn_room1:
                bundle.putString("room", "1");
                break;
            case R.id.btn_room2:
                bundle.putString("room", "2");
                break;
        }


        intent.putExtras(bundle);
        startActivity(intent);
    }
}
