package com.toledo.control.models;

import java.util.ArrayList;

public class Control {
    private ArrayList<DataBase> basesDeDatos = new ArrayList<>();
    private ArrayList<Student> estudiantes = new ArrayList<>();

    public Control(DataBase... bases) {
        for (DataBase base : bases) {
            basesDeDatos.add(base);
        }
    }

    public Boolean addAlumno(Student student){
        estudiantes.add(student);
        return true;
    }

    public void addBaseDatos(){
        for (DataBase base : basesDeDatos) {
            base.addStudent(estudiantes);
        }
    }

    public ArrayList<Student> getEstudiantes() {
        return estudiantes;
    }

    public void updateAlumno(Student student){
        for (DataBase base : basesDeDatos) {
            base.updateStudent(student);
            System.out.println("Estudiante actualizado en la base de datos: " + base.getClass().getSimpleName());
        }
    }

    public void saveAlumno(Student student) {
        for (DataBase base : basesDeDatos){
            base.saveStudent(student);
            System.out.println("Estudiante en la base de datos: " + base.getClass().getSimpleName());
        }
    }
}
