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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceReport extends AppCompatActivity {

    String start_date, end_date;
    Spinner spinner1;
    Button btnSubmit;
    ListView AttendanceReportListView;


    List<Attendance> attendances = new ArrayList<>();

    public List<AttendancePresentDetails> presentattendancearray= new ArrayList<>();
    public List<AttendanceTotalDetails> totalattendancearray= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_report);
        System.out.println(UserDetails.getInstance().getId());
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        AttendanceReportListView = (ListView) findViewById(R.id.AttendanceReportListView);

        final Context c = this;

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String month = spinner1.getSelectedItem().toString();

                switch (month){
                    case "Jan" :
                        start_date = "2020-01-01";
                        end_date = "2020-01-31";
                        break;
                    case "Feb" :
                        start_date = "2020-02-01";
                        end_date = "2020-02-31";
                        break;
                    case "Mar" :
                        start_date = "2020-03-01";
                        end_date = "2020-03-31";
                        break;
                    case "Apr" :
                        start_date = "2020-04-01";
                        end_date = "2020-04-31";
                        break;
                    case "May" :
                        start_date = "2020-05-01";
                        end_date = "2020-05-31";
                        break;
                    case "Jun" :
                        start_date = "2020-06-01";
                        end_date = "2020-06-31";
                        break;
                    case "Jul" :
                        start_date = "2020-07-01";
                        end_date = "2020-07-31";
                        break;
                    case "Aug" :
                        start_date = "2020-08-01";
                        end_date = "2020-08-31";
                        break;
                    case "Sep" :
                        start_date = "2020-09-01";
                        end_date = "2020-09-31";
                        break;
                    case "Oct" :
                        start_date = "2020-10-01";
                        end_date = "2020-10-31";
                        break;
                    case "Nov" :
                        start_date = "2020-11-01";
                        end_date = "2020-11-31";
                        break;
                    case "Dec" :
                        start_date = "2020-12-01";
                        end_date = "2020-12-31";
                        break;
                }
                List<Integer> totalDays = new ArrayList<>();
                List<String> subjects = new ArrayList<>();
                setAttendanceTotal(attendances, start_date,end_date, c, totalDays, subjects);

            }
        });
    }

    public void setAttendancePresent(final List<Attendance> attendances, final String start_date, final String end_date, final Context c, final List<Integer> totalDays, final List<String> subjects){
        try {
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("start_date", start_date);
            jsonBody.put("end_date", end_date);
            jsonBody.put("user_id", 1);

            final String requestBody = jsonBody.toString();
            StringRequest request= new StringRequest(Request.Method.POST, "https://kamal002.000webhostapp.com/sfzone/api/getAttendancePresent.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                            JSONObject obj = new JSONObject(response);

                            List<Integer> presentDays = new ArrayList<>();
                            JSONArray jsonArray = (JSONArray) obj.get("list");
                            for(int i=0; i<jsonArray.length();i++)
                            {
                                JSONObject jobj=(JSONObject) jsonArray.get(i);
                                AttendancePresentDetails note= new AttendancePresentDetails(
                                        jobj.getInt("total_present_days"),
                                        jobj.getString("sub_name")
                                );
//                                presentattendancearray.add(note);

                                for (Attendance at : attendances) {
                                    if (at.getSubName().equals(note.getSub_Name())) {
                                        at.setPresentDays(note.getTotal_Present_Days());
                                    }
                                }

                                for (Attendance at : attendances) {
                                    totalDays.add(at.totalDays);
                                    presentDays.add(at.presentDays);
                                    subjects.add(at.subName);
                                }



                            }
                                MyAdapter adapter = new MyAdapter(c,subjects,totalDays, presentDays);
                                AttendanceReportListView.setAdapter(adapter);
                            } catch(Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
            };

            Volley.newRequestQueue(this).add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setAttendanceTotal(final List<Attendance> attendances, final String start_date, final String end_date, final Context c, final List<Integer> totalDays, final List<String> subjects){
        try {
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("start_date", start_date);
            jsonBody.put("end_date", end_date);

            final String requestBody = jsonBody.toString();
            StringRequest request= new StringRequest(Request.Method.POST, "https://kamal002.000webhostapp.com/sfzone/api/getAttendanceTotal.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject obj = new JSONObject(response);

                                JSONArray jsonArray = (JSONArray) obj.get("list");
                                for(int i=0; i<jsonArray.length();i++)
                                {
                                    JSONObject jobj=(JSONObject) jsonArray.get(i);
                                    AttendanceTotalDetails note= new AttendanceTotalDetails(
                                            jobj.getString("sub_name"),
                                            jobj.getInt("total_days")
                                                                        );
                                    Attendance attendance = new Attendance();
                                    attendance.setSubName(note.getSub_Name());
                                    attendance.setTotalDays(note.getTotal_Days());
                                    attendance.setPresentDays(0);
                                    attendances.add(attendance);


                                }

                                setAttendancePresent(attendances, start_date,end_date, c, totalDays, subjects);
                            } catch(Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
            };

            Volley.newRequestQueue(this).add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        List<String> rsub;
        List<Integer> rtotal;
        List<Integer> rpresent;

        MyAdapter(Context c,List<String> subjects,List<Integer> totalDays,List<Integer> presentDays){
            super(c,R.layout.attendance_report_row,R.id.attendance_report_row_subject,subjects);
            this.context= (Context) c;
            this.rsub=subjects;
            this.rtotal=totalDays;
            this.rpresent=presentDays;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.attendance_report_row, parent, false);

            TextView myTitle=row.findViewById(R.id.attendance_report_row_subject);
           TextView myTitle1=row.findViewById(R.id.attendance_report_row_total);
          TextView myTitle2=row.findViewById(R.id.attendance_report_row_present);

            myTitle.setText(rsub.get(position));
            myTitle1.setText(""+rtotal.get(position));
          myTitle2.setText(""+rpresent.get(position));

            return row;
        }
    }

}
