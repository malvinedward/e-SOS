package com.example.malvinedward.e_sos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class screen3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);
    }

    public void tutor4(View view){
        Intent d = new Intent(this, screen4.class);
        startActivity(d);
        finish();
    }
}
