package com.example.parsero.a3ba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class crearProfesor extends AppCompatActivity {

    EditText campoNombreProfesor, campoEdadProfesor, campoCicloProfesor, campoCurso, campoTutor, campoDespacho;
    Button btnCrear;
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_profesor);

        btnCrear = (Button) findViewById(R.id.btnCrear);
        campoNombreProfesor = (EditText) findViewById(R.id.campoNombreProfesor);
        campoEdadProfesor = (EditText) findViewById(R.id.campoEdadProfesor);
        campoCicloProfesor = (EditText) findViewById(R.id.campoCicloProfesor);
        campoCurso = (EditText) findViewById(R.id.campoCurso);
        campoTutor = (EditText) findViewById(R.id.campoTutor);
        campoDespacho = (EditText) findViewById(R.id.campoDespacho);

    }

    public void crearProfesor(View v) {
        String nombreProfesor = campoNombreProfesor.getText().toString();
        String edadProfesor = campoEdadProfesor.getText().toString();
        String cicloProfesor = campoCicloProfesor.getText().toString();
        String cursoProfesor = campoCicloProfesor.getText().toString();
        String tutorProfesor = campoTutor.getText().toString();
        String despachoProfesor = campoDespacho.getText().toString();

        if (
                (nombreProfesor.compareTo("") != 0) &&
                        (edadProfesor.compareTo("") != 0) &&
                        (cicloProfesor.compareTo("") != 0) &&
                        (cursoProfesor.compareTo("") != 0) &&
                        (tutorProfesor.compareTo("") != 0) &&
                        (despachoProfesor.compareTo("") != 0)) {


            dbAdapter = new MyDBAdapter(getApplicationContext());
            dbAdapter.open();
            dbAdapter.insertarProfesor(
                    nombreProfesor,
                    edadProfesor,
                    cicloProfesor,
                    cursoProfesor,
                    tutorProfesor,
                    despachoProfesor);

            Toast creado = Toast.makeText(getApplicationContext(), "Profesor creado con exito", Toast.LENGTH_SHORT);
            creado.show();

        } else {

            Toast noCreado = Toast.makeText(getApplicationContext(), "Profesor no creado , revisa los campos", Toast.LENGTH_SHORT);
            noCreado.show();

        }
    }

}

