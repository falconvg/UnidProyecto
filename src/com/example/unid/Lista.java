package com.example.unid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("unused")


public class Lista extends Activity {
	
	private ListView lv_contacts_list;
	@SuppressWarnings("rawtypes")
	private ArrayAdapter adapter;
	private String getAllContactsURL = "http://unid20183.herokuapp.com/api_alumnos?user_hash=12345&action=get";
		
    @SuppressWarnings("rawtypes")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	  	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);               
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());    	
    	lv_contacts_list = (ListView)findViewById(R.id.lv_contacts_list);
    	adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
    	lv_contacts_list.setAdapter(adapter);
    	webServiceRest(getAllContactsURL);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    private void webServiceRest(String requestURL){
        try{
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String webServiceResult="";
            while ((line = bufferedReader.readLine()) != null){
                webServiceResult += line;
            }
            bufferedReader.close();
            parseInformation(webServiceResult);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


	@SuppressWarnings("unchecked")
	private void parseInformation(String jsonResult) {
		// TODO Auto-generated method stub
		 JSONArray jsonArray = null;
		    String id_alumno;
		    String nombre;
		    String domicilio;
		    String telefono;
		    String maestria;
		    try{
		        jsonArray = new JSONArray(jsonResult);
		    }catch (JSONException e){
		        e.printStackTrace();
		    }
		    for(int i=0;i<jsonArray.length();i++){
		        try{
		            JSONObject jsonObject = jsonArray.getJSONObject(i);
		            id_alumno = jsonObject.getString("id_alumno");
		            nombre = jsonObject.getString("nombre");
		            domicilio = jsonObject.getString("domicilio");
		            telefono = jsonObject.getString("telefono");
		            maestria = jsonObject.getString("maestria");
		            adapter.add(id_alumno + " .- " + "Nombre: " + nombre);		            
		        }catch (JSONException e){
		            e.printStackTrace();
		        }
		    }
	}
    
}
