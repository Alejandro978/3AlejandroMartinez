package com.example.parsero.a3ba;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class crearAlumno extends AppCompatActivity {

    Button btnCrear;
    EditText campoNombreAlumno, campoEdadAlumno, campoCicloAlumno, campoCurso;

    //Creamos isntancia de la base de datos:
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alumno);


        btnCrear = (Button) findViewById(R.id.btnCrear);
        campoNombreAlumno = (EditText) findViewById(R.id.campoNombreAlumno);
        campoEdadAlumno = (EditText) findViewById(R.id.campoEdadAlumno);
        campoCicloAlumno = (EditText) findViewById(R.id.campoCicloAlumno);
        campoCurso = (EditText) findViewById(R.id.campoCurso);


    }

    public void crearAlumno(View v) {

        String nombreAlumno = campoNombreAlumno.getText().toString();
        String edadAlumno = campoEdadAlumno.getText().toString();
        String cicloAlumno = campoCicloAlumno.getText().toString();
        String cursoAlumno = campoCurso.getText().toString();

        if (
                (nombreAlumno.compareTo("") != 0) &&
                        (edadAlumno.compareTo("") != 0) &&
                        (cicloAlumno.compareTo("") != 0) &&
                        (cursoAlumno.compareTo("") != 0)) {

            dbAdapter = new MyDBAdapter(getApplicationContext());
            dbAdapter.open();
            dbAdapter.insertarAlumno(
                    nombreAlumno,
                    edadAlumno,
                    cicloAlumno,
                    cursoAlumno);

            Toast creado = Toast.makeText(getApplicationContext(), "Alumno creado con exito", Toast.LENGTH_SHORT);
            creado.show();

        } else {
            Toast noCreado = Toast.makeText(getApplicationContext(), "Alumno no creado revisa los campos ", Toast.LENGTH_SHORT);
            noCreado.show();
        }

    }
}