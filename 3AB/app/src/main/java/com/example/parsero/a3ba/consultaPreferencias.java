package com.example.parsero.a3ba;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class consultaPreferencias extends Activity {

    private TextView textNombre,textNombreUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_preferencias);

        textNombre = (TextView) findViewById(R.id.textNombre);
        textNombreUsuario=(TextView) findViewById(R.id.textNombreUsuario);
        cargarPreferencias();
    }
    public void cargarPreferencias(){

        SharedPreferences preferences=getSharedPreferences("credenciales",Context.MODE_PRIVATE);

        String nombre= preferences.getString("nombre" , "0");
        String nombreUsuario = preferences.getString("nombreUsuario","0");

        textNombre.setText(nombre);
        textNombreUsuario.setText(nombreUsuario);

    }
}
