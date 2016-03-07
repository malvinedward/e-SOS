package com.example.malvinedward.e_sos;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class addcontact extends AppCompatActivity {

    private int counter;
    private Button add;
    private Button listkontak;
    private EditText ednama;
    private EditText ednomer;
    private DBSource datasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);
        add = (Button) findViewById(R.id.adddb);
        listkontak = (Button) findViewById(R.id.viewlist);
        ednama = (EditText) findViewById(R.id.editnama);
        ednomer = (EditText) findViewById(R.id.editnomer);



        datasource = new DBSource(this);
        datasource.open();


    }

    public void deletek(View view){
        datasource.deleteallcontact();
        Toast.makeText(addcontact.this, "All data deleted", Toast.LENGTH_SHORT).show();
    }

    public void menuawal(View view){
        String nama = null;
        String nomerhp = null;

        DBContact contact = null;

        if(ednama.getText()!=null && ednomer.getText()!=null){
            nama = ednama.getText().toString();
            nomerhp = ednomer.getText().toString();

        }

        switch (view.getId()){


            case R.id.mainmenu:
                Intent i3 = new Intent(this, MainActivity.class);
                startActivity(i3);
              setContentView(R.layout.activity_main);


        break;

            case R.id.adddb:
            contact = datasource.createContact(nama,nomerhp);

                Toast.makeText(this,"Masuk kontak: " + " "+contact.getNama()+" contact: "+contact.getNomerhp(),Toast.LENGTH_LONG).show();
                break;

            case R.id.viewlist:
                Log.d("reading", "reading all");
                List<DBContact> listcontact = datasource.getallcontact();
                for (DBContact d:listcontact){
                    Log.d("data", "ID: "+d.getId()+" nama: "+d.getNama()+" nomer: "+d.getNomerhp());
                }
                break;
            case R.id.delete:

                datasource.deleteContact(1);
        }
    }
}
