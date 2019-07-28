package com.example.volleyhorizontalrecyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowAdress extends AppCompatActivity {

    Button button ;

    TextView textView,textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_adress);

        button = findViewById(R.id.add_address);

        textView=findViewById(R.id.filling_text);
        textView1=findViewById(R.id.filling_name);


        /*Intent intent2 = getIntent();
        Bundle bundle = intent2.getExtras();*/

        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("user_details",MODE_PRIVATE);
        textView1.setText(sharedPreferences.getString("p_name",""));
        textView.setText(sharedPreferences.getString("p_city","" + sharedPreferences.getString("p_address","" + sharedPreferences.getInt("p_pincode",0 + sharedPreferences.getInt("p_pno",0)))));


        /*String c =intent2.getStringExtra("City");
        String a = intent2.getStringExtra("address");
        String pin =intent2.getStringExtra("pincode");
        String n = intent2.getStringExtra("name");
        String p_no=intent2.getStringExtra("phone no");

        textView1.setText(c + " "+a+ " "+ pin + "\n"+ p_no);
        textView.setText(" "+ n);*/



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowAdress.this,EnterDetailsofAddress.class);
                startActivity(intent);
            }
        });
    }
}
