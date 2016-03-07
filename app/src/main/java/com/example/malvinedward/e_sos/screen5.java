package com.example.malvinedward.e_sos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class screen5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen5);
    }

    public void home(View view){
        Intent f = new Intent(this, MainActivity.class);
        startActivity(f);
        finish();
    }
}
