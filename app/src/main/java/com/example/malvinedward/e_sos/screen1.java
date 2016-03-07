package com.example.malvinedward.e_sos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class screen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent a = getIntent();
        setContentView(R.layout.activity_screen1);
    }

    public void tutor2 (View view){
        Intent b = new Intent(this, screen2.class);
        startActivity(b);
        finish();
    }
}
