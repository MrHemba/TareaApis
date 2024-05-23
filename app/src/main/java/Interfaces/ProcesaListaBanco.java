package Interfaces;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.practica2.WebService.Asynchtask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


public class ProcesaListaBanco implements Asynchtask {
    TextView txtRespServer;

    public ProcesaListaBanco(TextView txtRespServer) {
        this.txtRespServer = txtRespServer;
    }
    @Override
    public void processFinish(String result) throws JSONException {
        String listaBanco="";
        JSONArray JSONlista =  new JSONArray(result);

        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco=  JSONlista.getJSONObject(i);
            listaBanco += banco.getString("code")
                    + " - " + banco.getString("name")+ "\n";

        }
        txtRespServer.setText("Lista Bancos: \n" + listaBanco);
    }
}
