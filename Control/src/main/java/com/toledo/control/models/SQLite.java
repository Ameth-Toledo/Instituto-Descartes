package com.toledo.control.models;

import java.util.ArrayList;

public class SQLite implements DataBase{
    private  ArrayList<Student> estudiantes;
    public SQLite(){
        estudiantes=new ArrayList<>();
    }

    @Override
    public boolean updateStude(Student student) {
        for (int i=0;i<estudiantes.size();i++){
            Student students = estudiantes.get(i);
            if (students.getName()==student.getName()){
                estudiantes.set(i,student);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean saveStudent(Student student) {
        return estudiantes.add(student);
    }

    public ArrayList<Student> getEstudiantes() {
        return estudiantes;
    }
}
