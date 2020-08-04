package com.example.sfzone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }

    @Override
    public void handleResult(Result qrDetails) {
        scan(qrDetails.getText());
//        Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_LONG).show();
        onBackPressed();

//
//
//
//        try {
//            JSONObject jsonBody = new JSONObject();
//            jsonBody.put("qr_name", qr_name);
//            jsonBody.put("user_id",1);
//
//            final String requestBody = jsonBody.toString();
//            StringRequest request= new StringRequest(Request.Method.POST, "https://kamal002.000webhostapp.com/sfzone/api/StoreAttendance.php",
//                new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
//                }
//            }){
//                @Override
//                public String getBodyContentType() {
//                    return "application/json; charset=utf-8";
//                }
//
//                @Override
//                public byte[] getBody() throws AuthFailureError {
//                    try {
//                        return requestBody == null ? null : requestBody.getBytes("utf-8");
//                    } catch (UnsupportedEncodingException uee) {
//                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
//                        return null;
//                    }
//                }
//            };
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//


    }

    public void scan(final String qrDetails){
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("qr_name", qrDetails);
            jsonBody.put("user_id", 1);

            final String requestBody = jsonBody.toString();
            StringRequest request= new StringRequest(Request.Method.POST, "https://kamal002.000webhostapp.com/sfzone/api/StoreAttendance.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onPause() {
        super.onPause();

        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}
