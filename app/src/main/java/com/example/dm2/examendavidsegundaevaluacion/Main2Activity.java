package com.example.dm2.examendavidsegundaevaluacion;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;


public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public Spinner spinner;
    ArrayList<String> archivos =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        spinner = (Spinner)findViewById(R.id.spinner);

        try
        {
            InputStream fraw = getResources().openRawResource(R.raw.fichero);

            BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
            String texto = brin.readLine();
            int i =0;
            while (texto != null) {
                archivos.add(texto);
                texto = brin.readLine();
            }
            fraw.close();
        }
        catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");
        }
        ////////////

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, archivos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(this);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Toast.makeText(this, "Has seleccionado "+ position , Toast.LENGTH_SHORT).show();
        String sonidoactual = archivos.get(position);

        MediaPlayer mp = MediaPlayer.create(this, getResources().getIdentifier(sonidoactual, "raw", getPackageName()));
        mp.start();



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
