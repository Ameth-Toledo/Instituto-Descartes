package com.toledo.control.models;

import java.util.ArrayList;

public class Control {
    private DataBase baseDeDatos;
    private ArrayList<Student> estudiantes=new ArrayList<>();

    public Boolean addAlumno(Student student){
        return estudiantes.add(student);
    }

    public void addBaseDatos(){
        baseDeDatos.addStudent(estudiantes);
    }

    public ArrayList<Student> getEstudiantes() {
        return estudiantes;
    }

}
