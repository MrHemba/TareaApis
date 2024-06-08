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

import Interfaces.ProcesoListaUser;

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

        txtRespServer = (TextView) findViewById(R.id.txtRespServer);
    }

    public void ListaBanco (View view) {

        Map<String, String> datos = new HashMap<String, String>();

        WebService ws= new
                WebService("https://jsonplaceholder.typicode.com/users",
                datos, MainActivity.this, new ProcesoListaUser(txtRespServer));

        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtResult = (TextView) findViewById(R.id.txtRespServer);
        txtResult.setText("Resp: " + result);

    }
}