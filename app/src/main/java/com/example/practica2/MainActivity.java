package com.example.practica2;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica2.WebService.Asynchtask;
import com.example.practica2.WebService.WebService;

import Interfaces.ProcesaListaBanco;

public class MainActivity extends AppCompatActivity implements Asynchtask {
     TextView txtNombre, txtClave, txtRespServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtClave = (TextView) findViewById(R.id.txtClave);
        txtRespServer = (TextView) findViewById(R.id.txtRespServer);
    }


    public void enviarWS (View view) {

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                getUrlLogin() ,
        datos, MainActivity.this, MainActivity.this);

           ws.execute("GET");

    }

    public String getUrlLogin() {
        return "https://revistas.uteq.edu.ec/ws/login.php?"+
                "usr="+ txtNombre.getText().toString() +
                "&pass="+txtClave.getText().toString();
    }

    public void enviarWSvolley (View view) {

        RequestQueue queue = Volley.newRequestQueue(this);
        getUrlLogin();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrlLogin(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtRespServer.setText("Respuesta: "+ response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtRespServer.setText("Error de Conexión");
                    }
                });

        queue.add(stringRequest);
    }

    public void ListaBanco (View view) {

        Map<String, String> datos = new HashMap<String, String>();

        WebService ws= new
                WebService("https://api-uat.kushkipagos.com/transfer/v1/bankList",
                datos, MainActivity.this, new ProcesaListaBanco(txtRespServer));

        ws.execute("GET","Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtResult = (TextView) findViewById(R.id.txtRespServer);
        txtResult.setText("Resp: " + result);

    }
}