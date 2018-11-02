package com.example.parsero.a3ba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDBAdapter {
    // Definiciones y constantes
    private static final String DATABASE_NAME = "clase.db";
    private static final String DATABASE_TABLE_ALUMNOS = "alumnos";
    private static final String DATABASE_TABLE_PROFESORES = "profesores";
    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_CREATE_PROFESORES = "CREATE TABLE " + DATABASE_TABLE_PROFESORES + " (_id integer primary key autoincrement,nombre text,edad text,ciclo text,curso text,tutor text,despacho text);";
    private static final String DATABASE_CREATE_ALUMNOS = "CREATE TABLE " + DATABASE_TABLE_ALUMNOS + " (_id integer primary key autoincrement,nombre text,edad text,ciclo text,curso text);";

    private static final String DATABASE_DROP_ALUMNOS = "DROP TABLE IF EXISTS " + DATABASE_TABLE_ALUMNOS + ";";
    private static final String DATABASE_DROP_PROFESORES = "DROP TABLE IF EXISTS " + DATABASE_TABLE_PROFESORES + ";";


    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;


    public MyDBAdapter(Context c) {
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        //OJO open();
    }

    public void open() {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = dbHelper.getReadableDatabase();
        }
    }

    public void insertarAlumno(String nombre, String edad, String ciclo, String curso) {
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put("nombre", nombre);
        newValues.put("edad", edad);
        newValues.put("ciclo", ciclo);
        newValues.put("curso", curso);
        db.insert(DATABASE_TABLE_ALUMNOS, null, newValues);
    }

    public void insertarProfesor(String nombre, String edad, String ciclo, String curso, String tutor, String despacho) {
        ContentValues newValues = new ContentValues();
        newValues.put("nombre", nombre);
        newValues.put("edad", edad);
        newValues.put("ciclo", ciclo);
        newValues.put("curso", curso);
        newValues.put("tutor", tutor);
        newValues.put("despacho", despacho);
        db.insert(DATABASE_TABLE_PROFESORES, null, newValues);
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_ALUMNOS);
            db.execSQL(DATABASE_CREATE_PROFESORES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_ALUMNOS);
            db.execSQL(DATABASE_DROP_PROFESORES);
            onCreate(db);
        }

    }

    //Método para hacer una consulta a al bbdd y traernos todos los Alumnos:
    public ArrayList<String> recuperarAlumnos() {
        ArrayList<String> alumnos = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada:
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNOS, null, null, null, null, null, null);
        //Recorremos el cursor y vamos a añadiendo al array list de alumnos cada uno de los alumnos
        //Nos desplazamos a la 1a posición del cursor y vamos iterando sobre el mismo:
        if (cursor != null && cursor.moveToFirst()) {
            do {
                alumnos.add(cursor.getString(1) + " \n " + cursor.getString(2) + " \n " + cursor.getString(3) + " \n " + cursor.getString(4));
            } while (cursor.moveToNext());
        }
        return alumnos;
    }

    public ArrayList<String> recuperarProfesores() {
        ArrayList<String> profesores = new ArrayList<String>();
        Cursor cursor = db.query(DATABASE_TABLE_PROFESORES, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                profesores.add(cursor.getString(1) + " \n " + cursor.getString(2) + " \n " + cursor.getString(3) + " \n" + cursor.getString(4) + "\n" + cursor.getString(5) + " \n " + cursor.getString(6));
            } while (cursor.moveToNext());
        }
        return profesores;
    }

    //Método que devuelve todos los usuarios y profesores
    public ArrayList<String> recuperarTodo() {
        ArrayList<String> todo = new ArrayList<String>();

        Cursor cursorProfesores = db.query(DATABASE_TABLE_PROFESORES, null, null, null, null, null, null);
        if (cursorProfesores != null && cursorProfesores.moveToFirst()) {
            do {
                todo.add("PROFESOR :" + " \n " + cursorProfesores.getString(1) + " \n " + cursorProfesores.getString(2) + " \n " + cursorProfesores.getString(3) + " \n" + cursorProfesores.getString(4) + "\n" + cursorProfesores.getString(5) + " \n " + cursorProfesores.getString(6) + " \n ");

            } while (cursorProfesores.moveToNext());
        }
        Cursor cursorAlumnos = db.query(DATABASE_TABLE_ALUMNOS, null, null, null, null, null, null);
        if (cursorAlumnos != null && cursorAlumnos.moveToFirst()) {
            do {
                todo.add("ALUMNOS :" + "\n " + cursorAlumnos.getString(1) + " \n " + cursorAlumnos.getString(2) + " \n " + cursorAlumnos.getString(3) + " \n " + cursorAlumnos.getString(4));
            } while (cursorAlumnos.moveToNext());
        }
        return todo;
    }

    //Método que devuelve con la sequencia WHERE ciclo igual a x
    public ArrayList<String> recuperarAlumnoCiclo(String query) {
        ArrayList<String> alumnoPorCiclo = new ArrayList<String>();
        String ciclo = query;
        Cursor cursorAlumnosPorCiclo = db.query(DATABASE_TABLE_ALUMNOS, null, "ciclo=?", new String[]{ciclo}, null, null, null);

        if (cursorAlumnosPorCiclo != null && cursorAlumnosPorCiclo.moveToFirst()) {
            do {
                alumnoPorCiclo.add(cursorAlumnosPorCiclo.getString(1) + " \n " + cursorAlumnosPorCiclo.getString(2) + " \n " + cursorAlumnosPorCiclo.getString(3) + " \n " + cursorAlumnosPorCiclo.getString(4));
            } while (cursorAlumnosPorCiclo.moveToNext());
        }
        return alumnoPorCiclo;
    }

    //Método que devuelve con la sequencia WHERE curso igual igual a x
    public ArrayList<String> recuperarAlumnoCurso (String query) {
        ArrayList<String> alumnoPorCurso = new ArrayList<String>();
        String curso = query;
        Cursor cursorAlumnosPorCurso = db.query(DATABASE_TABLE_ALUMNOS, null, "curso=?", new String[]{curso}, null, null, null);

        if (cursorAlumnosPorCurso != null && cursorAlumnosPorCurso.moveToFirst()) {
            do {
                alumnoPorCurso.add("ALUMNOS :" + "\n " + cursorAlumnosPorCurso.getString(1) + " \n " + cursorAlumnosPorCurso.getString(2) + " \n " + cursorAlumnosPorCurso.getString(3) + " \n " + cursorAlumnosPorCurso.getString(4));
            } while (cursorAlumnosPorCurso.moveToNext());
        }
        return alumnoPorCurso;
    }
}


