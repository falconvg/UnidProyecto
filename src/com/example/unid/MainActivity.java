package com.example.unid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);         
        controlador();
        controlador2();
    }
                         
       @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
         
     //hago clic y se abre el de lista
    public void controlador() {    	     
    	Button btnlista = (Button) findViewById(R.id.btnlista);
    	
        btnlista.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) { 
               Intent lista = new Intent(getApplicationContext(), Lista.class);               
			startActivity(lista);
            }
        });
      }
    
    
    public void controlador2() {    	     
    	Button btnbuscar = (Button) findViewById(R.id.btnbuscar);
    	
        btnbuscar.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) { 
               Intent buscar = new Intent(getApplicationContext(), Buscar.class);               
			startActivity(buscar);
            }
        });
      }
}
