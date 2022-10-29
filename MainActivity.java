package com.earningaide.admindeal;
 
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.Request;
import com.android.volley.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import com.android.volley.VolleyError;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.widget.ProgressBar;
import android.widget.ListView;
import java.util.HashMap;
import android.view.LayoutInflater;
import android.widget.Button;
import android.webkit.WebView;
import android.widget.ImageView;
import android.content.Intent;

public class MainActivity extends Activity { 
// TextView textView;

ProgressBar myProgressBar;
ListView myListView;
    WebView mWevView;
ArrayList <HashMap<String, String>> myArrayList = new ArrayList<>(); // Invisiable table created
HashMap<String, String> myHashMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //    textView = findViewById(R.id.textview);
        myProgressBar = findViewById(R.id.activitymainProgressBar1);
        myListView = findViewById(R.id.activitymainListView1);
    
        mWevView = findViewById(R.id.mwebView);
        
    
        
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://earningaide.000webhostapp.com/app/adminDeal.json";

// Request a string response from the provided URL.

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url,null,
            new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    myProgressBar.setVisibility(View.GONE);

                    try {

                        for(int x=0; x<response.length(); x++){



                            JSONObject jSONObject =  response.getJSONObject(x);
                            String name = jSONObject.getString("Name");
                            String link = jSONObject.getString("link");

                        //    textView.append(("Index Number: "+x+"\n"+name+"\n"+link+"\n\n"));
                        
                        myHashMap = new HashMap<>();
                        myHashMap.put("name",name);
                        myHashMap.put("link",link);
                        myArrayList.add(myHashMap); 
                        
                            MyAdapter myAdapter = new MyAdapter();
                            myListView.setAdapter(myAdapter);
                            
                        }
                        
                        
                    } catch (JSONException e) {}

                }

            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError p1) {
                    myProgressBar.setVisibility(View.GONE);
                }

            });

// Add the request to the RequestQueue.

        queue.add(jsonArrayRequest);

    }
    
    
    private class MyAdapter extends BaseAdapter {
        

        @Override
        public int getCount() {
            return myArrayList.size();
        }

        @Override
        public Object getItem(int p1) {
            return null;
        }

        @Override
        public long getItemId(int p1) {
            return 0;
        }

        @Override
        public View getView(int p1, View p2, ViewGroup p3) {
           LayoutInflater myLayoutInflater =  getLayoutInflater();
           View myView = myLayoutInflater.inflate(R.layout.item,null);
           TextView tvName = myView.findViewById(R.id.tvName);
           TextView tvTitle = myView.findViewById(R.id.tvTitle);
           Button btnDetails = myView.findViewById(R.id.btnDetails);
           ImageView imglogo = myView.findViewById(R.id.imglogo);
           
           
            
            
            HashMap<String, String> myHashMap = myArrayList.get(p1);
            
            String title = myHashMap.get("name");
            final String link = myHashMap.get("link");
            tvName.setText(title);
            tvTitle.setText(link);
            
            String img_url="http://img.youtube.com/vi/"+link+"/0.jpg";
            
            
           
            
            
            btnDetails.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) { 
               
                        
                    
                   mWevView.getSettings().setJavaScriptEnabled(true);

                    mWevView.loadUrl("https://m.me/billal01864");
                        
                        
                        
                    }
                });
            
            
            
            
            
           
           
            return myView;
        }
    }
    
        
        
    
	
} 
