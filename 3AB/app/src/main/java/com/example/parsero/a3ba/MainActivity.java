package com.example.parsero.a3ba;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText campoNombre, campoNombreUsuario;
    RadioButton rbHombre,rbMujer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoNombre = (EditText) findViewById(R.id.campoNombre);
        campoNombreUsuario= (EditText) findViewById(R.id.campoNombreUsuario);
        rbHombre=(RadioButton) findViewById(R.id.rbHombre);
        rbMujer=(RadioButton) findViewById(R.id.rbMujer);
    }

    public void btnGuardarPuls (View view){
        guardarPreferencias();
    }

    public void btnCargarPuls (View view){
        Intent intent = new Intent (MainActivity.this,consultaPreferencias.class);
        startActivity(intent);

    }

    public void guardarPreferencias(){


        boolean guardado = false;


        SharedPreferences preferences=getSharedPreferences("credenciales",MODE_PRIVATE);

        String nombre=campoNombre.getText().toString();
        String nombreUsuario=campoNombreUsuario.getText().toString();




        SharedPreferences.Editor editor =preferences.edit();

        editor.putString("nombre",nombre);
        editor.putString("nombreUsuario",nombreUsuario);


        if((nombre.length() > 0)&& (nombreUsuario.length() > 0)){
            guardado = true;
        }
        else {
            guardado = false;
        }

        if(guardado)
        {
            Toast.makeText(MainActivity.this,"Se ha guardado con exito", Toast.LENGTH_SHORT).show();
            editor.commit();
        }
        else{
            Toast.makeText(MainActivity.this,"No se ha guardado con exito , revisa los campos", Toast.LENGTH_SHORT).show();
        }





    }
}
