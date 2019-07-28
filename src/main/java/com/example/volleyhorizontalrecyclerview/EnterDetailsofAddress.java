package com.example.volleyhorizontalrecyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EnterDetailsofAddress extends AppCompatActivity {

   Spinner spinner;

    String states[] = { "Andra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir",
    "Jharkhand","Karnataka","Kerala","Madya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Orissa","Punjab","Rajasthan","Sikkim","Tamil Nadu",
    "Telagana","Tripura","Uttaranchal","Uttar Pradesh","West Bengal"};

    Button button;

    EditText city,address,pincode,name,pno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_detailsof_address);

        button = findViewById(R.id.adddetails);


        spinner = findViewById(R.id.spinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, states);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),states[position] , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        city=findViewById(R.id.city);
        address=findViewById(R.id.e_address);
        pincode=findViewById(R.id.pincode);
        name=findViewById(R.id.e_name);
        pno=findViewById(R.id.p_no);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Bundle extras = new Bundle();

                String p_name = name.getText().toString();
                String p_city= city.getText().toString();
                String p_address= address.getText().toString();
                Integer p_pincode = Integer.valueOf(pincode.getText().toString());
                Integer p_pno= Integer.valueOf(pno.getText().toString());

                SharedPreferences sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("p_name",p_name);
                editor.putString("p_city",p_city);
                editor.putString("p_address",p_address);
                editor.putInt("p_pincode",p_pincode);
                editor.putInt("p_pno",p_pno);

                editor.commit();

                Intent intent = new Intent(EnterDetailsofAddress.this,ShowAdress.class);

                /*intent.putExtra("City",city.getText().toString());
                intent.putExtra("address",address.getText().toString());
                intent.putExtra("pincode",pincode.getText().toString());
                intent.putExtra("name",name.getText().toString());
                intent.putExtra("phone no",pno.getText().toString());*/

                startActivity(intent);

            }
        });


    }

}
