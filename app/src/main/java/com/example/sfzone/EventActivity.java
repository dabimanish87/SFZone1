package com.example.sfzone;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    ListView eventListView;
    List<String> titleList = new ArrayList<>();
    List<String> dateList = new ArrayList<>();
    public List<EventDetails> eventarray= new ArrayList<EventDetails>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        getAllEvent(this);

        eventListView = (ListView) findViewById(R.id.eventListView);

    }

    public void getAllEvent(final EventActivity c){
        try {

            StringRequest request= new StringRequest(Request.Method.GET, "https://kamal002.000webhostapp.com/sfzone/api/getAllEvent.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {

                                JSONObject obj = new JSONObject(response);


                                // 1. convert object list to json array
                                JSONArray jsonArray = (JSONArray) obj.get("list");
                                // 2. create for loop to iterate to json array
                                for(int i=0; i<jsonArray.length();i++)
                                {
                                    // 3. create json object for each item of an json array = o
                                    // 4. convert json object to notedetails
                                    JSONObject jobj=(JSONObject) jsonArray.get(i);
                                    EventDetails events= new EventDetails(
                                            jobj.getInt("id"),
                                            jobj.getString("name"),
                                            jobj.getString("place"),
                                            jobj.getString("date"),
                                            jobj.getString("time")
                                    );
                                    eventarray.add(events);
                                    titleList.add(events.getName());
                                    dateList.add(events.getDate());
                                }

                                MyAdapter adapter = new MyAdapter (c, titleList, dateList);
                                eventListView.setAdapter(adapter);


                                Toast.makeText(getApplicationContext(),"Got the list.",Toast.LENGTH_SHORT).show();
                            }
                            catch(Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            });
            Volley.newRequestQueue(this).add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        List<String> rtitle;
        List<String> rDates;

        MyAdapter(Context c,List<String> title,List<String> dates) {
            super(c, R.layout.events_row, R.id.event_title1, title);
            this.context = c;
            this.rtitle = title;
            this.rDates = dates;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.events_row, parent, false);
            ImageView images = row.findViewById(R.id.event_image);
            TextView myTitle=row.findViewById(R.id.event_title1);
            TextView myDate=row.findViewById(R.id.event_title2);

            myTitle.setText(rtitle.get(position));
            myDate.setText(rDates.get(position));

            images.setImageResource(R.drawable.event);

            return row;
        }
    }
}
