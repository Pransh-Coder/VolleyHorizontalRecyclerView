package com.example.volleyhorizontalrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Blank extends AppCompatActivity {
    TextView textView,textView1,textView2;
    ImageView imageView;
    private Context context;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        queue = Volley.newRequestQueue(this);

        textView=findViewById(R.id.nameofItem);
        imageView=findViewById(R.id.pictures);
        textView1=findViewById(R.id.discriptiontext);
        textView2=findViewById(R.id.price);

        final Intent intent = getIntent();           // Receiving data from RecyclerAdapterTopseller (id) in blank activity
        final String ids = intent.getStringExtra("id");

        showData(ids);                                      //we are showing data with help of ids of products

        Button addtoCart = findViewById(R.id.addtocart);
        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent1 = new Intent(getApplicationContext(),items_cart.class);
                intent1.putExtra("id",ids);                                     // from here also we have to pass data we cannot directly pass from recycleradapterTopseller
                startActivity(intent1);
            }
        });

    }
    private void showData(final String i){
        StringRequest request = new StringRequest(Request.Method.POST, "http://crazymall.co.in/admin/not_usable/paticular_product_detail.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Blank.this,""+response,Toast.LENGTH_SHORT).show();
                //textView.setText(i);
                System.out.println(response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String p_name = jsonObject.getString("product_name");
                        String price = jsonObject.getString("product_price");
                        String des = jsonObject.getString("discription");
                        String img = jsonObject.getString("feature_url");
                        textView.setText(p_name);
                        textView2.setText(price);
                        Picasso.get().load(img).into(imageView);
                        textView1.setText(des);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {             // we are sending this data so that at the database we can check whether we have the same data or not i.e for matching
                HashMap<String,String> map=new HashMap<>();
                map.put("add_product_id",i);
                return map;
            }
        };
        queue.add(request);
        //Volley.newRequestQueue(context).add(request);
    }
}
