package com.example.sfzone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TimeTableActivity extends AppCompatActivity {

    ListView timetableListView;
    List<String> titleList = new ArrayList<>();
    List<String> iconList = new ArrayList<>();
    List<String> urlList = new ArrayList<>();
    public List<TimetableDetails> notearray= new ArrayList<TimetableDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        getAllTimetable(this);

        timetableListView = (ListView) findViewById(R.id.timetableListView);

        timetableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent noteswebviewIntent = new Intent(getApplicationContext(),notes_webview.class);
                noteswebviewIntent.putExtra("WEB_URL", urlList.get(position));
                startActivity(noteswebviewIntent);
            }
        });

    }

    public void getAllTimetable(final Context c){
        try {

            StringRequest request= new StringRequest(Request.Method.GET, "https://kamal002.000webhostapp.com/sfzone/api/getAllTimetable.php",
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
                                    TimetableDetails timetable= new TimetableDetails(
                                            jobj.getInt("id"),
                                            jobj.getString("file_name"),
                                            jobj.getString("file_url"),
                                            jobj.getString("file_icon")
                                    );
                                    notearray.add(timetable);
                                    titleList.add(timetable.getFile_Name());
                                    iconList.add(timetable.getFile_Icon());
                                    urlList.add(timetable.getFile_URL());
                                }

                                MyAdapter adapter = new MyAdapter(c, titleList, iconList);
                                timetableListView.setAdapter(adapter);


                                Toast.makeText(getApplicationContext(),"Got the list.",Toast.LENGTH_SHORT).show();
                            }
                            catch(Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
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



    class MyAdapter extends ArrayAdapter<String>{

        Context context;
        List<String> rtitle;
        List<String> rImgs;

        MyAdapter(Context c,List<String> title,List<String> imgs) {
            super(c, R.layout.timetable_row, R.id.timetable_title1, title);
            this.context = c;
            this.rtitle = title;
            this.rImgs = imgs;
        }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = layoutInflater.inflate(R.layout.timetable_row, parent, false);
                ImageView images = row.findViewById(R.id.timetable_image);
                TextView myTitle=row.findViewById(R.id.timetable_title1);

                myTitle.setText(rtitle.get(position));
                String imgType = rImgs.get(position);

                if(imgType.equals("pdf")) {
                    images.setImageResource(R.drawable.pdf2);
                }
                else if(imgType.equals("doc")) {
                    images.setImageResource(R.drawable.doc);
                }
                else if(imgType.equals("jpg")) {
                    images.setImageResource(R.drawable.jpg);
                }
                else if(imgType.equals("png")) {
                    images.setImageResource(R.drawable.png);
                }
                else if(imgType.equals("ppt")) {
                    images.setImageResource(R.drawable.ppt);
                }
                else if(imgType.equals("word")) {
                    images.setImageResource(R.drawable.word);
                }
                else if(imgType.equals("xls")) {
                    images.setImageResource(R.drawable.xls);
                }
                else if(imgType.equals("zip")) {
                    images.setImageResource(R.drawable.zip);
                }
                else{
                    images.setImageResource(R.drawable.dflt);
                }

                return row;
            }
        }
    }


