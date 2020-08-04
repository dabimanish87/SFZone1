package com.example.sfzone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class GruhUdhyog extends AppCompatActivity {

    String start_date, end_date;
    Spinner guspinner;
    Button gubtnSubmit;
    ListView ProductReportListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gruh_udhyog);
        guspinner = (Spinner) findViewById(R.id.guspinner);
        gubtnSubmit = (Button) findViewById(R.id.gubtnSubmit);
        ProductReportListView = (ListView) findViewById(R.id.ProductReportListView);

        final Context c = this;

        gubtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String month = guspinner.getSelectedItem().toString();

                switch (month) {
                    case "Jan":
                        start_date = "2020-01-01 00:00:00";
                        end_date = "2020-01-31 00:00:00";
                        break;
                    case "Feb":
                        start_date = "2020-02-01 00:00:00";
                        end_date = "2020-02-31 00:00:00";
                        break;
                    case "Mar":
                        start_date = "2020-03-01 00:00:00";
                        end_date = "2020-03-31 00:00:00";
                        break;
                    case "Apr":
                        start_date = "2020-04-01 00:00:00";
                        end_date = "2020-04-31 00:00:00";
                        break;
                    case "May":
                        start_date = "2020-05-01 00:00:00";
                        end_date = "2020-05-31 00:00:00";
                        break;
                    case "Jun":
                        start_date = "2020-06-01 00:00:00";
                        end_date = "2020-06-31 00:00:00";
                        break;
                    case "Jul":
                        start_date = "2020-07-01 00:00:00";
                        end_date = "2020-07-31 00:00:00";
                        break;
                    case "Aug":
                        start_date = "2020-08-01 00:00:00";
                        end_date = "2020-08-31 00:00:00";
                        break;
                    case "Sep":
                        start_date = "2020-09-01 00:00:00";
                        end_date = "2020-09-31 00:00:00";
                        break;
                    case "Oct":
                        start_date = "2020-10-01 00:00:00";
                        end_date = "2020-10-31 00:00:00";
                        break;
                    case "Nov":
                        start_date = "2020-11-01 00:00:00";
                        end_date = "2020-11-31 00:00:00";
                        break;
                    case "Dec":
                        start_date = "2020-12-01 00:00:00";
                        end_date = "2020-12-31 00:00:00";
                        break;
                }
                getAllProducts(c, start_date,end_date);
            }
    });
    }

    public void getAllProducts(final Context c, final  String start_date,final String end_date){
        try {
            final List<String> productList = new ArrayList<>();
            final List<String> priceList = new ArrayList<>();
            final List<String> qtyList = new ArrayList<>();
            final List<GruhUdhyogDetails> grarray= new ArrayList<>();
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("start_date", start_date);
            jsonBody.put("end_date", end_date);

            final String requestBody = jsonBody.toString();
            StringRequest request= new StringRequest(Request.Method.POST, "https://lower-decrements.000webhostapp.com/gruhudhyog/product_report.php",
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
                                    GruhUdhyogDetails note= new GruhUdhyogDetails(
                                            jobj.get("name").toString(),
                                            jobj.get("id").toString(),
                                            jobj.get("price").toString(),
                                            jobj.get("qty").toString()
                                    );
                                    grarray.add(note);
                                    productList.add(note.getName());
                                    priceList.add(note.getPrice());
                                    qtyList.add(note.getQty());
                                }

                                MyAdapter adapter = new MyAdapter(c, productList, priceList,qtyList);
                                ProductReportListView.setAdapter(adapter);

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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        List<String> rname;
        List<String> rprice;
        List<String> rqty;

        MyAdapter(Context c, List<String> product, List<String> price, List<String> qty){
            super(c,R.layout.activity_gruh_udhyog_row,R.id.product_report_row_name, product);
            this.context= (Context) c;
            this.rname=product;
            this.rprice=price;
            this.rqty=qty;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.activity_gruh_udhyog_row, parent, false);

            TextView product=row.findViewById(R.id.product_report_row_name);
            TextView price=row.findViewById(R.id.product_report_row_price);
            TextView quantity=row.findViewById(R.id.product_report_row_qty);

            product.setText(rname.get(position));
            price.setText(rprice.get(position));
            quantity.setText(rqty.get(position));

            return row;
        }
    }
}

