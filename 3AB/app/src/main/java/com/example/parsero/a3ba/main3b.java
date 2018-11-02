package com.example.parsero.a3ba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class main3b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3b);
    }
    public void btnParaProfesor(View view){
        Intent intent = new Intent(main3b.this,crearProfesor.class);
        startActivity(intent);
    }
    public void btnParaAlumno(View view){
        Intent intent = new Intent(main3b.this,crearAlumno.class);
        startActivity(intent);
    }
    public void btnParaUsuario(View view){
        Intent intent = new Intent(main3b.this,MainActivity.class);
        startActivity(intent);
    }
  public void btnParaConsultas(View view){
        Intent intent = new Intent (main3b.this,consultasSql.class);
        startActivity(intent);
  }
}
