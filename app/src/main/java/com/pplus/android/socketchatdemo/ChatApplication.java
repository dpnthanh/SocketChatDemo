package com.pplus.android.socketchatdemo;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by nhatthanh on 23/11/2017.
 */

class ChatApplication {
    private Socket mSocket;
    {
        try{
            mSocket = IO.socket("https://pplus-chat-group.herokuapp.com/");
//            mSocket = IO.socket("https://6928e5a7-4a24-4ca8-8ec0-c455c0973118:3000");

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket(){
        return mSocket;
    }
}
