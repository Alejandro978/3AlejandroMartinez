package com.example.parsero.a3ba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class consultasSql extends AppCompatActivity {
    TextView texto;
    EditText et_filter;
    Button consultaAlumnos, consultaProfesores, consultaTodo, btnBorrar,consultaAlumnosCurso;
    private MyDBAdapter dbAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_sql);
        consultaAlumnos = (Button) findViewById(R.id.consultaAlumnos);
        consultaProfesores = (Button) findViewById(R.id.consultaProfesores);
        consultaTodo = (Button) findViewById(R.id.consultaTodo);
        btnBorrar = (Button) findViewById(R.id.btnBorrar);
        consultaAlumnosCurso = (Button) findViewById(R.id.consultaAlumnosCurso);
        et_filter = (EditText) findViewById(R.id.et_filter);
        texto = (TextView) findViewById(R.id.texto);

    }


    public void mostrarAlumnos(View v) {

        dbAdapter = new MyDBAdapter(getApplicationContext());
        dbAdapter.open();
        ArrayList<String> alumnos = dbAdapter.recuperarAlumnos();

        for (int i = 0; i < alumnos.size(); i++) {
            texto.setText(texto.getText() + " \n" + alumnos.get(i));
        }

    }

    public void mostrarProfesores(View v) {
        dbAdapter = new MyDBAdapter(getApplicationContext());
        dbAdapter.open();
        ArrayList<String> profesores = dbAdapter.recuperarProfesores();

        for (int i = 0; i < profesores.size(); i++) {
            texto.setText(texto.getText() + " \n" + profesores.get(i));
        }
    }

    public void mostrarTodo(View v) {
        dbAdapter = new MyDBAdapter(getApplicationContext());
        dbAdapter.open();
        ArrayList<String> todo = dbAdapter.recuperarTodo();

        for (int i = 0; i < todo.size(); i++) {
            texto.setText(texto.getText() + " \n " + todo.get(i));
        }

    }

    public void mostrarAlumnosCiclo(View v) {
        dbAdapter = new MyDBAdapter(getApplicationContext());
        dbAdapter.open();
        String query = et_filter.getText().toString();
        ArrayList<String> alumnoPorCiclo = dbAdapter.recuperarAlumnoCiclo(query);

        for (int i = 0; i < alumnoPorCiclo.size(); i++) {
            texto.setText(texto.getText() + " \n " + alumnoPorCiclo.get(i));
        }
    }

    public void mostrarAlumnosCurso(View v) {
        dbAdapter = new MyDBAdapter(getApplicationContext());
        dbAdapter.open();
        String query = et_filter.getText().toString();
        ArrayList<String> alumnoPorCurso = dbAdapter.recuperarAlumnoCurso(query);

        for (int i = 0; i < alumnoPorCurso.size(); i++) {
            texto.setText(texto.getText() + " \n " + alumnoPorCurso.get(i));
        }
    }



    public void borrarTextView(View v) {
        texto.setText("");
    }
}
