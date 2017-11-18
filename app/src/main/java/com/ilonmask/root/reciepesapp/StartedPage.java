package com.ilonmask.root.reciepesapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class StartedPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCallingActivity()


        setContentView(R.layout.activity_started_page);
    }

    private void fillingCards(Bundle savedInstanceState){
        //запрос к серверу

        Context context = getActivity().getApplicationContext();
        LinearLayout layout =
    }
}
