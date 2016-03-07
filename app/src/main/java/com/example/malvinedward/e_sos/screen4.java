package com.example.malvinedward.e_sos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class screen4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);
    }

    public void tutor5(View view){
        Intent e = new Intent(this, screen5.class);
        startActivity(e);
        finish();
    }
}
