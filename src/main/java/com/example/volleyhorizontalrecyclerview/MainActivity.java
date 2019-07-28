package com.example.volleyhorizontalrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //For category
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    List<Category> categoryList =new ArrayList<>();

    // for TopDeals
    RecyclerView recyclerView2;
    RecyclerView.Adapter adapter2;
    RecyclerView.LayoutManager layoutManager2;

    List<TopDeals>topDealsList=new ArrayList<>();

    // for TopSeller
    RecyclerView recyclerView3;
    RecyclerView.Adapter adapter3;
    RecyclerView.LayoutManager layoutManager3;

    List<TopSeller> topSellerList = new ArrayList<>();



    RequestQueue queue;
    RequestQueue queue2;
    RequestQueue queue3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =(RecyclerView)findViewById(R.id.recyclerView);

        recyclerView2 = findViewById(R.id.recyclerView2);

        recyclerView3 =findViewById(R.id.recyclerView3);

        queue = Volley.newRequestQueue(this);
        layoutManager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        queue2 =Volley.newRequestQueue(this);
        layoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(layoutManager2);

        queue3=Volley.newRequestQueue(this);
        layoutManager3 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView3.setLayoutManager(layoutManager3);


        parseCategory();

        parseTopdeals();

        parseTopSeller();
    }


    private void parseCategory() {
        //StringReques- A canned request for retrieving the response body at a given URL as a String.
        StringRequest request =  new StringRequest(Request.Method.POST, "http://www.json-generator.com/api/json/get/cfuuUorKwi?indent=2", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject jsonObject =jsonArray.getJSONObject(i);

                        final Category  category =new Category();               // so that this name i.e category is not used at other places in program

                        category.setCategorynames(jsonObject.getString("cat"));
                        //category.setImages(jsonObject.getString("logo"));

                        categoryList.add(category);     //The data that we retrived  in obj category we pass it to arraylist

                    }
                    adapter = new RecyclerAdapter(MainActivity.this,categoryList);      //RecyclerAdapter constructor
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }


    private void parseTopdeals() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://www.json-generator.com/api/json/get/cqamAxTvIi?indent=2", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i =0;i<jsonArray.length();i++){

                        JSONObject jsonObject =jsonArray.getJSONObject(i);

                        final TopDeals topDeals = new TopDeals();               // topDeals is obj of class TopDeals which we have make static

                        topDeals.setMedicinetitle(jsonObject.getString("name"));
                        //topDeals.setMedicineimg(jsonObject.getString("images"));

                        topDealsList.add(topDeals);
                    }
                    adapter2 = new RecyclerAdapterTopDeals(MainActivity.this,topDealsList);                 // we added items in topDealsList and now we passing it in the constructor
                    recyclerView2.setAdapter(adapter2);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue2.add(request);
    }

    private void parseTopSeller() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://crazymall.co.in/admin/not_usable/product_list.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        final TopSeller topSeller = new TopSeller();    //topSeller-obj

                        //topSeller.setItemname(jsonObject.getString("item"));
                        //topSeller.setPics(jsonObject.getString("img"));
                        topSeller.setItemname(jsonObject.getString("tag2"));
                        topSeller.setPics(jsonObject.getString("feature_url"));
                        topSeller.setId(jsonObject.getString("add_product_id"));

                        topSellerList.add(topSeller);
                    }
                    adapter3 = new RecyclerAdapterTopSeller(MainActivity.this,topSellerList);
                    recyclerView3.setAdapter(adapter3);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue3.add(request);
    }


}
