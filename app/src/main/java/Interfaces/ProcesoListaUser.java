package Interfaces;

import android.widget.TextView;

import com.example.practica2.WebService.Asynchtask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ProcesoListaUser implements Asynchtask {
    TextView txtRespServer;

    public ProcesoListaUser(TextView txtRespServer) {
        this.txtRespServer = txtRespServer;
    }
    @Override
    public void processFinish(String result) throws JSONException {
        String listaBanco="";
        JSONArray JSONlista =  new JSONArray(result);

        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco=  JSONlista.getJSONObject(i);
            listaBanco += banco.getString("username")
                    + " , " + banco.getString("id")+ " , " + banco.getString("email")+"\n";

        }
        txtRespServer.setText("Lista Usuarios: \n" + listaBanco);
    }
}
